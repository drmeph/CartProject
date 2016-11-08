package com.drmeph.cartProject.controller;

import com.drmeph.cartProject.enums.ResultCode;
import com.drmeph.cartProject.model.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Locale;

/**
 * Created by kdorfer on 2016-11-07.
 */
//TODO I could also use @Component
@Controller
public class BaseController {
    @Autowired
    MessageSource messageSource;

    /**
     * Set the result code to the response model and pull the localized string for the response message
     *
     * @param responseModel the model encapsulating the response for the client
     * @param resultCode result code to send to the client
     * @param message an extra message to add to the response model
     */
    void setResultCode(ResponseModel responseModel, ResultCode resultCode, String message) {
        responseModel.setResultCode(resultCode);
        String localizedMessage = messageSource.getMessage(resultCode.getDescriptionKey(), null, Locale.ENGLISH);

        localizedMessage = (message != null) ? localizedMessage + " : " + message : localizedMessage;

        responseModel.setMessage(localizedMessage);
    }

    /**
     * Loop threw validation errors and initialized the error map in the response model
     *
     * @param responseModel
     * @param errors
     */
    void setErrors(ResponseModel responseModel, Errors errors) {

        for (FieldError error : errors.getFieldErrors()) {
            responseModel.getErrors().put(error.getField(), error.getDefaultMessage());
        }
    }
}
