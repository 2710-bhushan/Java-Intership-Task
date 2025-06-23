import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private JTextField guessField;
    private JLabel messageLabel, attemptsLabel, scoreLabel, roundLabel;
    private JButton guessButton, resetButton;
    private int randomNumber, attempts, score, round;
    private static final int MAX_ATTEMPTS = 5;  // Limiting attempts per round

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setLayout(new GridLayout(6, 1));  // 6 rows, 1 column layout

        // Initialize components
        messageLabel = new JLabel("Guess a number between 1 and 100:");
        add(messageLabel);

        guessField = new JTextField();
        add(guessField);

        guessButton = new JButton("Submit Guess");
        add(guessButton);

        resetButton = new JButton("Reset Game");
        resetButton.setEnabled(false);  // Disable until a round ends
        add(resetButton);

        attemptsLabel = new JLabel("Attempts Left: " + MAX_ATTEMPTS);
        add(attemptsLabel);

        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);

        roundLabel = new JLabel("Round: 1");
        add(roundLabel);

        // Initialize variables
        resetGame();

        // Event listener for the "Submit Guess" button
        guessButton.addActionListener(e -> handleGuess());

        // Event listener for the "Reset Game" button
        resetButton.addActionListener(e -> {
            round++;
            roundLabel.setText("Round: " + round);
            resetGame();
        });

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Generate a random number and reset variables
    private void resetGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;  // Random number between 1-100
        attempts = MAX_ATTEMPTS;
        attemptsLabel.setText("Attempts Left: " + attempts);
        messageLabel.setText("Guess a number between 1 and 100:");
        guessField.setText("");
        guessButton.setEnabled(true);
        resetButton.setEnabled(false);
    }

    // Handle the user’s guess
    private void handleGuess() {
        String input = guessField.getText();

        // Validate input
        try {
            int guess = Integer.parseInt(input);

            if (guess < 1 || guess > 100) {
                JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 100.");
                return;
            }

            attempts--;
            attemptsLabel.setText("Attempts Left: " + attempts);

            if (guess == randomNumber) {
                score += 10 - (MAX_ATTEMPTS - attempts);  // Points based on attempts
                scoreLabel.setText("Score: " + score);
                messageLabel.setText("Correct! The number was " + randomNumber);
                guessButton.setEnabled(false);
                resetButton.setEnabled(true);
            } else if (guess < randomNumber) {
                messageLabel.setText("Too low! Try again.");
            } else {
                messageLabel.setText("Too high! Try again.");
            }

            // If no attempts left
            if (attempts == 0 && guess != randomNumber) {
                messageLabel.setText("Out of attempts! The number was " + randomNumber);
                guessButton.setEnabled(false);
                resetButton.setEnabled(true);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGame();  // Launch the game
    }
}
