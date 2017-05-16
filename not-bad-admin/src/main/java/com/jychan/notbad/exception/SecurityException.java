package com.jychan.notbad.exception;

/**
 * Created by chenjinying on 2017/5/15.
 * mail: 415683089@qq.com
 */
public class SecurityException extends BijiException {

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public SecurityException(String statusCode, String statusMsg) {
        super(statusCode, statusMsg);
    }

    public SecurityException(String statusCode, String statusMsg, Throwable throwable) {
        super(statusCode, statusMsg, throwable);
    }
}
