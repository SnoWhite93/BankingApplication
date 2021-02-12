package com.github.snowhite93.bapp.dao;

import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.exceptions.BankingAppUserException;
import com.github.snowhite93.bapp.pojo.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> allTransactionsForAccount(long accountId) throws BankingAppSystemException;
    List<Transaction> seeAllPendingTransactions(long accountId) throws BankingAppException;
    boolean acceptTransaction (long transactionId ) throws BankingAppSystemException;
    boolean cancelTransaction(long transactionId) throws BankingAppSystemException;
    boolean createTransaction (long fromAccount, long toAccount, double amount) throws BankingAppSystemException;
    List<Transaction> showAllTransactions() throws BankingAppSystemException;

}
