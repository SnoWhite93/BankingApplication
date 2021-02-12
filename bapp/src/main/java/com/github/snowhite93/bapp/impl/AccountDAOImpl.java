package com.github.snowhite93.bapp.impl;

import com.github.snowhite93.bapp.connection.SqlConnection;
import com.github.snowhite93.bapp.dao.AccountDAO;
import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    static Account extractAccount(ResultSet rs) throws SQLException {
        Account account = new Account();

        account.setAccountId(rs.getLong("account_id"));
        account.setUserId(rs.getLong("user_id"));
        account.setBalance(rs.getDouble("balance"));
        account.setDateCreated(rs.getDate("date_created"));

        return account;

    }

    @Override
    public boolean createAccount(Account account) throws BankingAppException {
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "INSERT INTO accounts(user_id, balance, date_created) VALUES (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, account.getAccountId());
            stmt.setLong(2, account.getUserId());
            stmt.setDouble(3, account.getBalance());
            stmt.setDate(4, new java.sql.Date(account.getDateCreated().getTime()));
            int update = stmt.executeUpdate();
            return update == 1;

        } catch (SQLException e) {
            throw new BankingAppException("Could not create account, contact admin", e);
        }

    }

    @Override
    public boolean deleteAccount(long accountId) throws BankingAppException {
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "DELETE accounts WHERE account_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, accountId);
            return stmt.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new BankingAppException("Failed to delete account, contact admin", e);
        }

    }

    @Override
    public List<Account> showAllAccountsForUser(long userId) throws BankingAppException {
        List<Account> userAccounts = new ArrayList<>();
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "SELECT * FROM accounts WHERE user_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, userId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Account account = extractAccount(resultSet);
                userAccounts.add(account);
            }

        } catch (SQLException e) {
            throw new BankingAppException("Could not show accounts for user id : " + userId, e);
        }
        return userAccounts;
    }

    @Override
    public List<Account> showBalancesForUserAccounts(long userId) throws BankingAppSystemException {
        List<Account> userAccounts = new ArrayList<>();
        try {

            Connection connection = SqlConnection.getConnection();
            String sql = "SELECT account_id, balance WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Account account = extractAccount(resultSet);
                userAccounts.add(account);
            }

        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not show balances for user id : " + userId, e);
        }
        return userAccounts;
    }

    @Override
    public List<Account> showAllAccounts(long userId, long accountId) throws BankingAppSystemException {
        List<Account> accounts = new ArrayList<>();

        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "SELECT * FROM accounts";
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Account account = extractAccount(resultSet);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not show all accounts", e);
        }
        return accounts;
    }

    @Override
    public boolean updateBalance( double newBalance, long accountId) throws BankingAppSystemException {
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "UPDATE account SET balance = ? WHERE account_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, newBalance);
            stmt.setLong(2, accountId);
            int updatedRows = stmt.executeUpdate();
            return updatedRows == 1;
        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not update balance", e);
        }
    }

}
