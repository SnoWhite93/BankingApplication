package com.github.snowhite93.bapp.exceptions;

public class BankingAppUserException extends BankingAppException{

    public BankingAppUserException(String message) {
        super(message);
    }

    public BankingAppUserException(String message, Throwable cause) {
        super(message, cause);
    }

}
