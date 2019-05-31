package orientacao.servlets;

import orientacao.Aluno;
import orientacao.Usuario;
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
        name = "loginuserservlet",
        urlPatterns = "/login"
)

public class LoginUserServlet extends HttpServlet {

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String matricula = req.getParameter("matricula");
		String senha = req.getParameter("senha");
		Usuario user = new Usuario("", "", senha, "", matricula, "");
		HttpSession session = req.getSession();		
		try {
			user = user.efetuarLogin(matricula, senha);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("erro", "naoerrou");		
		if(user == null) {
			session.setAttribute("erro", "errou");
	   		resp.sendRedirect("index.jsp");
		}
		else {
			if(user instanceof Aluno) {
				session.setAttribute("user", user);
				session.setAttribute("erro", "naoerrou");
				resp.sendRedirect("homepageAluno.jsp");
			}
			else {
				session.setAttribute("user", user);
				session.setAttribute("erro", "naoerrou");						
				resp.sendRedirect("homepageProfessor.jsp");						
			}
		}
	}
}
