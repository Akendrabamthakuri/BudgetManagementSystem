package BudgetController;

import BudgetModel.Transaction;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    private static TransactionController instance;
    private List transactions;
    private static final String TRANSACTION_FILE = "src/BudgetView/transactions.txt";
    
    private TransactionController() {
        transactions = new ArrayList();
        loadTransactions();
    }
    
    public static TransactionController getInstance() {
        if (instance == null) {
            instance = new TransactionController();
        }
        return instance;
    }
    
    private void loadTransactions() {
        try {
            File file = new File(TRANSACTION_FILE);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 6) {
                        transactions.add(new Transaction(parts[0], Double.parseDouble(parts[1]), parts[2], parts[3], parts[4], parts[5]));
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
    }
    
    private void saveTransactions() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE));
            for (int i = 0; i < transactions.size(); i++) {
                Transaction t = (Transaction) transactions.get(i);
                writer.write(t.getType() + "," + t.getAmount() + "," + t.getCategory() + "," + 
                           t.getDate() + "," + t.getDescription() + "," + t.getUserEmail());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }
    
    public boolean addTransaction(String type, double amount, String category, String date, String description, String userEmail) {
        transactions.add(new Transaction(type, amount, category, date, description, userEmail));
        saveTransactions();
        return true;
    }
    
    public List getUserTransactions(String userEmail) {
        List userTrans = new ArrayList();
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = (Transaction) transactions.get(i);
            if (t.getUserEmail().equals(userEmail)) {
                userTrans.add(t);
            }
        }
        return userTrans;
    }
    
    public List getAllTransactions() {
        return transactions;
    }
    
    public double getTotalIncome(String userEmail) {
        double total = 0;
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = (Transaction) transactions.get(i);
            if (t.getUserEmail().equals(userEmail) && t.getType().equals("Income")) {
                total += t.getAmount();
            }
        }
        return total;
    }
    
    public double getTotalExpense(String userEmail) {
        double total = 0;
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = (Transaction) transactions.get(i);
            if (t.getUserEmail().equals(userEmail) && t.getType().equals("Expense")) {
                total += t.getAmount();
            }
        }
        return total;
    }
    
    public boolean deleteTransaction(String userEmail, String date, double amount) {
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = (Transaction) transactions.get(i);
            if (t.getUserEmail().equals(userEmail) && t.getDate().equals(date) && t.getAmount() == amount) {
                transactions.remove(i);
                saveTransactions();
                return true;
            }
        }
        return false;
    }
}
