package orientacao;

import orientacao.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import orientacao.Orientacao;

public class Professor extends Usuario {

	public Professor(String nome, String sobrenome, String senha, String email, String matricula, String cpf) {
		super(nome, sobrenome, senha, email, matricula, cpf);
	}

	public void recuperarNotificacoes(Professor professor) throws ClassNotFoundException {
		
		Connection con = null;
		try {
        	String sql = "SELECT * FROM ORIENTACAO WHERE destinatario=" + professor.getMatricula();        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Orientacao> orientacoes = new ArrayList<Orientacao>();
            while(rs.next()) {
            	Orientacao orientacao = recuperarOrientacaoDisciplina(rs.getInt("id"));
            	orientacoes.add(new Orientacao(rs.getInt("id"), rs.getString("data"), rs.getString("horario"), rs.getString("observacaoAluno"), rs.getString("observacaoProf"), recuperarUsuario(rs.getString("destinatario")), recuperarUsuario(rs.getString("remetente")), orientacao.getDisciplinas(), orientacao.getAprovado(), orientacao.getCursando()));
            }
            
            rs.close();
            stmt.close();
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			//con.close();
		}
	}
	
	public Usuario efetuarCadastro(Professor professor) throws SQLException, ClassNotFoundException {
		Professor user = null;
		Connection connection = new DataGetter().getConnection();
		String sql = "INSERT INTO USUARIO VALUES ('" + professor.getMatricula()+ "', '" + professor.getNome() + "', '" + professor.getSobrenome() + "', '" + professor.getEmail() + "', '" + professor.getSenha() + "', '" + professor.getCpf() + "')";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		if (!stmt.execute()) {
			sql = "INSERT INTO PROFESSOR VALUES ('" + professor.getMatricula() + "')";
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
