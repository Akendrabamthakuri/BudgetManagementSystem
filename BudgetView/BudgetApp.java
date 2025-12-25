package BudgetView;

/**
 * Main application class to start the Budget Management System
 * @author yakendrabamthakuri
 */
public class BudgetApp {
    
    public static void main(String[] args) {
        // Set the look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(BudgetApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        // Start the application with Login form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}