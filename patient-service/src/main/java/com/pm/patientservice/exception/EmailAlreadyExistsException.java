package com.pm.patientservice.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    //手动抛出的业务异常 -- 状态码为400
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
