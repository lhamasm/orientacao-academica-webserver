package orientacao;

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
}
