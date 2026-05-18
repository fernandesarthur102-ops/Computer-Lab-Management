import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {

    private Connection connection;

    public Conn() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/computerlab", "root", "root");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found. Include the driver in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed. Check your database URL, username, or password.");
            e.printStackTrace();
        }
    }

    // Returns the current connection
    public Connection getConnection() {
        return connection;
    }

    // Closes the connection
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed to close the connection.");
            e.printStackTrace();
        }
    }
}
