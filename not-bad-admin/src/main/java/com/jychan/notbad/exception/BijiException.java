package com.jychan.notbad.exception;

import com.jychan.notbad.common.Consts;

/**
 * Created by chenjinying on 2017/5/15.
 * mail: 415683089@qq.com
 */
public class BijiException extends RuntimeException {

    protected String statusCode;
    protected String statusMsg;

    public BijiException(String message) {
        super(message, null);
    }

    public BijiException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BijiException(String statusCode, String statusMsg) {
        this(statusCode, statusMsg, null);
    }

    public BijiException(Consts.SC_CODE_MAPPER statusCode) {
        this(statusCode.toString(), statusCode.getPrompt());
    }

    public BijiException(Consts.SC_CODE_MAPPER statusCode, Object... paramers) {
        this(statusCode.toString(), statusCode.getPromptWithFormat(paramers));
    }

    public BijiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BijiException(Throwable cause) {
        super(cause);
    }

    public BijiException(String statusCode, String statusMsg, Throwable throwable) {
        super("[" + statusCode + "]" + statusMsg, throwable);
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }


    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }
}
