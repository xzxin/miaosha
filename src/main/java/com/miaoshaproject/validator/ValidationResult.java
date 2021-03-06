package com.miaoshaproject.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    private boolean hasErrors = false;

    private Map<String, String> errorMsgMap = new HashMap<>();

    public String getErrorMsg() {
        return StringUtils.join(errorMsgMap.values().toArray(), ",");
    }
}
