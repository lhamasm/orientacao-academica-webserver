package orientacao.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import orientacao.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@SuppressWarnings("serial")
@WebServlet(
        name = "selecOri",
        urlPatterns = "/selecionarOrientacao"
)
public class SelecionaOrientacaoServlet extends HttpServlet {
	
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Professor professor = (Professor) session.getAttribute("user");
		ArrayList<Orientacao> respostas = new ArrayList<Orientacao>();		
		try {
			respostas = professor.recuperarNotificacoes();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int indice = Integer.parseInt(req.getParameter("indexOri"));
		session.setAttribute("orientacao", respostas.get(indice));
		resp.sendRedirect("msgProf.jsp");
	}
}
