
# 🏦 Bank Management System (Console-Based - Java)

This is a **console-based Bank Management System** built entirely in **Java 17**, focused on managing user bank accounts with functionality like deposits, withdrawals, PIN changes, and viewing transaction history. It uses **object serialization** to persist account data, ensuring user information is retained between program runs.

---

## 🚀 Features

- **Create New Account**  
  Users can create a new bank account by entering a valid account number, name, and a secure PIN.

- **User Authentication**  
  Users log in using their account number and PIN. Credentials are validated against stored account data.

- **Deposit Funds**  
  Allows users to deposit money. Transaction is recorded in the account's history.

- **Withdraw Funds**  
  Withdraws amount only if the balance is sufficient. The transaction is recorded.

- **Check Balance**  
  Displays the current balance of the logged-in account.

- **Transaction History**  
  Lists all deposits and withdrawals made by the user.

- **Change PIN**  
  Allows users to securely update their PIN.

- **Secure Logout**  
  Ends the session and returns to the main menu.

---

## 🧠 Input Validation

All user inputs are **carefully validated** using a `Validator` utility class:

| Field            | Validation Rule                                            |
|------------------|------------------------------------------------------------|
| Account Number   | Alphanumeric, minimum 3 characters                         |
| Name             | Only alphabets and spaces, minimum 2 characters            |
| Balance/Input    | Must be a non-negative number                              |
| Menu Choices     | Must be a valid numeric option from the displayed menu     |

This ensures no invalid or malformed data is accepted.

---

## 💾 Data Storage & Persistence

Account data is **persisted locally using Java serialization**. Here's how:

- A directory named `data/` stores individual account files named `{accountNumber}.dat`.
- A centralized file `src/main/resources/data/accounts.txt` stores a serialized map of all registered accounts.
- The `FileUtil` class handles reading/writing this data using `ObjectInputStream` and `ObjectOutputStream`.

This approach ensures that **data remains saved across multiple sessions**.

---

## 🏗️ Project Structure

```
com.bank
├── Main.java                # Entry point, console UI
│
├── model
│   └── Account.java         # Represents an account object (serializable)
│
├── dao
│   └── AccountDAO.java      # Handles account CRUD operations
│
├── service
│   └── BankService.java     # Core business logic (deposit, withdraw, etc.)
│
└── util
    ├── Validator.java       # Validates input fields
    └── FileUtil.java        # Handles file read/write for account data
```

---

## 🛠️ How to Run

### Prerequisites
- Java 17 installed
- Maven (optional, if managing dependencies)

### Steps
1. Clone or download this project.
2. Open it in IntelliJ IDEA or any Java IDE.
3. Ensure a folder named `data/` exists in the project root. If not, the system will auto-create it.
4. Run `Main.java`.
5. Interact with the system via console prompts.

---

## 🔒 Security Note

- User PINs are stored in plaintext (for demo simplicity). In real-world applications, PINs should be hashed and stored securely.
- File-based storage is suitable for small-scale educational projects. For production-grade systems, a database is recommended.

---

## 📦 Example

```
--- Bank Management System ---
1. Create Account
2. Login
3. Exit
Choose an option: 1
Enter Account Number: jt123
Enter Name: Jatin Tomar
Set PIN: 4321
✅ Account created successfully.
```

---

## 📚 Educational Purpose

This project is created as part of a college assignment and showcases:

- Java object-oriented programming (OOP)
- File I/O and Serialization
- Modular application structure
- Input validation and user interaction via the console

---

## 🤝 Contributions

Pull requests are welcome if you'd like to improve the structure, add encryption, or migrate to a database.

---

## 🧑‍💻 Authors

**Akshit Joshi**  
**Zara Khan**  
**Rakshita Gahlawat**  
**Khushi**  

Galgotias University  
Department of Computer Science and Engineering

---

## 📄 License

This project is open-source for academic and learning purposes. No warranties are provided.
