package org.example.reservation.dao;

import org.example.reservation.db.DBConnection;
import org.example.reservation.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CancellationDAO {

    public Reservation getReservation(String pnr) {

        String sql = "SELECT r.pnr, r.passenger_name, r.train_number, " +
                "t.train_name, r.class_type, r.journey_date, " +
                "r.source_station, r.destination_station " +
                "FROM reservations r " +
                "JOIN trains t ON r.train_number = t.train_number " +
                "WHERE r.pnr = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Reservation(
                        resultSet.getString("pnr"),
                        resultSet.getString("passenger_name"),
                        resultSet.getInt("train_number"),
                        resultSet.getString("train_name"),
                        resultSet.getString("class_type"),
                        resultSet.getString("journey_date"),
                        resultSet.getString("source_station"),
                        resultSet.getString("destination_station")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean cancelReservation(String pnr) {

        String sql = "DELETE FROM reservations WHERE pnr = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pnr);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}