//package com.ganeshban.smsserver.config;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Objects;
//
//import static com.ganeshban.smsserver.utils.Constants.Keyword.AUTH_ERROR_KEY;
//
//
//@Component
//public class SMSAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//        Object error = request.getAttribute(AUTH_ERROR_KEY);
//        if (Objects.nonNull(error)) {
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.getWriter().write(error.toString());
//        }
//    }
//}
