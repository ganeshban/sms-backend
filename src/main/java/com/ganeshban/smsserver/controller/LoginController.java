package com.ganeshban.smsserver.controller;

import com.ganeshban.smsserver.dto.LoginDTO;
import com.ganeshban.smsserver.model.UserModel;
import com.ganeshban.smsserver.service.impl.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Valid
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class LoginController {

    private final LoginService service;

    @PostMapping("/login")
    public UserModel login(@RequestBody LoginDTO request) {

        return service.doLogin(request);
    }

    @GetMapping("/rest/refresh-token")
    public Map<String, String> refreshToken(@RequestHeader("Authorization") String auth) {

        HashMap<String, String> map = new HashMap<>();
        map.put("token", service.refreshToken(auth));
        return map;
    }

    @GetMapping("/ping")
    public String ping(HttpServletRequest request) {
        log.info("ping success");

        log.info("getAuthType : " + request.getAuthType());
        log.info("getCharacterEncoding : " + request.getCharacterEncoding());
        log.info("getContentLength : " + request.getContentLength());
        log.info("getContextPath : " + request.getContextPath());
        log.info("getContentType : " + request.getContentType());
        log.info("getHeader<auth> : " + request.getHeader("authorization"));
        log.info("getLocalAddr : " + request.getLocalAddr());
        log.info("getLocalName : " + request.getLocalName());
        log.info("getLocalPort : " + request.getLocalPort());
        log.info("getMethod : " + request.getMethod());
        log.info("getLocalPort : " + request.getParameter("id"));
        log.info("getPathInfo : " + request.getPathInfo());
        log.info("getPathTranslated : " + request.getPathTranslated());
        log.info("getQueryString : " + request.getQueryString());
        log.info("getRemoteAddr : " + request.getRemoteAddr());
        log.info("getRemoteHost : " + request.getRemoteHost());
        log.info("getRemotePort : " + request.getRemotePort());
        log.info("getRemoteUser : " + request.getRemoteUser());
        log.info("getRequestId : " + request.getRequestId());
        log.info("getRequestURI : " + request.getRequestURI());
        log.info("getRequestURL : " + request.getRequestURL());
        log.info("getRequestedSessionId : " + request.getRequestedSessionId());
        log.info("getScheme : " + request.getScheme());
        log.info("getServerName : " + request.getServerName());
        log.info("getServerPort : " + request.getServerPort());
        log.info("getServletPath : " + request.getServletPath());
        log.info("---------------------------------------------------------------");
        for (Iterator<String> it = request.getHeaderNames().asIterator(); it.hasNext(); ) {
            String key = it.next();
            log.info("key : " + key + " value : " + request.getHeader(key));

        }


        return "Server is up";
    }


}
