package orientacao;

import orientacao.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import orientacao.Orientacao;
import orientacao.Departamento;

public class Professor extends Usuario {
	
	private Departamento departamento;

	public Professor(String nome, String sobrenome, String senha, String email, String matricula, String cpf,
			Departamento departamento) {
		super(nome, sobrenome, senha, email, matricula, cpf);
		this.departamento = departamento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public Usuario efetuarCadastro(Professor professor) throws SQLException, ClassNotFoundException {
		Professor user = null;
		Connection connection = new DataGetter().getConnection();
		String sql = "INSERT INTO USUARIO VALUES ('" + professor.getMatricula()+ "', '" + professor.getNome() + "', '" + professor.getSobrenome() + "', '" + professor.getEmail() + "', '" + professor.getSenha() + "', '" + professor.getCpf() + "')";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		if (!stmt.execute()) {
			sql = "INSERT INTO PROFESSOR VALUES ('" + professor.getMatricula() + "', " + professor.getDepartamento().getCodigo() + ")";
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			if (!stmt.execute()) {
				user = professor;
			} else {
				sql = "DELETE FROM USUARIO WHERE matricula = '" + professor.getMatricula() + "'";
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.execute();
			}
		}
		stmt.close();
		connection.close();
		return user;
	}
}
