package com.tribyte.form;

import com.tribyte.connection.UserSession;
import com.tribyte.model.ModelEvents;
import com.tribyte.swing.EventCellEditor;
import com.tribyte.swing.EventCellRenderer;
import com.tribyte.model.ModelEventStorage;
import com.tribyte.swing.ButtonCustomDBoard;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

    public class FormEvents extends JPanel {

        private JTextField searchField;
        private ActionListener event;
        
        public void addEvent(ActionListener event) {
            this.event = event;
        }
        
        public FormEvents(String role) {
            initComponents();
            setOpaque(false);

            setLayout(new MigLayout("fill, wrap 2, insets 12", "[grow, fill]15[grow, fill]", "[]0[]12[]20[grow, fill]"));
            this.removeAll();

            JPanel titlePanel = new JPanel(new MigLayout("insets 0", "[]5[]", "[]"));
            titlePanel.setOpaque(false);
            titlePanel.add(txt);
            titlePanel.add(lbImage);
            this.add(titlePanel, "span 2, wrap"); 
            this.add(txtSub, "span 2, wrap 20");

            if (!"Registrant".equals(role)) {
                Font buttonFont = new Font("Segoe UI", Font.BOLD, 28);

                if ("Admin".equals(role)) {
                    styleButton(btn1, "Create New Event", buttonFont);
                    styleButton(btn2, "Edit Existing Events", buttonFont);
                } else {
                    styleButton(btn1, "Create New Event", buttonFont);
                    styleButton(btn2, "Manage Events", buttonFont);
                }

                add(btn1, "grow, h 150!");
                add(btn2, "grow, h 150!");

                btn1.addActionListener(e -> {
                    if (event != null) {
                        event.actionPerformed(new ActionEvent(btn1, ActionEvent.ACTION_PERFORMED, btn1.getText()));
                    }
                });
                btn2.addActionListener(e -> {
                    if (event != null) {
                        event.actionPerformed(new ActionEvent(btn2, ActionEvent.ACTION_PERFORMED, btn2.getText()));
                    }
                });
            }

            this.add(panelRound1, "span 2, grow, pushy");

            initSearchUI();
            initTableData();
        }
        
        private void initSearchUI() {
            searchField = new JTextField();
            searchField.setPreferredSize(new Dimension(250, 35));
            searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            searchField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(4, 149, 22), 1, true),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            searchField.setToolTipText("Search events...");

            searchField.getDocument().addDocumentListener(new DocumentListener() {
                public void insertUpdate(DocumentEvent e) {
                    updateTable();
                }

                public void removeUpdate(DocumentEvent e) {
                    updateTable();
                }

                public void changedUpdate(DocumentEvent e) {
                    updateTable();
                }
            });

            JPanel header = new JPanel(new BorderLayout());
            header.setOpaque(false);

            jLabel2.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));
            header.add(jLabel2, BorderLayout.WEST);

            JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
            searchPanel.setOpaque(false);
            searchPanel.add(new JLabel("Search: "));
            searchPanel.add(searchField);
            header.add(searchPanel, BorderLayout.EAST);

            panelRound1.setLayout(new BorderLayout());
            panelRound1.add(header, BorderLayout.NORTH);
            panelRound1.add(jScrollPane1, BorderLayout.CENTER);
            
            jScrollPane1.setPreferredSize(new Dimension(jScrollPane1.getPreferredSize().width, 800));
        }
        
        public void initTableData() {
            String sessionRole = UserSession.getInstance().getRole();
            int sessionUserId = UserSession.getInstance().getUserId();

            ModelEventStorage.loadFromDatabase(sessionRole, sessionUserId);

            table1.fixTable(jScrollPane1);

            table1.setModel(new DefaultTableModel(new Object[][]{}, new String[]{""}));

            table1.getColumnModel().getColumn(0).setCellRenderer(new EventCellRenderer());
            table1.getColumnModel().getColumn(0).setCellEditor(new EventCellEditor());

            table1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
            table1.setRowHeight(180);
            table1.setShowGrid(false);
            table1.setIntercellSpacing(new Dimension(0, 15));

            updateTable();
        }

        private void updateTable() {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);

            String query = searchField.getText().toLowerCase();

            for (ModelEvents ev : ModelEventStorage.eventList) {
                if (ev.getName().toLowerCase().contains(query) || ev.getDate().toLowerCase().contains(query)) {
                    model.addRow(new Object[]{ev});
                }
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
                            .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbImage)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGap(18, 18, 18)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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