package orientacao.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import orientacao.*;
import orientacao.Aluno;
import orientacao.Professor;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;


@SuppressWarnings("serial")
@WebServlet(
        name = "montaorientacaoservlet",
        urlPatterns = "/montaOrientacao"
)
public class MontarGradeServlet extends HttpServlet {
	@Override
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String grade = request.getParameter("codigosGrade");
		String atual = request.getParameter("codigosAtual");
		String orientadoresA = request.getParameter("orientadores");
		String[] codigosGrade = grade.split("/");
		String[] codigosAtual = atual.split("/");
		String[] matriculaOrientadores = orientadoresA.split("/");
		String obsAluno = request.getParameter("obsAluno");
		HttpSession session = request.getSession();
		Aluno user = (Aluno) session.getAttribute("user");
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		ArrayList<Boolean> cursando = new ArrayList<Boolean>();
		ArrayList<Boolean> expectativas = new ArrayList<Boolean>();
		for(int i=0; i<codigosGrade.length; i++) {
			Disciplina d = null;
			try {
				d = user.recuperarDisciplina(codigosGrade[i]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cursando.add(false);
			disciplinas.add(d);
		}
		for(int i=0; i<codigosAtual.length; i++) {
			Disciplina d = null;
			try {
				d = user.recuperarDisciplina(codigosAtual[i]);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(request.getParameter("expectativa" + codigosAtual[i]).equals("Acho que vou passar")) {
				expectativas.add(true);
			}
			else {
				expectativas.add(false);
			}
			cursando.add(true);
			disciplinas.add(d);
		}
		String pattern = "DD/MM/YYYY";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		String date = simpleDateFormat.format(new Date());
		String patternHora = "HH:mm";
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(patternHora);
		simpleDateFormat1.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
		String hora = simpleDateFormat1.format(new Date());
		System.out.println(hora + " " + date);
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
