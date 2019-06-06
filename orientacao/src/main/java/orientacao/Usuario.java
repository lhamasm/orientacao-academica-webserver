package orientacao;

import java.sql.PreparedStatement;
import org.apache.commons.mail.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.mail.*;

import com.mysql.jdbc.Connection;

import orientacao.*;

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
			char numero = (char) rand.nextInt(123);
			if( (numero >= 48 && numero <= 57) || (numero >= 65 && numero <= 90) || (numero >= 97 && numero <= 122) ) {
				novaSenha += numero;
			} else {
				i--;
			}
		}
		System.out.println(novaSenha);
		return novaSenha;
	}
	
	public Disciplina recuperarDisciplina(String codigo) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
        	String sql = "SELECT * FROM DISCIPLINA WHERE codigo ='" + codigo + "'";        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Disciplina disciplina = null;
            while(rs.next()) {
            	disciplina = new Disciplina(codigo, rs.getString("nome"), rs.getInt("carga_horaria"));
            }
            
            rs.close();
            stmt.close();
            
            return disciplina;
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
		return null;
	}
	
	public Orientacao recuperarOrientacaoDisciplina(int id) throws ClassNotFoundException, SQLException{
		Connection con = null;
		try {
        	String sql = "SELECT * FROM ORIENTACAO_DISCIPLINA WHERE orientacao=" + id;        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Orientacao orientacao = null;
			ArrayList<Boolean> aprovado = new ArrayList<Boolean>();
        	ArrayList<Boolean> cursando = new ArrayList<Boolean>();
        	ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
            while(rs.next()) {
            	aprovado.add(rs.getBoolean("aprovado"));
            	cursando.add(rs.getBoolean("cursando"));
            	disciplinas.add(recuperarDisciplina(rs.getString("disciplina")));
            	orientacao = new Orientacao(id, null, null, null, null, null, null, disciplinas, cursando, aprovado, false);
            }
            
            rs.close();
            stmt.close();
            
            return orientacao;
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
		return null;
	}
	
	public ArrayList<Orientacao> recuperarNotificacoes() throws ClassNotFoundException, SQLException {
		
		Connection con = null;
		try {
        	String sql = "SELECT * FROM ORIENTACAO WHERE remetente='" + this.matricula + "' OR destinatario='" + this.matricula + "' ORDER BY dt DESC, horario DESC";        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Orientacao> orientacoes = new ArrayList<Orientacao>();
            while(rs.next()) {
            	Orientacao orientacao = recuperarOrientacaoDisciplina(rs.getInt("id"));
            	orientacao.setData(rs.getString("dt"));
            	orientacao.setHorario(rs.getString("horario"));
            	orientacao.setObservacaoAluno(rs.getString("observacaoAluno"));
            	orientacao.setObservacaoProf(rs.getString("observacaoProf"));
            	orientacao.setRemetente(recuperarAluno(rs.getString("remetente")));
            	orientacao.setDestinatario(recuperarProfessor(rs.getString("destinatario")));
            	orientacao.setLida(rs.getBoolean("lida"));
            	orientacoes.add(orientacao);
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
	
	public Professor recuperarProfessor(String matricula) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			String sql = "SELECT * FROM USUARIO WHERE matricula='" + matricula + "'";
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			Professor usuario = null;
			while(rs.next()) {
				usuario = new Professor(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("senha"), rs.getString("email"), matricula, rs.getString("cpf"));
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
	
	public Aluno recuperarAluno(String matricula) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			String sql = "SELECT USUARIO.*, ALUNO.semestre as semestre, CURSO.nome as nomeCurso, CURSO.codigo as codigoCurso FROM USUARIO, ALUNO, CURSO WHERE USUARIO.matricula = '" + matricula + "' AND ALUNO.curso = CURSO.codigo";
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			Aluno aluno = null;
			while(rs.next()) {
				aluno = new Aluno(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("senha"), rs.getString("email"), matricula, rs.getString("cpf"), new Curso(rs.getInt("codigoCurso"), rs.getString("nomeCurso"), 0, null, null, null), rs.getInt("semestre"));
			}
			
			rs.close();
            stmt.close();
            
            return aluno;
		} catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
		return null;
	}
	
	
	public boolean recuperarSenha(String cpf) throws SQLException, ClassNotFoundException, EmailException {
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
		if (stmt.execute()) {
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
		if (this.senha.equals(senhaAntiga)) {
			Connection connection = new DataGetter().getConnection();
			String sql = "UPDATE USUARIO SET senha = '" + novaSenha + "' WHERE matricula = '" + this.matricula + "'";
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			connection.close();
			stmt.close();
			connection.close();
			return true;
		}
		return false;
	}
	
	public Departamento recuperarDepartamento(int codDepartamento) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
        	String sql = "SELECT * FROM DEPARTAMENTO WHERE DEPARTAMENTO.codigo=" + codDepartamento + "";
        	
			con = new DataGetter().getConnection();

			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			Departamento departamento = null;
			while(rs.next()) {
            	departamento = new Departamento(rs.getInt("codigo"), rs.getString("nome"), null);
            	departamento.recuperarProfessores();
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
	
	public ArrayList<Curso> recuperarTodosOsCursos () throws SQLException, ClassNotFoundException {

    	String sql = "SELECT * FROM CURSO ORDER BY nome";
    	
		Connection con = new DataGetter().getConnection();
		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
        
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		while(rs.next()) {
        	Curso curso = new Curso(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("duracao"), null, null, null);
			cursos.add(curso);
        }

        rs.close();
        stmt.close();
		con.close();
		
        
        return cursos;
	}
	
	public Curso recuperarCurso (int codigo) throws SQLException, ClassNotFoundException {

		Connection con = new DataGetter().getConnection();
    	String sql = "SELECT CURSO.codigo AS codigo_curso, CURSO.nome AS nome_curso, CURSO.duracao, DEPARTAMENTO.codigo AS codigo_dep, DEPARTAMENTO.nome AS nome_dep FROM CURSO, DEPARTAMENTO WHERE CURSO.codigo=" + codigo + " AND CURSO.departamento=DEPARTAMENTO.codigo";			
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Curso curso = null;
        while(rs.next()) {
        	Departamento departamento = new Departamento(rs.getInt("codigo_dep"), rs.getString("nome_dep"), new ArrayList<Professor>());
        	departamento.recuperarProfessores();
        	curso = new Curso(rs.getInt("codigo_curso"), rs.getString("nome_curso"), rs.getInt("duracao"), departamento, null, null);
        	curso.recuperarObrigatorias();
        	curso.recuperarOptativas();
    	}

        rs.close();
        stmt.close();
		con.close();
		
        
        return curso;
	}
	
	
	public Usuario efetuarLogin(String matricula, String senha) throws SQLException, ClassNotFoundException {
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
				user = new Professor(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("senha"), rs.getString("email"), rs.getString("matricula"), rs.getString("cpf"));
			}
		}
		stmt.close();
		connection.close();
		return user;
	}
	public Usuario alterarCadastro(String nome, String sobrenome, String email, int semestre) throws ClassNotFoundException, SQLException{
		String sql_semestre = "UPDATE ALUNO SET semestre = " + semestre + " WHERE matricula = '" + this.matricula + "'";
		String sql_usuario = "UPDATE USUARIO SET nome = '" + nome + "', sobrenome = '" + sobrenome + "', email = '" + email + "' WHERE matricula = '" + this.matricula + "'";
		Connection connection = new DataGetter().getConnection();
		PreparedStatement stmt_usuario = (PreparedStatement) connection.prepareStatement(sql_usuario);
		PreparedStatement stmt_semestre = (PreparedStatement) connection.prepareStatement(sql_semestre);
		stmt_usuario.executeUpdate();
		if(semestre != -1) {
			stmt_semestre.executeUpdate();		
			Aluno aluno = (Aluno) this;
			aluno.setNome(nome);
			aluno.setSebrenome(sobrenome);
			aluno.setEmail(email);
			aluno.setSemestre(semestre);
			return aluno;
		}else {
			Professor professor = (Professor) this;
			professor.setNome(nome);
			professor.setSebrenome(sobrenome);
			professor.setEmail(email);
			return professor;
		}
	}	
	
	public ArrayList<Departamento> recuperarDepartamentos() throws SQLException, ClassNotFoundException{
		Connection con = null;
		try {
        	String sql = "SELECT * FROM DEPARTAMENTO ORDER BY nome";        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
            while(rs.next()) {
            	Departamento departamento = new Departamento(rs.getInt("codigo"), rs.getString("nome"), null);
            	departamentos.add(departamento);
            }
            
            rs.close();
            stmt.close();
            
            return departamentos;
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
		return null;
	}		
}
























