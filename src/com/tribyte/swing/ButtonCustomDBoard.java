package com.tribyte.swing;

import java.awt.Cursor;
import javax.swing.JButton;

public class ButtonCustomDBoard extends JButton {

    private int roundness = 20;

    public ButtonCustomDBoard() {
        setContentAreaFilled(false);
        setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        setBackground(new java.awt.Color(5, 75, 15));
    }

    public int getRoundness() {
        return roundness;
    }

    public void setRoundness(int roundness) {
        this.roundness = roundness;
        repaint(); 
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);

        super.paintComponent(g);
    }
}
