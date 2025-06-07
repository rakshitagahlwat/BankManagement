package com.bank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private String accountNumber;
    private String name;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountNumber, String name, String pin) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public String getPin() { return pin; }
    public double getBalance() { return balance; }
    public List<String> getTransactionHistory() { return transactionHistory; }

    public void setPin(String pin) { this.pin = pin; }
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: Rs." + amount);
    }
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawn: Rs." + amount);
            return true;
        }
        return false;
    }
    public void addTransaction(String txn) {
        transactionHistory.add(txn);
    }
}
