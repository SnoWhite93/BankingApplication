package com.github.snowhite93.bapp.exceptions;

public class BankingAppException extends RuntimeException{

    public BankingAppException(String message) {
        super(message);
    }

    public BankingAppException(String message, Throwable cause) {
        super(message, cause);
    }
}
