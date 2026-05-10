package com.tribyte.swing;

import com.tribyte.component.ItemEvent;
import com.tribyte.model.ModelEvents;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class EventCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ModelEvents) {
            ModelEvents data = (ModelEvents) value;
            ItemEvent item = new ItemEvent();
            item.setData(data);

            if (data.getStatus().equalsIgnoreCase("Closed") || data.getStatus().equalsIgnoreCase("Lock")) {
                item.getBtnJoin().setVisible(false);
            } 
            else if (data.isJoined()) {
                item.getBtnJoin().setVisible(true);
                item.getBtnJoin().setEnabled(false);
                item.getBtnJoin().setText("JOINED");

                item.getBtnJoin().setContentAreaFilled(false);
                item.getBtnJoin().setOpaque(false);
                item.getBtnJoin().setBackground(new Color(0, 0, 0, 0));

                item.getBtnJoin().setForeground(java.awt.Color.WHITE);
            }

            return item;
        }
        return new JLabel();
    }
}
