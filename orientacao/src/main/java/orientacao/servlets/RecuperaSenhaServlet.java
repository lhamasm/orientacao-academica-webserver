package orientacao.servlets;

import orientacao.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.sql.SQLException;


@SuppressWarnings("serial")
@WebServlet(
        name = "recuperarsenhaservlet",
        urlPatterns = "/recuperarSenha"
)

public class RecuperaSenhaServlet extends HttpServlet {

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cpf = req.getParameter("cpf");
		Usuario user = new Usuario("", "", "", "", "", "");
		try {
			user.recuperarSenha(cpf);
		} catch (ClassNotFoundException | SQLException | EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("index.jsp");
	}
	
}