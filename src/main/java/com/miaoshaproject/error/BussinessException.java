package com.miaoshaproject.error;

public class BussinessException extends Exception implements CommonError{
    private CommonError commonError;

    // 构造业务异常 直接传参
    public BussinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    // 接受自定义errMsg
    public BussinessException(CommonError commonError, String errMsg) {
        this.commonError = commonError;
        this.commonError.setErrorMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return commonError.getErrMsg();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
