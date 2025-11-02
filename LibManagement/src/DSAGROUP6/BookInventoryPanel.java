package DSAGROUP6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookInventoryPanel extends JPanel {

    private JTable bookTable;
    private DefaultTableModel tableModel;
    private int nextBookId = 1003;

    public BookInventoryPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 222, 179));

        
        String[] columns = {"Book ID", "Title", "Author", "Genre", "Quantity", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("📚 Book Inventory"));
        scrollPane.setPreferredSize(new Dimension(800, 300));
        add(scrollPane, BorderLayout.CENTER);

      
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 222, 179));
        formPanel.setBorder(BorderFactory.createTitledBorder("➕ Add New Book"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField titleField = new JTextField(12);
        JTextField authorField = new JTextField(12);
        JTextField genreField = new JTextField(12);
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"Available", "Borrowed"});
        JButton addButton = new JButton("Add Book");

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        formPanel.add(titleField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1;
        formPanel.add(authorField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Genre:"), gbc);
        gbc.gridx = 1;
        formPanel.add(genreField, gbc);

      
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        formPanel.add(quantitySpinner, gbc);

      
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        formPanel.add(statusBox, gbc);

        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(addButton, gbc);

        add(formPanel, BorderLayout.SOUTH);

      
        addButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String genre = genreField.getText().trim();
            int quantity = (int) quantitySpinner.getValue();
            String status = (String) statusBox.getSelectedItem();

            if (!title.isEmpty() && !author.isEmpty() && !genre.isEmpty()) {
                String bookId = "B" + nextBookId++;
                tableModel.addRow(new Object[]{bookId, title, author, genre, quantity, status});
                titleField.setText("");
                authorField.setText("");
                genreField.setText("");
                quantitySpinner.setValue(1);
                statusBox.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });

       //SAMPLE DATA
        tableModel.addRow(new Object[]{"B1001", "The Hobbit", "J.R.R. Tolkien", "Fantasy", 5, "Available"});
        tableModel.addRow(new Object[]{"B1002", "1984", "George Orwell", "Dystopian", 3, "Borrowed"});
    }
}

