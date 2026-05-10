package com.tribyte.swing.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCellAction extends DefaultCellEditor{
    private ModelAction data;
    
    public TableCellAction() {
        super (new JCheckBox());
        setClickCountToStart(1);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof ModelAction) {
            this.data = (ModelAction) value;

            boolean isAttendanceList = (data.getEvents() == null);
            Action cell = new Action(data, !isAttendanceList, true);
            cell.setBackground(new Color(240, 248, 240));

            return cell;
        }
        return null;
    }

    @Override
    public Object getCellEditorValue() {
        return data;
    }
}
