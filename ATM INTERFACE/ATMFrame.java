import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMFrame extends JFrame implements ActionListener {
    private BankAccount account;
    private JButton viewBalanceButton, depositButton, withdrawButton, changePinButton, exitButton;

    public ATMFrame(BankAccount account) {
        this.account = account;

        setTitle("ATM Dashboard");
        setSize(400, 300);
        setLayout(new GridLayout(5, 1));

        viewBalanceButton = new JButton("View Balance");
        viewBalanceButton.addActionListener(this);
        add(viewBalanceButton);

        depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        add(depositButton);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        changePinButton = new JButton("Change PIN");
        changePinButton.addActionListener(this);
        add(changePinButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        add(exitButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewBalanceButton) {
            JOptionPane.showMessageDialog(this, "Balance: $" + account.getBalance());
        } else if (e.getSource() == depositButton) {
            String input = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            double amount = Double.parseDouble(input);
            account.deposit(amount);
            JOptionPane.showMessageDialog(this, "Deposited $" + amount);
        } else if (e.getSource() == withdrawButton) {
            String input = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            double amount = Double.parseDouble(input);
            if (account.withdraw(amount)) {
                JOptionPane.showMessageDialog(this, "Withdrew $" + amount);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient Balance!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == changePinButton) {
            String input = JOptionPane.showInputDialog(this, "Enter new PIN:");
            int newPin = Integer.parseInt(input);
            account.changePin(newPin);
            JOptionPane.showMessageDialog(this, "PIN changed successfully!");
        } else if (e.getSource() == exitButton) {
            JOptionPane.showMessageDialog(this, "Thank you for using the ATM!");
            System.exit(0);
        }
    }
}
