package DSAGROUP6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserManagementPanel extends JPanel {

    private JTable userTable;
    private DefaultTableModel tableModel;
    private int nextUserId = 1003;

    public UserManagementPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 222, 179));

        String[] columns = {"User ID", "Email", "Username", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("👥 Registered Users"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.setBackground(new Color(245, 222, 179));

        JTextField emailField = new JTextField(12);
        JTextField usernameField = new JTextField(10);
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"Borrowed", "Returned", "Active"});
        JButton addButton = new JButton("Add User");

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusBox);
        formPanel.add(addButton);

        add(formPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String username = usernameField.getText().trim();
            String status = (String) statusBox.getSelectedItem();

            if (!email.isEmpty() && !username.isEmpty()) {
                String userId = "U" + nextUserId++;
                tableModel.addRow(new Object[]{userId, email, username, status});
                emailField.setText("");
                usernameField.setText("");
                statusBox.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            }
        });

        tableModel.addRow(new Object[]{"U1001", "brian@example.com", "Brian", "Active"});
        tableModel.addRow(new Object[]{"U1002", "anna@example.com", "Anna", "Returned"});
    }
}
