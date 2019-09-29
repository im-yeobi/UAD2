package com.uad2.application.exception;
/*
 * @USER JungHyun
 * @DATE 2019-09-22
 * @DESCRIPTION
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {
    //등록한 익셉션이 발생하면, 해당 ExceptionHandler에
    //매핑되어 알맞은 에러 처리 가능

    @ExceptionHandler(value = { RuntimeException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> runtimeException(RuntimeException e) {
        return getDefaultErrorMessage(e.toString(),e.getMessage());
    }

    //커스텀 익셉션
    @ExceptionHandler(value = { ClientException.class })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String,String> clientException(ClientException e) {
        return getDefaultErrorMessage(e.toString(),e.getMessage());
    }

    private Map<String,String> getDefaultErrorMessage(String error,String message){
        Map<String,String> returnMap = new HashMap<>();
        returnMap.put("error",error);
        returnMap.put("message",message);
        return returnMap;
    }
}