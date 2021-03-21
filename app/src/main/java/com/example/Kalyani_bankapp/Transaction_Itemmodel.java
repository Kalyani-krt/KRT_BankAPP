package com.example.Kalyani_bankapp;

public class Transaction_Itemmodel {
    String sender,receiver,amount;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String  amount) {
        this.amount = amount;
    }

    public Transaction_Itemmodel(String sender,String amount,String receiver) {
        this.sender = sender;
        this.amount = amount;
        this.receiver = receiver;

    }

}
