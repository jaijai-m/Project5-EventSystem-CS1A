package com.tribyte.component;

import com.tribyte.model.ModelEvents;
import com.tribyte.swing.ButtonCustomDBoard;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class ItemEvent extends JPanel {

    public ItemEvent() {
        initComponents();
        setOpaque(false);
        lbTitle.setForeground(Color.WHITE);
        lbDate.setForeground(Color.WHITE);
        lbProfessor.setForeground(Color.WHITE);
        lbAccessibility.setForeground(Color.WHITE);
        lbJoined.setForeground(Color.WHITE);
        lbLeft.setForeground(Color.WHITE);
        lbStatus.setForeground(Color.WHITE);
        
        lbTitle.setFont(new Font("Segoe UI", 1, 18));
        lbDate.setFont(new Font("Segoe UI", 1, 18));
        lbProfessor.setFont(new Font("Segoe UI", 1, 18));
        lbAccessibility.setFont(new Font("Segoe UI", 1, 18));
        lbJoined.setFont(new Font("Segoe UI", 0, 18));
        lbLeft.setFont(new Font("Segoe UI", 0, 18));
        lbStatus.setFont(new Font("Segoe UI", 0, 18));

        btnJoin.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnJoin.setForeground(java.awt.Color.WHITE);
        btnJoin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        javax.swing.border.Border line = javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(255, 255, 255, 80));
        // Add some "breathing room" (padding) so the text isn't touching the line
        javax.swing.border.Border margin = javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0);
        javax.swing.border.Border compound = javax.swing.BorderFactory.createCompoundBorder(line, margin);

        // Apply it to the labels that need a line on their LEFT
        lbDate.setBorder(compound);
        lbProfessor.setBorder(compound);
        lbAccessibility.setBorder(compound);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gd = new GradientPaint(0, 0, new Color(4, 149, 22), 0, getHeight(), new Color(9, 99, 20));

        g2.setPaint(gd);

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        //Seperator
        g2.setColor(new Color(255, 255, 255, 80));
        g2.drawLine(15, 35, getWidth() - 15, 35);

        super.paintComponent(g);
    }
    
    public void setData(ModelEvents data) {
        lbTitle.setText(data.getName());
        lbDate.setText(data.getDate());
        lbProfessor.setText(data.getProfessor());
        lbAccessibility.setText(data.getAccessibility());

        lbStatus.setText("Status: " + data.getStatus());
        lbJoined.setText("Joined: " + data.getJoinedTime());
        lbLeft.setText("Left: " + data.getLeftTime());

        btnJoin.setVisible(true);

        if ("Closed".equalsIgnoreCase(data.getStatus()) || "Locked".equalsIgnoreCase(data.getStatus())) {
            btnJoin.setVisible(false);
        } else {
            btnJoin.setVisible(true);
            btnJoin.setText("JOIN");
        }
    }
    
    public ButtonCustomDBoard getBtnJoin() {
        return btnJoin;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        lbAccessibility = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        lbProfessor = new javax.swing.JLabel();
        lbJoined = new javax.swing.JLabel();
        lbLeft = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        btnJoin = new com.tribyte.swing.ButtonCustomDBoard();

        lbTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTitle.setText("Title");

        lbAccessibility.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbAccessibility.setText("Accessibility");

        lbDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDate.setText("January 30, 2026");

        lbProfessor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbProfessor.setText("Sir. FirstName LastName");

        lbJoined.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbJoined.setText("Joined: ");

        lbLeft.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbLeft.setText("Left:");

        lbStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbStatus.setText("Status:");

        btnJoin.setBackground(new java.awt.Color(4, 149, 22));
        btnJoin.setForeground(new java.awt.Color(255, 255, 255));
        btnJoin.setText("JOIN");
        btnJoin.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLeft)
                    .addComponent(lbJoined)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTitle)
                        .addGap(20, 20, 20)
                        .addComponent(lbDate)
                        .addGap(20, 20, 20)
                        .addComponent(lbProfessor)
                        .addGap(20, 20, 20)
                        .addComponent(lbAccessibility))
                    .addComponent(lbStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 295, Short.MAX_VALUE)
                .addComponent(btnJoin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitle)
                    .addComponent(lbDate)
                    .addComponent(lbAccessibility)
                    .addComponent(lbProfessor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbJoined)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLeft)
                        .addGap(18, 18, 18)
                        .addComponent(lbStatus)
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addComponent(btnJoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tribyte.swing.ButtonCustomDBoard btnJoin;
    private javax.swing.JLabel lbAccessibility;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbJoined;
    private javax.swing.JLabel lbLeft;
    private javax.swing.JLabel lbProfessor;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTitle;
    // End of variables declaration//GEN-END:variables
}
