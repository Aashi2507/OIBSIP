package org.example.reservation.ui;

import org.example.reservation.dao.UserDAO;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    public LoginFrame() {

        setTitle("Train Reservation System");
        setSize(400, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Train Reservation System");
        titleLabel.setBounds(100, 30, 220, 30);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 90, 80, 25);

        usernameField = new JTextField();
        usernameField.setBounds(150, 90, 180, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 130, 80, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 130, 180, 25);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 180, 100, 30);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(210, 180, 130, 30);

        panel.add(titleLabel);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(createAccountButton);

        add(panel);

        loginButton.addActionListener(e -> login());

        createAccountButton.addActionListener(e -> createAccount());
    }

    private void login() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password."
            );
            return;
        }

        UserDAO userDAO = new UserDAO();

        if (userDAO.login(username, password)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!"
            );

            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password."
            );
        }
    }

    private void createAccount() {

        JTextField newUsernameField = new JTextField();
        JPasswordField newPasswordField = new JPasswordField();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new java.awt.Dimension(300, 130));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 90, 25);

        newUsernameField.setBounds(110, 20, 170, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 60, 90, 25);

        newPasswordField.setBounds(110, 60, 170, 25);

        panel.add(usernameLabel);
        panel.add(newUsernameField);
        panel.add(passwordLabel);
        panel.add(newPasswordField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Create Account",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String username = newUsernameField.getText().trim();
        String password = new String(newPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password."
            );

            return;
        }

        UserDAO userDAO = new UserDAO();

        if (userDAO.usernameExists(username)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Username already exists."
            );

            return;
        }

        if (userDAO.createAccount(username, password)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account created successfully."
            );

            usernameField.setText(username);
            passwordField.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Account creation failed. Please try again."
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}