package com.github.snowhite93.bapp.dao;

import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.User;

import java.util.List;

public interface UserDAO {

    boolean createUser(User user) throws BankingAppSystemException;

    boolean deleteUser(long userId) throws BankingAppSystemException;

    boolean updateUser(User user) throws BankingAppSystemException;

    User findUserByPhoneAndEmail(String phoneNumber, String email) throws BankingAppSystemException;

    List<User> getAllUsers() throws BankingAppSystemException;


}
