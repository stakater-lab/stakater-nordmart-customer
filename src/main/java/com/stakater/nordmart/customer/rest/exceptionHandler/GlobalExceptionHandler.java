package com.stakater.nordmart.customer.rest.exceptionHandler;

import com.stakater.nordmart.customer.rest.api.RestResponse;
import com.stakater.nordmart.customer.service.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.stakater.nordmart.customer.rest.api.ResponseCodesEnum.ENTITY_NOT_FOUND;
import static com.stakater.nordmart.customer.rest.api.ResponseCodesEnum.INTERNAL_SERVER_ERROR_GENERAL;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    RestResponse<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        LOG.error("Exception in customer service : " + ex.getMessage());
        return RestResponse.of(ENTITY_NOT_FOUND.getCode(), ENTITY_NOT_FOUND.getDescription() + ex.getMessage());
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    RestResponse<?> handleGenericException(Exception ex) {
        LOG.error("Exception in customer service : " + ex.getMessage());
        return RestResponse.of(INTERNAL_SERVER_ERROR_GENERAL.getCode(), INTERNAL_SERVER_ERROR_GENERAL.getDescription() + ex.getMessage());
    }

}
