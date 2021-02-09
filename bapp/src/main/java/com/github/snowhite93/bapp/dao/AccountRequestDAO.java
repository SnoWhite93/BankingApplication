package com.github.snowhite93.bapp.dao;

import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.AccRequest;

import java.util.List;

public interface AccountRequestDAO {

    AccRequest createAccRequest( AccRequest accRequest) throws BankingAppSystemException;
    List<AccRequest> seeAllAccRequests(long requestId) throws BankingAppSystemException;
    List<AccRequest> seeAllPendingRequests (String status) throws BankingAppSystemException;
    boolean rejectAccRequest(long requestId) throws BankingAppException;
    boolean approveAccRequest(long requestId) throws BankingAppException;
}
