public class Account {
    private String accountNumber;
    private String name;
    private double balance;

    public Account(String accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getName() { return name; }
    public double getBalance() { return balance; }

    public void setName(String name) { this.name = name; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return accountNumber + "," + name + "," + balance;
    }

    public static Account fromString(String data) {
        String[] parts = data.split(",");
        return new Account(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}
