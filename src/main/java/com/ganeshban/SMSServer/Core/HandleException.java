package com.ganeshban.SMSServer.Core;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class HandleException {
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> notFoundException(NotFound ex) {
        return new ResponseEntity<>(ex.message, HttpStatus.NOT_FOUND);
    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> notValidate(MethodArgumentNotValidException myException) {
        List<String> errorList = new ArrayList<>();
        myException.getBindingResult().getAllErrors().forEach((er) -> {
            errorList.add(er.getDefaultMessage());
        });
        return new ResponseEntity<>(errorList, HttpStatus.NOT_FOUND);
    }


}