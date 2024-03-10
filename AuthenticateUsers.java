import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticateUsers {
    private static String[][] userCredentials = {
        {"Tom", "Brady"}, // Passwords are hashed
        {"David", "Ortiz"},
        {"Raul", "Sanchez"},
        {"Paul", "Pierce"},
        {"Jayson", "Tatum"}
    };

    private static final Logger logger = Logger.getLogger(AuthenticateUsers.class.getName());

    private static boolean isUsernameValid(String username) {
        for (String[] credentials : userCredentials) {
            if (credentials[0].equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPasswordValid(String username, String password) {
        for (String[] credentials : userCredentials) {
            if (credentials[0].equals(username) && validatePassword(credentials[1], password)) {
                return true;
            }
        }
        return false;
    }

    private static boolean validatePassword(String storedPassword, String enteredPassword) {
        // Check if password matches (hashed or plain)
        if (storedPassword.equals(enteredPassword)) {
            return true;
        }
        // Try to validate hashed password
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(enteredPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            String hashedPassword = sb.toString();
            return storedPassword.equals(hashedPassword);
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, "Error validating password", ex);
            return false;
        }
    }

    public static void addUser(String username, String password) {
        // Check if username already exists
        for (String[] credentials : userCredentials) {
            if (credentials[0].equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }
        // Hash the password before adding to the array
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            String hashedPassword = sb.toString();
            // Add new user to the array
            String[][] newArray = new String[userCredentials.length + 1][2];
            System.arraycopy(userCredentials, 0, newArray, 0, userCredentials.length);
            newArray[userCredentials.length][0] = username;
            newArray[userCredentials.length][1] = hashedPassword;
            userCredentials = newArray;
        } catch (NoSuchAlgorithmException ex) {
            logger.log(Level.SEVERE, "Error hashing password", ex);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String username = "";
        String password = "";
        
        System.out.println("Enter username: ");
        username = input.nextLine();
        
        // Validate username
        while (!isUsernameValid(username)) {
            System.out.print("Invalid username. Would you like to create a new user? (yes/no): ");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("Enter new username: ");
                String newUsername = input.nextLine();
                System.out.println("Enter new password: ");
                String newPassword = input.nextLine();
                addUser(newUsername, newPassword);
                System.out.println("User added successfully.");
            } else {
                System.out.print("Please re-enter username: ");
                username = input.nextLine();
            }
        }
        
        System.out.println("Enter password: ");
        password = input.nextLine();
        
        // Validate password
        if (isPasswordValid(username, password)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Invalid password");
        }
    }
}
