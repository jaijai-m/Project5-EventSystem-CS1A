package com.tribyte.swing;

import com.tribyte.component.ItemEvent;
import com.tribyte.dialog.MessageEventCode;
import com.tribyte.model.ModelEvents;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

            if (currentData.getStatus().equalsIgnoreCase("Closed")) {
                item.getBtnJoin().setVisible(false);
                item.getBtnJoin().setEnabled(false);
            } else {
                item.getBtnJoin().addActionListener(e -> {
                    if (currentData.getAccessibility().equalsIgnoreCase("Private")) {
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(item);
                        MessageEventCode msg = new MessageEventCode(frame);
                        msg.showMessage("Locked Event", "This event requires a code!", "...");

                        if (msg.getMessageType() == MessageEventCode.MessageType.CONFIRM) {
                            String typedCode = msg.getEnteredCode();
                            String actualCode = String.valueOf(currentData.getEventCode());

                            if (typedCode.equals(actualCode)) {
                                System.out.println("Access Granted!");
                            } else {
                                System.out.println("Wrong Code! User typed: " + typedCode + " but expected: " + actualCode);
                            }
                        }
                    } else {
                        System.out.println("Joining Public Event...");
                    }
                    stopCellEditing();
                });
            } 

            return item;
        }
        return new JLabel("Error");
    }

    @Override
    public Object getCellEditorValue() {
        return currentData;
    }
}
