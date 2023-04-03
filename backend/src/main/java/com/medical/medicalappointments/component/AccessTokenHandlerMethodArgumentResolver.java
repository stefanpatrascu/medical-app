package com.medical.medicalappointments.component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.medical.medicalappointments.annotations.AccessToken;
import com.medical.medicalappointments.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AccessTokenHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AccessToken.class);
    }


    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Cookie[] cookies = request.getCookies();
        String accessToken = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT-TOKEN".equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                    break;
                }
            }
        }

        if (accessToken == null) {
            return null;
        }

        // Parse the access token to get the Claims
        Claims claims = Jwts.parser()
            .setSigningKey(jwtConfig.getSecret())
            .parseClaimsJws(accessToken)
            .getBody();

        return claims;
    }

}
