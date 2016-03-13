package com.chaljaa.util;

/**
 * This class is used to log Individual Field Error. It provides the details of the Error in Data passed for a particular field
 * @author Rajan Sanghvi
 *
 */
public class FieldError {
    
    /**
     * This is Integer code to identify the error uniquely among the different fields
     */
    private int code;
    
    /**
     * the field or the variable name of an entity for which this error is looged
     */
    private String field;
    
    /**
     * the actual error message.
     */
    private String message;

    public FieldError() {
        super();
    }

    public FieldError(int code, String field, String message) {
        super();
        this.code = code;
        this.field = field;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
