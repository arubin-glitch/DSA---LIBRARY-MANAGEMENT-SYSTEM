package DSAGROUP6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SortPanel extends JPanel {

    private JTable targetTable;

    public SortPanel(JTable tableToSort) {
        this.targetTable = tableToSort;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBackground(new Color(160, 82, 45));
        setBorder(BorderFactory.createTitledBorder("V Sort By"));

        String[] labels = {
            "Order ID", "Username", "User ID",
            "Book ID", "Date Borrowed", "Date Returned"
        };

        for (String label : labels) {
            JButton btn = new JButton(label);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(101, 67, 33));
            btn.setFont(new Font("SansSerif", Font.BOLD, 12));
            btn.setFocusPainted(false);
            btn.addActionListener(e -> sortBy(label));
            add(btn);
        }
    }

    private void sortBy(String criteria) {
        if (targetTable == null || targetTable.getRowCount() <= 1) {
            JOptionPane.showMessageDialog(this, "No data to sort.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) targetTable.getModel();
        int columnIndex = getColumnIndex(criteria, model);

        if (columnIndex == -1) {
            JOptionPane.showMessageDialog(this, "Column not found: " + criteria);
            return;
        }

        int rowCount = model.getRowCount();

        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = 0; j < rowCount - i - 1; j++) {
                Object val1 = model.getValueAt(j, columnIndex);
                Object val2 = model.getValueAt(j + 1, columnIndex);

                if (val1 != null && val2 != null && val1.toString().compareToIgnoreCase(val2.toString()) > 0) {
                    Object[] row1 = getRow(model, j);
                    Object[] row2 = getRow(model, j + 1);
                    setRow(model, j, row2);
                    setRow(model, j + 1, row1);
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Sorted by: " + criteria);
    }

    private int getColumnIndex(String criteria, DefaultTableModel model) {
        for (int i = 0; i < model.getColumnCount(); i++) {
            String col = model.getColumnName(i).toLowerCase();
            if (col.contains(criteria.toLowerCase().replace(" ", ""))) {
                return i;
            }
        }
        return -1;
    }

    private Object[] getRow(DefaultTableModel model, int rowIndex) {
        int cols = model.getColumnCount();
        Object[] row = new Object[cols];
        for (int i = 0; i < cols; i++) {
            row[i] = model.getValueAt(rowIndex, i);
        }
        return row;
    }

    private void setRow(DefaultTableModel model, int rowIndex, Object[] rowData) {
        for (int i = 0; i < rowData.length; i++) {
            model.setValueAt(rowData[i], rowIndex, i);
        }
    }

	public void setTargetTable(JTable table) {
		// TODO Auto-generated method stub
		
	}
}
