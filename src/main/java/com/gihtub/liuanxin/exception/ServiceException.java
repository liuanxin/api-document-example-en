package com.gihtub.liuanxin.exception;

import java.io.Serializable;

public class ServiceException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public ServiceException(String msg) {super(msg);}

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
