package com.tribyte.form;

import com.tribyte.model.ModelCard;
import com.tribyte.model.ModelEvents;
import com.tribyte.swing.table.EventAction;
import javax.swing.ImageIcon;

public class FormHome extends javax.swing.JPanel {

    public FormHome() {
        initComponents();
        table1.fixTable(jScrollPane1);
        setOpaque(false);
        
        setLayout(new net.miginfocom.swing.MigLayout("fillx, wrap 4, insets 10", "[fill, grow]15[fill, grow]15[fill, grow]15[fill, grow]", "[]20[]20[fill, grow]"));
        
        this.removeAll(); 
        add(jLabel1, "span, growx, gapbottom 10"); // 'span' makes the title take the full row
        add(card1, "growx");
        add(card2, "growx");
        add(card3, "growx");
        add(card4, "growx");
    
        add(panelRound1, "span, grow");
                
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
                System.out.println("Delete"); //Message/Glasspanepopup
            }

            @Override
            public void update(ModelEvents events) {
                System.out.println("Update");
            }
        };
        
        table1.addRow(new ModelEvents("Grand Alumni Homecoming", "Apr 25, 2026", "Main Lobby", 15, 100).toRowTable(eventAction));
        table1.addRow(new ModelEvents("IT Seminar 2026", "May 12, 2026", "Audio Visual Room", 45, 50).toRowTable(eventAction));
        table1.addRow(new ModelEvents("Basketball Finals", "Jun 02, 2026", "Gymnasium", 200, 200).toRowTable(eventAction));
        table1.addRow(new ModelEvents("Grand Alumni Homecoming", "Apr 25, 2026", "Main Lobby", 15, 100).toRowTable(eventAction));
        table1.addRow(new ModelEvents("IT Seminar 2026", "May 12, 2026", "Audio Visual Room", 45, 50).toRowTable(eventAction));
        table1.addRow(new ModelEvents("Basketball Finals", "Jun 02, 2026", "Gymnasium", 200, 200).toRowTable(eventAction));
        table1.addRow(new ModelEvents("Grand Alumni Homecoming", "Apr 25, 2026", "Main Lobby", 15, 100).toRowTable(eventAction));
        table1.addRow(new ModelEvents("IT Seminar 2026", "May 12, 2026", "Audio Visual Room", 45, 50).toRowTable(eventAction));
        table1.addRow(new ModelEvents("Basketball Finals", "Jun 02, 2026", "Gymnasium", 200, 200).toRowTable(eventAction));
        table1.addRow(new ModelEvents("Grand Alumni Homecoming", "Apr 25, 2026", "Main Lobby", 15, 100).toRowTable(eventAction));
        table1.addRow(new ModelEvents("IT Seminar 2026", "May 12, 2026", "Audio Visual Room", 45, 50).toRowTable(eventAction));
        table1.addRow(new ModelEvents("Basketball Finals", "Jun 02, 2026", "Gymnasium", 200, 200).toRowTable(eventAction));
        
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new com.tribyte.component.Card();
        jLabel1 = new javax.swing.JLabel();
        card2 = new com.tribyte.component.Card();
        card3 = new com.tribyte.component.Card();
        card4 = new com.tribyte.component.Card();
        panelRound1 = new com.tribyte.swing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.tribyte.swing.table.Table();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 149, 22));
        jLabel1.setText("DASHBOARD/HOME");

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
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.tribyte.swing.PanelRound panelRound1;
    private com.tribyte.swing.table.Table table1;
    // End of variables declaration//GEN-END:variables
}
