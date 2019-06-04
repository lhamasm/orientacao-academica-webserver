package orientacao;

import java.util.ArrayList;

public class Orientacao {
	
	private int id;
	private String data;
	private String horario;
	private String observacaoAluno;
	private String observacaoProf;
	private Professor destinatario;
	private Aluno remetente;
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Boolean> cursando;
	private ArrayList<Boolean> aprovado;
	private boolean lida;
	
	public Orientacao(int id, String data, String horario, String observacaoAluno, String observacaoProf, Professor destinatario, Aluno remetente, ArrayList<Disciplina> disc,
			ArrayList<Boolean> curs, ArrayList<Boolean> apr, boolean lida){
		this.setId(id);
		this.setData(data);
		this.setHorario(horario);
		this.setObservacaoAluno(observacaoAluno);
		this.setObservacaoProf(observacaoProf);
		this.setDestinatario(destinatario);
		this.setRemetente(remetente);
		this.setDisciplinas(disc);
		this.setCursando(curs);
		this.setAprovado(apr);
		this.setLida(lida);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getObservacaoAluno() {
		return observacaoAluno;
	}

	public void setObservacaoAluno(String observacaoAluno) {
		this.observacaoAluno = observacaoAluno;
	}
	
	public String getObservacaoProf() {
		return observacaoProf;
	}

	public void setObservacaoProf(String observacaoProf) {
		this.observacaoProf = observacaoProf;
	}

	public Professor getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Professor destinatario) {
		this.destinatario = destinatario;
	}

	public Aluno getRemetente() {
		return remetente;
	}

	public void setRemetente(Aluno remetente) {
		this.remetente = remetente;
	}

	public ArrayList<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public ArrayList<Boolean> getCursando() {
		return cursando;
	}

	public void setCursando(ArrayList<Boolean> cursando) {
		this.cursando = cursando;
	}

	public ArrayList<Boolean> getAprovado() {
		return aprovado;
	}

	public void setAprovado(ArrayList<Boolean> aprovado) {
		this.aprovado = aprovado;
	}
	
	public boolean getLida() {
		return lida;
	}

	public void setLida(boolean lida) {
		this.lida = lida;
	}

}