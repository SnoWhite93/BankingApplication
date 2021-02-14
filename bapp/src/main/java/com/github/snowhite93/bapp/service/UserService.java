package com.github.snowhite93.bapp.service;

import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.User;

import java.util.List;

public interface UserService {
    public void createUser(User user) throws BankingAppSystemException;
    public void deleteUser(long userId) throws BankingAppSystemException;
    public void updateUser(User user) throws BankingAppSystemException;
    public User findUserByPhoneAndEmail(String phoneNumber, String email) throws BankingAppSystemException;
    public List<User> getAllUsers() throws BankingAppSystemException;
}
