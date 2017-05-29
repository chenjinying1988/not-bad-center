package com.jychan.notbad.common;

/**
 * 业务上的常量
 *
 * Created by chenjinying on 2017/5/15.
 * mail: 415683089@qq.com
 */
public interface Consts {

    String LOGIN_USER = "login_user";

    interface Common {
        String CHARSET_UTF8 = "UTF-8";
        String STATUS_CODE = "statusCode";
        String STATUS_MSG = "statusMsg";
        String DATA = "data";
    }

    /** Security */
    interface SC {
        /** 处理成功 */
        String SUCCESS = "CODE_SUCCESS";
        /** 处理失败 */
        String FAIL = "CODE_FAIL";
        /** 未知异常 */
        String UNKNOWN = "CODE_UNKNOWN";
        /** 请求错误 */
        String REQ_ERROR = "CODE_REQ_ERROR";
        /** 安全错误 */
        String SECURE_ERROR = "CODE_SECURE_ERROR";
        /** 未登陆错误 */
        String NOT_LOGIN_ERROR = "CODE_NOT_LOGIN_ERROR";
        /** 数据格式错误 */
        String DATA_FORMAT_ERROR = "CODE_DATA_FORMAT_ERROR";
        /** 内部错误 */
        String INTERNAL_ERROR = "CODE_INTERNAL_ERROR";
        /** 参数错误 */
        String PARAMETER_ERROR = "CODE_PARAMETER_ERROR";
    }

    public enum RESPON_CODE {
        SUCCESS(200, "处理成功"),
        FAIL(101, "处理失败"),
        UNKNOWN(102, "未知异常"),
        REQ_ERROR(103, "请求错误"),
        SECURE_ERROR(104, "认证未通过"),
        NOT_LOGIN_ERROR(105, "用户未登录"),
        DATA_FORMAT_ERROR(106, "数据格式错误"),
        INTERNAL_ERROR(107, "内部错误"),
        PARAMETER_ERROR(108, "参数%s错误 ") {
            public String getPrompt() {
                return "参数错误";
            }
        };
        private int code;
        private String msg;
        private RESPON_CODE(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
        public String getPrompt() {
            return msg;
        }
        public String getPromptWithFormat(Object ... values) {
            return String.format(msg, values);
        }

    }
//
//    public enum SC_CODE_MAPPER {
//        SUCCESS("处理成功"),
//        FAIL("处理失败"),
//        UNKNOWN("未知异常"),
//        REQ_ERROR("请求错误"),
//        SECURE_ERROR("认证未通过"),
//        NOT_LOGIN_ERROR("用户未登录"),
//        DATA_FORMAT_ERROR("数据格式错误"),
//        INTERNAL_ERROR("内部错误"),
//        PARAMETER_ERROR("参数%s错误 ") {
//            public String getPrompt() {
//                return "参数错误";
//            }
//        };
//
//        private String msg;
//        private SC_CODE_MAPPER(String msg) {
//            this.msg = msg;
//        }
//        public String getPrompt() {
//            return msg;
//        }
//        public String getPromptWithFormat(Object ... values) {
//            return String.format(msg, values);
//        }
//    }

}
