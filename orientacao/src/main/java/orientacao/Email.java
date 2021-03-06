package orientacao;

import org.apache.commons.mail.*;
import javax.mail.*;

public class Email {
	private HtmlEmail mail;
	private String emailDestinatario;
	private String nomeDestinatario;
	private String assunto;
	private String corpo;
	
	public Email() {
		this.mail = new HtmlEmail();
		try {
			this.mail.setCharset("utf-8");
			this.mail.setHostName("smtp.googlemail.com");
			this.mail.setSmtpPort(465);
			this.mail.setAuthenticator(new DefaultAuthenticator("machad.lari@gmail.com", "doremifasol"));
			this.mail.setSSLOnConnect(true);
			this.mail.setFrom("machad.lari@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public HtmlEmail getMail() {
		return mail;
	}

	public void setMail(HtmlEmail mail) {
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
		try {
			this.mail.addTo(email);
			this.mail.setSubject("Recuperação de Senha do Sistema de Orientação Acadêmica"); 
			this.mail.setHtmlMsg("Sua nova senha é : " + novaSenha);
			this.mail.send();			
			return true;
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
	}
	
	public void notificarOrientacao(String email, String nomeProfessor, String nomeAluno){
		try {
			System.out.println(email);
			this.mail.addTo(email);
			this.mail.setSubject("Nova Orientação no Sistema de Orientação Acadêmica"); 
			this.mail.setHtmlMsg("Caro(a) Professor(a) " + nomeProfessor + ",<br>O aluno " + nomeAluno + " solicita a sua orientação.");
			this.mail.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void respostaOrientacao(String email, String nomeProfessor, String nomeAluno) {
		try {
			this.mail.addTo(email);
			this.mail.setSubject("Notificação de Resposta no Sistema de Orientação Acadêmica"); 
			this.mail.setHtmlMsg("Caro(a) Aluno(a) " + nomeAluno + ", <br>Você possui uma nova resposta do professor(a) " + nomeProfessor + ".");
			this.mail.send();		
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
