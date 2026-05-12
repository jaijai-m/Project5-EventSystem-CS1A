package com.tribyte.swing.table;

import com.tribyte.dashboard.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class Table extends JTable{
    
    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(value + "");
                if (i1 == 6) {
                    header.setHorizontalAlignment(JLabel.CENTER); 
                } else {
                    header.setHorizontalAlignment(JLabel.LEFT);  
                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                
                if (value instanceof ModelAction) {
                    ModelAction data = (ModelAction) value;
                    boolean isAttendanceList = (data.getEvents() == null);

                    Action action = new Action(data, !isAttendanceList, true);

                    if (isSelected) {
                        action.setBackground(new Color(240, 248, 240));
                    } else {
                        action.setBackground(Color.WHITE);
                    }
                    return action;
                } else {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    com.setForeground(new Color(102, 102, 102));
                    setBorder(noFocusBorder);
                    if (isSelected) {
                        com.setBackground(new Color(240, 248, 240));
                    } else {
                        com.setBackground(Color.WHITE);
                    }
                    return com;
                }
            }
        });
    }
    
    @Override
    public TableCellEditor getCellEditor(int row, int col) {
        // 1. Check if the specific column has a custom editor explicitly registered via setCellEditor()
        TableCellEditor columnEditor = getColumnModel().getColumn(col).getCellEditor();
        if (columnEditor != null) {
            return columnEditor; // Uses your FormEventAttendees ActionEditor instantly!
        }

        // 2. Fallback to your default global editor if no column-specific editor is found
        if (col == 4 || col == 5) {
            return new TableCellAction();
        }
        return super.getCellEditor(row, col);
    }
    
    public void addRow(Object[]row) {
        DefaultTableModel mod = (DefaultTableModel)getModel();
        mod.addRow(row);
    }
    
    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }
}
