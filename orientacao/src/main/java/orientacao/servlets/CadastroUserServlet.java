package orientacao.servlets;

import orientacao.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;


@SuppressWarnings("serial")
@WebServlet(
        name = "cadastrouserservlet",
        urlPatterns = "/cadastro"
)

public class CadastroUserServlet extends HttpServlet {
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
 {
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");
		String cpf = req.getParameter("cpf");
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		String matricula = req.getParameter("matricula");
		HttpSession session = req.getSession();						
		boolean cadastro = false;
		if(req.getParameter("tipo").equals("Estudante")) {
			int codcurso = Integer.parseInt(req.getParameter("curso"));
			int semestre = Integer.parseInt(req.getParameter("semestre"));
			Aluno aluno = new Aluno(nome, sobrenome, senha, email, matricula, cpf, null, semestre);
			try {
				cadastro = aluno.efetuarCadastro(codcurso);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(cadastro) {
				session.setAttribute("user", aluno);
				resp.sendRedirect("homepageAluno.jsp");
			}
			else {
				req.setAttribute("erro", "erro");
				resp.sendRedirect("cadsatro.jsp");
			}
		}
		else if(req.getParameter("tipo").equals("Docente")) {
			int departamento = Integer.parseInt(req.getParameter("dep"));
			Professor professor = new Professor(nome, sobrenome, senha, email, matricula, cpf);

			try {
				cadastro = professor.efetuarCadastro(departamento);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(cadastro) {
				session.setAttribute("user", professor);
		        resp.sendRedirect("homepageProfessor.jsp");
			}
			else {
				session.setAttribute("erro", "errou");
				resp.sendRedirect("cadastro.jsp");		
			}
		}
	}
}
