package DSAGROUP6;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BorrowPanel extends JPanel {

    private JPanel listPanel;
    private JButton checkoutButton;
    private LinkedList<String> borrowedTitles = new LinkedList<>();
    private String username = "Brian"; // Optional: can be set from MainMenu

    // Color palette
    private final Color beige = new Color(245, 222, 179);
    private final Color darkBrown = new Color(101, 67, 33);
    private final Color lightBrown = new Color(205, 133, 63);
    private final Color highlight = new Color(160, 82, 45);

    public BorrowPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(beige);
        setBorder(BorderFactory.createTitledBorder("📚 Books to Borrow"));
        setPreferredSize(new Dimension(300, 0)); // Wider panel

        // 📘 Book list panel
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(beige);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.getViewport().setBackground(beige);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // ✅ Proceed to Checkout button
        checkoutButton = new JButton("Proceed to Checkout");
        checkoutButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        checkoutButton.setBackground(highlight);
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFocusPainted(false);
        checkoutButton.setPreferredSize(new Dimension(250, 40));

        checkoutButton.addActionListener(e -> {
            if (borrowedTitles.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No books selected for checkout.");
            } else {
                new CheckoutPanel(username, new LinkedList<>(borrowedTitles));
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(beige);
        bottomPanel.add(checkoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void addBook(Book book) {
        String title = book.getTitle();
        if (borrowedTitles.contains(title)) {
            JOptionPane.showMessageDialog(this, "You've already added \"" + title + "\" to borrow list.");
            return;
        }

        borrowedTitles.add(title);

        JPanel bookRow = new JPanel(new BorderLayout(10, 5));
        bookRow.setBackground(beige);
        bookRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, lightBrown));

        JLabel titleLabel = new JLabel("📘 " + title);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        titleLabel.setForeground(darkBrown);

        JButton returnButton = new JButton("Return");
        returnButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        returnButton.setBackground(lightBrown);
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);
        returnButton.setPreferredSize(new Dimension(90, 30));

        returnButton.addActionListener(e -> {
            listPanel.remove(bookRow);
            borrowedTitles.remove(title);
            listPanel.revalidate();
            listPanel.repaint();
        });

        bookRow.add(titleLabel, BorderLayout.CENTER);
        bookRow.add(returnButton, BorderLayout.EAST);
        listPanel.add(bookRow);
        listPanel.revalidate();
        listPanel.repaint();
    }

    public LinkedList<String> getBorrowedTitles() {
        return new LinkedList<>(borrowedTitles);
    }

    public void setUsername(String username) {
        this.username = username;
    }
}



