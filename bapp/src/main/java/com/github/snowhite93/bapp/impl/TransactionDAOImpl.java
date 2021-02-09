package com.github.snowhite93.bapp.impl;

import com.github.snowhite93.bapp.dao.TransactionDAO;
import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.exceptions.BankingAppUserException;
import com.github.snowhite93.bapp.pojo.Transaction;

import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public List<Transaction> allTransactiosForUser(long userId) throws BankingAppSystemException {
        return null;
    }

    @Override
    public List<Transaction> seeAllPendingTransactions(long transactionId) throws BankingAppException {
        return null;
    }

    @Override
    public void withdrawMoney(double amount) throws BankingAppUserException {

    }

    @Override
    public void depositMoney(double amount) throws BankingAppUserException {

    }

    @Override
    public void transferMoney(double amount) throws BankingAppUserException {

    }

    @Override
    public boolean acceptTransfer(long fromAccount) throws BankingAppSystemException {
        return false;
    }

    @Override
    public boolean rejectTransfer(long fromAccount) throws BankingAppSystemException {
        return false;
    }

    @Override
    public boolean cancelTransfer(long toAccount) throws BankingAppSystemException {
        return false;
    }

}
