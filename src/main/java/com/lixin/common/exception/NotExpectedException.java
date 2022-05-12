package com.lixin.common.exception;

/**
 * @author lixin
 */
public class NotExpectedException extends RuntimeException {
    private static final long serialVersionUID = 1579862207974005524L;

    public NotExpectedException(String message) {
        super(message);
    }
}
