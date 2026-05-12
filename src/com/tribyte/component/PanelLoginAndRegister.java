package com.tribyte.component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.tribyte.connection.PasswordSecurity;
import com.tribyte.connection.UserSession;
import com.tribyte.connection.ConnectDatabase;
import com.tribyte.dashboard.main.DashboardMain;
import com.tribyte.swing.Button;
import com.tribyte.swing.MyPasswordField;
import com.tribyte.swing.MyTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;


public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    private EventLogin event; 
    
    public void setEventLogin(EventLogin event){
        this.event = event;
    }
    
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
        
        txtCoInfo = new MyTextField();
        txtCoInfo.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/contact.png")));
        txtCoInfo.setHint("Contact Number");
        register.add(txtCoInfo, fieldConstraints);
        
        txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/mail.png")));
        txtEmail.setHint("example@gordoncollege.edu.ph");
        register.add(txtEmail, fieldConstraints);
        
        txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, fieldConstraints);
        
        txtConPass = new MyPasswordField();
        txtConPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/pass.png")));
        txtConPass.setHint("Confirm Password");
        register.add(txtConPass, fieldConstraints);
        
        Button cmd = new Button();
        cmd.setBackground(new Color(4, 149, 22));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("REGISTER");
        register.add(cmd, "w 50%, h 50");
        
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String first = txtFName.getText();
                String last = txtLName.getText();
                String phone = txtCoInfo.getText();
                String email = txtEmail.getText();
                String password = new String(txtPass.getPassword());
                String confirmPassword = new String(txtConPass.getPassword());

                if (first.isEmpty() || last.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
                    return;
                }
                
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match! Please try again.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                ConnectDatabase db = new ConnectDatabase();
                if (db.registerUser(first, last, email, phone, password)) {
                    JOptionPane.showMessageDialog(null, "Account Created Successfully! \nYou can now Sign In/Log In!");
                
                if (event != null) {
                event.finishedRegistration();
                }
                }
            }
        });
    }
    
    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]5[]30[]10[]5[]10[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", Font.BOLD, 50));
        label.setForeground(new Color(4, 149, 22));
        login.add(label);
        JLabel subLabel = new JLabel("To continue your event attendance.");
        subLabel.setFont(new Font("sansserif", Font.PLAIN, 35));
        subLabel.setForeground(new Color(4, 149, 22));
        login.add(subLabel);

        String fieldConstraints = "w 50%, h 50";

        MyTextField txtLoginEmail = new MyTextField();
        txtLoginEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/mail.png")));
        txtLoginEmail.setHint("example@gordoncollege.edu.ph");
        login.add(txtLoginEmail, "w 50%, h 50, wrap");
        JLabel lblEmailError = new JLabel("This field is required.");
        lblEmailError.setForeground(Color.RED);
        lblEmailError.setFont(new Font("sansserif", Font.PLAIN, 12));
        lblEmailError.setVisible(false); 
        login.add(lblEmailError, "h 20!, gapleft 15, wrap");

        MyPasswordField txtLoginPass = new MyPasswordField();
        txtLoginPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/pass.png")));
        txtLoginPass.setHint("Password");
        login.add(txtLoginPass, "w 50%, h 50, wrap");
        
        txtLoginEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                txtLoginEmail.setLineColor(new Color(210, 230, 225)); // Reset to default green-gray
                lblEmailError.setVisible(false);
                login.revalidate();
            }
        });

        txtLoginPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                txtLoginPass.setLineColor(new Color(210, 230, 225));
                lblEmailError.setVisible(false);
                login.revalidate();
            }
        });                                             

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
        
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtLoginEmail.getText().trim();
                String pass = new String(txtLoginPass.getPassword());

                lblEmailError.setVisible(false);

                if (email.isEmpty()) {
                    lblEmailError.setText("This field is required.");
                    lblEmailError.setVisible(true);
                    login.revalidate();
                    login.repaint();
                    return;
                }

                String hashSHA256 = PasswordSecurity.hashSHA256(pass);
                
                try (Connection con = ConnectDatabase.conn()) {
                    String sql = "SELECT * FROM users WHERE email=? AND password=?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, email);
                    pst.setString(2, hashSHA256);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        String role = rs.getString("role");
                        int id = rs.getInt("user_id");
                        String fName = rs.getString("first_name");
                        String lName = rs.getString("last_name");

                        UserSession.init(id, fName, lName, role);

                        Window loginWindow = SwingUtilities.getWindowAncestor(PanelLoginAndRegister.this);
                        DashboardMain dashboard = new DashboardMain(role, id);

                        if (loginWindow instanceof Frame) {
                            int state = ((Frame) loginWindow).getExtendedState();
                            dashboard.setExtendedState(state);
                        }

                        dashboard.setVisible(true);

                        if (loginWindow != null) {
                            loginWindow.dispose();
                        }
                    } else {
                        txtLoginEmail.setLineColor(Color.RED);
                        txtLoginPass.setLineColor(Color.RED);
                        lblEmailError.setText("Invalid email or password.");
                        lblEmailError.setVisible(true);
                        login.revalidate();
                        login.repaint();
                    } 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
    
    private MyTextField txtFName;
    private MyTextField txtLName;
    private MyTextField txtCoInfo;
    private MyTextField txtEmail;
    private MyPasswordField txtPass;
    private MyPasswordField txtConPass;
    
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
    // End of variables declaration//GEN-END:variables
}
