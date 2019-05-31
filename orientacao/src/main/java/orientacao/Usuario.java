package orientacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

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
	
	public Disciplina recuperarDisciplina(String codigo) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
        	String sql = "SELECT * FROM DISCIPLINA WHERE codigo =" + codigo;        	
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
            	disciplinas.add(recuperarDisciplina(rs.getString("codigo")));
            	orientacao = new Orientacao(id, null, null, null, null, null, null, disciplinas, cursando, aprovado);
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

	public Usuario recuperarUsuario(String matricula) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
			String sql = "SELECT * FROM USUARIO WHERE matricula=" + matricula;
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			Usuario usuario = null;
			while(rs.next()) {
				usuario = new Usuario(rs.getString("nome"), 
						rs.getString("sobrenome"),
						rs.getString("senha"),
						rs.getString("email"),
						matricula, 
						rs.getString("cpf")
				);
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
            	orientacao.setData(rs.getString("data"));
            	orientacao.setHorario(rs.getString("horario"));
            	orientacao.setObservacaoAluno(rs.getString("observacao_aluno"));
            	orientacao.setObservacaoProf(rs.getString("observacao_professor"));
            	orientacao.setRemetente(recuperarUsuario(rs.getString("remetente")));
            	orientacao.setDestinatario(recuperarUsuario(rs.getString("destinatario")));
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
	
	public Departamento recuperarDepartamento(int codDepartamento) throws SQLException, ClassNotFoundException {
		Connection con = null;
		try {
        	String sql = "SELECT * FROM DEPARTAMENTO WHERE DEPARTAMENTO.codigo='" + codDepartamento + "'";
        	
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

			Connection con = new DataGetter().getConnection();
        	String sql = "SELECT * FROM CURSO WHERE CURSO.codigo=" + codigo;			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			Curso curso = null;
            while(rs.next()) {
            	Departamento departamento = recuperarDepartamento(rs.getInt("departamento"));
            	curso = new Curso(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("duracao"), departamento, null, null);
            	curso.setObrigatorias(curso.recuperarObrigatorias());
            	curso.setOptativas(curso.recuperarOptativas());            
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
        
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		while(rs.next()) {
        	Departamento departamento = recuperarDepartamento(rs.getInt("departamento"));
        	Curso curso = new Curso(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("duracao"), departamento, null, null);
        	curso.setObrigatorias(curso.recuperarObrigatorias());
        	curso.setOptativas(curso.recuperarOptativas());
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
				user = new Professor(rs.getString("nome"), rs.getString("sobrenome"), rs.getString("senha"), rs.getString("email"), rs.getString("matricula"), rs.getString("cpf"), recuperarDepartamento(rs.getInt("departamento")));
			}
		}
		stmt.close();
		connection.close();
		return user;
	}
	public Usuario alterarCadastro(String nome, String sobrenome, String email, int semestre) throws ClassNotFoundException, SQLException{
		String sql_semestre = "UPDATE ALUNO SET semestre = '" + semestre + "' WHERE matricula = '" + this.matricula + "'";
		String sql_nome = "UPDATE USUARIO SET nome = '" + nome + "' WHERE matricula = '" + this.matricula + "'";
		String sql_sobrenome = "UPDATE USUARIO SET sobrenome = '" + sobrenome + "' WHERE matricula = '" + this.matricula + "'";
		String sql_email = "UPDATE USUARIO SET email = '" + email + "' WHERE matricula = '"  + this.matricula + "'";
		Connection connection = new DataGetter().getConnection();
		PreparedStatement stmt_nome = (PreparedStatement) connection.prepareStatement(sql_nome);
		PreparedStatement stmt_sobrenome = (PreparedStatement) connection.prepareStatement(sql_sobrenome);
		PreparedStatement stmt_email = (PreparedStatement) connection.prepareStatement(sql_email);
		PreparedStatement stmt_semestre = (PreparedStatement) connection.prepareStatement(sql_semestre);
		stmt_nome.executeUpdate();
		stmt_sobrenome.executeUpdate();
		stmt_email.executeUpdate();
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
        	String sql = "SELECT * FROM DEPARTAMENTO";        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
            while(rs.next()) {
            	departamentos.add(new Departamento(rs.getInt("codigo"), rs.getString("nome")));
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
























