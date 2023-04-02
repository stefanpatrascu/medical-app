package com.medical.medicalappointments.service;
import com.medical.medicalappointments.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtConfig jwtConfig;

    public String generateToken(String email) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        List<String> roles = userDetails.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .collect(Collectors.toList());

        Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("roles", roles);

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
