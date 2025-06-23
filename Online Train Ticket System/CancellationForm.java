import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CancellationForm extends JFrame {
    private JTextField pnrField;
    private JButton cancelButton;
    private ArrayList<Ticket> ticketList;

    public CancellationForm(ArrayList<Ticket> ticketList) {
        this.ticketList = ticketList;
        setTitle("Cancellation Form");
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Enter Train Number to Cancel:"));
        pnrField = new JTextField();
        add(pnrField);

        cancelButton = new JButton("Cancel Ticket");
        add(cancelButton);


        cancelButton.addActionListener(e -> {
            String trainNumber = pnrField.getText();
            boolean found = false;

            // Search for the ticket to cancel
            for (Ticket ticket : ticketList) {
                if (ticket.getTrainNumber().equals(trainNumber)) {
                    ticketList.remove(ticket);
                    JOptionPane.showMessageDialog(this, "Ticket Canceled Successfully!");
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Ticket Not Found!");
            }
        });

        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
