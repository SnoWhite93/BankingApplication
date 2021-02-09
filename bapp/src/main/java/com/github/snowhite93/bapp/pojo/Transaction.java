package com.github.snowhite93.bapp.pojo;

import java.util.Date;

public class Transaction {

    private long transactionId;
    private long fromAcc;
    private long toAcc;
    private double amount;
    private String status;
    private Date transferDate;

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getFromAcc() {
        return fromAcc;
    }

    public void setFromAcc(long fromAcc) {
        this.fromAcc = fromAcc;
    }

    public long getToAcc() {
        return toAcc;
    }

    public void setToAcc(long toAcc) {
        this.toAcc = toAcc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromAcc=" + fromAcc +
                ", toAcc=" + toAcc +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", transferDate=" + transferDate +
                '}';
    }

}
