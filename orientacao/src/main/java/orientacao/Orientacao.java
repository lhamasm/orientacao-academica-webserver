package orientacao;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Orientacao {
	
	private int id;
	private Date data;
	private Time horario;
	private String observacao;
	private Usuario destinatario;
	private Usuario remetente;
	private ArrayList<Disciplina> disciplinas;
	private ArrayList<Boolean> cursando;
	private ArrayList<Boolean> aprovado;
	
	public Orientacao(int id, Date data, Time horario, String obs, Usuario destinatario, Usuario remetente, ArrayList<Disciplina> disc,
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
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