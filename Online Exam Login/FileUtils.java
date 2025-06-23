import java.io.*;
import java.util.*;

public class FileUtils {
    public static Map<String, String> loadUsers() throws IOException {
        Map<String, String> users = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader("data/users.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            users.put(parts[0], parts[1]);  // Username, Password
        }
        reader.close();
        return users;
    }

    public static void saveUser(String username, String password) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.txt", true));
        writer.write(username + "," + password);
        writer.newLine();
        writer.close();
    }
}
