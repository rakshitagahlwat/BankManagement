package com.bank.dao;

import com.bank.model.Account;
import com.bank.util.FileUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AccountDAO {
    private static final String FILE_PATH = "src/main/resources/data/accounts.txt";
    private Map<String, Account> accounts = new HashMap<>();

    public AccountDAO() {
        accounts = FileUtil.readAccounts(FILE_PATH);
    }

    public void createAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
        FileUtil.writeAccounts(FILE_PATH, accounts);
    }

    public Account getAccount(String accountNumber) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("data/" + accountNumber + ".dat"))) {
            return (Account) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
    public void updateAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
        FileUtil.writeAccounts(FILE_PATH, accounts);
    }
    public void saveAccount(Account account) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File accountFile = new File(directory, account.getAccountNumber() + ".dat");

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(accountFile))) {
                oos.writeObject(account);
            }
            System.out.println("✅ Account saved at: " + accountFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}