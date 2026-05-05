package com.tribyte.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Glass extends JComponent {

    private float alpha = 0f;
    private final Color glassColor = new Color(50, 50, 50); // Pre-define your gray

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        // Clamp the alpha between 0 and 1 to prevent errors
        this.alpha = Math.max(0f, Math.min(1f, alpha));
        repaint();
    }

    public Glass() {
        setOpaque(false); // Helps Swing skip unnecessary background painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Skip painting entirely if alpha is 0
        if (alpha <= 0.01f) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();

        // Use the alpha directly in the composite
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        g2.setColor(glassColor);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.dispose();
        // Removed super.paintComponent(g) because JComponent doesn't have UI to paint
    }
}
