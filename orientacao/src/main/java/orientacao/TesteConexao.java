package orientacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TesteConexao {
	public static void main(String args[]) throws SQLException, ClassNotFoundException {
		Connection con = null;
		Curso curso = null;
		try {
        	String sql = "SELECT * FROM CURSO WHERE CURSO.codigo=112140";
        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
    			curso = new Curso(rs.getInt("codigo"), rs.getString("nome"), rs.getInt("duracao"), null, /*obrigatorias*/ null, null);
            }
            
            rs.close();
            stmt.close();
        } catch(SQLException e) {
            System.out.println(e);            
        } finally {        
			con.close();
		}
		System.out.println(curso.getCodigo() + " - " + curso.getNome());
	}
}
