package com.bank;

import com.bank.model.Account;
import com.bank.service.BankService;
import com.bank.util.Validator;

import java.util.Scanner;

public class Main {
    private static final BankService service = new BankService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            if (!Validator.isValidMenuOption(input, 1, 3)) {
                System.out.println("Invalid option.");
                continue;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Thank you for using our system.");
                    System.exit(0);
                }
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        if (!Validator.isValidAccountNumber(accNo)) {
            System.out.println("Invalid account number. Use only letters and numbers.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        if (!Validator.isValidName(name)) {
            System.out.println("Invalid name. Only alphabets and spaces allowed.");
            return;
        }

        System.out.print("Set PIN: ");
        String pin = scanner.nextLine();

        Account account = new Account(accNo, name, pin);
        service.createAccount(account);
        System.out.println("✅ Account created successfully.");
    }

    private static void login() {
        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();
        if (!Validator.isValidAccountNumber(accNo)) {
            System.out.println("Invalid account number. Use only letters and numbers.");
            return;
        }

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (service.authenticate(accNo, pin)) {
            System.out.println("✅ Login successful.");
            dashboard(accNo);
        } else {
            System.out.println("❌ Invalid credentials.");
        }
    }

    private static void dashboard(String accNo) {
        while (true) {
            System.out.println("\n--- Dashboard ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Logout");
            System.out.print("Choose: ");
            String input = scanner.nextLine();

            if (!Validator.isValidMenuOption(input, 1, 6)) {
                System.out.println("Invalid menu option.");
                continue;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount: ");
                    String amtInput = scanner.nextLine();
                    if (!Validator.isValidBalance(amtInput)) {
                        System.out.println("Enter a valid non-negative amount.");
                        break;
                    }
                    double amt = Double.parseDouble(amtInput);
                    if (service.deposit(accNo, amt))
                        System.out.println("✅ Deposited successfully.");
                    else
                        System.out.println("❌ Deposit failed.");
                }
                case 2 -> {
                    System.out.print("Enter amount: ");
                    String amtInput = scanner.nextLine();
                    if (!Validator.isValidBalance(amtInput)) {
                        System.out.println("Enter a valid non-negative amount.");
                        break;
                    }
                    double amt = Double.parseDouble(amtInput);
                    if (service.withdraw(accNo, amt))
                        System.out.println("✅ Withdrawal successful.");
                    else
                        System.out.println("❌ Insufficient balance.");
                }
                case 3 -> System.out.println("💰 Balance: Rs. " + service.getBalance(accNo));
                case 4 -> service.printTransactionHistory(accNo);
                case 5 -> {
                    System.out.print("Enter new PIN: ");
                    String newPin = scanner.nextLine();
                    if (service.changePin(accNo, newPin))
                        System.out.println("🔑 PIN updated.");
                    else
                        System.out.println("❌ PIN update failed.");
                }
                case 6 -> {
                    System.out.println("👋 Logged out.");
                    return;
                }
            }
        }
    }
}
