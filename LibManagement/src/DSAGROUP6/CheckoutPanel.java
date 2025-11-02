package DSAGROUP6;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class CheckoutPanel extends JFrame {

    private final Color beige = new Color(245, 222, 179);
    private final Color darkBrown = new Color(101, 67, 33);
    private final Color lightBrown = new Color(205, 133, 63);

    private JTextField cashField;
    private JSpinner dateSpinner;
    private JLabel totalLabel;
    private double totalPrice;
    private LinkedList<String> borrowedTitles;

    public CheckoutPanel(String username, LinkedList<String> borrowedTitles) {
        this.borrowedTitles = borrowedTitles;

        setTitle("Checkout Summary");
        setSize(500, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(beige);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(beige);
        add(mainPanel, BorderLayout.CENTER);

        
        JLabel header = new JLabel("📋 Checkout Summary", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 18));
        header.setForeground(darkBrown);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(header);

        
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String title : borrowedTitles) {
            model.addElement("📘 " + title);
        }

        JList<String> bookList = new JList<>(model);
        bookList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        bookList.setBackground(Color.WHITE);
        bookList.setForeground(darkBrown);
        JScrollPane scrollPane = new JScrollPane(bookList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Books to Borrow"));
        scrollPane.setPreferredSize(new Dimension(450, 200));
        mainPanel.add(scrollPane);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(beige);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        inputPanel.add(new JLabel("📅 Return Date:", SwingConstants.RIGHT));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        inputPanel.add(dateSpinner);

        inputPanel.add(new JLabel("💰 Total Price:", SwingConstants.RIGHT));
        totalLabel = new JLabel();
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        totalLabel.setForeground(darkBrown);
        inputPanel.add(totalLabel);

        inputPanel.add(new JLabel("💵 Cash Payment:", SwingConstants.RIGHT));
        cashField = new JTextField();
        inputPanel.add(cashField);

        mainPanel.add(inputPanel);

        dateSpinner.addChangeListener(e -> updateTotalPrice());
        updateTotalPrice(); 


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(beige);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(lightBrown);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("SansSerif", Font.BOLD, 13));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 13));

        confirmButton.addActionListener(e -> showReceipt(username));
        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel);

        setVisible(true);
    }

    private void updateTotalPrice() {
        LocalDateTime now = LocalDateTime.now();
        java.util.Date selectedDate = (java.util.Date) dateSpinner.getValue();
        LocalDateTime returnDate = LocalDateTime.ofInstant(selectedDate.toInstant(), java.time.ZoneId.systemDefault());

        long days = java.time.Duration.between(now.toLocalDate().atStartOfDay(), returnDate.toLocalDate().atStartOfDay()).toDays();
        days = Math.max(days, 0); 

        double basePrice = borrowedTitles.size() * 50;
        double extra = borrowedTitles.size() * 25 * days;
        totalPrice = basePrice + extra;

        totalLabel.setText("₱" + totalPrice);
    }

    private void showReceipt(String username) {
        String cashText = cashField.getText().trim();
        double cashPaid;

        try {
            cashPaid = Double.parseDouble(cashText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid cash amount.");
            return;
        }

        if (cashPaid < totalPrice) {
            JOptionPane.showMessageDialog(this, "Insufficient payment.");
            return;
        }

        double change = cashPaid - totalPrice;
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String orderId = now.format(DateTimeFormatter.ofPattern("MMddyyyyHHmmss")) + username.length();
        String returnDate = new SimpleDateFormat("yyyy-MM-dd").format(dateSpinner.getValue());

      
        JFrame receiptFrame = new JFrame("Receipt");
        receiptFrame.setSize(400, 400);
        receiptFrame.setLocationRelativeTo(this);
        receiptFrame.setLayout(new BorderLayout());
        receiptFrame.getContentPane().setBackground(beige);

        JTextArea receipt = new JTextArea();
        receipt.setFont(new Font("Monospaced", Font.PLAIN, 13));
        receipt.setEditable(false);
        receipt.setBackground(Color.WHITE);
        receipt.setForeground(darkBrown);

        receipt.append("🧾 Receipt\n");
        receipt.append("------------------------------\n");
        receipt.append("Order ID: " + orderId + "\n");
        receipt.append("Username: " + username + "\n");
        receipt.append("Checkout Time: " + timestamp + "\n");
        receipt.append("Return Date: " + returnDate + "\n");
        receipt.append("Total Price: ₱" + totalPrice + "\n");
        receipt.append("Cash Paid: ₱" + cashPaid + "\n");
        receipt.append("Change Returned: ₱" + String.format("%.2f", change) + "\n");
        receipt.append("------------------------------\n");
        receipt.append("✅ Thank you!\n");

        receiptFrame.add(new JScrollPane(receipt), BorderLayout.CENTER);
        receiptFrame.setVisible(true);
        dispose();
    }
}



