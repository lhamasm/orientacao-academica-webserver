

package orientacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.mysql.jdbc.Connection;

import orientacao.Curso;
import orientacao.Aluno;
import orientacao.Departamento;
import orientacao.DataGetter;
import orientacao.Email;

public class Usuario {
	private String nome;
	private String sobrenome;
	private String senha;
	private String email;
	private String matricula;
	private String cpf;

	public Usuario(String nome, String sobrenome, String senha, String email, String matricula, String cpf) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.senha = senha;
		this.email = email;
		this.matricula = matricula;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSebrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	private String gerarNovaSenha() {
		String novaSenha = "";

		for(int i = 0; i<9; i++){
			Random rand = new Random();
			int numero = rand.nextInt(123);
			if( (numero >= 48 && numero <= 57) || (numero >= 65 && numero <= 90) || (numero >= 97 && numero <= 122) ) {
				novaSenha += numero;
			} else {
				i--;
			}
		}

		return novaSenha;
	}

	public boolean recuperarSenha(String cpf) throws SQLException, ClassNotFoundException {
		Connection connection = new DataGetter().getConnection();
		String sql = "SELECT email FROM USUARIO WHERE cpf = '" + cpf + "'";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		//stmt.execute();
		ResultSet rs = stmt.executeQuery();
		String emailDest;
		if (rs.next()) {
			emailDest = rs.getString("email");
		} else {
			stmt.close();
			connection.close();
			return false;
		}
		
		String novaSenha = gerarNovaSenha();
		sql = "UPDATE USUARIO SET senha = '" + novaSenha + "' WHERE cpf = '" + cpf + "'";
		stmt = (PreparedStatement) connection.prepareStatement(sql);
		if (!stmt.execute()) {
			stmt.close();
			connection.close();
			return false;
		}
		
		Email emailer = new Email();
		if (!emailer.recuperarSenhaEmail(emailDest, novaSenha)) {
			stmt.close();
			connection.close();
			return false;
		}
	    
		stmt.close();
		connection.close();
		return true;
	}
	
	public boolean alterarSenha(String senhaAntiga, String novaSenha) throws SQLException, ClassNotFoundException {
		Connection connection = new DataGetter().getConnection();
		String sql = "SELECT senha FROM USUARIO WHERE matricula = '" + this.matricula + "'";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next() && rs.getString("senha").equals(senhaAntiga)) {
			sql = "UPDATE USUARIO SET senha = '" + novaSenha + "' WHERE matricula = '" + this.matricula + "'";
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			connection.close();
			return true;
		}
		stmt.close();
		connection.close();
		return false;
	}
	
	public void logOut() {
		
	}
	
	public Departamento recuperarDepartamento(String nomeDepartamento) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
        	String sql = "SELECT * FROM DEPARTAMENTO WHERE DEPARTAMENTO.nome=" + nomeDepartamento;
        	
			con = new DataGetter().getConnection();

			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			Departamento departamento = null;
			while(rs.next()) {
            	departamento = new Departamento(rs.getInt("codigo"), rs.getString("nome"));
            }
            
            rs.close();
            stmt.close();
            
            return departamento;
			
		} catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
		return null;
	}
	
	public Curso recuperarCurso (int codigo) throws SQLException, ClassNotFoundException {

        	String sql = "SELECT * FROM CURSO WHERE CURSO.codigo=" + codigo;
        	
			Connection con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Curso curso = null;
            while(rs.next()) {
            	Aluno aluno = new Aluno(this.nome, this.sobrenome, this.senha, this.email, this.matricula, this.cpf, null, 0);
            	Departamento departamento = recuperarDepartamento(rs.getString("departamento"));
            	ArrayList<Disciplina> optativas = aluno.recuperarOptativas();
            	//ArrayList<Disciplina> obrigatorias = aluno.recuperarObrigatorias();
    			
    			curso = new Curso(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("duracao"), departamento, /*obrigatorias*/ null, optativas);
            }

            rs.close();
            stmt.close();
			con.close();
			
            
            return curso;
	}
	
	public ArrayList<Curso> recuperarTodosOsCursos () throws SQLException, ClassNotFoundException {

    	String sql = "SELECT * FROM CURSO";
    	
		Connection con = new DataGetter().getConnection();
		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
        
		ArrayList<Curso> cursos = null;
		
		while(rs.next()) {
        	Aluno aluno = new Aluno(this.nome, this.sobrenome, this.senha, this.email, this.matricula, this.cpf, null, 0);
        	Departamento departamento = recuperarDepartamento(rs.getString("departamento"));
        	ArrayList<Disciplina> optativas = aluno.recuperarOptativas();
        	//ArrayList<Disciplina> obrigatorias = aluno.recuperarObrigatorias();
        	Curso curso;
			curso = new Curso(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("duracao"), departamento, /*obrigatorias*/ null, optativas);
			cursos.add(curso);
        }

        rs.close();
        stmt.close();
		con.close();
		
        
        return cursos;
}
	
	
	public Usuario login(String matricula, String senha) throws SQLException, ClassNotFoundException {
		Usuario user = null;
		Connection connection = new DataGetter().getConnection();
		String sql = "SELECT USUARIO.*, ALUNO.semestre, Aluno.curso FROM USUARIO, ALUNO WHERE USUARIO.matricula='" + matricula + "' AND USUARIO.matricula = ALUNO.matricula";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			if (rs.getString("senha").equals(senha)) {
				user = new Aluno(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("senha"), rs.getString("email"), rs.getString("matricula"), rs.getString("cpf"), recuperarCurso(rs.getInt("curso")),  rs.getInt("semestre"));
			}
		} else {
			sql = "SELECT USUARIO.*, PROFESSOR.departamento FROM USUARIO, PROFESSOR WHERE USUARIO.matricula='" + matricula + "' AND USUARIO.matricula = PROFESSOR.matricula";
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next() && rs.getString("senha").equals(senha)) {
				user = new Professor(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("senha"), rs.getString("email"), rs.getString("matricula"), rs.getString("cpf"), recuperarDepartamento(rs.getString("departamento")));
			}
		}
		stmt.close();
		connection.close();
		return user;
	}
}
























