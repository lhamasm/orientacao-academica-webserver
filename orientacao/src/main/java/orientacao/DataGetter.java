package orientacao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DataGetter {
	public Connection getConnection() {
        try {
            return (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost/oa_db", "root", "modesto");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
