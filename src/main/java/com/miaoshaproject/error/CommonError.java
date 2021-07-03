package com.miaoshaproject.error;

public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    // 同一个错误码说明
    public CommonError setErrorMsg(String errorMsg);
}
