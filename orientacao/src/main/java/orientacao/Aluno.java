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
	
	public void enviarNotificacao(Orientacao orientacao) throws ClassNotFoundException, SQLException {
		
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
		        ArrayList<Boolean> aprovado = orientacao.getAprovado();
		        ArrayList<Boolean> cursando = orientacao.getCursando();
		        
		        while(rs.next()) {
		        	for(int i=0; i<disciplinas.size(); i++){
						sql = "INSERT INTO ORIENTACAO_DISCIPLINA VALUES('" +
								disciplinas.get(i).getCodigo() + "'," + 
								rs.getInt("id") + "," +
								aprovado.get(i) + "," +
								cursando.get(i) + ")"; 
						
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
		            	Email email = new Email();
		    		email.notificarOrientacao(rs.getString("email"), rs.getString("nome"), this.getNome());
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
	
	public ArrayList<Professor> recuperarProfessores() throws SQLException, ClassNotFoundException{
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
	
	public ArrayList<Orientacao> recuperarNotificacoes(Aluno aluno) throws ClassNotFoundException, SQLException {
		
		Connection con = null;
		try {
        	String sql = "SELECT * FROM ORIENTACAO WHERE remetente=" + aluno.getMatricula();        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Orientacao> orientacoes = new ArrayList<Orientacao>();
            while(rs.next()) {
            	Orientacao orientacao = recuperarOrientacaoDisciplina(rs.getInt("id"));
            	orientacoes.add(new Orientacao(rs.getInt("id"), rs.getDate("data"), rs.getTime("horario"), rs.getString("observacao"), recuperarUsuario(rs.getString("destinatario")), recuperarUsuario(rs.getString("remetente")), orientacao.getDisciplinas(), orientacao.getAprovado(), orientacao.getCursando()));
            }
            
            rs.close();
            stmt.close();
            
            return orientacoes;
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
		return null;
	}
	public ArrayList<Disciplina> recuperarPreRequisitos(String codigo) throws ClassNotFoundException, SQLException{
		Connection con = null;
		try {
	    	String sql = "SELECT * FROM PREREQUISITO WHERE PREREQUISITO.trancado = " + codigo;
	    	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
	        
			ArrayList<Disciplina> preRequisitos = new ArrayList<Disciplina>();
			while(rs.next()) {
	        	preRequisitos.add(recuperarDisciplina(codigo));
	        }
	        
	        rs.close();
	        stmt.close();
	        
	        return preRequisitos;
	        
	    } catch(SQLException e) {
	        System.out.println(e);
	    } finally {        
			con.close();
		}
		return null;
	}
	
	public ArrayList<Disciplina> recuperarDesbloqueia(String codigo) throws ClassNotFoundException, SQLException{
		Connection con = null;
		try {
	    	String sql = "SELECT * FROM PREREQUISITO WHERE PREREQUISITO.trancador = " + codigo;
	    	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
	        
			ArrayList<Disciplina> desbloqueia = new ArrayList<Disciplina>();
			while(rs.next()) {
	        	desbloqueia.add(recuperarDisciplina(codigo));
	        }
	        
	        rs.close();
	        stmt.close();
	        
	        return desbloqueia;
	        
	    } catch(SQLException e) {
	        System.out.println(e);
	    } finally {        
			con.close();
		}
		return null;
	}

	
	public Usuario efetuarCadastro(Aluno aluno) throws SQLException, ClassNotFoundException {
		Aluno user = null;
		Connection connection = new DataGetter().getConnection();
		String sql = "INSERT INTO USUARIO VALUES ('" + aluno.getMatricula() + "', '" + aluno.getNome() + "', '" + aluno.getSobrenome() + "', '" + aluno.getEmail() + "', '" + aluno.getSenha() + "', '" + aluno.getCpf() + "')";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		if (!stmt.execute()) {
			sql = "INSERT INTO ALUNO VALUES ('" + aluno.getMatricula() + "', " + aluno.getSemestre() + ", " + aluno.getCurso().getCodigo() + ")";
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			if (!stmt.execute()) {
				user = aluno;
			} else {
				sql = "DELETE FROM USUARIO WHERE matricula = '" + aluno.getMatricula() + "'";
				stmt = (PreparedStatement) connection.prepareStatement(sql);
				stmt.execute();
			}
		}
		stmt.close();
		connection.close();
		return user;
	}
}
