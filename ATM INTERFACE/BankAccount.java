public class BankAccount {
    private double balance;
    private int pin;

    public BankAccount(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void changePin(int newPin) {
        this.pin = newPin;
    }
}
