package com.tribyte.component;

import com.tribyte.swing.Button;
import com.tribyte.swing.MyPasswordField;
import com.tribyte.swing.MyTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import database.connection.DatabaseConnection;
import com.tribyte.utilities.PasswordSecurity;
//import com.tribyte.dialog.Message;


public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]5[]30[]10[]10[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("Register Now");
        label.setFont(new Font ("sansserif", Font.BOLD, 50));
        label.setForeground(new Color(4, 149, 22));
        register.add(label);
        
        JLabel subLabel = new JLabel("To start your event attendance.");
        subLabel.setFont(new Font ("sansserif", Font.PLAIN, 35));
        subLabel.setForeground(new Color(4, 149, 22));
        register.add(subLabel);
        
        String fieldConstraints = "w 50%, h 50";
        
        txtFName = new MyTextField();
        txtFName.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/user.png")));
        txtFName.setHint("First Name");
        register.add(txtFName, fieldConstraints);
        
        txtLName = new MyTextField();
        txtLName.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/user.png")));
        txtLName.setHint("Last Name");
        register.add(txtLName, fieldConstraints);
        
        txtContactNumber = new MyTextField();
        txtContactNumber.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/contact.png")));
        txtContactNumber.setHint("Contact Number");
        register.add(txtContactNumber, fieldConstraints);
        
        txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/mail.png")));
        txtEmail.setHint("example@gordoncollege.edu.ph");
        register.add(txtEmail, fieldConstraints);
        
        txtPassword = new MyPasswordField();
        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/pass.png")));
        txtPassword.setHint("Password");
        register.add(txtPassword, fieldConstraints);
        
        txtConPassword = new MyPasswordField();
        txtConPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/pass.png")));
        txtConPassword.setHint("Confirm Password");
        register.add(txtConPassword, fieldConstraints);
        
        Button cmd = new Button();
        cmd.setBackground(new Color(4, 149, 22));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("REGISTER");
        register.add(cmd, "w 50%, h 50");
        
        cmd.addActionListener(e -> {
            String fName = txtFName.getText().trim();
            String lName = txtLName.getText().trim();
            String email = txtEmail.getText().trim();
            String contact = txtContactNumber.getText().trim();
            String password = String.valueOf(txtPassword.getPassword());
            String confirmPassword = String.valueOf(txtConPassword.getPassword());

            //JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            //Message ms = new Message(frame);
            
            if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || contact.isEmpty() || password.isEmpty()) {
                //ms.showMessage(Message.MessageType.CANCEL, "Missing Info", "All fields are required.", "Please fill out the form completely.");
                return;
            }
            if (!password.equals(confirmPassword)) {
                //ms.showMessage(Message.MessageType.CANCEL, "Match Error", "Passwords do not match.", "Please double-check your password.");
                return;
            }

            try (java.sql.Connection conn = DatabaseConnection.getConnection()) {
                // Using the PasswordSecurity Class to hash the password
                String hashedPassword = PasswordSecurity.hashSHA256(password); 

                // SQL with Hardcoded Registrant role
                String sql = "INSERT INTO users (first_name, last_name, email, contact_number, password, role) VALUES (?, ?, ?, ?, ?, 'registrant')";
                java.sql.PreparedStatement p = conn.prepareStatement(sql);

                p.setString(1, fName);
                p.setString(2, lName);
                p.setString(3, email);
                p.setString(4, contact);
                p.setString(5, hashedPassword);

                p.executeUpdate();
                
                //ms.showMessage(Message.MessageType.SUCCESS, "Success!", "Account is Created", "You can now login!");
                
                showRegister(false); 
            } catch (java.sql.SQLException ex) {
                //ms.showMessage(Message.MessageType.CANCEL, "Database Connection Failed", "Unable to Sign Up", ex.getMessage());
            }
        });
    }
    
    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]5[]30[]10[]5[]20[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font ("sansserif", Font.BOLD, 50));
        label.setForeground(new Color(4, 149, 22));
        login.add(label);
        
        JLabel subLabel = new JLabel("To continue your event attendance.");
        subLabel.setFont(new Font ("sansserif", Font.PLAIN, 35));
        subLabel.setForeground(new Color(4, 149, 22));
        login.add(subLabel);
        String fieldConstraints = "w 50%, h 50";
        
        txtEmailLogin = new MyTextField();
        txtEmailLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/mail.png")));
        txtEmailLogin.setHint("example@gordoncollege.edu.ph");
        login.add(txtEmailLogin, fieldConstraints);
        
        txtPasswordLogin = new MyPasswordField();
        txtPasswordLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/pass.png")));
        txtPasswordLogin.setHint("Password");
        login.add(txtPasswordLogin, fieldConstraints);
        
        JButton cmdForget = new JButton("Forgot password?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        
        Button cmd = new Button();
        cmd.setBackground(new Color(4, 149, 22));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("LOGIN");
        
        login.add(cmd, "w 50%, h 50");

        cmd.addActionListener(e -> {
            String email = txtEmailLogin.getText().trim();
            String password = String.valueOf(txtPasswordLogin.getPassword());

            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            //Message ms = new Message(frame);
            
            if (email.isEmpty() || password.isEmpty()) {
                //ms.showMessage(Message.MessageType.CANCEL, "Sign In Error", "Fields cannot be empty.", "Enter both email and password!");
                System.out.println("Error: Fields are empty");
                return;
            }
                
            try (java.sql.Connection conn = DatabaseConnection.getConnection()) {
                String hashedInput = PasswordSecurity.hashSHA256(password);    
                    
                String sql = "SELECT * FROM users WHERE email= ? AND password= ?";
                java.sql.PreparedStatement p = conn.prepareStatement(sql);
                p.setString(1, email);    
                p.setString(2, hashedInput);        
                    
                java.sql.ResultSet rs = p.executeQuery();    

                if (rs.next()) {
                    String role = rs.getString("role");
                    
                    if (role.equalsIgnoreCase("Admin")) {
                        // Transition to Admin Dashboard
                        com.tribyte.login.main.Main main = (com.tribyte.login.main.Main) frame;
                        main.showAdminDashboard();
                    //} else {
                        //System.out.println("Login Success! Role: " + role);
                    // Transition to Dashboard here
                    } 
                } else {
                    //ms.showMessage(Message.MessageType.CANCEL, "Access Denied", "Invalid Email or Password!", "Please try again.");
                    System.out.println("Access Denied: Invalid Email or Password");
                }
            } catch (java.sql.SQLException ex) {
                //ms.showMessage(Message.MessageType.CANCEL, "Database Connection Failed", "Unable to Sign In right now!", ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
    
    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;

    private MyTextField txtFName;
    private MyTextField txtLName;
    private MyTextField txtContactNumber;
    private MyTextField txtEmail;
    private MyPasswordField txtPassword;
    private MyPasswordField txtConPassword;

    private MyTextField txtEmailLogin;
    private MyPasswordField txtPasswordLogin;
    // End of variables declaration//GEN-END:variables
}
