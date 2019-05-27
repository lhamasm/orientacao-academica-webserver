package orientacao;

import orientacao.Usuario;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
		Usuario user = new Usuario("Bruno", "Dias da Silva", senha, "bruno.dias3@hotmail.com", matricula, "07478868576");
		req.setAttribute("usuario", user);
        RequestDispatcher view = req.getRequestDispatcher("index.jsp");
        view.forward(req, resp);		
	}
}
