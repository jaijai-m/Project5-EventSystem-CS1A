package com.tribyte.dashboard.scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(3, 5));
        setForeground(new Color(4, 149, 22));
        setUnitIncrement(20);
        setOpaque(false);
    }
}