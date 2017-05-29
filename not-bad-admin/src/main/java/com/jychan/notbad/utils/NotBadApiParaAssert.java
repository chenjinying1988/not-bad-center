package com.jychan.notbad.utils;

import com.jychan.notbad.common.Consts;
import com.jychan.notbad.exception.ApiParaException;
import org.apache.commons.lang3.StringUtils;

/**
 * 异常抛出，在 {@com.jychan.notbad.controller.ResponseErrorHandler} 捕捉
 *
 * Created by chenjinying on 2017/5/21.
 * mail: 415683089@qq.com
 */
public class NotBadApiParaAssert {

    /** 为空 */
    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ApiParaException(Consts.RESPON_CODE.PARAMETER_ERROR.getCode(), message);
        }
        if (object instanceof String && "".equals(object)) {
            throw new ApiParaException(Consts.RESPON_CODE.PARAMETER_ERROR.getCode(), message);
        }
    }

    /** 包含空格 */
    public static void containsWhitespace(String str, String message) {
        if (StringUtils.containsWhitespace(str)) {
            throw new ApiParaException(Consts.RESPON_CODE.PARAMETER_ERROR.getCode(), message);
        }
    }

    /** 不是数值 */
    public static void notNumeric(String numeric, String message) {
        if (!StringUtils.isBlank(numeric) && !StringUtils.isNumeric(numeric)) {
            throw new ApiParaException(Consts.RESPON_CODE.PARAMETER_ERROR.getCode(), message);
        }
    }

}
