package com.tribyte.component;

import com.tribyte.event.EventMenuSelected;
import com.tribyte.model.ModelMenu;
import com.tribyte.swing.ButtonCustomDBoard;
import com.tribyte.swing.MenuItem;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    public void setEvent(EventMenuSelected event) {
        this.event = event;
    }

    private MigLayout layout;
    private JPanel panelMenu;
    private JButton cmdLogout;
    private Top header;
    private Bottom bottom;
    private EventMenuSelected event;
    
    public Menu() {
        initComponents();
        setOpaque(false);
        init();
    }
    
    private void init() {
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "0[]5[]push[]0"));
        panelMenu = new JPanel();
        bottom = new Bottom();
        
        createButtonLogout();
        
        panelMenu.setOpaque(false);
        layout = new MigLayout("fillx, wrap", "0[fill]0", "[]0[]3[]0");
        panelMenu.setLayout(layout);
        add(panelMenu);
        add(cmdLogout, "pos 1al 1al 100% 100, height 60!");
        add(bottom);
        setAlpha(1f);
    }
    
    public void addMenu(ModelMenu menu) {
        MenuItem item = new MenuItem(menu.getIcon(), menu.getMenuName(), panelMenu.getComponentCount());
        item.addEvent(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                clearMenu(index);
            }
        });
        item.addEvent(event);
        panelMenu.add(item);
    }

    private void createButtonLogout() {
        cmdLogout = new ButtonCustomDBoard();
        cmdLogout.setIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/logout.png")));

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        top1 = new com.tribyte.component.Top();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(top1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(top1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 560, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(4, 149, 22), 0, getHeight(), new Color(9, 99, 20));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    private void clearMenu(int exceptIndex) {
        for (Component com : panelMenu.getComponents()) {
            MenuItem item = (MenuItem)com;
            if(item.getIndex() != exceptIndex) {
                item.setSelected(false);
            }
        }
    }
    
    public void addEventLogout(ActionListener event) {
        cmdLogout.addActionListener(event);
    }
    
    public void setAlpha(float alpha) {
        bottom.setAlpha(alpha);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tribyte.component.Top top1;
    // End of variables declaration//GEN-END:variables
}