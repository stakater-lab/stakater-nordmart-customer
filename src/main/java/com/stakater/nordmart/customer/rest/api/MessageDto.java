package com.stakater.nordmart.customer.rest.api;

public class MessageDto {

    private String code;

    private String message;

    public MessageDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
