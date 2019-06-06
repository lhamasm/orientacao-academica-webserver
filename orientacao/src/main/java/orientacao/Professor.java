package orientacao;

import org.apache.commons.mail.*;
import orientacao.Usuario;
import javax.mail.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import orientacao.Orientacao;
import orientacao.Departamento;

public class Professor extends Usuario {
	
	public Professor(String nome, String sobrenome, String senha, String email, String matricula, String cpf) {
		super(nome, sobrenome, senha, email, matricula, cpf);
	}


	public boolean efetuarCadastro(int departamento) throws SQLException, ClassNotFoundException {
		Connection con = null;
		boolean cadastrou = false;
		try{
			con = new DataGetter().getConnection();
			String sql = "INSERT INTO USUARIO VALUES ('" + this.getMatricula() + "', '" + this.getNome() + "', '" + this.getSobrenome() + "', '" + this.getEmail() + "', '" + this.getSenha() + "', '" + this.getCpf() + "')";
			PreparedStatement stmt = con.prepareStatement(sql);

			if (!stmt.execute()) {
				sql = "INSERT INTO PROFESSOR VALUES ('" + this.getMatricula() + "', " + departamento + ")";
				stmt = con.prepareStatement(sql);
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
	public boolean responderNotificacao(Orientacao orientacao) throws SQLException, ClassNotFoundException, EmailException {
		Connection con = null;
		try{
			boolean falhou = false;
			con = new DataGetter().getConnection();
			String sql = "UPDATE ORIENTACAO SET observacaoProf = '" + orientacao.getObservacaoProf()  + "', lida = TRUE WHERE id = '" + orientacao.getId() + "'";
			PreparedStatement stmt = con.prepareStatement(sql);
			if(!stmt.execute()){
				for(int i=0; i<orientacao.getAprovado().size(); i++) {
					if(orientacao.getCursando().get(i) == false) {
						sql = "UPDATE ORIENTACAO_DISCIPLINA SET aprovado = " + orientacao.getAprovado().get(i) + " WHERE disciplina = '" + orientacao.getDisciplinas().get(i).getCodigo() + "' AND orientacao = " + orientacao.getId();
						stmt = con.prepareStatement(sql);
						if(stmt.execute()) {
							falhou = true;
							break;
						}
					}
				}
				if(falhou == false) {
					Email email = new Email();
					email.respostaOrientacao(orientacao.getRemetente().getEmail(), this.getNome(), orientacao.getRemetente().getNome());
					return true;
				}
			}		
		}catch(SQLException e){
			 System.out.println(e);
		} finally{
			con.close();
		}
		return false;
	}
}
