package BudgetModel;

/**
 * User model class representing user data
 * @author yakendrabamthakuri
 */
public class User {
    private String username;
    private String email;
    private String password;
    private double budgetLimit;
    
    // Default constructor
    public User() {
        this.budgetLimit = 50000.0; // Default budget limit
    }
    
    // Parameterized constructor
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.budgetLimit = 50000.0; // Default budget limit
    }
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public double getBudgetLimit() {
        return budgetLimit;
    }
    
    public void setBudgetLimit(double budgetLimit) {
        this.budgetLimit = budgetLimit;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}