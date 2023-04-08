package com.medical.medicalappointments.component;

import com.medical.medicalappointments.security.RoleRequired;
import com.medical.medicalappointments.model.enums.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final RoleRequired roleRequired = handlerMethod.getMethod().getAnnotation(RoleRequired.class);

            if (roleRequired != null) {
                final Role[] requiredRoles = roleRequired.value();

                // Get the current user role from the SecurityContextHolder
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                final GrantedAuthority grantedAuthority = authentication.getAuthorities().iterator().next();
                final Role currentUserRole = Role.valueOf(grantedAuthority.getAuthority());

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
