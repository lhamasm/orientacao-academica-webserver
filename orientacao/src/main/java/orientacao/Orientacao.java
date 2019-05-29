package orientacao;

import java.util.ArrayList;

public class Orientacao {
	
	private int id;
	private String data;
	private String horario;
	private String observacao;
	private Usuario destinatario;
	private Usuario remetente;
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Boolean> cursando;
	private ArrayList<Boolean> aprovado;
	
	public Orientacao(int id, String data, String horario, String obs, Usuario destinatario, Usuario remetente, ArrayList<Disciplina> disc,
			ArrayList<Boolean> curs, ArrayList<Boolean> apr){
		this.setId(id);
		this.setData(data);
		this.setHorario(horario);
		this.setObservacao(obs);
		this.setDestinatario(destinatario);
		this.setRemetente(remetente);
		this.setDisciplinas(disc);
		this.setCursando(curs);
		this.setAprovado(apr);
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
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

}