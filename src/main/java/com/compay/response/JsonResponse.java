package com.compay.response;

import org.apache.commons.lang3.StringUtils;

public class JsonResponse {

    private String status = StringUtils.EMPTY;
    private String errorMessage = StringUtils.EMPTY;

    public JsonResponse(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
