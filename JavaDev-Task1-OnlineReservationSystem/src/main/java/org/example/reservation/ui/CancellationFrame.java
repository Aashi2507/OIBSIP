package org.example.reservation.ui;

import org.example.reservation.dao.CancellationDAO;
import org.example.reservation.model.Reservation;

import javax.swing.*;
import java.awt.*;

public class CancellationFrame extends JFrame {

    private JTextField pnrField;
    private JTextArea detailsArea;
    private JButton fetchButton;
    private JButton cancelButton;

    private CancellationDAO cancellationDAO;

    private String currentPNR;

    public CancellationFrame() {

        cancellationDAO = new CancellationDAO();

        setTitle("Cancel Reservation");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Cancel Reservation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(160, 20, 220, 30);

        JLabel pnrLabel = new JLabel("Enter PNR:");
        pnrLabel.setBounds(50, 80, 100, 25);

        pnrField = new JTextField();
        pnrField.setBounds(150, 80, 180, 25);

        fetchButton = new JButton("Fetch");
        fetchButton.setBounds(340, 80, 80, 25);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBounds(50, 130, 370, 180);

        cancelButton = new JButton("Confirm Cancellation");
        cancelButton.setBounds(150, 340, 190, 35);
        cancelButton.setEnabled(false);

        panel.add(titleLabel);
        panel.add(pnrLabel);
        panel.add(pnrField);
        panel.add(fetchButton);
        panel.add(scrollPane);
        panel.add(cancelButton);

        add(panel);

        fetchButton.addActionListener(e -> fetchReservation());

        cancelButton.addActionListener(e -> confirmCancellation());
    }

    private void fetchReservation() {

        String pnr = pnrField.getText().trim();

        if (pnr.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a PNR number."
            );

            return;
        }

        Reservation reservation =
                cancellationDAO.getReservation(pnr);

        if (reservation == null) {

            detailsArea.setText("");
            cancelButton.setEnabled(false);
            currentPNR = null;

            JOptionPane.showMessageDialog(
                    this,
                    "No reservation found for this PNR."
            );

            return;
        }

        currentPNR = reservation.getPnr();

        detailsArea.setText(
                "PNR: " + reservation.getPnr() + "\n\n"
                        + "Passenger Name: " + reservation.getPassengerName() + "\n"
                        + "Train Number: " + reservation.getTrainNumber() + "\n"
                        + "Train Name: " + reservation.getTrainName() + "\n"
                        + "Class: " + reservation.getClassType() + "\n"
                        + "Journey Date: " + reservation.getJourneyDate() + "\n"
                        + "Source: " + reservation.getSourceStation() + "\n"
                        + "Destination: " + reservation.getDestinationStation()
        );

        cancelButton.setEnabled(true);
    }

    private void confirmCancellation() {

        if (currentPNR == null) {
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to cancel this reservation?",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            boolean cancelled =
                    cancellationDAO.cancelReservation(currentPNR);

            if (cancelled) {

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation cancelled successfully."
                );

                detailsArea.setText("");
                pnrField.setText("");
                cancelButton.setEnabled(false);
                currentPNR = null;

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Cancellation failed. Please try again."
                );
            }
        }
    }
}