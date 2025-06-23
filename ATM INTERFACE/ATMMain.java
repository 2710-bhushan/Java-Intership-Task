public class ATMMain {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000.00, 1234); // Initial balance and PIN
        new LoginFrame(account);
    }
}
