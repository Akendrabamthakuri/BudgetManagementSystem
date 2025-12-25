package BudgetController;

import BudgetModel.User;
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
    
    private UserController() {
        users = new ArrayList();
        // Create admin account (cannot be registered normally)
        admin = new User("admin", "admin@budget.com", "admin123");
        
        // Add regular test users
        users.add(new User("user1", "user1@example.com", "password123"));
        users.add(new User("user2", "user2@example.com", "password456"));
    }
    
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }
    
    public boolean authenticateUser(String email, String password) {
        // Check admin first
        if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            return true;
        }
        
        // Check regular users
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
        // Prevent admin email registration
        if (admin.getEmail().equals(email)) {
            return false;
        }
        
        // Check if email already exists in regular users
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        users.add(new User(username, email, password));
        return true;
    }
    
    public boolean resetPassword(String email, String newPassword) {
        // Admin password reset
        if (admin.getEmail().equals(email)) {
            admin.setPassword(newPassword);
            return true;
        }
        
        // Regular user password reset
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getEmail().equals(email)) {
                user.setPassword(newPassword);
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