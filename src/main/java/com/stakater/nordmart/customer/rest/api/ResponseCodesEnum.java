package com.stakater.nordmart.customer.rest.api;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public enum ResponseCodesEnum {

    SUCCESS(-1, HttpStatus.OK, "Success"),

    GENERAL_NOT_YET_IMPLEMENTED(0, HttpStatus.INTERNAL_SERVER_ERROR, "The method you called is not yet implemented."),
    INTERNAL_SERVER_ERROR_GENERAL(1, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error."),
    ENTITY_NOT_FOUND(2, HttpStatus.NOT_FOUND, "Could not find the entity. ");

    private static final HashMap<String, ResponseCodesEnum> responseCodeValueMap = new HashMap<>();

    static {
        for (ResponseCodesEnum type : ResponseCodesEnum.values()) {
            responseCodeValueMap.put(type.code, type);
        }
    }

    private HttpStatus httpStatusCode;
    private String code;
    private String description;

    ResponseCodesEnum(int errorCode, HttpStatus httpStatusCode, String faultMessage) {
        this.code = String.valueOf(errorCode);
        this.description = faultMessage;
        this.httpStatusCode = httpStatusCode;
    }

    public static ResponseCodesEnum mapCodeToEnum(String responseCode) {
        ResponseCodesEnum mapEnum = responseCodeValueMap.get(responseCode);

        if (mapEnum != null) {
            return mapEnum;
        } else {
            return INTERNAL_SERVER_ERROR_GENERAL;
        }
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
