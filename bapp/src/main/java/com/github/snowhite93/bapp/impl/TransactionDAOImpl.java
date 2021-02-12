package com.github.snowhite93.bapp.impl;

import com.github.snowhite93.bapp.connection.SqlConnection;
import com.github.snowhite93.bapp.dao.TransactionDAO;
import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {

    private static Transaction extractTransaction(ResultSet rs) throws SQLException {

        Transaction transaction = new Transaction();
        transaction.setTransactionId(rs.getLong("transaction_id"));
        transaction.setFromAcc(rs.getLong("from_account"));
        transaction.setToAcc(rs.getLong("to_account"));
        transaction.setAmount(rs.getDouble("amount"));
        transaction.setStatus(rs.getString("status"));
        transaction.setTransferDate(rs.getDate("transfer_date"));
        return transaction;

    }

    @Override
    public List<Transaction> allTransactionsForAccount(long accountId) throws BankingAppSystemException {
        List<Transaction> allTransactionsForAccount = new ArrayList<>();
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "SELECT * FROM transactions where from_account = ? OR to_account = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, accountId);
            stmt.setLong(2, accountId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                allTransactionsForAccount.add(extractTransaction(resultSet));
            }

        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not show all transactions for accountId " + accountId, e);
        }
        return allTransactionsForAccount;
    }

    @Override
    public List<Transaction> seeAllPendingTransactions(long accountId) throws BankingAppException {
        List<Transaction> allPendingTransactions = new ArrayList<>();
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "SELECT * FROM transactions where to_account = ? AND status = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, accountId);
            stmt.setString(2, "Pending");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                allPendingTransactions.add(extractTransaction(resultSet));
            }
        } catch (SQLException e) {
            throw new BankingAppException("Could not return all pending transactions", e);
        }
        return allPendingTransactions;
    }

    @Override
    public boolean acceptTransaction(long transactionId) throws BankingAppSystemException {

        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "UPDATE transactions set status = ? WHERE transaction_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "Approved");
            stmt.setLong(2, transactionId);
            int updateMade = stmt.executeUpdate();
            return updateMade == 1;
        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not accept transaction", e);
        }
    }

    @Override
    public boolean cancelTransaction(long transactionId) throws BankingAppSystemException {

        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "UPDATE transactions SET status = ? WHERE transaction_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "Canceled");
            stmt.setLong(2, transactionId);
            int update = stmt.executeUpdate();
            return update == 1;
        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not cancel transaction with id " + transactionId, e);
        }

    }

    @Override
    public boolean createTransaction(long fromAccount, long toAccount, double amount) throws BankingAppSystemException {
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "INSERT INTO transactions(from_account, to_account, amount, status, transfer_date) VALUES(?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, fromAccount);
            stmt.setLong(2, toAccount);
            stmt.setDouble(3, amount);
            stmt.setString(4, "Pending");
            stmt.setDate(5, new java.sql.Date(new Date().getTime()));
            int rows = stmt.executeUpdate();
            return rows == 1;

        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not create transaction", e);
        }
    }

    public List<Transaction> showAllTransactions() throws BankingAppSystemException {
        List<Transaction> allTransactions = new ArrayList<>();
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "SELECT * FROM transactions";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = extractTransaction(resultSet);
                allTransactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not retrieve all transactions", e);
        }
        return allTransactions;
    }

}
