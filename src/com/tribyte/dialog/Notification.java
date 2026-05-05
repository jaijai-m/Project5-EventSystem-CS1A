package com.tribyte.dialog;

import java.awt.*;
import javax.swing.*;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

  

    public class Notification extends JPanel {

        private final String message;
        private final JFrame parent;

        public Notification(JFrame parent, String message) {
            this.parent = parent;
            this.message = message;
            setOpaque(false);
            initComponents();
        }

        public void showNotification() {
            int x = (parent.getWidth() - getPreferredSize().width) / 2;
            int y = parent.getHeight() - 100;
            setBounds(x, y, getPreferredSize().width, 40);

            parent.getLayeredPane().add(this, JLayeredPane.POPUP_LAYER);

            Timer timer = new Timer(2000, e -> {
                parent.getLayeredPane().remove(this);
                parent.getLayeredPane().repaint();
            });
            timer.setRepeats(false);
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(50, 50, 50, 230));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            FontMetrics ft = g2.getFontMetrics();
            int tx = (getWidth() - ft.stringWidth(message)) / 2;
            int ty = (getHeight() - ft.getHeight()) / 2 + ft.getAscent();
            g2.drawString(message, tx, ty);
            g2.dispose();
        }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
