package com.tribyte.form;

import com.tribyte.connection.ConnectDatabase;
import com.tribyte.dialog.Message;
import com.tribyte.model.ModelAttendance;
import com.tribyte.model.ModelEvents;
import com.tribyte.swing.ButtonDBoard;
import com.tribyte.swing.table.Table;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import net.miginfocom.swing.MigLayout;

public class FormEventAttendees extends JPanel {

    private ModelEvents selectedEvent;
    private ActionListener backEvent;
    private List<ModelAttendance> attendanceList;
    private MouseAdapter statusToggleListener; 
    private JTextField searchField;
    

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

        // --- NEW SEARCH BAR INITIALIZATION BLOCK ---
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(250, 35));
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchField.setForeground(new Color(50, 50, 50));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(4, 149, 22), 1, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField.setToolTipText("Search attendees by name or email...");

        // Fire table filter redraw loops instantly as the admin types letters
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                updateTableRows();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                updateTableRows();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                updateTableRows();
            }
        });

        JPanel headerContainer = new JPanel(new BorderLayout());
        headerContainer.setOpaque(false);

        JLabel lbTableTitle = new JLabel("ATTENDANCE: " + selectedEvent.getName() + " (" + selectedEvent.getDate() + ")");
        lbTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTableTitle.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 0));
        headerContainer.add(lbTableTitle, BorderLayout.WEST);

        JPanel searchWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        searchWrapper.setOpaque(false);
        searchWrapper.add(new JLabel("Search: "));
        searchWrapper.add(searchField);
        headerContainer.add(searchWrapper, BorderLayout.EAST);
        // --- END OF SEARCH BAR INITIALIZATION BLOCK ---

        panelRound1.add(headerContainer, BorderLayout.NORTH); // Changed from lbTableTitle to headerContainer
        panelRound1.add(jScrollPane1, BorderLayout.CENTER);

        this.add(panelRound1, "grow");

        btnBack.addActionListener(e -> {
            if (backEvent != null) {
                backEvent.actionPerformed(e);
            }
        });
    }

    public interface AttendanceAction {

        void delete(ModelAttendance attendance, int rowIndex);
    }

    private void initTableData() {
        table1.fixTable(jScrollPane1);

        table1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Full Name", "Email", "Time In", "Time Out", "Status", "Action"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        });

        table1.setRowHeight(40);

        ConnectDatabase db = new ConnectDatabase();
        attendanceList = db.getAttendanceList(selectedEvent.getEventID());

        AttendanceAction attendanceAction = new AttendanceAction() {
            @Override
            public void delete(ModelAttendance attendance, int rowIndex) {
                if (showConfirm("Delete Attendee",
                        "Are you sure you want to remove " + attendance.getName() + " from this event?",
                        "This action is permanent.")) {
                    if (db.unregisterStudent(attendance.getEmail(), selectedEvent.getEventID())) {
                        SwingUtilities.invokeLater(() -> {
                            attendanceList.remove(attendance); 
                            updateTableRows(); 
                        });
                    }
                }
            }
        };

        table1.getColumnModel().getColumn(5).setCellRenderer(new ActionRenderer());
        table1.getColumnModel().getColumn(5).setCellEditor(new ActionEditor(attendanceAction));

        updateTableRows();
    }

    private void updateTableRows() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        String queryText = (searchField == null) ? "" : searchField.getText().toLowerCase().trim();

        for (int i = 0; i < attendanceList.size(); i++) {
            ModelAttendance att = attendanceList.get(i);

            boolean matchesName = att.getName().toLowerCase().contains(queryText);
            boolean matchesEmail = att.getEmail().toLowerCase().contains(queryText);

            if (matchesName || matchesEmail) {
                model.addRow(new Object[]{
                    att.getName(),
                    att.getEmail(),
                    att.getTimeIn(),
                    att.getTimeOut(),
                    att.getStatus(),
                    null
                });
            }
        }

        panelRound1.revalidate();
        panelRound1.repaint();
    }

    private boolean showConfirm(String title, String message, String subMessage) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        Message obj = new Message(frame);
        obj.showMessage(title, message, subMessage);
        return obj.getMessageType() == Message.MessageType.CONFIRM;
    }

    public void addBackEvent(ActionListener event) {
        this.backEvent = event;
    }

    private class ActionRenderer extends DefaultTableCellRenderer {

        private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        private final ButtonDBoard btn = new ButtonDBoard();

        public ActionRenderer() {
            panel.setOpaque(false);
            btn.setIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/delete.png")));
            btn.setOpaque(false);
            panel.add(btn);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            panel.setBackground(c.getBackground());
            return panel;
        }

    }

    private class ActionEditor extends AbstractCellEditor implements TableCellEditor {

        private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        private final ButtonDBoard btn = new ButtonDBoard();
        private final AttendanceAction action;
        private int currentRow;

        public ActionEditor(AttendanceAction action) {
            this.action = action;
            panel.setOpaque(false);

            btn.setIcon(new ImageIcon(getClass().getResource("/com/tribyte/icon/delete.png")));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setOpaque(false);

            btn.addActionListener(e -> {
                int targetRow = table1.getEditingRow();
                if (targetRow != -1) {
                    fireEditingStopped();
                    
                    String rowEmail = table1.getValueAt(targetRow, 1).toString();
                    ModelAttendance targetAttendance = null;

                    for (ModelAttendance att : attendanceList) {
                        if (att.getEmail().equalsIgnoreCase(rowEmail)) {
                            targetAttendance = att;
                            break;
                        }
                    }

                    if (targetAttendance != null) {
                        action.delete(targetAttendance, targetRow);
                    }
                }
            });

            panel.add(btn);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.currentRow = row;
            panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            if (e instanceof MouseEvent) {
                return ((MouseEvent) e).getClickCount() >= 1;
            }
            return true;
        }
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
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
