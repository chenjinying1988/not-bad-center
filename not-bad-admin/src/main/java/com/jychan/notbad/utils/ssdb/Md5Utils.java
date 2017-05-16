package com.jychan.notbad.utils.ssdb;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by chenjinying on 2017/5/14.
 * mail: 415683089@qq.com
 */
public class Md5Utils {

    private static final Logger logger = LoggerFactory.getLogger(Md5Utils.class);

    /**
     * 将字符串转为 MD5
     */
    public static String safeToMd5(String str) {
        return safeToMd5(str, "UTF-8");
    }

    /**
     * 将字符串转为 MD5
     */
    public static String safeToMd5(String src, String charset) {
        try {
            return DigestUtils.md5Hex(src.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            logger.error("safe to md5 error. charset={}", charset, e);
        }
        return "";
    }
}
