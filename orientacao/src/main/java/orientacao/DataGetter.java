package orientacao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DataGetter {
	public Connection getConnection() throws ClassNotFoundException {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            return (Connection) DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/oa_db?autoReconnect=true&useSSL=false", "root", "wonhoeuteamo123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
