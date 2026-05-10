package com.tribyte.form;

import com.tribyte.connection.ConnectDatabase;
import com.tribyte.dialog.Message;
import com.tribyte.model.ModelCard;
import com.tribyte.model.ModelEventStorage;
import com.tribyte.model.ModelEvents;
import com.tribyte.swing.table.EventAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class FormHome extends JPanel {

    private String role;
    private int currentUserId; 
    private ActionListener event; 

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public FormHome(String role, int currentUserId) {
        ModelEventStorage.loadFromDatabase();
        this.role = role;
        this.currentUserId = currentUserId;

        initComponents();
        table1.fixTable(jScrollPane1);
        setOpaque(false);

        boolean isRegistrant = "Registrant".equals(role);

        String wrapCount = isRegistrant ? "wrap 2" : "wrap 4";
        String colConstraints = isRegistrant ? "[fill, grow]15[fill, grow]" : "[fill, grow]15[fill, grow]15[fill, grow]15[fill, grow]";

        setLayout(new MigLayout("fillx, " + wrapCount + ", insets 10", colConstraints, "[]0[]0[]20[fill, grow]"));

        this.removeAll();

        JPanel titlePanel = new JPanel(new MigLayout("insets 0", "[]5[]", "[]"));
        titlePanel.setOpaque(false);
        titlePanel.add(txt);
        titlePanel.add(lbImage);

        add(titlePanel, "span, growx, gapbottom -10");
        add(txtSub, "span, growx, gapbottom 12");

        add(card1, "growx");
        add(card2, "growx");

        if (!isRegistrant) {
            add(card3, "growx");
            add(card4, "growx");
        }

        add(panelRound1, "span, grow");

        if (isRegistrant) {
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
                    if (showConfirm("Confirm Deletion", "Do you want to delete:\n " + events.getName() + "?", "This action is permanent and will remove all registrations.")) {

                        ConnectDatabase db = new ConnectDatabase();
                        if (db.deleteEvent(events.getEventID())) {

                            ModelEventStorage.loadFromDatabase();

                            initTableData();

                            System.out.println("Event deleted from DB successfully.");
                        } else {
                            showWarning("Database Error", "Failed to delete the event from the database.", "Please contact an admin");
                        }
                    }
                } else {
                    showWarning("Permission Denied", "You cannot perform this action.", "Please contact the admin.");
                }
            }

            @Override
            public void update(ModelEvents events) {
                if (canModify(events)) {
                    if (showConfirm("Updating Event", "Do you want to edit:\n " + events.getName() + "?", "This will open the editor.")) {
                        if (event != null) {
                            event.actionPerformed(new ActionEvent(events, ActionEvent.ACTION_PERFORMED, "EDIT_EVENT"));
                        }
                    }
                } else {
                    String sub = "This event is not yours. Please ask " + events.getProfessor() + " (the creator) to delete or update it.";
                    showWarning("Access Denied", "Action Restricted", sub);
                }
            }
        };

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        for (ModelEvents ev : ModelEventStorage.eventList) {
            if ("Registrant".equals(role)) {
                if (ev.isUserJoined(currentUserId)) {
                    table1.addRow(ev.toRowTable(eventAction));
                }
            } else {
                table1.addRow(ev.toRowTable(eventAction));
            }
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

    private void showWarning(String title, String message, String subMessage) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        Message obj = new Message(frame);
        obj.showMessage(title, message, subMessage);
    }
    
    private void initCardData() {
        ConnectDatabase db = new ConnectDatabase();
        
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/com/tribyte/icon/eventDboard.png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/com/tribyte/icon/checkDboard.png"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/com/tribyte/icon/plusDboard.png"));
        ImageIcon icon4 = new ImageIcon(getClass().getResource("/com/tribyte/icon/upcomingEvent.png"));

        if ("Registrant".equals(role)) {
            // Registrant/Student POV
            int joinedCount = db.getCount("registrations", "user_id = " + currentUserId);
            card1.setData(new ModelCard("Events I Joined", joinedCount, 100, icon1));

            int attendedCount = db.getCount("registrations", "user_id = " + currentUserId + " AND attendance_status = 'Present'");
            card2.setData(new ModelCard("Events Attended", attendedCount, 100, icon2));
        } else {
            // Admin and Staff POV
            int totalEvents = db.getCount("events", "");
            int totalRegistrations = db.getCount("registrations", "");
            int upcoming = db.getCount("events", "event_date >= CURDATE()");

            card1.setData(new ModelCard("Total Events", totalEvents, 100, icon1));
            card2.setData(new ModelCard("Total Attendees", totalRegistrations, 100, icon2));
            card3.setData(new ModelCard("Registrations", totalRegistrations, 100, icon3));
            card4.setData(new ModelCard("Upcoming Events", upcoming, 100, icon4));
        }
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
