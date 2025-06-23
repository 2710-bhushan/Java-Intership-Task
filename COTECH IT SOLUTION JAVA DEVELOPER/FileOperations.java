import java.io.*;
import java.util.Scanner;

/**
 * A Java program to demonstrate basic file operations: reading, writing,
 * and modifying text files.
 */
public class FileOperations {

    // File path to work with
    private static final String FILE_PATH = "example.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\nFile Operations Menu:");
                System.out.println("1. Write to File");
                System.out.println("2. Read from File");
                System.out.println("3. Modify File");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        writeToFile(scanner);
                        break;
                    case 2:
                        readFromFile();
                        break;
                    case 3:
                        modifyFile(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Writes user-provided text to a file. If the file exists, it will be overwritten.
     * @param scanner Scanner for user input.
     */
    private static void writeToFile(Scanner scanner) {
        System.out.println("Enter text to write to the file:");
        String content = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(content);
            System.out.println("Text written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads and displays the content of the file.
     */
    private static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("\nFile Content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found. Please write to the file first.");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    /**
     * Modifies the file by appending user-provided text to its content.
     * @param scanner Scanner for user input.
     */
    private static void modifyFile(Scanner scanner) {
        System.out.println("Enter text to append to the file:");
        String content = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.newLine(); // Add a new line before appending
            writer.write(content);
            System.out.println("Text appended to file successfully.");
        } catch (IOException e) {
            System.err.println("Error modifying the file: " + e.getMessage());
        }
    }
}
