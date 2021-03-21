package com.example.Kalyani_bankapp;
public class ItemModel {
String name;    String email;    String accno;
String balance;    String ifsc;    String mobileno;
String sender;    String amount;
    String receiver;

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

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ItemModel(String sender, String receiver, String amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String getName() {
        return name;    }
    public void setName(String name)    {
        this.name = name;    }
    public String getEmail() {
        return email;    }
    public void setEmail(String location) {
        this.email = email;    }
    public String getAccno() {
        return accno;    }
    public void setAccno(String accno) {
        this.accno = accno;    }
    public String getBalance() {
        return balance;    }
    public void setBalance(String balance) {
        this.balance = balance;    }
    public String getIfsc() {
        return ifsc;    }
    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;    }
    public String getMobileno() {
        return mobileno;    }
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;    }
    public ItemModel(String mobileno,String name, String email, String accno,String ifsc, String balance) {
        this.name = name;
        this.email = email;
        this.accno = accno;
        this.balance = balance;
        this.ifsc = ifsc;
        this.mobileno = mobileno;
    }
}
