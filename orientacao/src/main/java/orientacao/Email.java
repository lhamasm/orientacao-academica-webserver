package orientacao;

import org.apache.commons.mail.*;
import orientacao.Usuario;

public class Email {
	private SimpleEmail mail;
	private String emailDestinatario;
	private String nomeDestinatario;
	private String assunto;
	private String corpo;
	
	public Email() {
		super();
		this.mail = new SimpleEmail();		
		this.mail.setHostName("smtp.googlemail.com");
		this.mail.setSmtpPort(465);
		this.mail.setAuthenticator(new DefaultAuthenticator("machad.lari@gmail.com", "doremifasol"));
		this.mail.setSSLOnConnect(true);
		this.mail.setFrom("machad.lari@gmail.com");		
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	
	public boolean recuperarSenhaEmail(String email, String novaSenha){
		Usuario usuario = new Usuario();
		
		this.mail.addTo(email);
		this.mail.setSubject("Recupera��o de Senha do Sistema de Orienta��o Acad�mica"); 
		this.mail.setMsg("Sua nova senha � : " + novaSenha);
		if(this.mail.send() != "") {
			System.out.println("Erro ao enviar email");
		}
	}
	
	public void notificarOrientacao(String email, String nomeProfessor, String nomeAluno){
		this.mail.addTo(email); // email do destinatario.
		this.mail.setSubject("Nova Orienta��o no Sistema de Orienta��o Acad�mica"); 
		this.mail.setMsg("Caro(a) Professor(a) " + nomeProfessor + ",<br>O aluno " + nomeAluno + " solicita a sua orienta��o.");
		if(this.mail.send() != "") {
			System.out.println("Erro ao enviar email");
		}
	}
	
	public void respostaOrientacao(String email, String nomeProfessor, String nomeAluno) {
		this.mail.addTo(email); // email do destinatario.
		this.mail.setSubject("Notifica��o de Resposta no Sistema de Orienta��o Acad�mica"); 
		this.mail.setMsg("Caro(a) Aluno(a) " + nomeAluno + ", <br>Voc� possui uma nova resposta do professor(a) " + nomeProfessor + ".");
		if(this.mail.send() != "") {
			System.out.println("Erro ao enviar email");
		}
	}	
}
