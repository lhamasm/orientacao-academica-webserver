package orientacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Obrigatoria extends Disciplina{
	private int semestreSugerido;
	private ArrayList<Disciplina> preRequisitos;

	public Obrigatoria(String codigo, String nome, int cargaHoraria, int semestreSugerido, ArrayList<Disciplina> preRequisitos) {
		super(codigo, nome, cargaHoraria);
		this.semestreSugerido = semestreSugerido;
		this.preRequisitos = preRequisitos;
	}

	public int getSemestreSugerido() {
		return semestreSugerido;
	}
	public void setSemestreSugerido(int semestreSugerido) {
		this.semestreSugerido = semestreSugerido;
	}
	
	public ArrayList<Disciplina> getPreRquisitos() {
		return preRequisitos;
	}

	public void recuperaPreRequisitos(int codigoCurso) throws SQLException, ClassNotFoundException {
		Connection con = new DataGetter().getConnection();
		String sql = "SELECT DISCIPLINA.codigo as codigo, DISCIPLINA.nome as nome, DISCIPLINA.carga_horaria as carga_horaria FROM DISCIPLINA, PREREQUISITO WHERE PREREQUISITO.trancador = DISCIPLINA.codigo AND PREREQUISITO.trancado = '" + this.getCodigo() + "' AND PREREQUISITO.curso = " + codigoCurso;
		
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
        
        while(rs.next()) {
        	this.preRequisitos.add(new Disciplina(rs.getString("codigo"), rs.getString("nome"), rs.getInt("carga_horaria")));
        }
        
        rs.close();
        stmt.close();
        con.close();
	}
	
}
