package com.medical.medicalappointments.security;

import com.medical.medicalappointments.annotations.RoleRequired;
import com.medical.medicalappointments.model.enums.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RoleRequired roleRequired = handlerMethod.getMethod().getAnnotation(RoleRequired.class);

            if (roleRequired != null) {
                Role[] requiredRoles = roleRequired.value();

                // Get the JWT token from the request header
                String token = request.getHeader("Authorization");
                if (token == null || !token.startsWith("Bearer ")) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid authorization header.");
                    return false;
                }
                token = token.substring(7);

                // Decode the JWT token and get the user role
                DecodedJWT decodedJWT = JWT.decode(token);
                String roleName = decodedJWT.getClaim("role").asString();
                Role currentUserRole = Role.valueOf(roleName);

                // Check if the user has any of the required roles
                for (Role role : requiredRoles) {
                    if (currentUserRole == role) {
                        return true;
                    }
                }
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have the required role to access this resource.");
                return false;
            }
        }

        return true;
    }
}
