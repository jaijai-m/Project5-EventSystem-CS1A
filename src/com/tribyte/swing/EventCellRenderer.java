package com.tribyte.swing;

import com.tribyte.component.ItemEvent;
import com.tribyte.model.ModelEvents;
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
            return item;
        }
        return new JLabel();
    }
}
