import java.io.*;
import java.util.*;

// Account Model Class
class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(int accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

// DAO Class for CRUD using File
public class AccountDAO {
    private static final String FILE_PATH = "accounts.txt";

    // Create or Add a new account
    public void createAccount(Account account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String record = account.getAccountNumber() + "," +
                            account.getAccountHolderName() + "," +
                            account.getBalance();
            writer.write(record);
            writer.newLine();
            System.out.println(" Account added successfully.");
        } catch (IOException e) {
            System.out.println(" Error while adding account: " + e.getMessage());
        }
    }

    // Read all accounts
    public List<Account> readAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int accNo = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    accounts.add(new Account(accNo, name, balance));
                }
            }
        } catch (IOException e) {
            System.out.println(" Error reading accounts: " + e.getMessage());
        }
        return accounts;
    }

    // Update balance of an account
    public void updateAccount(int accountNumber, double newBalance) {
        List<Account> accounts = readAllAccounts();
        boolean updated = false;

        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                acc.setBalance(newBalance);
                updated = true;
                break;
            }
        }

        if (updated) {
            saveAllAccounts(accounts);
            System.out.println("Account updated successfully.");
        } else {
            System.out.println(" Account not found.");
        }
    }

    // Delete an account
    public void deleteAccount(int accountNumber) {
        List<Account> accounts = readAllAccounts();
        boolean deleted = accounts.removeIf(acc -> acc.getAccountNumber() == accountNumber);

        if (deleted) {
            saveAllAccounts(accounts);
            System.out.println(" Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    // Save all accounts back to the file (overwrite)
    private void saveAllAccounts(List<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Account acc : accounts) {
                String record = acc.getAccountNumber() + "," +
                                acc.getAccountHolderName() + "," +
                                acc.getBalance();
                writer.write(record);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(" Error saving accounts: " + e.getMessage());
        }
    }

    // Main method to test CRUD
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();

        // Create test accounts
        dao.createAccount(new Account(101, "Alice", 5000));
        dao.createAccount(new Account(102, "Bob", 3000));

        // Read accounts
        System.out.println(" All Accounts:");
        for (Account acc : dao.readAllAccounts()) {
            System.out.println(acc.getAccountNumber() + " - " + acc.getAccountHolderName() + " - ₹" + acc.getBalance());
        }

        // Update an account
        dao.updateAccount(101, 6000);

        // Delete an account
        dao.deleteAccount(102);
    }
}
