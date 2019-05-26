package orientacao;

public class Professor extends Usuario {

	private Departamento departamento;
	
	public Professor(String nome, String sobrenome, String senha, String email, String matricula, String cpf, Departamento departamento) {
		super(nome, sobrenome, senha, email, matricula, cpf);
		this.departamento = departamento;
	}

	
	
}
