package com.lixin.common.enums;

/**
 * 状态码
 *
 * @author lixin
 */
public enum HttpStatus {
    /**
     * service error
     */
    ERROR(500),
    /**
     * request success
     */
    SUCCESS(200),
    /**
     * resource not find
     */
    NOT_FIND(404);

    /**
     * 状态码
     */
    private final int code;

    HttpStatus(int status) {
        this.code = status;
    }

    public int getCode() {
        return code;
    }
    
}
