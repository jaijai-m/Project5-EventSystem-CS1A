package com.tribyte.form;

import com.tribyte.model.ModelEvents;
import com.tribyte.swing.EventCellEditor;
import com.tribyte.swing.EventCellRenderer;
import com.tribyte.component.ItemEvent;
import com.tribyte.connection.UserSession;
import com.tribyte.model.ModelEventStorage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class FormAttendance extends JPanel {

    private ActionListener event;
    private JTextField searchField;
    private String currentOrganizer;
    private int currentUserID; 
    private String role;

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public FormAttendance(String role, int userID) {
        this.role = role;
        this.currentUserID = userID;
        initComponents();
        setOpaque(false);

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(250, 35)); 
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setForeground(new Color(50, 50, 50));

        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(4, 149, 22), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));

        searchField.setToolTipText("Search by Event Name...");
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

        setLayout(new MigLayout("fillx, wrap 1, insets 12", "[grow, fill]", "[]0[]12[grow, fill]"));
        this.removeAll();

        JPanel titlePanel = new JPanel(new MigLayout("insets 0", "[]5[]", "[]"));
        titlePanel.setOpaque(false);
        titlePanel.add(txt);
        titlePanel.add(lbImage);

        this.add(titlePanel, "wrap, gapbottom -8");
        this.add(txtSub, "wrap 20");

        setupTableProperties();
        updateTable();
        this.add(panelRound1, "grow");

        this.revalidate();
        this.repaint();
    }

    private void setupTableProperties() {
        table1.fixTable(jScrollPane1);
        table1.setModel(new DefaultTableModel(new Object[][]{}, new String[]{""}));
        table1.setTableHeader(null);

        table1.getColumnModel().getColumn(0).setCellRenderer(new ViewRenderer());
        table1.getColumnModel().getColumn(0).setCellEditor(new ViewEditor());

        table1.setRowHeight(180);
        table1.setShowGrid(false);
        table1.setIntercellSpacing(new Dimension(0, 15));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        jLabel2.setVerticalAlignment(SwingConstants.CENTER);
        headerPanel.add(jLabel2, BorderLayout.WEST);
        jLabel2.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 0));

        JPanel searchContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10)); 
        searchContainer.setOpaque(false);
        searchContainer.add(new JLabel("Search: "));
        searchContainer.add(searchField);

        headerPanel.add(searchContainer, BorderLayout.EAST);

        panelRound1.setLayout(new BorderLayout());
        panelRound1.add(headerPanel, BorderLayout.NORTH);
        panelRound1.add(jScrollPane1, BorderLayout.CENTER);
    }

    private void updateTable() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        String searchText = searchField.getText().toLowerCase();

        for (ModelEvents ev : ModelEventStorage.eventList) {
            boolean matchesSearch = ev.getName().toLowerCase().contains(searchText);

            if ("Registrant".equals(this.role)) {
                if (ev.isJoined() && matchesSearch) {
                    model.addRow(new Object[]{ev});
                }
            } else {
                boolean isAllowed = "Staff".equalsIgnoreCase(this.role) || (ev.getOwnerID() == currentUserID);

                if (isAllowed && matchesSearch) {
                    model.addRow(new Object[]{ev});
                }
            }
        }
    }

    
    private class ViewRenderer extends EventCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            ItemEvent item = (ItemEvent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if ("Registrant".equals(role)) {
                item.getLbJoined().setVisible(true);
                item.getLbLeft().setVisible(true);
                item.getBtnJoin().setVisible(false);
            } else {
                item.getLbJoined().setVisible(false);
                item.getLbLeft().setVisible(false);
                item.getBtnJoin().setVisible(true);
                item.getBtnJoin().setText("VIEW");
            }
            return item;
        }
    }

    private class ViewEditor extends EventCellEditor {

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            ItemEvent item = (ItemEvent) super.getTableCellEditorComponent(table, value, isSelected, row, column);

            if ("Registrant".equals(role)) {
                item.getLbJoined().setVisible(true);
                item.getLbLeft().setVisible(true);
                item.getBtnJoin().setVisible(false);
                item.getBtnJoin().setEnabled(false);
            } else {
                item.getLbJoined().setVisible(false);
                item.getLbLeft().setVisible(false);
                item.getBtnJoin().setVisible(true);
                item.getBtnJoin().setEnabled(true);
                item.getBtnJoin().setText("VIEW");

                for (ActionListener al : item.getBtnJoin().getActionListeners()) {
                    item.getBtnJoin().removeActionListener(al);
                }

                item.getBtnJoin().addActionListener(e -> {
                    ModelEvents data = (ModelEvents) value;
                    stopCellEditing();
                    if (event != null) {
                        event.actionPerformed(new ActionEvent(data, ActionEvent.ACTION_PERFORMED, "VIEW_ATTENDANCE"));
                    }
                });
            }
            return item;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new javax.swing.JLabel();
        panelRound1 = new com.tribyte.swing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.tribyte.swing.table.Table();
        lbImage = new javax.swing.JLabel();
        txtSub = new javax.swing.JLabel();

        txt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt.setForeground(new java.awt.Color(4, 149, 22));
        txt.setText("ATTENDANCE");

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("EVENTS");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1222, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/attendanceSmall.png"))); // NOI18N

        txtSub.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtSub.setForeground(new java.awt.Color(4, 149, 22));
        txtSub.setText("Events Overview");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbImage))
                            .addComponent(txtSub))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(txtSub)
                .addGap(18, 18, 18)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbImage;
    private com.tribyte.swing.PanelRound panelRound1;
    private com.tribyte.swing.table.Table table1;
    private javax.swing.JLabel txt;
    private javax.swing.JLabel txtSub;
    // End of variables declaration//GEN-END:variables
}