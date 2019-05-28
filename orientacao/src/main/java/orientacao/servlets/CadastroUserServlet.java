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
		Usuario user = new Usuario(nome, sobrenome, senha, email, cpf, matricula);		
		if(req.getParameter("tipo").equals("Estudante")) {
			int codcurso = Integer.parseInt(req.getParameter("curso"));
			Curso curso = null;

			try {
				curso = user.recuperarCurso(codcurso);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int semestre = Integer.parseInt(req.getParameter("semestre"));
			Aluno aluno = new Aluno(nome, sobrenome, senha, email, matricula, cpf, curso, semestre);

			try {
				aluno = (Aluno) aluno.efetuarCadastro(aluno);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(aluno != null) {
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
			Departamento dep = null;

			try {
				dep = user.recuperarDepartamento(departamento);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Professor professor = new Professor(nome, sobrenome, senha, email, matricula, cpf, dep);

			try {
				professor = (Professor) professor.efetuarCadastro(professor);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(professor != null) {
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
