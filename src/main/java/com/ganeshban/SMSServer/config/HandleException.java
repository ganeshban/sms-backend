package com.ganeshban.smsserver.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class HandleException extends Exception{
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> notFoundException(NotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UnAuthorized.class)
    public ResponseEntity<String> expireToken(UnAuthorized ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> notValidate(MethodArgumentNotValidException myException) {
        List<String> errorList = new ArrayList<>();
        myException.getBindingResult().getAllErrors().forEach(er -> errorList
                .add(er.getCodes()[1]
                        .split("\\.")[1] + " - " + er.getDefaultMessage()));
        return new ResponseEntity<>(errorList, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> userNameNoFound(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}