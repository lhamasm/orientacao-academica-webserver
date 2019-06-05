package orientacao.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import orientacao.*;
import orientacao.Professor;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@SuppressWarnings("serial")
@WebServlet(
        name = "respondemsgservlet",
        urlPatterns = "/responderMsg"
)


public class ResponderMsgServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Professor professor = (Professor) session.getAttribute("user");
		Orientacao ori = (Orientacao) session.getAttribute("orientacao");
		ArrayList<Boolean> aprovado = ori.getAprovado();
		String[] selecionados = request.getParameterValues("veredicto[]");
		for(int i=0; i<selecionados.length; i++) {
			aprovado.set(Integer.parseInt(selecionados[i]), true);
		}
		String respostaProf = request.getParameter("mensagem");
		ori.setObservacaoProf(respostaProf);
		ori.setAprovado(aprovado);
		ori.setLida(true);
		professor.responderNotificacao(ori);
		response.sendRedirect("inboxProf.jsp");
	}
}