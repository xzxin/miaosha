package com.miaoshaproject.error;

import org.omg.CORBA.UNKNOWN;

public enum EnumBussinessError implements CommonError {
    // 通用错误类型00001
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),
    // 10000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户手机号或密码不正确");
    ;

    private int errCode;
    private String errMsg;

    EnumBussinessError(int errorCode, String errorMsg) {
        this.errCode = errorCode;
        this.errMsg = errorMsg;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errMsg = errorMsg;
        return this;
    }
}
