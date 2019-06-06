package orientacao.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import orientacao.Aluno;
import orientacao.Professor;

import java.io.IOException;
import java.sql.SQLException;


@SuppressWarnings("serial")
@WebServlet(
        name = "alterarcadastroservlet",
        urlPatterns = "/alterarCadastro"
)

public class AlterarCadastroServlet extends HttpServlet{
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nomeAlterar");
		String sobrenome = req.getParameter("sobrenome");
		String email = req.getParameter("email");
		String senhaAntiga = req.getParameter("senhaAntiga");
		String senhaNova = req.getParameter("senhaNova");
		System.out.println(email);
		String tipo = req.getParameter("tipo");
		int semestre;
		HttpSession session = req.getSession();
		if(tipo.equals("aluno")) {
			Aluno aluno = (Aluno) session.getAttribute("user");
			semestre = Integer.parseInt(req.getParameter("semestre"));
			try {
				aluno = (Aluno) aluno.alterarCadastro(nome, sobrenome, email, semestre);
				if(senhaAntiga != null && senhaNova != null) {
					aluno.alterarSenha(senhaAntiga, senhaNova);
				}				
				session.setAttribute("user", aluno);
				resp.sendRedirect("homepageAluno.jsp");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(tipo.equals("professor")) {
			Professor professor = (Professor) session.getAttribute("user");
			semestre = -1;
			try {
				professor = (Professor) professor.alterarCadastro(nome, sobrenome, email, semestre);
				if(senhaAntiga != null && senhaNova != null) {
					professor.alterarSenha(senhaAntiga, senhaNova);
				}				
				session.setAttribute("user", professor);
				resp.sendRedirect("homepageProfessor.jsp");				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}