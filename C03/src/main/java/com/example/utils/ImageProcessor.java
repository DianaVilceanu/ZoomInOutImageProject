package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ImageProcessor {
    public void saveImageToDatabase(String imageId, byte[] imageBytes) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://C06_HOST:3306/imagesDB", "user", "password")) {
            String sql = "INSERT INTO images (image_id, image_data) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, imageId);
            statement.setBytes(2, imageBytes);
            statement.executeUpdate();
            System.out.println("Image saved to database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
