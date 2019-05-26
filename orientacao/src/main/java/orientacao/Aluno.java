package orientacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import orientacao.DataGetter;
import orientacao.Usuario;
import orientacao.Orientacao;
import orientacao.Disciplina;
import orientacao.Curso;

public class Aluno extends Usuario{
	private Curso curso;
	private int semestre;
	
	public Aluno(String nome, String sobrenome, String senha, String email, String matricula, String cpf, Curso curso,
			int semestre) {
		super(nome, sobrenome, senha, email, matricula, cpf);
		this.curso = curso;
		this.semestre = semestre;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	public void enviarNotificacao(Orientacao orientacao) {
		
		Connection con = null;
		try {
			String sql = "INSERT INTO ORIENTACAO VALUES('" +
							orientacao.getData() + "','" +
							orientacao.getHorario() + "','" +
							orientacao.getObservacao() + "','" +
							orientacao.getDestinatario() + "','" +
							orientacao.getRemetente() + "')";
					
			con = new DataGetter().getConnection();
			
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.execute();
	        
	        try {
	        	sql = "SELECT id FROM ORIENTACAO WHERE data=" +
	        			orientacao.getData() + 
	        			" AND horario=" +
	        			orientacao.getHorario() +
	        			" AND observacao=" +
	        			orientacao.getObservacao() +
	        			" AND destinatario=" + 
	        			(orientacao.getDestinatario()).getMatricula() +
	        			" AND remetente=" + 
	        			(orientacao.getRemetente()).getMatricula();
	        	
	        	stmt = con.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
		        
		        ArrayList<Disciplina> disciplinas = orientacao.getDisciplinas();
		        ArrayList<boolean> aprovado = orientacao.getAprovado();
		        ArrayList<boolean> cursando = orientacao.getCursando();
		        
		        while(rs.next()) {
		        	for(int i=0; i<disciplinas.size(); i++){
						sql = "INSERT INTO ORIENTACAO_DISCIPLINA VALUES('" +
								disciplinas[i].getCodigo() + "'," + 
								rs.getInt("id") + "," +
								aprovado[i] + "," +
								cursando[i] + ")"; 
						
						stmt = con.prepareStatement(sql);
				        stmt.execute();
					}
		        }
		        
		        try {
		        	sql = "SELECT nome, email FROM USUARIO WHERE matricula=" +
		        			(orientacao.getDestinatario()).getMatricula();
		        	
		        	stmt = con.prepareStatement(sql);
		            rs = stmt.executeQuery();
		            
		            while(rs.next()) {
		            	//Email email = new Email();
		    			//email.notificarOrientacao(rs.getString("email"), rs.getString("nome"), this.getNome());
		            }
		            
		            rs.close();
		            stmt.close();
		            
		        } catch(SQLException e) {
		            System.out.println(e);
		        }		        

	        } catch(SQLException e) {
	            System.out.println(e);
	        }
	        
		}catch(SQLException e) {
            System.out.println(e);
		}finally {        
			con.close();
		}
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
	
	public ArrayList<Disciplina> recuperarOptativas() {
		
		Connection con = null;
		try {
        	String sql = "SELECT * FROM DISCIPLINA WHERE DISCIPLINA.codigo NOT IN (SELECT disciplina FROM OBRIGATORIA)";
        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Disciplina> optativas = new ArrayList<Disciplina>();
            while(rs.next()) {
            	optativas.add(new Disciplina(rs.getString("codigo"), rs.getString("nome"), rs.getInt("carga_horaria"), null, null));
            }
            
            rs.close();
            stmt.close();
            
            return optativas;
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
	}
	
	public ArrayList<Orientacao> recuperarNotificacoes(Aluno aluno) {
		
		Connection con = null;
		try {
        	String sql = "SELECT * FROM ORIENTACAO WHERE remetente=" + aluno.getMatricula();        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Orientacao> orientacoes = new ArrayList<Orientacao>();
            while(rs.next()) {
            	orientacoes.add(new Orientacao(rs.getInt("id"), rs.getDate("data"), rs.getTime("horario"), rs.getString("observacao"), rs.getString("destinatario"), rs.getString("remetente")));
            }
            
            rs.close();
            stmt.close();
            
            return orientacoes;
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
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
				user = new Aluno(nome, sobrenome, senha, email,  matricula, cpf, curso, semestre);
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
