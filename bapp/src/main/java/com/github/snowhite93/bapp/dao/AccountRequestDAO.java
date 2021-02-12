package com.github.snowhite93.bapp.dao;

import com.github.snowhite93.bapp.exceptions.BankingAppException;
import com.github.snowhite93.bapp.exceptions.BankingAppSystemException;
import com.github.snowhite93.bapp.pojo.AccRequest;

import java.util.List;

public interface AccountRequestDAO {

   boolean createAccRequest( long userId, double startingBalance) throws BankingAppSystemException;
    List<AccRequest> seeAllAccRequests() throws BankingAppSystemException;
    List<AccRequest> seeAllPendingRequests () throws BankingAppException;
    boolean rejectAccRequest(long requestId, String rejectionReason) throws BankingAppException;
    boolean approveAccRequest(long requestId) throws BankingAppException;
    List<AccRequest> seeAllUserRequests (long userId) throws BankingAppException;
    AccRequest findRequestByRequestId (long requestId);
}
