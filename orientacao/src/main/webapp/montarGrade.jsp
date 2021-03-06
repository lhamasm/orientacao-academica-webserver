<%@ page import ="orientacao.*" %>
<%@ page import ="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!doctype html>

<html>

<head>
	<meta charset="utf-8">
	<meta name="view-port" content="width=width-device, initial-scale=1.0, shrink-to-fit=no">
	<title>Orienta��o Acad�mica</title>
    <link rel="icon" href="./imagens/BrasaoUFBA.png">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">   
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="./estilo/estiloGrade1.css">
    <link rel="stylesheet" type="text/css" href="./estilo/estiloNavBarAluno.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script> 
</head>

<% 
	Aluno aluno = (Aluno) session.getAttribute("user");
	ArrayList<Disciplina> b = aluno.getCurso().getOptativas();
	ArrayList<Obrigatoria> a = aluno.getCurso().getObrigatorias();
%>

<body>
    <form id = "disciplinasTotal">
    </form>
    <form id = "nomeDisciplinas">
    </form>
    <nav class="navbar navbar-expand-lg navbar-fixed-top px-5" id = "navbar">
        <div class="container-fluid">
            <ul class = "navbar-nav">
                <li class = "nav-item">
                    <span class = "mr-2"> <i class="fas fa-user-graduate" id = "icone-user"></i> </span>
                </li>
                <li class = "nav-item">
                    <span id = "nome"> <% out.println(aluno.getNome() + " " + aluno.getSobrenome()); %> </span>
                    <span id = "curso"> <% out.println(aluno.getCurso().getNome()); %> </span> 
                    <span id = "sem"> <% out.println(aluno.getSemestre() + "� Semestre"); %> </span>
                </li>
            </ul>
            <ul class = "navbar-nav">
                <li class = "nav-item mr-3" id = "alterar-cadastro">
                    <button type = "button" class = "btn btn-alterar"> Alterar Dados Cadastrais </button>
                </li>
                <li class = "nav-item" id = "sair">
                    <button onclick = "sair();" type = "button" class = "btn btn-danger"> Sair </button>
                </li>
                <li class = "nav-item">
                    <span class = "ml-5" id = "notif" onclick = "redirectMsg();"> <i class="fas fa-envelope"></i> </span>
                    <span id = "notif" onclick = "redirectHome();"> <i class="fas fa-home"></i> </span>
                </li>                
            </ul>
        </div>
    </nav>
    <div class = "mt-2 row" id = "botoes">
        <button type = "button" class = "btn" id = "obrigatorias" onmouseover = "trocaCor('obg');" onmouseleave = "voltaCor('obg');" onclick = "alterarConteudo('obg');"> Mat�rias Obrigat�rias </button>
        <button type = "button" class = "btn" id = "optativas" onmouseover = "trocaCor('op');" onmouseleave = "voltaCor('op');" onclick = "alterarConteudo('op');"> Mat�rias Optativas </button>
    </div>
    <div class = "mt-4 container" id = "container-obrigatorias">
     	  	<% 
     	  		for(int j=1; j<=aluno.getCurso().getDuracao(); j++){
     	  			out.println("<h6>" + j + "� Semestre </h6>");
     	  			out.println("<div class = \"mb-1 row\">");
  	  				for (int i=0; i< a.size(); i++) {
  	  					if(a.get(i).getSemestreSugerido() == j){
      						out.println("<button class = 'ml-1 mt-1 btn btn-materias' data-toggle = 'modal' data-target = '#infoDisc" + a.get(i).getCodigo() + "' onclick = \"identificaDiscObg('" + a.get(i).getCodigo() + "');\" id ='" +a.get(i).getCodigo()+ "'> <b id = 'codigo" +a.get(i).getCodigo()+"'>" + a.get(i).getCodigo() +"</b> <br> <span id = 'nome" +a.get(i).getCodigo()+"'>" + a.get(i).getNome() + "</span> </button>");
      						out.println("<div class=\"modal fade\" role=\"dialog\" id=\"infoDisc" + a.get(i).getCodigo() + "\">");
      	        		    out.println("<label style =\"display: none;\" id = \"discEscolhida"+ a.get(i).getCodigo() + "\"> </label>");
      	        		    out.println("<label style =\"display: none;\" id = \"operacaoDisc"+ a.get(i).getCodigo() + "\"></label>");
      	        		    out.println("<label style =\"display: none;\" id = \"tipoDisc" + a.get(i).getCodigo() + "\"> </label>");
      	        		    out.println("<div class=\"modal-dialog modal-dialog-centered\">");
      	        		    out.println("<div class=\"modal-content\">");
      	        		    out.println("<div class=\"modal-header\">");
      	        		    out.println("<h4 class=\"modal-title\">" + a.get(i).getNome() + "</h4>");
      	        		    out.println("<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>");
      	        		    out.println("</div>");
      	        		    out.println("<div class=\"modal-body\"");
      	        		    out.println("<p> <b> Carga Horaria: </b> <span id = \"carga"+ a.get(i).getCodigo() + "\">" + a.get(i).getCargaHoraria()+ "</span>h </p>");
      	        			out.println("</div>");
      	        		    out.println("<div class=\"modal-footer\">");
      	        		    out.println("<button id = \"botaoModal" + a.get(i).getCodigo() + "\" type = \"button\" class = \"btn\" data-dismiss = \"modal\" onclick = \"alterarMateria('" + a.get(i).getCodigo() + "');\"> Adicionar na  grade </button>");
      	        			out.println("</div>");
      	        			out.println("</div>");
      	        			out.println("</div>");
      	        			out.println("</div>");  	  					
  	  					}
 	  				}
  	  				out.println("</div>");
  	  			}     		
     		%>
    </div>
    <div class = "mt-5 container" id = "container-optativas">
    	  	<% 
    	  		for (int j=0; j < b.size(); j++) {
        			out.println("<button class = 'text-left col-12 btn btn-light link-optativas' data-toggle = 'modal' data-target = '#infoDisc" + b.get(j).getCodigo() + "' onclick = \"identificaDiscOp('" + b.get(j).getCodigo() + "');\" id ='" +b.get(j).getCodigo()+ "'> <b id = 'codigo" +b.get(j).getCodigo()+"'>" + b.get(j).getCodigo() +"</b> - <span id = 'nome" +b.get(j).getCodigo()+"'>" + b.get(j).getNome() + "</span> </button>");
        		    out.println("<div class=\"modal fade\" role=\"dialog\" id=\"infoDisc" + b.get(j).getCodigo() + "\">");
        		    out.println("<label style =\"display: none;\" id = \"discEscolhida"+ b.get(j).getCodigo() + "\"> </label>");
        		    out.println("<label style =\"display: none;\" id = \"operacaoDisc"+ b.get(j).getCodigo() + "\"></label>");
        		    out.println("<label style =\"display: none;\" id = \"tipoDisc" + b.get(j).getCodigo() + "\"> </label>");
        		    out.println("<div class=\"modal-dialog modal-dialog-centered\">");
        		    out.println("<div class=\"modal-content\">");
        		    out.println("<div class=\"modal-header\">");
        		    out.println("<h4 class=\"modal-title\">" + b.get(j).getNome() + "</h4>");
        		    out.println("<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>");
        		    out.println("</div>");
        		    out.println("<div class=\"modal-body\"");
        		    out.println("<p> <b> Carga Horaria: </b> <span id = \"carga" + b.get(j).getCodigo() + "\">" + b.get(j).getCargaHoraria()+ "</span>h </p>");
        			out.println("</div>");
        		    out.println("<div class=\"modal-footer\">");
        		    out.println("<button id = \"botaoModal" + b.get(j).getCodigo() + "\" type = \"button\" class = \"btn\" data-dismiss = \"modal\" onclick = \"alterarMateria('" + b.get(j).getCodigo() + "');\"> Adicionar na  grade </button>");
        			out.println("</div>");
        			out.println("</div>");
        			out.println("</div>");
        			out.println("</div>");
    	  		}     		
       		%>
    </div>

    <div class = "mt-5 row">
        <button type = "button" class = "offset-2 col-2 btn btn-info" data-toggle = "modal" data-target = "#modalGrade" onclick = "preencheGrade();"> Ver grade </button>
        <button type = "button" onclick = "redirectGrade1();" class = "offset-4 col-2 btn btn-success"> Continuar </button>    
    </div>

    <div class="modal fade" role="dialog" id="modalGrade">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Disciplinas Escolhidas</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body" id = "gradeInfo">
                </div>
                <div class="modal-footer">
                    <button type = "button" class = "btn btn-danger" data-dismiss = "modal"> Fechar </button>
              </div>
            </div>
        </div>
    </div>
   <form method = "post" action = "sair" id = "formSair">
   		<input type = "hidden" id = "sair" name = "sair">
   </form>     
</body>
<script type = "text/javascript" src="./script/scriptGrade1.js"> </script>     
<script type = "text/javascript" src="./script/scriptGrade.js"> </script>  	   
</html>