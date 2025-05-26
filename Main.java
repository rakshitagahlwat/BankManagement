import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // FileUtil.initializeFile(); 
        AccountFileManager manager = new AccountFileManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Bank Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Update Account");
            System.out.println("4. Delete Account");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice < 1 || choice > 5) {
                    System.out.println("Please enter a number between 1 and 5.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    String accNo;
                    while (true) {
                        System.out.print("Enter account number (alphanumeric): ");
                        accNo = sc.nextLine().trim();
                        if (!accNo.matches("[a-zA-Z0-9]+")) {
                            System.out.println("Invalid account number. Try again.");
                        } else {
                            break;
                        }
                    }

                    String name;
                    while (true) {
                        System.out.print("Enter name (alphabets and spaces only): ");
                        name = sc.nextLine().trim();
                        if (!name.matches("[a-zA-Z ]+")) {
                            System.out.println("Invalid name. Try again.");
                        } else {
                            break;
                        }
                    }

                    double balance;
                    while (true) {
                        System.out.print("Enter balance (non-negative number): ");
                        try {
                            balance = Double.parseDouble(sc.nextLine());
                            if (balance < 0) {
                                System.out.println("Balance cannot be negative.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number. Try again.");
                        }
                    }

                    boolean added = manager.addAccount(new Account(accNo, name, balance));
                    System.out.println(added ? "Account created successfully." : "Account creation failed (may already exist).");
                    break;

                case 2:
                    System.out.println("All Accounts:");
                    for (Account acc : manager.getAllAccounts()) {
                        System.out.println(acc);
                    }
                    break;

                case 3:
                    System.out.print("Enter account number to update: ");
                    String accToUpdate = sc.nextLine().trim();

                    if (accToUpdate.isEmpty()) {
                        System.out.println("Account number cannot be empty.");
                        break;
                    }

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine().trim();

                    if (!newName.matches("[a-zA-Z ]+")) {
                        System.out.println("Invalid name.");
                        break;
                    }

                    System.out.print("Enter new balance: ");
                    double newBalance;
                    try {
                        newBalance = Double.parseDouble(sc.nextLine());
                        if (newBalance < 0) {
                            System.out.println("Balance cannot be negative.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid balance.");
                        break;
                    }

                    boolean updated = manager.updateAccount(accToUpdate, newName, newBalance);
                    System.out.println(updated ? "Account updated." : "Account not found.");
                    break;

                case 4:
                    System.out.print("Enter account number to delete: ");
                    String accToDelete = sc.nextLine().trim();
                    if (accToDelete.isEmpty()) {
                        System.out.println("Account number cannot be empty.");
                        break;
                    }
                    boolean deleted = manager.deleteAccount(accToDelete);
                    System.out.println(deleted ? "Account deleted." : "Account not found.");
                    break;

                case 5:
                    System.out.println("Exiting. Thank you.");
                    sc.close();
                    return;
            }
        }
    }
}

class Account {
    private String accountNumber;
    private String name;
    private double balance;

    public Account(String accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}

class AccountFileManager {
    private static final String FILE_NAME = "accounts.txt";

    public boolean addAccount(Account account) {
        List<Account> accounts = getAllAccounts();
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(account.getAccountNumber())) {
                return false;
            }
        }
        accounts.add(account);
        saveAllAccounts(accounts);
        return true;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    accounts.add(new Account(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (IOException e) {
            // Ignore if file does not exist
        }
        return accounts;
    }

    public boolean updateAccount(String accNo, String newName, double newBalance) {
        List<Account> accounts = getAllAccounts();
        boolean found = false;
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) {
                acc.setName(newName);
                acc.setBalance(newBalance);
                found = true;
                break;
            }
        }
        if (found) {
            saveAllAccounts(accounts);
        }
        return found;
    }

    public boolean deleteAccount(String accNo) {
        List<Account> accounts = getAllAccounts();
        boolean removed = accounts.removeIf(acc -> acc.getAccountNumber().equals(accNo));
        if (removed) {
            saveAllAccounts(accounts);
        }
        return removed;
    }

    private void saveAllAccounts(List<Account> accounts) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts) {
                pw.println(acc.getAccountNumber() + "," + acc.getName() + "," + acc.getBalance());
            }
        } catch (IOException e) {
            // Handle error
        }
    }
}