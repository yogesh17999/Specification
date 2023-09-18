package com.specificationstructure.specificationstructure.exceptions.handler;

import com.specificationstructure.specificationstructure.dto.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.ws.rs.BadRequestException;

@ControllerAdvice
@Slf4j
public class BaseHandler {

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage getBadRequestException(BadRequestException ex)
    {
        return new ErrorMessage(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage getException(Exception ex)
    {
        log.error("error ",ex.getStackTrace());
        return new ErrorMessage(HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getMessage());
    }

}
