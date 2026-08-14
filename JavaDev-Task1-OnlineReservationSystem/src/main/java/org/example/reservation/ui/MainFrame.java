package org.example.reservation.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JButton bookButton;
    private JButton cancelButton;
    private JButton logoutButton;

    public MainFrame() {

        setTitle("Train Reservation System");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Train Reservation System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(95, 40, 280, 35);

        JLabel welcomeLabel = new JLabel("Welcome to the Reservation System");
        welcomeLabel.setBounds(110, 85, 250, 25);

        bookButton = new JButton("Book Reservation");
        bookButton.setBounds(125, 130, 200, 35);

        cancelButton = new JButton("Cancel Reservation");
        cancelButton.setBounds(125, 180, 200, 35);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(165, 240, 120, 35);

        panel.add(titleLabel);
        panel.add(welcomeLabel);
        panel.add(bookButton);
        panel.add(cancelButton);
        panel.add(logoutButton);

        add(panel);

        bookButton.addActionListener(e -> openReservationFrame());

        cancelButton.addActionListener(e -> openCancellationFrame());

        logoutButton.addActionListener(e -> logout());
    }

    private void openReservationFrame() {

        ReservationFrame reservationFrame =
                new ReservationFrame();

        reservationFrame.setVisible(true);
    }

    private void openCancellationFrame() {

        CancellationFrame cancellationFrame =
                new CancellationFrame();

        cancellationFrame.setVisible(true);
    }

    private void logout() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);

            dispose();
        }
    }
}