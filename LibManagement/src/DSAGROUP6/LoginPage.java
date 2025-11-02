package DSAGROUP6;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {


    private JTextField loginUsernameField, loginEmailField, regUsernameField, regEmailField;
    private JPasswordField loginPasswordField, regPasswordField, regConfirmPasswordField;
    private JButton loginButton, registerButton;

 
    private final Color darkBrown = new Color(101, 67, 33);
    private final Color lightBrown = new Color(205, 133, 63);
    private final Color beige = new Color(245, 222, 179);

    public LoginPage() {
        setTitle("Library Management System");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

       
        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        loginPanel.setBackground(beige);

        loginPanel.add(createLabel("Username:"));
        loginUsernameField = createTextField();
        loginPanel.add(loginUsernameField);

        loginPanel.add(createLabel("Email:"));
        loginEmailField = createTextField();
        loginPanel.add(loginEmailField);

        loginPanel.add(createLabel("Password:"));
        loginPasswordField = createPasswordField();
        loginPanel.add(loginPasswordField);

        loginButton = createButton("Login");
        loginPanel.add(new JLabel()); 
        loginPanel.add(loginButton);

       
        JPanel registerPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        registerPanel.setBorder(BorderFactory.createTitledBorder("Register"));
        registerPanel.setBackground(beige);

        registerPanel.add(createLabel("Username:"));
        regUsernameField = createTextField();
        registerPanel.add(regUsernameField);

        registerPanel.add(createLabel("Email:"));
        regEmailField = createTextField();
        registerPanel.add(regEmailField);

        registerPanel.add(createLabel("Password:"));
        regPasswordField = createPasswordField();
        registerPanel.add(regPasswordField);

        registerPanel.add(createLabel("Confirm Password:"));
        regConfirmPasswordField = createPasswordField();
        registerPanel.add(regConfirmPasswordField);

        registerButton = createButton("Register");
        registerPanel.add(new JLabel());
        registerPanel.add(registerButton);

    
        add(loginPanel);
        add(registerPanel);

        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());

        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(darkBrown);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(150, 25));
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setPreferredSize(new Dimension(150, 25));
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return field;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(lightBrown);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        return button;
    }

    private void handleLogin() {
        String username = loginUsernameField.getText();
        String email = loginEmailField.getText();
        String password = new String(loginPasswordField.getPassword());

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.");
            return;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Invalid email format.");
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Login successful!");
        dispose();
        new MainMenu(username);
    }

    private void handleRegister() {
        String username = regUsernameField.getText();
        String email = regEmailField.getText();
        String password = new String(regPasswordField.getPassword());
        String confirmPassword = new String(regConfirmPasswordField.getPassword());

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.");
            return;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Email must contain '@'.");
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Registration successful!");
        dispose();
        new MainMenu(username);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}







