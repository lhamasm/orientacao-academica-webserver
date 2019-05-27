package orientacao;

import orientacao.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@SuppressWarnings("serial")
@WebServlet(
        name = "cadastrouserservlet",
        urlPatterns = "/cadastro"
)

public class CadastroUserServlet extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");
		String cpf = req.getParameter("cpf");
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		String matricula = req.getParameter("matricula");
		Usuario user = new Usuario(nome, sobrenome, senha, email, cpf, matricula);		
		if(req.getParameter("tipo").equals("Estudante")) {
			int codcurso = Integer.parseInt(req.getParameter("curso"));
			Curso curso = null;
			try {
				curso = user.recuperarCurso(codcurso);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			int semestre = Integer.parseInt(req.getParameter("semestre"));
			Aluno aluno = new Aluno(nome, sobrenome, senha, email, matricula, cpf, curso, semestre);
			try {
				aluno = (Aluno) aluno.efetuarCadastro(aluno);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(aluno != null) {
				req.setAttribute("aluno", aluno);
		        RequestDispatcher view = req.getRequestDispatcher("homepageAluno.jsp");
		        view.forward(req, resp);
			}
			else {
				req.setAttribute("erro", "erro");
				RequestDispatcher view = req.getRequestDispatcher("cadastro.jsp");
				view.forward(req, resp);
			}
		}
		else if(req.getParameter("tipo").equals("Docente")) {
			String departamento = req.getParameter("dep");
			Departamento dep = null;
			try {
				dep = user.recuperarDepartamento(departamento);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Professor professor = new Professor(nome, sobrenome, senha, email, matricula, cpf, dep);
			try {
				professor = (Professor) professor.efetuarCadastro(professor);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(professor != null) {
				req.setAttribute("professor", professor);
		        RequestDispatcher view = req.getRequestDispatcher("homepageProfessor.jsp");
		        view.forward(req, resp);				
			}
			else {
				req.setAttribute("erro", "erro");
				RequestDispatcher view = req.getRequestDispatcher("cadastro.jsp");
				view.forward(req, resp);				
			}
		}
	}
}
