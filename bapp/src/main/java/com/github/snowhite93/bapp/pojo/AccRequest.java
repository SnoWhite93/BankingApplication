package com.github.snowhite93.bapp.pojo;

import java.util.Date;

public class AccRequest {

    private long requestId;
    private long userId;
    private String requestStatus;
    private double startingBalance;
    private Date dateRequested;
    private String rejectionReason;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public double getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(double startingBalance) {
        this.startingBalance = startingBalance;
    }

    public Date getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Override
    public String toString() {
        return "AccRequest{" +
                "requestId=" + requestId +
                ", userId=" + userId +
                ", requestStatus='" + requestStatus + '\'' +
                ", startingBalance=" + startingBalance +
                ", dateRequested=" + dateRequested +
                ", rejectionReason='" + rejectionReason + '\'' +
                '}';
    }

}
