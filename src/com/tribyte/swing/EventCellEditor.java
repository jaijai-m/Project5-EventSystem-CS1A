package com.tribyte.swing;

import com.tribyte.component.ItemEvent;
import com.tribyte.connection.ConnectDatabase;
import com.tribyte.connection.UserSession;
import com.tribyte.form.FormEvents;
import com.tribyte.model.ModelEventStorage;
import com.tribyte.model.ModelEvents;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

public class EventCellEditor extends AbstractCellEditor implements TableCellEditor {

    private ItemEvent item;
    private ModelEvents currentData;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof ModelEvents) {
            currentData = (ModelEvents) value;
            item = new ItemEvent();
            item.setData(currentData);

            String attendance = currentData.getUserAttendanceStatus();

            if ("Attended".equalsIgnoreCase(attendance)) {
                item.getBtnJoin().setVisible(true);
                item.getBtnJoin().setEnabled(false);
                item.getBtnJoin().setText("ATTENDED");
                return item;
            }

            if ("Absent".equalsIgnoreCase(attendance)) {
                item.getBtnJoin().setVisible(true);
                item.getBtnJoin().setEnabled(false);
                item.getBtnJoin().setText("ABSENT");
                return item;
            }

            if (!currentData.isUserJoined(UserSession.getInstance().getUserId())) {
                if (currentData.getStatus().equalsIgnoreCase("Closed")
                        || currentData.getStatus().equalsIgnoreCase("Lock")
                        || currentData.getFilledSlots() >= currentData.getMaxSlots()) {

                    item.getBtnJoin().setVisible(true);
                    item.getBtnJoin().setEnabled(false);
                    item.getBtnJoin().setText("FULL");
                } else {
                    item.getBtnJoin().setVisible(true);
                    item.getBtnJoin().setEnabled(true);
                    item.getBtnJoin().setText("JOIN");

                    for (ActionListener al : item.getBtnJoin().getActionListeners()) {
                        item.getBtnJoin().removeActionListener(al);
                    }

                    item.getBtnJoin().addActionListener(e -> {
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);
                        performJoin(frame, table);
                    });
                }
            }
            else {
                item.getBtnJoin().setVisible(true);

                if (currentData.getStatus().equalsIgnoreCase("Lock") || currentData.getStatus().equalsIgnoreCase("Closed")) {
                    item.getBtnJoin().setEnabled(false);
                    // Check their current status to show a relevant label
                    if ("Present".equalsIgnoreCase(attendance)) {
                        item.getBtnJoin().setText("AUTO-ATTENDED");
                    } else if ("Absent".equalsIgnoreCase(attendance)) {
                        item.getBtnJoin().setText("ABSENT");
                    } else {
                        item.getBtnJoin().setText("LOCKED");
                    }
                    return item;
                }

                if ("Pending".equalsIgnoreCase(attendance) || attendance == null) {
                    item.getBtnJoin().setEnabled(true);
                    item.getBtnJoin().setText("TIME IN");

                    setSingleActionListener(item.getBtnJoin(), e -> {
                        ConnectDatabase db = new ConnectDatabase();
                        int userId = UserSession.getInstance().getUserId();
                        if (db.recordTimeIn(userId, currentData.getEventID())) {
                            currentData.setUserAttendanceStatus("Present");

                            String currentTime = new SimpleDateFormat("hh:mm a").format(new Date());
                            currentData.setTimeIn(currentTime);

                            String sessionRole = UserSession.getInstance().getRole();
                            ModelEventStorage.loadFromDatabase(sessionRole, userId);
                            stopCellEditing();
                            table.clearSelection();
                            table.repaint();
                        }
                    });
                } else if ("Present".equalsIgnoreCase(attendance)) {
                    item.getBtnJoin().setEnabled(true);
                    item.getBtnJoin().setText("TIME OUT");

                    setSingleActionListener(item.getBtnJoin(), e -> {
                        ConnectDatabase db = new ConnectDatabase();
                        int userId = UserSession.getInstance().getUserId();

                        if (db.recordTimeOut(userId, currentData.getEventID())) {
                            currentData.setUserAttendanceStatus("Attended");

                            String currentTime = new SimpleDateFormat("hh:mm a").format(new Date());
                            currentData.setTimeOut(currentTime);

                            String sessionRole = UserSession.getInstance().getRole();
                            ModelEventStorage.loadFromDatabase(sessionRole, userId);
                            stopCellEditing();
                            table.clearSelection();
                            table.repaint();
                        }
                    });
                }
            }

            return item;
        }
        return new JLabel("Error");
    }

    private void setSingleActionListener(JButton btn, ActionListener listener) {
        for (ActionListener al : btn.getActionListeners()) {
            btn.removeActionListener(al);
        }
        btn.addActionListener(listener);
    }

    private void performJoin(JFrame frame, JTable table) {

        ConnectDatabase db = new ConnectDatabase();
        int userId = UserSession.getInstance().getUserId();

        if (db.registerForEvent(userId, currentData.getEventID())) {
            String sessionRole = UserSession.getInstance().getRole();
            ModelEventStorage.loadFromDatabase(sessionRole, userId);
            currentData.setJoined(true);

            JOptionPane.showMessageDialog(null, "Joined: " + currentData.getName());

            stopCellEditing();

            Container parent = table.getParent();
            while (parent != null && !(parent instanceof FormEvents)) {
                parent = parent.getParent();
            }

            if (parent instanceof FormEvents) {
                FormEvents parentForm = (FormEvents) parent;
                SwingUtilities.invokeLater(() -> {
                    parentForm.initTableData();
                });
            } else {
                table.repaint();
                table.revalidate();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed or already joined!");
            stopCellEditing();
        }
    }

    @Override
    public Object getCellEditorValue() {
        return currentData;
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }
}
