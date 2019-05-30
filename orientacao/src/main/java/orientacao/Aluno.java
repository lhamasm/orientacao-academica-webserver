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
							orientacao.getObservacaoAluno() + "','" +
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

	public Orientacao recuperarOrientacaoDisciplina(int orientacao) throws ClassNotFoundException, SQLException{
		Connection con = null;
		try {
        	String sql = "SELECT * FROM ORIENTACAO_DISCIPLINA WHERE orientacao=" + orientacao;        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Orientacao o = null;
            while(rs.next()) {
            	ArrayList<Boolean> aprovado = new ArrayList<Boolean>();
            	ArrayList<Boolean> cursando = new ArrayList<Boolean>();
            	ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
            	aprovado.add(rs.getBoolean("aprovado"));
            	cursando.add(rs.getBoolean("cursando"));
            	disciplinas.add(recuperarDisciplina(rs.getString("codigo")));
            	o = new Orientacao(-1, null, null, null, null, null, null, disciplinas, aprovado, cursando);
            }
            
            rs.close();
            stmt.close();
            
            return o;
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
		return null;
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
            	orientacoes.add(new Orientacao(rs.getInt("id"), rs.getString("data"), rs.getString("horario"), rs.getString("observacaoAluno"), rs.getString("observacaoProf"), recuperarUsuario(rs.getString("destinatario")), recuperarUsuario(rs.getString("remetente")), orientacao.getDisciplinas(), orientacao.getAprovado(), orientacao.getCursando()));
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

	public Usuario recuperarUsuario(String matricula) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			String sql = "SELECT * FROM USUARIO WHERE matriucla=" + matricula;
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			Usuario usuario = null;
			while(rs.next()) {
				usuario = new Usuario(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("senha"), rs.getString("email"), matricula, rs.getString("cpf"));
			}
			
			rs.close();
            stmt.close();
            
            return usuario;
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
