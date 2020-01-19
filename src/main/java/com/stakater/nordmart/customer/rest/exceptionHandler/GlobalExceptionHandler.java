package com.stakater.nordmart.customer.rest.exceptionHandler;

import com.stakater.nordmart.customer.rest.api.RestResponse;
import com.stakater.nordmart.customer.service.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.stakater.nordmart.customer.rest.api.ResponseCodesEnum.ENTITY_NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    RestResponse<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        return RestResponse.of(ENTITY_NOT_FOUND.getCode(), ENTITY_NOT_FOUND.getDescription() + ex.getMessage());
    }

}
