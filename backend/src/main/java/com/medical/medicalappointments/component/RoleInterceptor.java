package com.medical.medicalappointments.component;

import com.medical.medicalappointments.security.RoleRequired;
import com.medical.medicalappointments.security.config.JwtConfig;
import com.medical.medicalappointments.model.enums.Role;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RoleRequired roleRequired = handlerMethod.getMethod().getAnnotation(RoleRequired.class);

            if (roleRequired != null) {
                Role[] requiredRoles = roleRequired.value();

                // Get the JWT token from the cookies
                String token = null;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("JWT-TOKEN".equals(cookie.getName())) {
                            token = cookie.getValue();
                            break;
                        }
                    }
                }

                if (token == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid authorization cookie.");
                    return false;
                }

                // Verify the JWT signature with the secret key
                try {
                    Claims claims = Jwts.parser()
                        .setSigningKey(jwtConfig.getSecret())
                        .parseClaimsJws(token)
                        .getBody();

                    String roleName = claims.get("role", String.class);
                    Role currentUserRole = Role.valueOf(roleName);

                    // Check if the user has any of the required roles
                    for (Role role : requiredRoles) {
                        if (currentUserRole == role) {
                            return true;
                        }
                    }
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have the required role to access this resource.");
                    return false;

                } catch (JwtException e) {
                    // Log the error and return a 401 response
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token.");
                    return false;
                }
            }
        }
        return true;
    }

}
