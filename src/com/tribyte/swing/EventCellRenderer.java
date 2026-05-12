package com.tribyte.swing;

import com.tribyte.component.ItemEvent;
import com.tribyte.connection.UserSession;
import com.tribyte.model.ModelEvents;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class EventCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ModelEvents) {
            ModelEvents currentData = (ModelEvents) value;
            ItemEvent item = new ItemEvent();
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
                }
            } 
            else {
                item.getBtnJoin().setVisible(true);

                if (currentData.getStatus().equalsIgnoreCase("Lock") || currentData.getStatus().equalsIgnoreCase("Closed")) {
                    item.getBtnJoin().setEnabled(false);
                    if ("Present".equalsIgnoreCase(attendance)) {
                        item.getBtnJoin().setText("AUTO-ATTENDED");
                    } else {
                        item.getBtnJoin().setText("LOCKED");
                    }
                    return item;
                }

                if ("Pending".equalsIgnoreCase(attendance) || attendance == null) {
                    item.getBtnJoin().setEnabled(true);
                    item.getBtnJoin().setText("TIME IN");
                } else if ("Present".equalsIgnoreCase(attendance)) {
                    item.getBtnJoin().setEnabled(true);
                    item.getBtnJoin().setText("TIME OUT");
                }
            }
            return item;
        }
        return new JLabel("Error");
    }
}
