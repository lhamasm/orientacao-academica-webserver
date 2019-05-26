package orientacao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Aluno extends Usuario {
	private Curso curso;
	private int semestre;
	
	public Aluno(String nome, String sobrenome, String senha, String email, String matricula, String cpf, int semestre, Curso curso) {
		super(nome, sobrenome, senha, email, matricula, cpf);
		this.curso = curso;
		this.semestre = semestre;
	}
	
	public ArrayList<Professor> recuperarProfessores() throws SQLException{
		ArrayList<Professor> professores = new ArrayList<Professor>();
		Connection connection = new DataGetter().getConnection();
		String sql = "SELECT USUARIO.* , DEPARTAMENTO.codigo, DEPARTAMENTO.nome AS nomedep FROM USUARIO, PROFESSOR, CURSO, DEPARTAMENTO WHERE CURSO.codigo = " + this.curso.getCodigo() + " AND CURSO.departamento = DEPARTAMENTO.codigo AND DEPARTAMENTO.codigo = PROFESSOR.departamento AND  USUARIO.matricula = PROFESSOR.matricula";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
			professores.add(new Professor(rs.getString("nome"), rs.getString("sobrenome"), "", rs.getString("email"), rs.getString("matricula"), "", new Departamento(rs.getInt("codigo"), rs.getString("nomedep"))));
		}
		
		return professores;
	}
	
	public Usuario efetuarCadastro(String nome, String sobrenome, String senha, String email, String matricula, String cpf, int semestre, Curso curso) throws SQLException {
		Aluno user = null;
		Connection connection = new DataGetter().getConnection();
		String sql = "INSERT INTO USUARIO VALUES ('" + matricula + "', '" + nome + "', '" + sobrenome + "', '" + email + "', '" + senha + "', '" + cpf + "')";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		if (stmt.execute()) {
			sql = "INSERT INTO ALUNO VALUES ('" + matricula + "', " + curso.getCodigo() + ", " + semestre + ")";
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			if (stmt.execute()) {
				user = new Aluno(nome, sobrenome, senha, email,  matricula, cpf, semestre, curso);
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
