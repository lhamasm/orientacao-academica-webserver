package orientacao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.util.Random;

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
	
	public boolean recuperarSenha(String cpf) throws SQLException {
		Connection connection = new DataGetter().getConnection();
		String sql = "SELECT email FROM USUARIO WHERE cpf = '" + cpf + "'";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		//stmt.execute();
		ResultSet rs = stmt.executeQuery();
		String emailDest;
		if (rs.next()) {
			emailDest = rs.getString("email");
		} else {
			return false;
		}
		
		String novaSenha = gerarNovaSenha();
		sql = "UPDATE USUARIO SET senha = '" + novaSenha + "' WHERE cpf = '" + cpf + "'";
		stmt = (PreparedStatement) connection.prepareStatement(sql);
		stmt.execute();
		
		Email emailer = new Email();
		if (!emailer.recuperarSenhaEmail(emailDest, novaSenha)) {
			return false;
		}
	    
		stmt.close();
		connection.close();
		return true;
	}
	
	public boolean alterarSenha(String senhaAntiga, String novaSenha) throws SQLException {
		Connection connection = new DataGetter().getConnection();
		String sql = "SELECT senha FROM USUARIO WHERE matricula = '" + this.matricula + "'";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next() && rs.getString("senha") == senhaAntiga) {
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
}
