package com.github.snowhite93.bapp.service;

import com.github.snowhite93.bapp.dao.UserDAO;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.exceptions.BankingAppUserException;
import com.github.snowhite93.bapp.impl.UserDAOImpl;
import com.github.snowhite93.bapp.pojo.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void createUser(User user) throws BankingAppSystemException {
        checkValidUser(user);
        boolean createUser = userDAO.createUser(user);
        if (!createUser) {
            throw new BankingAppSystemException("Could not create user");
        }

    }

    @Override
    public void deleteUser(long userId) throws BankingAppSystemException {
        boolean deleteUser = userDAO.deleteUser(userId);
        if (!deleteUser) {
            throw new BankingAppSystemException("Could not delete user with id " + userId);
        }
    }

    @Override
    public void updateUser(User user) throws BankingAppSystemException {
        checkValidUser(user);
        boolean updateUser = userDAO.updateUser(user);
        if (!updateUser) {
            throw new BankingAppSystemException("Could not update user");
        }
    }

    @Override
    public User findUserByPhoneAndEmail(String phoneNumber, String email) throws BankingAppSystemException {
        if (phoneNumber == null || email == null) {
            throw new BankingAppSystemException("Missing phone or email");
        }
        User userByPhoneAndEmail = userDAO.findUserByPhoneAndEmail(phoneNumber, email);
        return userByPhoneAndEmail;
    }

    @Override
    public List<User> getAllUsers() throws BankingAppSystemException {
        List<User> allUsers = userDAO.getAllUsers();
        if (allUsers == null) {
            throw new BankingAppSystemException("All users are missing");

        }
        return allUsers;

    }

    private void checkValidUser(User user) {
        if (user == null) {
            throw new BankingAppUserException("No user");
        } else if (user.getPassword() == null) {
            throw new BankingAppUserException("No password");
        } else if (user.getEmail() == null) {
            throw new BankingAppUserException("No email");
        } else if (user.getPhoneNumber() == null) {
            throw new BankingAppUserException("No phone number");
        } else if (user.getUsername() == null) {
            throw new BankingAppUserException("No username");
        } else if (user.getFirstName() == null) {
            throw new BankingAppUserException("No first name");
        } else if (user.getLastName() == null) {
            throw new BankingAppUserException("No last name");
        } else if (user.getDob() == null) {
            throw new BankingAppUserException("No date of birth");
        }
    }

}
