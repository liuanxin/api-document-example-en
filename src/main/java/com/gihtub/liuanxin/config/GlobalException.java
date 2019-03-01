package com.gihtub.liuanxin.config;

import com.gihtub.liuanxin.exception.ServiceException;
import com.gihtub.liuanxin.util.JsonCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

/**
 * @see org.springframework.boot.web.servlet.error.ErrorController
 * @see org.springframework.boot.autoconfigure.web.ErrorProperties
 * @see org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
 */
@ControllerAdvice
public class GlobalException {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalException.class);

    @Value("${online:false}")
    private boolean online;

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> service(ServiceException e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("service exception", e);
        }
        return ResponseEntity.status(JsonCode.FAIL.getFlag()).body(e.getMessage());
    }

    // other custom exception


    // ... inner with spring's exception ...

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> noHandler(NoHandlerFoundException e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("404", e);
        }
        String msg = String.format("Not found(%s %s)", e.getHttpMethod(), e.getRequestURL());
        return ResponseEntity.status(JsonCode.NOT_FOUND.getFlag()).body(msg);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> missParam(MissingServletRequestParameterException e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("miss param", e);
        }
        String msg = String.format("Missing required param(%s), type(%s)", e.getParameterName(), e.getParameterType());
        return ResponseEntity.status(JsonCode.BAD_REQUEST.getFlag()).body(msg);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> notSupported(HttpRequestMethodNotSupportedException e) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("method not support", e);
        }
        String msg = "not support method.";
        if (!online) {
            msg += String.format(". current(%s), support(%s)", e.getMethod(), Arrays.toString(e.getSupportedMethods()));
        }
        return ResponseEntity.status(JsonCode.FAIL.getFlag()).body(msg);
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
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("unclear exception", e);
        }
        return ResponseEntity.status(JsonCode.FAIL.getFlag()).body(msg);
    }
}
