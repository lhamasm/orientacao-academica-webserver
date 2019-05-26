package orientacao;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Professor extends Usuario {

	private Departamento departamento;
	
	public Professor(String nome, String sobrenome, String senha, String email, String matricula, String cpf, Departamento departamento) {
		super(nome, sobrenome, senha, email, matricula, cpf);
		this.departamento = departamento;
	}

	public Usuario efetuarCadastro(String nome, String sobrenome, String senha, String email, String matricula, String cpf, Departamento departamento) throws SQLException {
		Professor user = null;
		Connection connection = new DataGetter().getConnection();
		String sql = "INSERT INTO USUARIO VALUES ('" + matricula + "', '" + nome + "', '" + sobrenome + "', '" + email + "', '" + senha + "', '" + cpf + "')";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		if (stmt.execute()) {
			sql = "INSERT INTO PROFESSOR VALUES ('" + matricula + "', " + departamento.getCodigo() + ")";
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			if (stmt.execute()) {
				user = new Professor(nome, sobrenome, senha, email,  matricula, cpf, departamento);
			} else {
				sql = "DELETE FROM USUARIO WHERE matricula = '" + matricula + "'";
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.execute();
			}
		}
		stmt.close();
		connection.close();
		return user;
	}
}
