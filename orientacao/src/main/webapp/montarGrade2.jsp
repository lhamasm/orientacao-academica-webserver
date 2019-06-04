<%@ page import ="javax.servlet.*" %>
<%@ page import ="orientacao.*" %>
<%@ page import ="java.util.*" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Orientação Acadêmica</title>
	
	<link rel="icon" href="./imagens/BrasaoUFBA.png">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet'>
    <link rel="stylesheet" type="text/css" href="./estilo/estiloNavBarAluno.css">
    <link rel="stylesheet" type="text/css" href="./estilo/estiloGrade2.css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./script/scriptGrade.js"></script>

</head>

<% 
	Aluno aluno = (Aluno) session.getAttribute("user");
%>

<body>
    <nav class="navbar navbar-expand-lg navbar-fixed-top px-5" id = "navbar">
        <div class="container-fluid">
            <ul class = "navbar-nav">
                <li class = "nav-item">
                    <span class = "mr-2"> <i class="fas fa-user-graduate" id = "icone-user"></i> </span>
                </li>
                <li class = "nav-item">
                    <span id = "nome"> <% out.println(aluno.getNome() + " " + aluno.getSobrenome()); %> </span>
                    <span id = "curso"> <% out.println(aluno.getCurso().getNome()); %> </span> 
                    <span id = "sem"> <% out.println(aluno.getSemestre() + "º Semestre"); %> </span>
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
                    <span class = "ml-5" id = "notif" onclick = "redirectMsg();"> <i class="fas fa-envelope"></i> </span> <span class="px-1 pt-0 badge badge-pill badge-danger" id = "notif-num">1</span>
                </li>
                <li class = "nav-item">
                    <span id = "notif" onclick = "redirectHome();"> <i class="fas fa-home"></i> </span>
                </li>                
            </ul>
        </div>
    </nav>
    
    <div class="container">
    	<button type="button" class="btn" id="voltar" title="Voltar à página anterior" onclick="redirectBack();"> < </button>
    	<form method = "post" onsubmit = "return juntaMaterias();" action = "montaOrientacao">
    		<input type = "hidden" name = "codigosGrade" id = "codigosGrade">
    		<input type = "hidden" name = "nomesGrade" id = "nomesGrade">
    		<input type = "hidden" name = "chGrade" id = "chGrade">
    		<input type = "hidden" name = "codigosAtual" id = "codigosAtual">
    		<input type = "hidden" name = "nomesAtual" id = "nomesAtual">
    		<input type = "hidden" name = "chAtual" id = "chAtual">    		
    		<input type = "hidden" name = "orientadores" id = "orientadores">
	    	<h6>Matérias que estou cursando esse semestre</h6>
	    	<div class="row form-group">
	    		<div id="inserir-materias" class="row">
	    		<button type="button" class="btn mt-2" id="adicionar-materias" data-toggle="modal" data-target="#lista-de-materias" data-backdrop="static" keyboard="false"><i class="fas fa-plus"></i></button>	    		
	    		</div>
	    	</div>

	    	<h6 class="mt-4">Escolha os orientadores</h6>
                <div class="list-group" style = "max-height: 200px; overflow-y: scroll;">
                	<% 	
                		for(int k=0; k<aluno.getCurso().getDepartamento().getProfessores().size(); k++){
                			out.println("<button id = \"orientador" + aluno.getCurso().getDepartamento().getProfessores().get(k).getMatricula() + "\" onclick = \"selecionaOrientador('" + aluno.getCurso().getDepartamento().getProfessores().get(k).getMatricula() + "');\" class=\"list-group-item list-group-item-action\">" + aluno.getCurso().getDepartamento().getProfessores().get(k).getNome() + "</button>");
                		}
                	// <button id = "orientador1" onclick = "selecionaOrientador('1');" class="list-group-item list-group-item-action">Tiago Januário</button>
                	%>
                					
				</div>
	    	<h6 class="mt-4">Algo a acrescentar?</h6>
	    	<TEXTAREA name = "obsAluno" class="form-control" cols="10"></TEXTAREA>
	    	
	    	<div id="subirForm">
	    		<button class="btn mt-4 btn-info" type="submit">Enviar</button>
	    	</div>
	    </form>
    </div>

    <div class="modal fade" id="lista-de-materias">
    	<div class="modal-dialog modal-dialog-centered">
    		<div class="modal-content">
		    	<div class="modal-header">
		    		<h6 class="modal-title offset-1 col-10 text-center">Selecione as matérias que você está cursando</h6>
		    		<button type="button" class="close col-1" data-dismiss="modal">&times;</button>
		    	</div>
		    	<div class="modal-body container-fluid">
		    		<ul>
		    		<%
		    			for(int i=0; i<aluno.getCurso().getObrigatorias().size(); i++){
		    				out.println("<li>");
		    				out.println("<div class=\"btn materias\" id = \"" + aluno.getCurso().getObrigatorias().get(i).getCodigo() + "Div\" onclick=\"expandir_botao('" + aluno.getCurso().getObrigatorias().get(i).getCodigo() + "', this);\">");
	    					out.println("<h6>" + aluno.getCurso().getObrigatorias().get(i).getCodigo() + " - <span id = \"nome" + aluno.getCurso().getObrigatorias().get(i).getCodigo() + "\">" + aluno.getCurso().getObrigatorias().get(i).getNome() + "</h6>");
		    				out.println("<div class=\"botao-materia-expandida\" id=\""+ aluno.getCurso().getObrigatorias().get(i).getCodigo() + "Exp\">");
		    				out.println("<hr>");
		    				out.println("<span class=\"col-3 col-sm-3 col-lg-3 col-xl-3\"><b> Carga Horária:</b> <span id = \"carga" + aluno.getCurso().getObrigatorias().get(i).getCodigo() + "\">" + aluno.getCurso().getObrigatorias().get(i).getCargaHoraria() + "h </span> </span> <br>");	    						
		    				out.println("</div>");
	    					out.println("</div>");
	    					out.println("</li>");	
		    			}
	    				for(int i=0; i<aluno.getCurso().getOptativas().size(); i++){
		    				out.println("<li>");
		    				out.println("<div class=\"btn materias\" id = \"" + aluno.getCurso().getOptativas().get(i).getCodigo() + "Div\" onclick=\"expandir_botao('" + aluno.getCurso().getOptativas().get(i).getCodigo() + "', this);\">");
	    					out.println("<h6>" + aluno.getCurso().getOptativas().get(i).getCodigo() + " - <span id = \"nome" + aluno.getCurso().getOptativas().get(i).getCodigo() + "\">" + aluno.getCurso().getOptativas().get(i).getNome() + " </span> </h6>");
		    				out.println("<div class=\"botao-materia-expandida\" id=\""+ aluno.getCurso().getOptativas().get(i).getCodigo() + "Exp\">");
		    				out.println("<hr>");
		    				out.println("<span class=\"col-3 col-sm-3 col-lg-3 col-xl-3\"><b> Carga Horária:</b> <span id = \"carga" + aluno.getCurso().getOptativas().get(i).getCodigo() + "\">" + aluno.getCurso().getOptativas().get(i).getCargaHoraria() + "h </span> </span> <br>");	    						
		    				out.println("</div>");
	    					out.println("</div>");
	    					out.println("</li>");	
		    			}
		    		%>
		    		</ul>
		    	</div>
    	        <div class="modal-footer">
          			<button type="button" class="btn btn-success" data-dismiss="modal" id = "fechaModal" onclick = "codigosmateriasselecionadas.forEach(confirmaMaterias);">Confirmar</button>
        		</div>
		    </div>
		</div>
    </div> 
   <form method = "post" action = "sair" id = "formSair">
   		<input type = "hidden" id = "sair" name = "sair">
   </form>     
</body>
</html>