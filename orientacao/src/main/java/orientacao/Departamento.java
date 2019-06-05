package orientacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Departamento {
	private int codigo;
	private String nome;
	private ArrayList<Professor> professores;
	
	public Departamento(int codigo, String nome, ArrayList<Professor> professores) {
		this.codigo = codigo;
		this.nome = nome;
		this.professores = professores;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public ArrayList<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(ArrayList<Professor> professores) {
		this.professores = professores;
	}
	
	public void recuperarProfessores() throws SQLException, ClassNotFoundException{
		Connection con = null;
		try {
			String sql = "SELECT USUARIO.* FROM USUARIO, PROFESSOR WHERE PROFESSOR.matricula = USUARIO.matricula AND PROFESSOR.departamento = " + this.codigo;
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Professor> professores = new ArrayList<Professor>();
			while(rs.next()) {
            	Professor professor = new Professor(rs.getString("nome"),
            			rs.getString("sobrenome"),
            			rs.getString("senha"),
            			rs.getString("email"),
            			rs.getString("matricula"),
            			rs.getString("cpf")
            	);
            	professores.add(professor);
            	System.out.println(professores.size());
            }
            
            rs.close();
            stmt.close();
            
            this.setProfessores(professores);
            
		} catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
	}
}