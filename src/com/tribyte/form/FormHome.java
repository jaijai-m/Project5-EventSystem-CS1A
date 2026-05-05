package com.tribyte.form;

import com.tribyte.dialog.Message;
import com.tribyte.model.ModelCard;
import com.tribyte.model.ModelEventStorage;
import com.tribyte.model.ModelEvents;
import com.tribyte.swing.table.EventAction;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class FormHome extends javax.swing.JPanel {

    private String role;
    private int currentUserId; // To track which events the Admin owns
    private ActionListener event; // Add this

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public FormHome(String role, int currentUserId) {
        this.role = role;
        this.currentUserId = currentUserId;

        initComponents();
        table1.fixTable(jScrollPane1);
        setOpaque(false);

        setLayout(new net.miginfocom.swing.MigLayout("fillx, wrap 4, insets 10", "[fill, grow]15[fill, grow]15[fill, grow]15[fill, grow]", "[]0[]0[]20[fill, grow]"));
        this.removeAll();
        
        javax.swing.JPanel titlePanel = new javax.swing.JPanel(new net.miginfocom.swing.MigLayout("insets 0", "[]5[]", "[]"));
        titlePanel.setOpaque(false);
        titlePanel.add(txt);
        titlePanel.add(lbImage); 

        add(titlePanel, "span, growx, gapbottom -10");
        add(txtSub, "span, growx, gapbottom 12");

        add(card1, "growx");
        add(card2, "growx");
        add(card3, "growx");
        add(card4, "growx");

        add(panelRound1, "span, grow");
         
        if ("Student".equals(role)) {
            table1.getColumnModel().getColumn(4).setMinWidth(0);
            table1.getColumnModel().getColumn(4).setMaxWidth(0);
            table1.getColumnModel().getColumn(4).setWidth(0);
        }

        initData();
    }

    private void initData() {
        initCardData();
        initTableData();
    }
    
    private void initTableData() {
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(ModelEvents events) {
                if (canModify(events)) {
                    if (showConfirm("Confirm Deletion", "Do you want to delete:\n " + events.getName() + "?", "Be careful, this action is permanent.")) {
                        ModelEventStorage.eventList.remove(events);
                        initTableData();
                        // SQL delete logic goes here
                    }
                } else {
                    showWarning("Permission Denied", "You can only delete your own events.");
                }
            }

            @Override
            public void update(ModelEvents events) {
                if (canModify(events)) {
                    if (showConfirm("Updating Event", "Do you want to edit:\n " + events.getName() + "?", "This will open the editor.")) {
                        if (event != null) {
                            event.actionPerformed(new java.awt.event.ActionEvent(events, java.awt.event.ActionEvent.ACTION_PERFORMED, "EDIT_EVENT"));
                        }
                    }
                } else {
                    showWarning("Access Denied", "You can only edit events you created.");
                }
            }
        };

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        for (ModelEvents ev : ModelEventStorage.eventList) {
            table1.addRow(ev.toRowTable(eventAction));
        }
    }
    private boolean canModify(ModelEvents event) {
        if ("Staff".equals(role)) {
            return true;
        }
        if ("Admin".equals(role)) {
            return event.getOwnerID() == currentUserId;
        }
        return false;
    }

    private void showWarning(String title, String message) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        Message obj = new Message(frame);
        obj.showMessage(title, message, "Please contact the main office if this is an error.");
    }
    
    private void initCardData() {
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/com/tribyte/icon/eventDboard.png"));
        card1.setData(new ModelCard("Total Events", 10, 20, icon1));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/com/tribyte/icon/checkDboard.png"));
        card2.setData(new ModelCard("Total Attendees", 10, 20, icon2));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/com/tribyte/icon/plusDboard.png"));
        card3.setData(new ModelCard("Registrations", 10, 20, icon3));
        ImageIcon icon4 = new ImageIcon(getClass().getResource("/com/tribyte/icon/upcomingEvent.png"));
        card4.setData(new ModelCard("Upcoming Events", 10, 20, icon4));
    }    
    
    private boolean showConfirm(String title, String message, String subMessage) {
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    Message obj = new Message(frame);
    obj.showMessage(title, message, subMessage);
    return obj.getMessageType() == Message.MessageType.CONFIRM;
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new com.tribyte.component.Card();
        txt = new javax.swing.JLabel();
        txtSub = new javax.swing.JLabel();
        card2 = new com.tribyte.component.Card();
        card3 = new com.tribyte.component.Card();
        card4 = new com.tribyte.component.Card();
        panelRound1 = new com.tribyte.swing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.tribyte.swing.table.Table();
        lbImage = new javax.swing.JLabel();

        txt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt.setForeground(new java.awt.Color(4, 149, 22));
        txt.setText("DASHBOARD/HOME");
        txt.setPreferredSize(new java.awt.Dimension(88, 32));

        txtSub.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSub.setForeground(new java.awt.Color(4, 149, 22));
        txtSub.setText("Events Overview");

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("DATA EVENTS");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Date", "Venue", "Slots", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbImage.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbImage.setForeground(new java.awt.Color(4, 149, 22));
        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/dboardSmall.png"))); // NOI18N
        lbImage.setPreferredSize(new java.awt.Dimension(88, 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSub)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(txtSub)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tribyte.component.Card card1;
    private com.tribyte.component.Card card2;
    private com.tribyte.component.Card card3;
    private com.tribyte.component.Card card4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbImage;
    private com.tribyte.swing.PanelRound panelRound1;
    private com.tribyte.swing.table.Table table1;
    private javax.swing.JLabel txt;
    private javax.swing.JLabel txtSub;
    // End of variables declaration//GEN-END:variables
}
