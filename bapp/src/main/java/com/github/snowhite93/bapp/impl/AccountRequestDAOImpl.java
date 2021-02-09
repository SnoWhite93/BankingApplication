package com.github.snowhite93.bapp.impl;

import com.github.snowhite93.bapp.dao.AccountRequestDAO;
import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.AccRequest;

import java.util.List;

public class AccountRequestDAOImpl implements AccountRequestDAO {

    @Override
    public AccRequest createAccRequest(AccRequest accRequest) throws BankingAppSystemException {
        return null;
    }

    @Override
    public List<AccRequest> seeAllAccRequests(long requestId) throws BankingAppSystemException {
        return null;
    }

    @Override
    public List<AccRequest> seeAllPendingRequests(String status) throws BankingAppSystemException {
        return null;
    }

    @Override
    public boolean rejectAccRequest(long requestId) throws BankingAppException {
        return false;
    }

    @Override
    public boolean approveAccRequest(long requestId) throws BankingAppException {
        return false;
    }

}
