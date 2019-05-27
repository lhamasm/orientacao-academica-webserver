package orientacao;

import java.util.ArrayList;

public class Disciplina {
	private String codigo;
	private String nome;
	private int cargaHoraria;
	private ArrayList<Disciplina> preRequisitos;
	private ArrayList<Disciplina> desbloqueia;
	
	public Disciplina(String codigo, String nome, int cargaHoraria, ArrayList<Disciplina> preRequisitos, ArrayList<Disciplina> desbloqueia) {
		this.codigo = codigo;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.preRequisitos = preRequisitos;
		this.desbloqueia = desbloqueia;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public ArrayList<Disciplina> getPreRequisitos() {
		return preRequisitos;
	}
	
	public void setPreRequisitos(ArrayList<Disciplina> preRequisitos) {
		this.preRequisitos = preRequisitos;
	}
	
	public ArrayList<Disciplina> getDesbloqueia() {
		return desbloqueia;
	}
	
	public void setDesbloqueia(ArrayList<Disciplina> desbloqueia) {
		this.desbloqueia = desbloqueia;
	}
	
	
}
