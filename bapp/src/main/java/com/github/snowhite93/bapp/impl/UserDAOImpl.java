package com.github.snowhite93.bapp.impl;

import com.github.snowhite93.bapp.connection.SqlConnection;
import com.github.snowhite93.bapp.dao.UserDAO;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static User extractUser(ResultSet rs) throws SQLException {

        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("pass"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setEmail(rs.getString("email"));
        user.setDob(rs.getDate("date_of_birth"));
        return user;
    }

    @Override
    public boolean createUser(User user) throws BankingAppSystemException {
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "INSERT INTO bank_users(user_id, username, pass, first_name, last_name, phone_number, email, date_of_birth) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, user.getUserId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getPhoneNumber());
            stmt.setString(7, user.getEmail());
            stmt.setDate(8, new java.sql.Date(user.getDob().getTime()));
            int update = stmt.executeUpdate();
            return update == 1;
        } catch (SQLException e) {
            throw new BankingAppSystemException("Failed to create user, contact admin", e);
        }

    }

    @Override
    public boolean deleteUser(long userId) throws BankingAppSystemException {
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "DELETE bank_users WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, userId);
            return stmt.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new BankingAppSystemException("Failed to delete user, contact admin", e);

        }
    }

    @Override
    public boolean updateUser(User user) throws BankingAppSystemException {
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "UPDATE bank_users SET phone_number = ?, email = ? WHERE user_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getPhoneNumber());
            stmt.setString(2, user.getEmail());
            stmt.setLong(3, user.getUserId());

            return stmt.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not update user, contact admin", e);
        }
    }

    @Override
    public User findUserByPhoneAndEmail(String phoneNumber, String email) throws BankingAppSystemException {
        User user = null;
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "SELECT * FROM bank_users WHERE phone_number =? AND email = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not read user with phone number " + phoneNumber + " and email " + email);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws BankingAppSystemException {
        List<User> allUsers = new ArrayList<>();
        try {
            Connection connection = SqlConnection.getConnection();
            String sql = "SELECT * FROM bank_users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = extractUser(resultSet);
                allUsers.add(user);
            }

        } catch (SQLException e) {
            throw new BankingAppSystemException("Could not show all users", e);
        }
        return allUsers;
    }

}
