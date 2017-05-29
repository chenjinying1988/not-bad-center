package com.jychan.notbad.exception;

import com.jychan.notbad.common.Consts;

/**
 * Created by chenjinying on 2017/5/15.
 * mail: 415683089@qq.com
 */
public class NbException extends RuntimeException {

    protected int statusCode;
    protected String statusMsg;

    public NbException(String message) {
        super(message, null);
    }

    public NbException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NbException(int statusCode, String statusMsg) {
        this(statusCode, statusMsg, null);
    }

    public NbException(Consts.RESPON_CODE responCode) {
        this(responCode.getCode(), responCode.getPrompt());
    }

    public NbException(Consts.RESPON_CODE responCode, Object... paramers) {
        this(responCode.getCode(), responCode.getPromptWithFormat(paramers));
    }

    public NbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NbException(Throwable cause) {
        super(cause);
    }

    public NbException(int statusCode, String statusMsg, Throwable throwable) {
        super("[" + statusCode + "]" + statusMsg, throwable);
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }
}
