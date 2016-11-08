package com.drmeph.cartProject.model.response;

import com.drmeph.cartProject.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

/**
 * Created by kdorfer on 2016-11-07.
 */
public class ResponseModel {
    @JsonIgnore
    private ResultCode resultCode;

    @JsonInclude(NON_NULL)
    private String message;

    @JsonInclude(NON_EMPTY)
    private HashMap<String, String> errors;

    public ResponseModel() {
        errors = new HashMap<>();
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("resultCode")
    public int getResultCodeId() {
        return this.resultCode.getId();
    }
}
