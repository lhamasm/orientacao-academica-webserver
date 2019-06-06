package orientacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import orientacao.*;

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

	
	public void enviarNotificacao(String destinatario, String obsAluno, ArrayList<Disciplina> listaMaterias, ArrayList<Boolean> listaCursando, ArrayList<Boolean> listaAprovado) throws ClassNotFoundException, SQLException {
		
		Connection con = null;
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat horaFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date();

			String sql = "INSERT INTO ORIENTACAO(dt, horario, observacaoAluno, observacaoProf, destinatario, remetente, lida) VALUES('" +
							dateFormat.format(date) + "','" +
							horaFormat.format(date) + "','" +
							obsAluno +
							"', ' ', '" +
							destinatario + "','" +
							this.getMatricula() + "', FALSE)";
					
			con = new DataGetter().getConnection();
			
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.execute();
	        
	        try {
	        	sql = "SELECT LAST_INSERT_ID()";
	        	
	        	stmt = con.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
		        
		        while(rs.next()) {
		        	for(int i=0; i<listaMaterias.size(); i++){
						sql = "INSERT INTO ORIENTACAO_DISCIPLINA VALUES('" +
								listaMaterias.get(i).getCodigo() + "'," + 
								rs.getInt("LAST_INSERT_ID()") + "," +
								listaCursando.get(i) + "," +
								listaAprovado.get(i) + ")"; 
						
						stmt = con.prepareStatement(sql);
				        stmt.execute();
					}
		        }
		        
		        try {
		        	sql = "SELECT nome, email FROM USUARIO WHERE matricula=" +
		        			destinatario;
		        	
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
	
	public boolean efetuarCadastro(int codcurso) throws SQLException, ClassNotFoundException {
		Connection con = null;
		boolean cadastrou = false;
		try{
			con = new DataGetter().getConnection();
			String sql = "INSERT INTO USUARIO VALUES ('" + this.getMatricula() + "', '" + this.getNome() + "', '" + this.getSobrenome() + "', '" + this.getEmail() + "', '" + this.getSenha() + "', '" + this.getCpf() + "')";
			PreparedStatement stmt = con.prepareStatement(sql);
			if (!stmt.execute()) {
				sql = "INSERT INTO ALUNO VALUES ('" + this.getMatricula() + "', " + this.semestre + ", " + codcurso + ")";
				stmt = con.prepareStatement(sql);
				this.setCurso(this.recuperarCurso(codcurso));
				if (stmt.execute()) {
					sql = "DELETE FROM USUARIO WHERE matricula = '" + this.getMatricula() + "'";
					stmt = con.prepareStatement(sql);
					stmt.execute();
					cadastrou = false;
				}
				else {
					cadastrou = true;
				}
			}
			stmt.close();
			con.close();
		}catch(SQLException e){
			 System.out.println(e);
		} finally{
			con.close();
		}
		return cadastrou;
	}
}
