package io.swen90007sm2.constant;

/**
 * @author xiaotian
 */
public enum StatusCodeEnume {

    SUCCESS(0, "Success"),
    UNKNOW_EXCEPTION(10000,"Unknown exception."),
    VAILDATION_EXCEPTION(10001,"Param format exception."),
    TO_MANY_REQUEST(10002,"Too many requests, please try again"),
    USER_EXIST_EXCEPTION(15001,"Duplicated user"),
    LOGIN_PASSWORD_EXCEPTION(15002,"Wrong passwords or username"),
    ;

    private final Integer code;
    private final String message;

    StatusCodeEnume(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}