package com.github.snowhite93.bapp.dao;

import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.Account;

import java.util.List;

public interface AccountDAO {

    boolean createAccount(Account account) throws BankingAppException;
    boolean deleteAccount(long accountId) throws BankingAppException ;
    List<Account> showAllAccountsForUser(long userId) throws BankingAppException;
    List<Account> showBalancesForUserAccounts(long userId) throws BankingAppSystemException;
    List<Account> showAllAccounts(long userId, long accountId) throws BankingAppSystemException;
    boolean updateBalance(double newBalance, long accountId) throws BankingAppSystemException;

}
