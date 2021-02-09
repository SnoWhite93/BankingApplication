package com.github.snowhite93.bapp.exceptions;

public class BankingAppSystemException extends BankingAppException{

    public BankingAppSystemException(String message) {
        super(message);
    }

    public BankingAppSystemException(String message, Throwable cause) {
        super(message, cause);
    }

}
