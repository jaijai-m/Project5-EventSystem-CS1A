package com.tribyte.form;

import com.tribyte.connection.ConnectDatabase;
import com.tribyte.dialog.Message;
import com.tribyte.model.ModelAttendance;
import com.tribyte.model.ModelEvents;
import com.tribyte.swing.ButtonDBoard;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import net.miginfocom.swing.MigLayout;

public class FormEventAttendees extends JPanel {

    private ModelEvents selectedEvent;
    private ActionListener backEvent;

    public FormEventAttendees(ModelEvents data) {
        this.selectedEvent = data;
        initComponents();
        setOpaque(false);

        setLayout(new MigLayout("fill, wrap 1, insets 12", "[grow, fill]", "[]0[]12[fill, grow]"));

        initCustomUI();
        initTableData();
    }

    private void initCustomUI() {
        this.removeAll();

        JPanel titlePanel = new JPanel(new MigLayout("insets 0", "[]10[]5[]", "[]"));
        titlePanel.setOpaque(false);

        btnBack.setBackground(new Color(242, 242, 242)); 
        btnBack.setRoundness(15); 

        titlePanel.add(btnBack);
        titlePanel.add(txt);
        titlePanel.add(lbImage);

        this.add(titlePanel, "wrap, gapbottom -8");


        panelRound1.setLayout(new BorderLayout());

        JLabel lbTableTitle = new JLabel("ATTENDANCE: " + selectedEvent.getName() + " (" + selectedEvent.getDate() + ")");
        lbTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTableTitle.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 0));

        panelRound1.add(lbTableTitle, BorderLayout.NORTH);
        panelRound1.add(jScrollPane1, BorderLayout.CENTER);

        this.add(panelRound1, "grow");

        btnBack.addActionListener(e -> {
            if (backEvent != null) {
                backEvent.actionPerformed(e);
            }
        });
    }

    private void initTableData() {
        table1.fixTable(jScrollPane1);

        table1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Full Name", "Email", "Registered At", "Status", "Action"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        });

        table1.setRowHeight(40);

        table1.getColumnModel().getColumn(4).setCellRenderer(new ActionRenderer());
        table1.getColumnModel().getColumn(4).setCellEditor(new ActionEditor());

        table1.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        ConnectDatabase db = new ConnectDatabase();
        java.util.List<com.tribyte.model.ModelAttendance> list = db.getAttendanceList(selectedEvent.getEventID());

        for (ModelAttendance att : list) {
            model.addRow(new Object[]{
                att.getName(), 
                att.getEmail(),
                att.getRegisteredAt(), 
                att.getStatus(),
                null 
            });
        }
    }
    
    private boolean showConfirm(String title, String message, String subMessage) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        Message obj = new Message(frame);
        obj.showMessage(title, message, subMessage);
        return obj.getMessageType() == Message.MessageType.CONFIRM;
    }

    private class ActionRenderer extends DefaultTableCellRenderer {

        private ButtonDBoard btn = new ButtonDBoard();

        public ActionRenderer() {
            btn.setIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/delete.png")));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                btn.setBackground(table.getSelectionBackground());
            } else {
                btn.setBackground(table.getBackground());
            }
            return btn;
        }
    }

    private class ActionEditor extends AbstractCellEditor implements TableCellEditor {

        private ButtonDBoard btn = new ButtonDBoard();
        private int row;

        public ActionEditor() {
            btn.setIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/delete.png")));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            btn.addActionListener(e -> {
                stopCellEditing();

                String studentName = table1.getValueAt(row, 0).toString();
                String studentEmail = table1.getValueAt(row, 1).toString();

                if (showConfirm("Confirm Removal", "Remove " + studentName + "?", "This will unregister the student from the event.")) {
                    ConnectDatabase db = new ConnectDatabase();
                    if (db.unregisterStudent(studentEmail, selectedEvent.getEventID())) {
                        ((DefaultTableModel) table1.getModel()).removeRow(row);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete from database.");
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row; 
            return btn;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;
        }
    }
    
    public void addBackEvent(ActionListener event) {
        this.backEvent = event;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new com.tribyte.swing.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new com.tribyte.swing.table.Table();
        btnBack = new com.tribyte.swing.ButtonCustomDBoard();
        txt = new javax.swing.JLabel();
        lbImage = new javax.swing.JLabel();

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("DATA EVENTS");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1206, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnBack.setBackground(new java.awt.Color(242, 242, 242));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/back.png"))); // NOI18N
        btnBack.addActionListener(this::btnBackActionPerformed);

        txt.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txt.setForeground(new java.awt.Color(4, 149, 22));
        txt.setText("ATTENDEES");

        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/tribyte/icon/attendanceSmall.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbImage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
