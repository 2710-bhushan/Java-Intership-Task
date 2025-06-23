import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ReservationSystem extends JFrame {
    private JTextField passengerNameField, trainNumberField, fromField, toField, dateField;
    private JComboBox<String> classTypeComboBox;
    private JButton reserveButton, cancelButton;
    private static ArrayList<Ticket> ticketList = new ArrayList<>();  // In-memory ticket storage

    public ReservationSystem() {
        setTitle("Online Reservation System");
        setLayout(new GridLayout(7, 2));


        add(new JLabel("Passenger Name:"));
        passengerNameField = new JTextField();
        add(passengerNameField);

        add(new JLabel("Train Number:"));
        trainNumberField = new JTextField();
        add(trainNumberField);

        add(new JLabel("Class Type:"));
        classTypeComboBox = new JComboBox<>(new String[]{"AC", "Sleeper", "General"});
        add(classTypeComboBox);

        add(new JLabel("Date of Journey:"));
        dateField = new JTextField("YYYY-MM-DD");
        add(dateField);

        add(new JLabel("From:"));
        fromField = new JTextField();
        add(fromField);

        add(new JLabel("To:"));
        toField = new JTextField();
        add(toField);

        reserveButton = new JButton("Reserve");
        add(reserveButton);

        cancelButton = new JButton("Cancel Ticket");
        add(cancelButton);

        reserveButton.addActionListener(e -> {
            String passengerName = passengerNameField.getText();
            String trainNumber = trainNumberField.getText();
            String classType = (String) classTypeComboBox.getSelectedItem();
            String date = dateField.getText();
            String from = fromField.getText();
            String to = toField.getText();


            Ticket ticket = new Ticket(passengerName, trainNumber, classType, date, from, to);
            ticketList.add(ticket);
            JOptionPane.showMessageDialog(this, "Ticket Reserved Successfully!");
        });


        cancelButton.addActionListener(e -> new CancellationForm(ticketList));

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
