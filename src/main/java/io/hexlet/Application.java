package io.hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    // Нужно указывать базовое исключение,
    // потому что выполнение запросов может привести к исключениям
    public static void main(String[] args) throws SQLException {
        try (var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

            var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (var statement = conn.createStatement()) {
                statement.execute(sql);
            }

            var sql2 = "INSERT INTO users (username, phone) VALUES ('tommy', '123456789')";
            try (var statement2 = conn.createStatement()) {
                statement2.executeUpdate(sql2);
            }

            var sql2_1 = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (var preparedStatement = conn.prepareStatement(sql2_1)) {
                preparedStatement.setString(1, "Alex");
                preparedStatement.setString(2, "012-012-012");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "Noga");
                preparedStatement.setString(2, "012-012-012");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "Steve");
                preparedStatement.setString(2, "012-012-012");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "Mirmir");
                preparedStatement.setString(2, "012-012-012");
                preparedStatement.executeUpdate();
            }

            var sql2_2 = "DELETE FROM users WHERE username = ?";
            try (var preparedStatement = conn.prepareStatement(sql2_2)) {
                preparedStatement.setString(1, "Mirmir");
                preparedStatement.executeUpdate();
            }

            var sql3 = "SELECT * FROM users";
            try (var statement3 = conn.createStatement()) {
                var resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.printf("%s %s\n",
                            resultSet.getString("username"),
                            resultSet.getString("phone")
                    );
                }
            }
        }
    }
}