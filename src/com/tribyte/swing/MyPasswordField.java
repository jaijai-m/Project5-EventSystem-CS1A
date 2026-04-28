package com.tribyte.swing;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class MyPasswordField extends JPasswordField {

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }

    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";
    private final Image eyeView;
    private final Image eyeHide;
    private boolean hide = true;

    public MyPasswordField() {
    setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setBackground(new Color(60, 60, 60));
    setForeground(new Color(105, 105, 105));
    setFont(new java.awt.Font("sansserif", 1, 13));
    setSelectionColor(new Color(130, 130, 130, 160));
    setEchoChar('•');
    setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    setBackground(new Color(0, 0, 0, 0));
    eyeView = new ImageIcon(getClass().getResource("/com/tribyte/icon/eyeView.png")).getImage();
    eyeHide = new ImageIcon(getClass().getResource("/com/tribyte/icon/eyeHide.png")).getImage();

    addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            int x = getWidth() - 30;
            if (e.getX() >= x) {
                hide = !hide;
                if (hide) {
                    setEchoChar('•'); 
                } else {
                    setEchoChar((char) 0);
                }
                repaint();
            }
        }
    });

    addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
        @Override
        public void mouseMoved(java.awt.event.MouseEvent e) {
            int x = getWidth() - 30;
            if (e.getX() >= x) {
                setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            } else {
                setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
            }
        }
    });
}

    @Override
    protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    g2.setColor(new Color(210, 230, 225));
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
    paintIcon(g);
    int eyeSize = 20; 
    int x = getWidth() - eyeSize - 10; 
    int y = (getHeight() - eyeSize) / 2;
    g2.drawImage(hide ? eyeHide : eyeView, x, y, eyeSize, eyeSize, null);
    super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getPassword().length == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(110, 125, 120, 150));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
    
    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }
        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 10, y, this);
        }
    }

    private void initBorder() {
    int left = 15;
    int right = 35; 
    
    if (prefixIcon != null) {
        left = prefixIcon.getIconWidth() + 20; 
    }
    if (suffixIcon != null) {
        right = suffixIcon.getIconWidth() + 45;
    }
    setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }
}