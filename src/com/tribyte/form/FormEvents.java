package com.tribyte.form;

import com.tribyte.model.ModelEvents;
import com.tribyte.swing.EventCellEditor;
import com.tribyte.swing.EventCellRenderer;
import com.tribyte.component.ItemEvent;
import com.tribyte.model.ModelEventStorage;
import com.tribyte.swing.ButtonCustomDBoard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

    public class FormEvents extends JPanel {

        private ActionListener event;
        
        public void addEvent(ActionListener event) {
            this.event = event;
        }
        
        public FormEvents(String role) {
            initComponents();
            setOpaque(false);
            setLayout(new MigLayout("fill, wrap 2, insets 12", "[grow, fill]15[grow, fill]", "[]0[]12[]20[fill, grow]"));
            this.removeAll();

            JPanel titlePanel = new JPanel(new MigLayout("insets 0", "[]5[]", "[]"));
            titlePanel.setOpaque(false);
            titlePanel.add(txt);
            titlePanel.add(lbImage);
            this.add(titlePanel, "span 3, wrap");
            this.add(txtSub, "span 3, wrap 20");

            Font buttonFont = new Font("Segoe UI", Font.BOLD, 28);

            if ("Student".equals(role)) {
                styleButton(btn1, "Edit Profile", buttonFont);
                styleButton(btn2, "My Registrations", buttonFont);
            } else if ("Admin".equals(role)) {
                styleButton(btn1, "Create New Event", buttonFont);
                styleButton(btn2, "Edit Existing Events", buttonFont);
            } else {
                styleButton(btn1, "Create New Event", buttonFont);
                styleButton(btn2, "Manage Events", buttonFont);
            }

            add(btn1, "grow, h 150!");
            add(btn2, "grow, h 150!");
            this.add(panelRound1, "span 2, grow");
            initTableData();
            
            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (event != null) {
                        event.actionPerformed(new ActionEvent(btn1,
                                ActionEvent.ACTION_PERFORMED, "Create New Event"));
                    } else {
                        System.out.println("DEBUG: Event listener is NULL!");
                    }
                }
            });

            btn2.addActionListener(e -> {
                if (event != null) {
                    event.actionPerformed(new ActionEvent(btn2,
                            ActionEvent.ACTION_PERFORMED, btn2.getText()));
                }
            });
        }
        
        private void initTableData() {
            table1.fixTable(jScrollPane1);

            table1.setModel(new DefaultTableModel(
                    new Object[][]{},
                    new String[]{""} 
            ));

            table1.getColumnModel().getColumn(0).setCellRenderer(new EventCellRenderer());
            table1.getColumnModel().getColumn(0).setCellEditor(new EventCellEditor());
            
            table1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

            ItemEvent dummyItem = new ItemEvent(); 
            table1.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    int row = table1.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        Object value = table1.getValueAt(row, 0);
                        Rectangle cellRect = table1.getCellRect(row, 0, false);
                        int xInCell = e.getX() - cellRect.x;
                        int yInCell = e.getY() - cellRect.y;

                        dummyItem.setSize(table1.getColumnModel().getColumn(0).getWidth(), table1.getRowHeight());
                        dummyItem.doLayout();

                        Component child = dummyItem.getComponentAt(xInCell, yInCell);
                        if (child != null && child == dummyItem.getBtnJoin()) {
                            table1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                        } else {
                            table1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        }
                    }
                }
            });
            
            table1.setRowHeight(180);
            table1.setShowGrid(false); 
            table1.setIntercellSpacing(new Dimension(0, 15)); 

            panelRound1.setLayout(new BorderLayout());
            panelRound1.add(jLabel2, BorderLayout.NORTH);
            panelRound1.add(jScrollPane1, BorderLayout.CENTER);

            jLabel2.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 0));

            //Item for the Events
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0); // Clear the table first

            for (ModelEvents ev : ModelEventStorage.eventList) {
                model.addRow(new Object[]{ev});
            }

            panelRound1.revalidate();
            panelRound1.repaint();
        }
        
        private void styleButton(ButtonCustomDBoard btn, String text, Font font) {
            btn.setFont(font);
            btn.setForeground(Color.WHITE);
            btn.setText(text);
            btn.setRoundness(30); 
        }
        
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new javax.swing.JLabel();
        panelRound1 = new com.tribyte.swing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.tribyte.swing.table.Table();
        txtSub = new javax.swing.JLabel();
        lbImage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn1 = new com.tribyte.swing.ButtonCustomDBoard();
        btn2 = new com.tribyte.swing.ButtonCustomDBoard();

        txt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt.setForeground(new java.awt.Color(4, 149, 22));
        txt.setText("EVENTS");

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("CURRENT EVENTS");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1216, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtSub.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSub.setForeground(new java.awt.Color(4, 149, 22));
        txtSub.setText("Create, organize, and monitor events.");

        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/eventSmall.png"))); // NOI18N

        jPanel1.setLayout(new java.awt.GridLayout(1, 3, 12, 0));

        btn1.setBackground(new java.awt.Color(4, 149, 22));
        btn1.setForeground(new java.awt.Color(255, 255, 255));
        btn1.setText("BUTTON");
        btn1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn1.setRoundness(50);
        btn1.addActionListener(this::btn1ActionPerformed);
        jPanel1.add(btn1);

        btn2.setBackground(new java.awt.Color(4, 149, 22));
        btn2.setForeground(new java.awt.Color(255, 255, 255));
        btn2.setText("BUTTON");
        btn2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn2.setRoundness(50);
        btn2.addActionListener(this::btn2ActionPerformed);
        jPanel1.add(btn2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSub)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbImage)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(txtSub)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
            FormCreateEvent createEventForm = new FormCreateEvent();
            event.actionPerformed(evt);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        if (event != null) {
            event.actionPerformed(evt);
        }
    }//GEN-LAST:event_btn2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tribyte.swing.ButtonCustomDBoard btn1;
    private com.tribyte.swing.ButtonCustomDBoard btn2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbImage;
    private com.tribyte.swing.PanelRound panelRound1;
    private com.tribyte.swing.table.Table table1;
    private javax.swing.JLabel txt;
    private javax.swing.JLabel txtSub;
    // End of variables declaration//GEN-END:variables
}