package com.stakater.nordmart.customer.rest.api;

public class RestResponse<T> {

    private MessageDto response;

    private T data;

    private RestResponse(MessageDto response) {
        this.response = response;
    }

    private RestResponse(ResponseCodesEnum responseCodesEnum) {
        this.response = new MessageDto(responseCodesEnum.getCode(), responseCodesEnum.getDescription());
    }

    public static <T> RestResponse<T> of(ResponseCodesEnum responseCodesEnum) {
        return new RestResponse<>(responseCodesEnum);
    }

    public static <T> RestResponse<T> of(String code, String message) {
        return new RestResponse<>(new MessageDto(code, message));
    }

    public RestResponse<T> withData(T data) {
        this.data = data;
        return this;
    }

    public MessageDto getResponse() {
        return response;
    }

    public T getData() {
        return data;
    }

}
