<%@ page import ="javax.servlet.*" %>
<%@ page import ="orientacao.*" %>
<%@ page import ="java.util.*" %>



<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=width-device, initial-scale=1.0, shrink-to-fit=no">

		<title>Orientação Acadêmica</title>
		<link rel="icon" href="./imagens/BrasaoUFBA.png">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">   
	    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	    <link rel="stylesheet" type="text/css" href="./estilo/estiloNavBarProf.css">
	    <link rel="stylesheet" type="text/css" href="./estilo/estiloLerMensagem.css">
	    
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
	    <script src="http://netdna.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 
	    <script type="text/javascript" src="./script/scriptMsg.js"></script>
<% 
	Professor professor = (Professor) session.getAttribute("user");
	Orientacao ori = (Orientacao) session.getAttribute("orientacao");
	ArrayList<Disciplina> disc = ori.getDisciplinas();
	int i=0;
	int j=0;
%>	

	</head>
	<body>
		<nav class="navbar navbar-expand-md navbar-dark navbar-fixed-top px-5">
			<div class="container-fluid">
				<button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navHomePageProf">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navHomePageProf">
					<ul class="navbar-nav">	
						<li class="nav-item">
							<span class="navbar-brand"><i id="iconeProfessor" class="fas fa-chalkboard-teacher"></i></span>
							<div class="itensinfo">
								<span id="nome"> <% out.println(professor.getNome() + " " + professor.getSobrenome()); %> </span>
							</div>
						</li>
					</ul>
					<ul class="navbar-nav ml-auto" id="botoesnavbar">
						<li class = "nav-item mr-3" id = "alterar-cadastro">
		                    <button type = "button" onclick = "redirectCadastro();" class = "btn btn-alterar"> Alterar Dados Cadastrais </button>
		                </li>
		                <li class = "nav-item mr-4">
		                    <button onclick = "sair();" type = "button" class = "btn btn-danger"> Sair </button>
		                </li>
		                <li class = "nav-item">
		                    <span onclick = "redirectMsg();" id = "notif"> <i class="fas fa-envelope"></i> </span> 
		                    <span onclick = "redirectHome();" id = "notif"> <i class="fas fa-home"></i> </span>
		                </li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="mt-3 mb-2 row">
			<p class="offset-1 col-2" id="nomeAluno"><b>Remetente: </b> <% out.println(ori.getRemetente().getNome()); %></p>
			<p class="col-2"><b>Semestre: </b><% out.println(ori.getRemetente().getSemestre()); %></p>
		</div>

		<div class="mt-3 container shadow conteudos">
			<p id="conteudoMensagem"> <% out.println(ori.getObservacaoAluno()); %> </p>
		</div>

		<form method = "post" action = "responderMsg"> 

			<div class = "row">
				<div class = "offset-1 col-5">
					<h5 class = "sub-title"> Matérias cursadas atualmente </h5>
					<div class="container shadow conteudos" id="tabelaMateriasSemAtual">
						<table class="table">
							<thead class = "thead-light">
								<tr>
									<th>
										Matéria
									</th>
									<th>
										Carga Horária
									</th>
									<th>
										Expectativa
									</th>
								</tr>
							</thead>
							<tbody>
							<%
								for(i=0; i<disc.size(); i++){
									if(ori.getCursando().get(i) == true){
										out.println("<tr>");
										out.println("<td>");
										out.println(disc.get(i).getCodigo() + " - " + disc.get(i).getNome());
										out.println("</td>");
										out.println("<td>");
										out.println(Integer.toString(disc.get(i).getCargaHoraria()));
										out.println("</td>");
										out.println("<td>");
										if(ori.getAprovado().get(i) == true){
											out.println("Acho que vou passar");
										}
										else{
											out.println("Acho que vou reprovar");
										}
										out.println("</td>");
										out.println("</tr>");
									}
								}
							%>
							</tbody>
						</table>
					</div>
				</div>
				<div class = "col-5" >
					<h5 class = "sub-title"> Intenção de grade </h5>					
					<div class="container shadow conteudos" id = "tabelaMateriasProxSem">
						<table class="table">
							<thead class = "thead-light">
								<tr>
									<th>
										Matéria
									</th>
									<th>
										Carga Horária
									</th>
									<th>
										Veredicto
									</th>
								</tr>
							</thead>
							<tbody>
							<%
								for(j=0; j<disc.size(); j++){
									if(ori.getCursando().get(j) == false){
										out.println("<tr>");
										out.println("<td>");
										out.println(disc.get(j).getCodigo() + " - " + disc.get(j).getNome());
										out.println("</td>");
										out.println("<td>");
										out.println(Integer.toString(disc.get(j).getCargaHoraria()));
										out.println("</td>");									
										out.println("<td class = \"px-5\">");
										if(!ori.getLida()){
											out.println("<input type=\"checkbox\" name=\"veredicto[]\" value = \"" + j +  "\">");
										}
										else{
											if(ori.getAprovado().get(j)){
												out.println("<input type=\"checkbox\" checked disabled name=\"veredicto[]\" value = \"" + j +  "\">");
											}
											else{
												out.println("<input type=\"checkbox\" disabled name=\"veredicto[]\" value = \"" + j +  "\">");												
											}
										}
										out.println("</tr>");
									}
								}
							%>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<% 
				if(!ori.getLida()){
					out.println("<div class = \"mt-3 mb-3 container\">");
					out.println("<textarea placeholder = \"Escreva uma mensagem para resposta\" id = \"mensagem\" name = \"mensagem\" class = \"shadow form-control\" rows = 3></textarea>");
					out.println("</div>");
					out.println("<div class = \"row\">");
					out.println("<button type = \"submit\" class = \"offset-5 col-2 text-center btn btn-info\"> Responder </button>");
					out.println("</div>");
				}
				else{
					out.println("<div class = \"mt-3 mb-3 container\">");
					out.println("<textarea disabled id = \"mensagem\" name = \"mensagem\" class = \"shadow form-control\" rows = 3>" + ori.getObservacaoProf() + "</textarea>");
					out.println("</div>");
				}
			%>
		</form>
   <form method = "post" action = "sair" id = "formSair">
   		<input type = "hidden" id = "sair" name = "sair">
   		<input type = "hidden" id = "tipo" name = "tipo">
   </form> 		
	</body>
</html>