package com.bank.util;

import com.bank.model.Account;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {
    public static Map<String, Account> readAccounts(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (Map<String, Account>) ois.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public static void writeAccounts(String path, Map<String, Account> accounts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Failed to write accounts: " + e.getMessage());
        }
    }
}
