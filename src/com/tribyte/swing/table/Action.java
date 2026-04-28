package com.tribyte.swing.table;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Action extends javax.swing.JPanel {

    public Action(ModelAction data) {
        initComponents();
        buttonDBoard2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.getEvent().update(data.getEvents());
            }
        });
        buttonDBoard3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.getEvent().update(data.getEvents());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.setColor(new Color(203, 230, 230));
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonDBoard2 = new com.tribyte.swing.ButtonDBoard();
        buttonDBoard3 = new com.tribyte.swing.ButtonDBoard();

        buttonDBoard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/edit.png"))); // NOI18N
        buttonDBoard2.addActionListener(this::buttonDBoard2ActionPerformed);

        buttonDBoard3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/delete.png"))); // NOI18N
        buttonDBoard3.addActionListener(this::buttonDBoard3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonDBoard2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDBoard3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonDBoard3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDBoard2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDBoard2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDBoard2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDBoard2ActionPerformed

    private void buttonDBoard3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDBoard3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDBoard3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tribyte.swing.ButtonDBoard buttonDBoard2;
    private com.tribyte.swing.ButtonDBoard buttonDBoard3;
    // End of variables declaration//GEN-END:variables
}
