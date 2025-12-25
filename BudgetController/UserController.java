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
    
    private UserController() {
        users = new ArrayList();
        users.add(new User("admin", "admin@budget.com", "admin123"));
        users.add(new User("user1", "user1@example.com", "password123"));
    }
    
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }
    
    public boolean authenticateUser(String email, String password) {
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean registerUser(String username, String email, String password) {
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
        for (int i = 0; i < users.size(); i++) {
            User user = (User) users.get(i);
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}