package orientacao.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import orientacao.*;
import orientacao.Aluno;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@SuppressWarnings("serial")
@WebServlet(
        name = "montaorientacaoservlet",
        urlPatterns = "/montaOrientacao"
)
public class MontarGradeServlet extends HttpServlet {
	@Override
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grade = request.getParameter("codigosGrade");
		String gradeNomes = request.getParameter("nomesGrade");
		String gradeCh = request.getParameter("chGrade");		
		String atual = request.getParameter("codigosAtual");
		String atualNomes = request.getParameter("nomesAtual");
		String atualCh = request.getParameter("chAtual");
		String orientadoresA = request.getParameter("orientadores");
		String[] codigosGrade = grade.split("/");
		String[] nomesGrade = gradeNomes.split("/");
		String[] chGrade = gradeCh.split("/");
		String[] codigosAtual = atual.split("/");
		String[] nomesAtual = atualNomes.split("/");
		String[] chAtual = atualCh.split("/");
		String[] matriculaOrientadores = orientadoresA.split("/");
		String obsAluno = request.getParameter("obsAluno");
		HttpSession session = request.getSession();
		Aluno user = (Aluno) session.getAttribute("user");
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		ArrayList<Boolean> cursando = new ArrayList<Boolean>();
		ArrayList<Boolean> expectativas = new ArrayList<Boolean>();
		for(int i=0; i<codigosAtual.length; i++) {
			Disciplina d = new Disciplina(codigosAtual[i], nomesAtual[i], Integer.parseInt(chAtual[i]));
			if(request.getParameter("expectativa" + codigosAtual[i]).equals("Acho que vou passar")) {
				expectativas.add(true);
			}
			else {
				expectativas.add(false);
			}
			cursando.add(true);
			disciplinas.add(d);
		}		
		for(int i=0; i<codigosGrade.length; i++) {
			Disciplina d = new Disciplina(codigosGrade[i], nomesGrade[i], Integer.parseInt(chGrade[i]));
			cursando.add(false);
			expectativas.add(false);
			disciplinas.add(d);
		}
		for(int i=0; i<matriculaOrientadores.length ; i++) {
			try {
				user.enviarNotificacao(matriculaOrientadores[i], obsAluno, disciplinas, cursando, expectativas);
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
