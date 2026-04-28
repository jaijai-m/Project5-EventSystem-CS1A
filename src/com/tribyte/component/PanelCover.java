package com.tribyte.component;

import com.tribyte.swing.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelCover extends javax.swing.JPanel {
    
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event;
    private MigLayout layout;
    private JLabel logo;
    private JLabel title;
    private JLabel description1;
    private JLabel description2;
    private JLabel description3;
    private ButtonOutLine button;
    private boolean isLogin;
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]5[]5[]40[]10[]10[]push");
        setLayout(layout);
        init();
    }
    
    private void init() {
        logo = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/schoolLogo.png")));
        add(logo);
        title = new JLabel("GORDON COLLEGE");
        title.setFont(new Font("sansserif", Font.BOLD, 35));
        title.setForeground(new Color(245, 245, 245));
        add(title);
        description1 = new JLabel("CHARACTER • EXCELLENCE • SERVICE");
        description1.setFont(new Font("sansserif", Font.BOLD, 20));
        description1.setForeground(new Color(245, 245, 245));
        add(description1);
        description2 = new JLabel("Event Attendance System");
        description2.setFont(new Font("sansserif", Font.PLAIN, 20));
        description2.setForeground(new Color(245, 245, 245));
        add (description2);
        description3 = new JLabel("Already have an account?");
        description3.setForeground(new Color(245, 245, 245));
        add (description3);
        button = new ButtonOutLine();
        button.setBackground(new Color(245, 245, 245));
        button.setForeground(new Color(245, 245, 245));
        button.setText("SIGN IN");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.actionPerformed(e);
            }
        });
        add (button, "w 60%, h 40");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // image behind the panel
    @Override
    protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    
    java.net.URL imgURL = getClass().getResource("/com/tribyte/icon/schoolBG.jpg");
    if (imgURL != null) {
        java.awt.Image bgImage = new javax.swing.ImageIcon(imgURL).getImage();
        
        g2.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }

    // color and size for panel
    Color color1 = new Color(4, 149, 22, 235); 
    Color color2 = new Color(9, 99, 20, 235);
    GradientPaint gra = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
    g2.setPaint(gra);
    g2.fillRect(0, 0, getWidth(), getHeight());
    g2.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
    super.paintComponent(g); 
    }
    
    public void addEvent(ActionListener event) {
        this.event = event;
        
    }
    
    // moving the words in panel 
    public void registerLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(description3, "pad 0 -" + v + "% 0 0");
    }

    public void registerRight(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(description3, "pad 0 -" + v + "% 0 0");
    }

    public void loginLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(description3, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginRight(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(description3, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    // Changing of text for register and for login 
    private void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                description3.setText("Don't have an account?");
                button.setText("REGISTER");
            } else {
                description3.setText("Already have an account?");
                button.setText("SIGN IN");
            }
            this.isLogin = login;
        }
            
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}          
