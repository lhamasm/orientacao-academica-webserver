package orientacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Curso {
	private int codigo;
	private String nome;
	private int duracao;
	private Departamento departamento;
	private ArrayList<Disciplina> obrigatorias;
	private ArrayList<Disciplina> optativas;
	
	public Curso(int codigo, String nome, int duracao, Departamento departamento, ArrayList<Disciplina> obrigatorias, ArrayList<Disciplina> optativas) {
		this.codigo = codigo;
		this.nome = nome;
		this.duracao = duracao;
		this.departamento = departamento;
		this.obrigatorias = obrigatorias;
		this.optativas = optativas;
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
	
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public ArrayList<Disciplina> getObrigatorias(){
		return obrigatorias;
	}
	public void setObrigatorias(ArrayList<Disciplina> obrigatorias) {
		this.obrigatorias = obrigatorias;
	}
	
	public ArrayList<Disciplina> getOptativas(){
		return optativas;
	}
	public void setOptativas(ArrayList<Disciplina> optativas) {
		this.optativas = optativas;
	}
	
	
public ArrayList<Disciplina> recuperarOptativas() throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		try {
        	String sql = "SELECT * FROM DISCIPLINA WHERE DISCIPLINA.codigo NOT IN (SELECT disciplina FROM OBRIGATORIA)";
        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Disciplina> optativas = new ArrayList<Disciplina>();
            while(rs.next()) {
            	optativas.add(new Disciplina(rs.getString("codigo"), rs.getString("nome"), rs.getInt("carga_horaria"), null, null));
            }
            
            rs.close();
            stmt.close();
            
            return optativas;
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
		return null;
	}

	public ArrayList<Disciplina> recuperarObrigatorias() throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		try {
	    	String sql = "SELECT * FROM DISCIPLINA WHERE DISCIPLINA.codigo NOT IN (SELECT disciplina FROM OPTATIVA)";
	    	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
	        
			ArrayList<Disciplina> obrigatorias = new ArrayList<Disciplina>();
	        while(rs.next()) {
	        	obrigatorias.add(new Disciplina(rs.getString("codigo"), rs.getString("nome"), rs.getInt("carga_horaria"), null, null));
	        }
	        
	        rs.close();
	        stmt.close();
	        
	        return obrigatorias;
	        
	    } catch(SQLException e) {
	        System.out.println(e);
	    } finally {        
			con.close();
		}
		return null;
	}	
}
