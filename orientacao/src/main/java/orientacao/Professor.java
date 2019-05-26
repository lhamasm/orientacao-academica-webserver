package orientacao;

import orientacao.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import orientacao.Aluno;
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
	
	public void recuperarNotificacoes(Professor professor) {
		
		Connection con = null;
		try {
        	String sql = "SELECT * FROM ORIENTACAO WHERE destinatario=" + professor.getMatricula();        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Orientacao> orientacoes = new ArrayList<Orientacao>();
            while(rs.next()) {
            	orientacoes.add(new Orientacao(rs.getInt("id"), rs.getDate("data"), rs.getTime("horario"), rs.getString("observacao"), rs.getString("destinatario"), rs.getString("remetente")));
            }
            
            rs.close();
            stmt.close();
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
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
