package orientacao;

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
}