package DSAGROUP6;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private JPanel contentPanel;
    private SortPanel sortPanel; 

    public AdminDashboard() {
        setTitle("📊 Admin Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(101, 67, 33));
        sidebar.setPreferredSize(new Dimension(200, 0));

        String[] buttons = {
            "User Management",
            "Borrowed Books",
            "Receipt History",
            "Book Inventory",
        };

        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(180, 40));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(160, 82, 45));
            btn.setFont(new Font("SansSerif", Font.BOLD, 14));
            btn.setFocusPainted(false);
            btn.addActionListener(e -> switchPanel(label));
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebar.add(btn);
        }

     
        BorrowedBooksPanel borrowedPanel = new BorrowedBooksPanel();
        sortPanel = new SortPanel(borrowedPanel.getTable()); 
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(sortPanel);

        sidebar.add(Box.createVerticalGlue());
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signOutButton.setMaximumSize(new Dimension(180, 40));
        signOutButton.setForeground(Color.WHITE);
        signOutButton.setBackground(Color.RED);
        signOutButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        signOutButton.setFocusPainted(false);
        signOutButton.addActionListener(e -> signOut());
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(signOutButton);

        add(sidebar, BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        contentPanel.add(borrowedPanel); 
        setVisible(true);
    }

    private void switchPanel(String name) {
        contentPanel.removeAll();
        try {
            switch (name) {
                case "User Management" -> contentPanel.add(new UserManagementPanel());
                case "Borrowed Books" -> {
                    BorrowedBooksPanel panel = new BorrowedBooksPanel();
                    sortPanel.setTargetTable(panel.getTable()); 
                    contentPanel.add(panel);
                }
                case "Receipt History" -> contentPanel.add(new ReceiptHistoryPanel());
                case "Book Inventory" -> contentPanel.add(new BookInventoryPanel());
                default -> contentPanel.add(new JLabel("Panel not found"));
            }
        } catch (Exception e) {
            contentPanel.add(new JLabel("Error loading panel: " + e.getMessage()));
            e.printStackTrace();
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void signOut() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to sign out?", "Confirm Sign Out", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminDashboard::new);
    }
}


