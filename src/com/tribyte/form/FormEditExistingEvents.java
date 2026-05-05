package com.tribyte.form;

import com.tribyte.model.ModelEvents;
import com.tribyte.swing.EventCellEditor;
import com.tribyte.swing.EventCellRenderer;
import com.tribyte.component.ItemEvent;
import com.tribyte.model.ModelEventStorage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class FormEditExistingEvents extends JPanel {

    private ActionListener event;
    private JTextField searchField;
    private String currentOrganizer;
    private int currentUserID; 

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public FormEditExistingEvents(String role, int userID) {
        this.currentUserID = userID; // Now userID comes from the Dashboard
        initComponents();
        setOpaque(false);

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(250, 35)); // Slightly taller
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setForeground(new Color(50, 50, 50));

        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(4, 149, 22), 1, true), // Green rounded line
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Text padding inside
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

        setLayout(new MigLayout("fill, wrap 1, insets 12", "[grow, fill]", "[]0[]12[fill, grow]"));
        this.removeAll();

        JPanel titlePanel = new JPanel(new MigLayout("insets 0", "[]5[]", "[]"));
        titlePanel.setOpaque(false);
        titlePanel.add(btnBack); // Added the back button here
        titlePanel.add(txt);
        titlePanel.add(lbImage);
        this.add(titlePanel, "wrap");

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

        table1.getColumnModel().getColumn(0).setCellRenderer(new EditRenderer());
        table1.getColumnModel().getColumn(0).setCellEditor(new EditEditor());

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
            boolean isMine = (ev.getOwnerID() == currentUserID);
            boolean matchesSearch = ev.getName().toLowerCase().contains(searchText);

            if (isMine && matchesSearch) {
                model.addRow(new Object[]{ev});
            }
        }
    }

    public void addBackEvent(ActionListener event) {
        btnBack.addActionListener(event);
    }
    
    private class EditRenderer extends EventCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            ItemEvent item = (ItemEvent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // FORCE visibility and text so it never disappears
            item.getBtnJoin().setVisible(true);
            item.getBtnJoin().setText("EDIT");
            item.getBtnJoin().setEnabled(true);

            return item;
        }
    }

    // 2. KEEP ONLY ONE: The Editor handles the actual click
    private class EditEditor extends EventCellEditor {

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            ItemEvent item = (ItemEvent) super.getTableCellEditorComponent(table, value, isSelected, row, column);

            // FORCE visibility during the click phase
            item.getBtnJoin().setVisible(true);
            item.getBtnJoin().setText("EDIT");
            item.getBtnJoin().setEnabled(true);

            for (ActionListener al : item.getBtnJoin().getActionListeners()) {
                item.getBtnJoin().removeActionListener(al);
            }

            item.getBtnJoin().addActionListener(e -> {
                ModelEvents selectedEvent = (ModelEvents) value;
                if (event != null) {
                    event.actionPerformed(new ActionEvent(selectedEvent, ActionEvent.ACTION_PERFORMED, "EDIT_EVENT"));
                }
                stopCellEditing();
            });

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
        btnBack = new com.tribyte.swing.ButtonCustomDBoard();

        txt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt.setForeground(new java.awt.Color(4, 149, 22));
        txt.setText("EDIT EXISTING EVENTS");

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
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/eventSmall.png"))); // NOI18N

        btnBack.setBackground(new java.awt.Color(242, 242, 242));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/back.png"))); // NOI18N
        btnBack.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImage)
                .addContainerGap(843, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.tribyte.swing.ButtonCustomDBoard btnBack;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbImage;
    private com.tribyte.swing.PanelRound panelRound1;
    private com.tribyte.swing.table.Table table1;
    private javax.swing.JLabel txt;
    // End of variables declaration//GEN-END:variables
}