package DSAGROUP6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BorrowedBooksPanel extends JPanel {

    private JTable borrowTable;
    private DefaultTableModel tableModel;

    public BorrowedBooksPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 222, 179));

       
        String[] columns = {
            "Order ID", "Book ID", "Username", "Book Title",
            "Checkout Date", "Return Date", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0);
        borrowTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(borrowTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("📚 Borrowed Books"));
        add(scrollPane, BorderLayout.CENTER);

      
        tableModel.addRow(new Object[]{
            "ORD1001", "B1005", "Brian", "Spider-Man Adventures",
            "2025-11-02", "2025-11-10", "Borrowed"
        });

        tableModel.addRow(new Object[]{
            "ORD1002", "B1002", "Anna", "The Great Gatsby",
            "2025-11-01", "2025-11-08", "Returned"
        });
    }

    
    public JTable getTable() {
        return borrowTable;
    }
}

