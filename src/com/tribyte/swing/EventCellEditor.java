package com.tribyte.swing;

import com.tribyte.component.ItemEvent;
import com.tribyte.connection.ConnectDatabase;
import com.tribyte.connection.UserSession;
import com.tribyte.dialog.MessageEventCode;
import com.tribyte.model.ModelEventStorage;
import com.tribyte.model.ModelEvents;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
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

            if (currentData.getStatus().equalsIgnoreCase("Closed") || currentData.getStatus().equalsIgnoreCase("Lock")) {
                item.getBtnJoin().setVisible(false);
                item.getBtnJoin().setEnabled(false);
            } 
            else if (currentData.isUserJoined(UserSession.getInstance().getUserId())) {
                item.getBtnJoin().setVisible(true);
                item.getBtnJoin().setEnabled(false);
                item.getBtnJoin().setText("JOINED");
            } 
            else {
                item.getBtnJoin().setVisible(true);
                item.getBtnJoin().setEnabled(true);
                item.getBtnJoin().setText("JOIN");

                for (ActionListener al : item.getBtnJoin().getActionListeners()) {
                    item.getBtnJoin().removeActionListener(al);
                }

                item.getBtnJoin().addActionListener(e -> {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(table);

                    if (currentData.getAccessibility().equalsIgnoreCase("Private")) {
                        MessageEventCode msg = new MessageEventCode(frame);
                        msg.showMessage("Private Event", "This event requires a code!", "Verify");

                        if (msg.getMessageType() == MessageEventCode.MessageType.CONFIRM) {
                            if (msg.getEnteredCode().equals(currentData.getEventCode())) {
                                performJoin(frame, table); // Call the join logic
                            } else {
                                JOptionPane.showMessageDialog(frame, "Wrong Code!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        // Public Event
                        performJoin(frame, table);
                    }
                });
            }

            return item;
        }
        return new JLabel("Error");
    }
    
    private void performJoin(JFrame frame, JTable table) {
        ConnectDatabase db = new ConnectDatabase();
        int userId = UserSession.getInstance().getUserId();

        if (db.registerForEvent(userId, currentData.getEventID())) {

            ModelEventStorage.loadFromDatabase();

            currentData.setJoined(true);

            JOptionPane.showMessageDialog(null, "Joined: " + currentData.getName());

            stopCellEditing();

            table.repaint();
            table.revalidate();

        } else {
            JOptionPane.showMessageDialog(null, "Registration failed or already joined!");
            stopCellEditing();
        }
    }

    @Override
    public Object getCellEditorValue() {
        return currentData;
    }
}
