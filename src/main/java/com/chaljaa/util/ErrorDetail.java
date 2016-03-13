package com.chaljaa.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class to group all the Field Errors and Sent it to the client to notify them about all the errors present in the various field
 * @author Rajan Sanghvi
 *
 */
public class ErrorDetail {
    
    /**
     * This is a unique code to identify the Error. 
     */
    private int errorCode;
    
    /**
     * This can be the actual error or the technical error with the field data.
     */
    private String errorMessage;
    
    /**
     * this field is used to specify the error which is shown to the end user and is not technical, rather giving the information about what is wrong with the data
     */
    private String humanizedMessage;
    
    /**
     * This is used to group all the Field Erros that represent the Errors with each individual fields in the data provided
     */
    private List<FieldError> fieldErrors;

    public ErrorDetail() {
        super();
    }

    public ErrorDetail(int errorCode, String errorMessage, String humanizedMessage, List<FieldError> fieldErrors) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.humanizedMessage = humanizedMessage;
        this.fieldErrors = fieldErrors;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getHumanizedMessage() {
        return humanizedMessage;
    }

    public void setHumanizedMessage(String humanizedMessage) {
        this.humanizedMessage = humanizedMessage;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
    
    public void addFieldError(FieldError fieldError)
    {
        if(this.fieldErrors == null)
            this.fieldErrors = new ArrayList<FieldError>();
        
        this.fieldErrors.add(fieldError);
    }
}
