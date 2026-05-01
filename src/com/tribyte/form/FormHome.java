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
        table1 = new com.tribyte.swing.table.Table();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelRound1 = new com.tribyte.swing.PanelRound();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 149, 22));
        jLabel1.setText("DASHBOARD/HOME");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(88, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tribyte.component.Card card1;
    private com.tribyte.component.Card card2;
    private com.tribyte.component.Card card3;
    private com.tribyte.component.Card card4;
    private javax.swing.JLabel jLabel1;
    private com.tribyte.swing.table.Table table1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.tribyte.swing.PanelRound panelRound1;
    // End of variables declaration//GEN-END:variables
}
