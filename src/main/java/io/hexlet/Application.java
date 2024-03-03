package io.hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {

        var connection = DriverManager.getConnection("jdbc:h2:mem:hexlet_test");


        try (connection) {

            var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
            try (var statement = connection.createStatement()) {
                statement.execute(sql);
            }

            var sql2_1 = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (var preparedStatement = connection.prepareStatement(sql2_1)) {
                preparedStatement.setString(1, "Daniel");
                preparedStatement.setString(2, "012-012-012");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "Steve");
                preparedStatement.setString(2, "012-012-012");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "Mirmir");
                preparedStatement.setString(2, "012-012-012");
                preparedStatement.executeUpdate();
            }

            var sql3 = "SELECT * FROM users";
            try (var statement3 = connection.createStatement()) {
                var resultSet = statement3.executeQuery(sql3);
                while (resultSet.next()) {
                    System.out.printf("%s %s\n",
                            resultSet.getString("username"),
                            resultSet.getString("phone")
                    );
                }
            }

            var userDAO = new UserDAO(connection);
            var user = new User("Alex", "222-212-222");
            System.out.println("UserID Before added to DB: " + user.getId());
            userDAO.save(user);
            System.out.println("UserID After added to DB: " + user.getId());
            userDAO.delete(user);
            System.out.println("UserID After deleted from DB: " + user.getId());
        }
    }
}