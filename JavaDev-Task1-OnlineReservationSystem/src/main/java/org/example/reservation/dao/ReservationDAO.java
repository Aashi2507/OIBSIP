package org.example.reservation.dao;

import org.example.reservation.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReservationDAO {

    public boolean saveReservation(
            String pnr,
            String passengerName,
            int trainNumber,
            String classType,
            String journeyDate,
            String sourceStation,
            String destinationStation) {

        String sql = "INSERT INTO reservations " +
                "(pnr, passenger_name, train_number, class_type, journey_date, " +
                "source_station, destination_station) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);
            statement.setString(2, passengerName);
            statement.setInt(3, trainNumber);
            statement.setString(4, classType);
            statement.setString(5, journeyDate);
            statement.setString(6, sourceStation);
            statement.setString(7, destinationStation);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}