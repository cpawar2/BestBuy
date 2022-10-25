import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {

    static Connection getConnection()
    {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BEST_BUY_DB", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
