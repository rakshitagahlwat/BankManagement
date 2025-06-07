package com.bank.service;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;

import java.util.List;

public class BankService {
    private final AccountDAO accountDAO;

    public BankService() {
        this.accountDAO = new AccountDAO();
    }

    public boolean authenticate(String accountNumber, String pin) {
        Account acc = accountDAO.getAccount(accountNumber);
        if (acc == null) {
            System.out.println("No account found with account number: " + accountNumber);
            return false;
        }
        if (!acc.getPin().equals(pin)) {
            System.out.println("Incorrect PIN for account: " + accountNumber);
            return false;
        }
        return true;
    }


    public void createAccount(Account account) {
        accountDAO.saveAccount(account);
    }


    public boolean deposit(String accountNumber, double amount) {
        Account acc = accountDAO.getAccount(accountNumber);
        if (acc != null) {
            acc.deposit(amount);
            accountDAO.saveAccount(acc);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account acc = accountDAO.getAccount(accountNumber);
        if (acc != null && acc.withdraw(amount)) {
            accountDAO.saveAccount(acc);
            return true;
        }
        return false;
    }

    public double getBalance(String accountNumber) {
        Account acc = accountDAO.getAccount(accountNumber);
        return acc != null ? acc.getBalance() : 0.0;
    }

    public boolean changePin(String accountNumber, String newPin) {
        Account acc = accountDAO.getAccount(accountNumber);
        if (acc != null) {
            acc.setPin(newPin);
            accountDAO.saveAccount(acc);
            return true;
        }
        return false;
    }

    public Account getAccount(String accountNumber) {
        return accountDAO.getAccount(accountNumber);
    }

    public void printTransactionHistory(String accountNumber) {
        Account acc = accountDAO.getAccount(accountNumber);
        if (acc != null) {
            List<String> history = acc.getTransactionHistory();
            for (String txn : history) {
                System.out.println(txn);
            }
        } else {
            System.out.println("Account not found.");
        }
    }
}
