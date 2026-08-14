package org.example.reservation.ui;

import org.example.reservation.dao.ReservationDAO;
import org.example.reservation.dao.TrainDAO;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;

public class ReservationFrame extends JFrame {

    private JTextField passengerNameField;
    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JComboBox<String> classTypeBox;
    private JTextField journeyDateField;
    private JTextField sourceStationField;
    private JTextField destinationStationField;

    private JButton bookButton;
    private JButton cancelButton;
    private JButton backButton;

    private TrainDAO trainDAO;
    private ReservationDAO reservationDAO;

    public ReservationFrame() {

        trainDAO = new TrainDAO();
        reservationDAO = new ReservationDAO();

        setTitle("Book Train Reservation");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Train Reservation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(160, 20, 220, 30);

        JLabel passengerNameLabel = new JLabel("Passenger Name:");
        passengerNameLabel.setBounds(50, 80, 120, 25);

        passengerNameField = new JTextField();
        passengerNameField.setBounds(180, 80, 240, 25);

        JLabel trainNumberLabel = new JLabel("Train Number:");
        trainNumberLabel.setBounds(50, 120, 120, 25);

        trainNumberField = new JTextField();
        trainNumberField.setBounds(180, 120, 240, 25);

        JLabel trainNameLabel = new JLabel("Train Name:");
        trainNameLabel.setBounds(50, 160, 120, 25);

        trainNameField = new JTextField();
        trainNameField.setBounds(180, 160, 240, 25);
        trainNameField.setEditable(false);

        JLabel classTypeLabel = new JLabel("Class Type:");
        classTypeLabel.setBounds(50, 200, 120, 25);

        String[] classTypes = {
                "AC First Class",
                "AC 2 Tier",
                "AC 3 Tier",
                "Sleeper",
                "Second Sitting"
        };

        classTypeBox = new JComboBox<>(classTypes);
        classTypeBox.setBounds(180, 200, 240, 25);

        JLabel journeyDateLabel = new JLabel("Journey Date:");
        journeyDateLabel.setBounds(50, 240, 120, 25);

        journeyDateField = new JTextField();
        journeyDateField.setBounds(180, 240, 240, 25);

        JLabel sourceStationLabel = new JLabel("Source Station:");
        sourceStationLabel.setBounds(50, 280, 120, 25);

        sourceStationField = new JTextField();
        sourceStationField.setBounds(180, 280, 240, 25);

        JLabel destinationStationLabel = new JLabel("Destination:");
        destinationStationLabel.setBounds(50, 320, 120, 25);

        destinationStationField = new JTextField();
        destinationStationField.setBounds(180, 320, 240, 25);

        bookButton = new JButton("Book Ticket");
        bookButton.setBounds(70, 380, 120, 35);

        cancelButton = new JButton("Cancel Reservation");
        cancelButton.setBounds(195, 380, 160, 35);

        backButton = new JButton("Back");
        backButton.setBounds(360, 380, 80, 35);

        panel.add(titleLabel);

        panel.add(passengerNameLabel);
        panel.add(passengerNameField);

        panel.add(trainNumberLabel);
        panel.add(trainNumberField);

        panel.add(trainNameLabel);
        panel.add(trainNameField);

        panel.add(classTypeLabel);
        panel.add(classTypeBox);

        panel.add(journeyDateLabel);
        panel.add(journeyDateField);

        panel.add(sourceStationLabel);
        panel.add(sourceStationField);

        panel.add(destinationStationLabel);
        panel.add(destinationStationField);

        panel.add(bookButton);
        panel.add(cancelButton);
        panel.add(backButton);

        add(panel);

        trainNumberField.addActionListener(e -> findTrain());

        bookButton.addActionListener(e -> bookReservation());

        cancelButton.addActionListener(e -> openCancellationFrame());

        backButton.addActionListener(e -> dispose());
    }

    private void findTrain() {

        String trainNumberText = trainNumberField.getText().trim();

        if (trainNumberText.isEmpty()) {
            trainNameField.setText("");
            return;
        }

        try {

            int trainNumber = Integer.parseInt(trainNumberText);

            String trainName = trainDAO.getTrainName(trainNumber);

            if (trainName != null) {

                trainNameField.setText(trainName);

            } else {

                trainNameField.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "Train number not found."
                );
            }

        } catch (NumberFormatException e) {

            trainNameField.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    "Train number must be numeric."
            );
        }
    }

    private void bookReservation() {

        String passengerName = passengerNameField.getText().trim();
        String trainNumberText = trainNumberField.getText().trim();
        String classType = (String) classTypeBox.getSelectedItem();
        String journeyDate = journeyDateField.getText().trim();
        String sourceStation = sourceStationField.getText().trim();
        String destinationStation = destinationStationField.getText().trim();

        if (passengerName.isEmpty()
                || trainNumberText.isEmpty()
                || journeyDate.isEmpty()
                || sourceStation.isEmpty()
                || destinationStation.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all required fields."
            );

            return;
        }

        int trainNumber;

        try {

            trainNumber = Integer.parseInt(trainNumberText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Train number must be numeric."
            );

            return;
        }

        String trainName = trainDAO.getTrainName(trainNumber);

        if (trainName == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Train number not found."
            );

            return;
        }

        LocalDate selectedDate;

        try {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            selectedDate = LocalDate.parse(journeyDate, formatter);

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date.\nPlease use yyyy-MM-dd format."
            );

            return;
        }

        if (selectedDate.isBefore(LocalDate.now())) {

            JOptionPane.showMessageDialog(
                    this,
                    "Journey date cannot be in the past."
            );

            return;
        }

        String pnr = generatePNR();

        boolean saved = reservationDAO.saveReservation(
                pnr,
                passengerName,
                trainNumber,
                classType,
                journeyDate,
                sourceStation,
                destinationStation
        );

        if (saved) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking successful!\n\n"
                            + "PNR: " + pnr + "\n"
                            + "Passenger: " + passengerName + "\n"
                            + "Train: " + trainName + "\n"
                            + "Train Number: " + trainNumber + "\n"
                            + "Class: " + classType + "\n"
                            + "Journey Date: " + journeyDate + "\n"
                            + "From: " + sourceStation + "\n"
                            + "To: " + destinationStation,
                    "Reservation Confirmed",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking failed. Please try again."
            );
        }
    }

    private void openCancellationFrame() {

        CancellationFrame cancellationFrame =
                new CancellationFrame();

        cancellationFrame.setVisible(true);
    }

    private String generatePNR() {

        Random random = new Random();

        long number = 10000000L + random.nextInt(90000000);

        return String.valueOf(number);
    }

    private void clearFields() {

        passengerNameField.setText("");
        trainNumberField.setText("");
        trainNameField.setText("");
        journeyDateField.setText("");
        sourceStationField.setText("");
        destinationStationField.setText("");

        classTypeBox.setSelectedIndex(0);
    }
}