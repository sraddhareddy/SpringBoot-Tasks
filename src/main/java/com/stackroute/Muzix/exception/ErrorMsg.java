package com.stackroute.Muzix.exception;

import lombok.Data;

@Data
public class ErrorMsg {

    private String errormessage;

    private String requestedURI;

    public void setErrormessage(String message) {
    }

    public void setRequestedURI(String requestURI) {
    }
}
