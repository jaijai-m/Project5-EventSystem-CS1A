package com.tribyte.dialog;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class Background extends JPanel{
    
    private Shape cachedShape;
    private int lastWidth; 
    private int lastHeight; 
    
    public Background() {
        init();
    }
    
    private void init() {
        setOpaque(false);
        setBackground(new Color(245, 245, 245));
        setForeground(new Color(118, 118, 118));
    }
   
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int round = 25;
        int headerHeight = 40;

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, round, round);

        g2.setColor(Color.WHITE);
        Area header = new Area(new RoundRectangle2D.Double(0, 0, width, headerHeight + 10, round, round));
        header.intersect(new Area(new Rectangle(0, 0, width, headerHeight)));
        g2.fill(header);

        g2.dispose();
        super.paintComponent(g);
    }

    private void createShape() {
        int x = 0;
        int y = 40;
        int width = getWidth();
        int height = getHeight();
        int round = 25;
        int iconSpace = 7;
        int iconSize = y * 2;
        int iconX = (width - (iconSize + iconSpace * 2)) / 2;

        Area area = new Area(new java.awt.geom.RoundRectangle2D.Double(x, y, width, height - y, round, round));
        area.subtract(new Area(new Ellipse2D.Double(iconX, -iconSpace, iconSize + iconSpace * 2, iconSize + iconSpace * 2)));
        area.add(new Area(new Ellipse2D.Double(iconX + iconSpace, 0, iconSize, iconSize)));

        cachedShape = area;
    }
}
