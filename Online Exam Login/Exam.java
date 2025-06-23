import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Exam extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton submitButton;
    private JLabel timerLabel;
    private int currentTime = 600;  // 10 minutes in seconds

    public Exam(String username) {
        setTitle("Online Exam");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel("Q1: What is the capital of France?");
        options = new JRadioButton[4];
        group = new ButtonGroup();
        timerLabel = new JLabel("Time left: 10:00");
        submitButton = new JButton("Submit");

        options[0] = new JRadioButton("Paris");
        options[1] = new JRadioButton("London");
        options[2] = new JRadioButton("Berlin");
        options[3] = new JRadioButton("Madrid");

        for (JRadioButton option : options) {
            group.add(option);
        }

        submitButton.addActionListener(e -> submitExam());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(questionLabel);
        for (JRadioButton option : options) {
            panel.add(option);
        }
        panel.add(timerLabel);
        panel.add(submitButton);

        add(panel);
        startTimer();
        setVisible(true);
    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                currentTime--;
                int minutes = currentTime / 60;
                int seconds = currentTime % 60;
                timerLabel.setText(String.format("Time left: %02d:%02d", minutes, seconds));

                if (currentTime <= 0) {
                    timer.cancel();
                    submitExam();
                }
            }
        }, 0, 1000);
    }

    private void submitExam() {
        JOptionPane.showMessageDialog(this, "Exam Submitted!");
        dispose();  // Close exam window
        new Login();  // Return to login
    }
}
