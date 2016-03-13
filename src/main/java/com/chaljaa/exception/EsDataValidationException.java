package com.chaljaa.exception;

import com.chaljaa.util.ErrorDetail;

/**
 * This class is a custom exception representing the Data Validation Failure
 * @author Rajan Sanghvi
 *
 */
public class EsDataValidationException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private ErrorDetail errorDetail;

    public EsDataValidationException() {
        super();
    }

    public EsDataValidationException(String message) {
        super(message);
    }

    public EsDataValidationException(String message, ErrorDetail errorDetail) {
        super(message);
        this.errorDetail = errorDetail;
    }

    public ErrorDetail getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }
}
