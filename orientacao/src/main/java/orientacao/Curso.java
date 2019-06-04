package orientacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import orientacao.*;

public class Curso {
	private int codigo;
	private String nome;
	private int duracao;
	private Departamento departamento;
	private ArrayList<Obrigatoria> obrigatorias;
	private ArrayList<Disciplina> optativas;
	
	public Curso(int codigo, String nome, int duracao, Departamento departamento, ArrayList<Obrigatoria> obrigatorias, ArrayList<Disciplina> optativas) {
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
	
	public ArrayList<Obrigatoria> getObrigatorias(){
		return obrigatorias;
	}
	public void setObrigatorias(ArrayList<Obrigatoria> obrigatorias) {
		this.obrigatorias = obrigatorias;
	}
	
	public ArrayList<Disciplina> getOptativas(){
		return optativas;
	}
	public void setOptativas(ArrayList<Disciplina> optativas) {
		this.optativas = optativas;
	}
	
	public void recuperarOptativas() throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		try {
        	String sql = "SELECT * FROM DISCIPLINA WHERE DISCIPLINA.codigo NOT IN (SELECT disciplina FROM OBRIGATORIA)";
        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Disciplina> optativas = new ArrayList<Disciplina>();
            while(rs.next()) {
            	optativas.add(new Disciplina(rs.getString("codigo"), rs.getString("nome"), rs.getInt("carga_horaria")));
            }
            
            rs.close();
            stmt.close();
            
            this.setOptativas(optativas);
                        
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
	}
	
	public void recuperarObrigatorias() throws SQLException, ClassNotFoundException {
		
		Connection con = null;
		try {
        	String sql = "SELECT DISCIPLINA.codigo as codigo, DISCIPLINA.nome as nome, DISCIPLINA.carga_horaria as carga_horaria, OBRIGATORIA.semestre_sugerido as semestre_sugerido FROM DISCIPLINA, OBRIGATORIA WHERE OBRIGATORIA.disciplina = DISCIPLINA.codigo";
        	
			con = new DataGetter().getConnection();
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
            
			ArrayList<Obrigatoria> obrigatorias = new ArrayList<Obrigatoria>();
            while(rs.next()) {
            	Obrigatoria ob = new Obrigatoria(rs.getString("codigo"), rs.getString("nome"), rs.getInt("carga_horaria"), rs.getInt("semestre_sugerido"), null);
            	ob.recuperaPreRequisitos(this.codigo);
            	obrigatorias.add(ob);
            }
            
            rs.close();
            stmt.close();
            this.obrigatorias = obrigatorias;
            
        } catch(SQLException e) {
            System.out.println(e);
        } finally {        
			con.close();
		}
	}
}
