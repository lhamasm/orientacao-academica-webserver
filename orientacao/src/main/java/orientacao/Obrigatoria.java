package orientacao;

import java.util.ArrayList;

public class Obrigatoria extends Disciplina{
	private int semestreSugerido;

	public Obrigatoria(String codigo, String nome, int cargaHoraria, ArrayList<Disciplina> preRequisitos,
			ArrayList<Disciplina> desbloqueia, int semestreSugerido) {
		super(codigo, nome, cargaHoraria, preRequisitos, desbloqueia);
		this.semestreSugerido = semestreSugerido;
	}

	public int getSemestreSugerido() {
		return semestreSugerido;
	}

	public void setSemestreSugerido(int semestreSugerido) {
		this.semestreSugerido = semestreSugerido;
	}
	
}
