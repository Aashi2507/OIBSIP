package org.example.reservation.dao;

import org.example.reservation.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrainDAO {

    public String getTrainName(int trainNumber) {

        String sql = "SELECT train_name FROM trains WHERE train_number = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, trainNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("train_name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}