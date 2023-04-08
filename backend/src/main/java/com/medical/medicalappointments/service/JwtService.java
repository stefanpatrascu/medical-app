package com.medical.medicalappointments.service;
import com.medical.medicalappointments.security.config.JwtConfig;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class JwtService {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(String email) {

        final User userDetails = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        final Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("id", userDetails.getId());
        claims.put("role", userDetails.getRole().toString());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtConfig.getExpirationTime());

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
            .compact();
    }

    public Claims validateClaims(String token) {
        return Jwts.parser()
            .setSigningKey(jwtConfig.getSecret())
            .parseClaimsJws(token)
            .getBody();
    }

    public void addJwtCookieToResponse(HttpServletResponse response, String jwtToken) {
        final Cookie jwtCookie = new Cookie("JWT-TOKEN", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/"); // Set the path for the cookie
        jwtCookie.setMaxAge((int) jwtConfig.getExpirationTime() / 1000); // Set the cookie's max age to the same as the token's
        response.addCookie(jwtCookie);
    }
}
