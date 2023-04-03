package com.medical.medicalappointments.service;
import com.medical.medicalappointments.config.JwtConfig;
import com.medical.medicalappointments.model.entity.User;
import com.medical.medicalappointments.model.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(String email) {

        User userDetails = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        Claims claims = Jwts.claims();
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

    public Claims getClaims(String token) {
        return Jwts.parser()
            .setSigningKey(jwtConfig.getSecret())
            .parseClaimsJws(token)
            .getBody();
    }
}
