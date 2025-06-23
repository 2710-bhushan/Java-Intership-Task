import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField pinField;
    private JButton loginButton;
    private BankAccount account;

    public LoginFrame(BankAccount account)
    {
        this.account = account;

        setTitle("ATM Login");
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));

        add(new JLabel("Enter PIN:"));
        pinField = new JTextField();
        add(pinField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(loginButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        int enteredPin = Integer.parseInt(pinField.getText());
        if (account.validatePin(enteredPin)) {
            new ATMFrame(account);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid PIN!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
