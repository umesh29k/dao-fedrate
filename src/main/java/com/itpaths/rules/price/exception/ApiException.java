package com.itpaths.rules.price.exception;

import lombok.Data;

@Data
public class ApiException extends Exception {
    private String msg;
    public ApiException(String msg) {
    }
}
