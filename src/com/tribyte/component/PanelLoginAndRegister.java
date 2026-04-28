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
        MyTextField txtFName = new MyTextField();
        txtFName.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/user.png")));
        txtFName.setHint("First Name");
        register.add(txtFName, fieldConstraints);
        MyTextField txtLName = new MyTextField();
        txtLName.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/user.png")));
        txtLName.setHint("Last Name");
        register.add(txtLName, fieldConstraints);
        MyTextField txtCoInfo = new MyTextField();
        txtCoInfo.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/contact.png")));
        txtCoInfo.setHint("Contact Number");
        register.add(txtCoInfo, fieldConstraints);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/mail.png")));
        txtEmail.setHint("example@gordoncollege.edu.ph");
        register.add(txtEmail, fieldConstraints);
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, fieldConstraints);
        MyPasswordField txtConPass = new MyPasswordField();
        txtConPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/pass.png")));
        txtConPass.setHint("Confirm Password");
        register.add(txtConPass, fieldConstraints);
        Button cmd = new Button();
        cmd.setBackground(new Color(4, 149, 22));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("REGISTER");
        register.add(cmd, "w 50%, h 50");
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
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/mail.png")));
        txtEmail.setHint("example@gordoncollege.edu.ph");
        login.add(txtEmail, fieldConstraints);
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, fieldConstraints);
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
    // End of variables declaration//GEN-END:variables
}
