package com.github.snowhite93.bapp.dao;

import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.exceptions.BankingAppUserException;
import com.github.snowhite93.bapp.pojo.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> allTransactiosForUser(long userId) throws BankingAppSystemException;
    List<Transaction> seeAllPendingTransactions(long transactionId) throws BankingAppException;
    void withdrawMoney(double amount) throws BankingAppUserException;
    void depositMoney (double amount) throws BankingAppUserException;
    void transferMoney(double amount) throws BankingAppUserException;
    boolean acceptTransfer (long fromAccount) throws BankingAppSystemException;
    boolean rejectTransfer(long fromAccount) throws BankingAppSystemException;
    boolean cancelTransfer (long toAccount) throws BankingAppSystemException;

}
