package com.github.snowhite93.bapp.impl;

import com.github.snowhite93.bapp.connection.SqlConnection;
import com.github.snowhite93.bapp.dao.AccountRequestDAO;
import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.AccRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRequestDAOImpl implements AccountRequestDAO {

    private static AccRequest extractAccountReq(ResultSet rs) throws SQLException {
        AccRequest accRequest = new AccRequest();
        accRequest.setRequestId(rs.getLong("request_id"));
        accRequest.setUserId(rs.getLong("user_id"));
        accRequest.setRequestStatus(rs.getString("request_status"));
        accRequest.setStartingBalance(rs.getDouble("starting_balance"));
        accRequest.setDateRequested(rs.getDate("date_requested"));
        accRequest.setRejectionReason(rs.getString("rejection_reason"));
        return accRequest;

    }

    @Override
    public boolean createAccRequest(long userId, double startingBalance) throws BankingAppSystemException {

        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "INSERT INTO account_requests (user_id, request_status, starting_balance, date_requested, rejection_reason) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            int updated = stmt.executeUpdate();
            return updated == 1;
        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not create request", e);
        }
    }

    @Override
    public List<AccRequest> seeAllAccRequests() throws BankingAppSystemException {
        List<AccRequest> allRequests = new ArrayList<>();
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "select * from account_requests";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allRequests.add(extractAccountReq(rs));
            }
        } catch (SQLException e) {
            throw new BankingAppSystemException(" Could not retrieve all requests", e);
        }
        return allRequests;
    }

    @Override
    public List<AccRequest> seeAllPendingRequests() throws BankingAppException {
        List<AccRequest> allPendingRequests = new ArrayList<>();
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "select * from account_requests where request_status = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "Pending");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allPendingRequests.add(extractAccountReq(rs));
            }

        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not retrieve all pending requests", e);
        }
        return allPendingRequests;
    }

    @Override
    public boolean rejectAccRequest(long requestId, String rejectionReason) throws BankingAppException {
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "UPDATE account_requests set request_status = ?, rejection_reason = ? WHERE request_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "Rejected");
            stmt.setString(2, rejectionReason);
            stmt.setLong(3, requestId);
            int rows = stmt.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            throw new BankingAppException("Could not reject account request", e);
        }
    }

    @Override
    public boolean approveAccRequest(long requestId) throws BankingAppException {
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "UPDATE account_requests set request_status = ? where request_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "Approved");
            stmt.setLong(2, requestId);
            int updateRow = stmt.executeUpdate();
            return updateRow == 1;
        } catch (SQLException e) {
            throw new BankingAppException("Could not approve account request", e);
        }

    }


    public List<AccRequest> seeAllUserRequests(long userId) throws BankingAppException {
        List<AccRequest> allUserRequests = new ArrayList<>();
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "select * from account_requests where user_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allUserRequests.add(extractAccountReq(rs));
            }
        } catch (SQLException e) {
            throw new BankingAppException("Could not retrieve requests for user id " + userId, e);
        }
        return allUserRequests;
    }

    public AccRequest findRequestByRequestId(long requestId) {
        AccRequest accRequest = null;
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "select * from account_requests where request_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                accRequest = extractAccountReq(resultSet);
            }
        } catch (SQLException e) {
            throw new BankingAppException("Could not retrieve request with request id " + requestId, e);
        }
        return accRequest;
    }

}
