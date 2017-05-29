package com.jychan.notbad.exception;

/**
 * Created by chenjinying on 2017/5/21.
 * mail: 415683089@qq.com
 */
public class ApiParaException extends NbException {

    public ApiParaException(String message) {
        super(message);
    }

    public ApiParaException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ApiParaException(int statusCode, String statusMsg) {
        super(statusCode, statusMsg);
    }

    public ApiParaException(int statusCode, String statusMsg, Throwable throwable) {
        super(statusCode, statusMsg, throwable);
    }
}
