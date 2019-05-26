package orientacao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

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

	public void recuperarSenha(String cpf) throws SQLException {
		Connection connection = new DataGetter().getConnection();
		String sql = "SELECT email FROM USUARIO WHERE cpf = '" + cpf + "'";
		PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
		//stmt.execute();
		ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {
	    }
		stmt.close();
		connection.close();
	}
	
	public void alterarSenha(String senhaAntiga, String novaSenha) {
		
	}
	
	public void logOut() {
		
	}
}
