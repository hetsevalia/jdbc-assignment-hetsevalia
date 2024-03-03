import java.sql.*;

public class MySQLDatabase {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_database";
    private static final String USERNAME = "ROOT";
    private static final String PASSWORD = "99099";

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Insert data into the students table
            insertData(connection);

            // Retrieve and print all records from the students table
            retrieveData(connection);

            // Update the age of one student
            updateData(connection);

            // Delete one student from the table
            deleteData(connection);

            // Close the connection
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            // Inserting additional record
            preparedStatement.setString(1, "Bob");
            preparedStatement.setInt(2, 25);
            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully.");
        }
    }

    private static void retrieveData(Connection connection) throws SQLException {
        String selectQuery = "SELECT * FROM students";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            System.out.println("Retrieving data from students table:");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("Name: " + name + ", Age: " + age);
            }
        }
    }

    private static void updateData(Connection connection) throws SQLException {
        String updateQuery = "UPDATE students SET age = ? WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            // Updating age of student named "John"
            preparedStatement.setInt(1, 21);
            preparedStatement.setString(2, "John");
            preparedStatement.executeUpdate();

            System.out.println("Data updated successfully.");
        }
    }

    private static void deleteData(Connection connection) throws SQLException {
        String deleteQuery = "DELETE FROM students WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            // Deleting student named "Alice"
            preparedStatement.setString(1, "Alice");
            preparedStatement.executeUpdate();

            System.out.println("Data deleted successfully.");
        }
    }
}
