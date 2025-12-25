package BudgetController;

import BudgetModel.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for handling user authentication and management
 * @author yakendrabamthakuri
 */
public class UserController {
    private static UserController instance;
    private List users;
    private User admin;
    private static final String USER_FILE = "src/BudgetView/users.txt";
    
    private UserController() {
        users = new ArrayList();
        admin = new User("admin", "admin@budget.com", "admin123");
        loadUsers();
    }
    
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }
    
    private void loadUsers() {
        try {
            File file = new File(USER_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        users.add(new User(parts[0], parts[1], parts[2]));
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }
    
    private void saveUsers() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE));
            for (int i = 0; i < users.size(); i++) {
                User user = (User) users.get(i);
                writer.write(user.getUsername() + "," + user.getEmail() + "," + user.getPassword());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
    
    public boolean authenticateUser(String email, String password) {
        if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            return true;
        }
        
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isAdmin(String email) {
        return admin.getEmail().equals(email);
    }
    
    public boolean registerUser(String username, String email, String password) {
        if (admin.getEmail().equals(email)) {
            return false;
        }
        
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        users.add(new User(username, email, password));
        saveUsers();
        return true;
    }
    
    public boolean resetPassword(String email, String newPassword) {
        if (admin.getEmail().equals(email)) {
            admin.setPassword(newPassword);
            return true;
        }
        
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getEmail().equals(email)) {
                user.setPassword(newPassword);
                saveUsers();
                return true;
            }
        }
        return false;
    }
    
    public User getUserByEmail(String email) {
        if (admin.getEmail().equals(email)) {
            return admin;
        }
        
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}