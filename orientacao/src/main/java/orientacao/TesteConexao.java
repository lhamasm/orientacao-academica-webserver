package orientacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TesteConexao {
	public static void main(String args[]) throws SQLException, ClassNotFoundException {
		Email email = new Email();
		email.notificarOrientacao("luis-modesto@outlook.com", "Thiaginho", "Bruno");
	}
}
