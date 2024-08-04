import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection;


    private DatabaseConnection() {

    }

    public static Connection getConnection() throws SQLException {
        Properties dbProperties = PropertyFileReader.getProperties("src/main/resources/database.properties");
        if (connection == null || connection.isClosed()) {
            try {
                String url = dbProperties.getProperty("db.url");
                String user = dbProperties.getProperty("db.user");
                String password = dbProperties.getProperty("db.password");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException("Error connecting to the database", e);
            }
        }
        System.out.println("Connection is open");
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
            System.out.println("Connection is close");
        }
        System.out.println("Connection is close");
    }
}
