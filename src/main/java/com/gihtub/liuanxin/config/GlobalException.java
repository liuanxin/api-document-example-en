package com.gihtub.liuanxin.config;

import com.gihtub.liuanxin.util.JsonCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @see org.springframework.boot.web.servlet.error.ErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorProperties
 * @see org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
 */
@ControllerAdvice
public class GlobalException {

    @Value("${online:false}")
    private boolean online;

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> noHandler(NoHandlerFoundException e) {
        // debug log
        String msg = String.format("Not found(%s %s)", e.getHttpMethod(), e.getRequestURL());
        return ResponseEntity.status(JsonCode.NOT_FOUND.getFlag()).body(msg);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> missParam(MissingServletRequestParameterException e) {
        // debug log
        String msg = String.format("Missing required param(%s), type(%s)", e.getParameterName(), e.getParameterType());
        return ResponseEntity.status(JsonCode.BAD_REQUEST.getFlag()).body(msg);
    }


    // ... other exception


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> other(Throwable e) {
        String msg;
        if (online) {
            msg = "Request Error, We will handle it as soon as possible";
        } else if (e instanceof NullPointerException) {
            msg = "Null Point Exception, Contact the backend to view the log for processing";
        } else {
            msg = e.getMessage();
        }
        // error log
        return ResponseEntity.status(JsonCode.FAIL.getFlag()).body(msg);
    }
}
