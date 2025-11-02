package DSAGROUP6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReceiptHistoryPanel extends JPanel {

    private JTable receiptTable;
    private DefaultTableModel tableModel;

    public ReceiptHistoryPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 222, 179));

        
        String[] columns = {
            "Order ID", "Username", "Checkout Time", "Return Date",
            "Total Price", "Cash Paid", "Change Returned"
        };

        tableModel = new DefaultTableModel(columns, 0);
        receiptTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(receiptTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("🧾 Receipt History"));
        add(scrollPane, BorderLayout.CENTER);

        
        tableModel.addRow(new Object[]{
            "1102202521226", "Brian", "2025-11-02 21:22", "2025-11-10",
            "₱200.00", "₱500.00", "₱300.00"
        });

        tableModel.addRow(new Object[]{
            "1101202520155", "Anna", "2025-11-01 20:15", "2025-11-08",
            "₱125.00", "₱200.00", "₱75.00"
        });
    }
}
