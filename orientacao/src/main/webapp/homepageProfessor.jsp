<%@ page import ="javax.servlet.*" %>
<%@ page import ="orientacao.*" %>

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
	    <link rel="stylesheet" type="text/css" href="./estilo/estiloHomepageProf.css">
	    <link rel="stylesheet" type="text/css" href="./estilo/estiloNavBarProf.css">
    	<script type = "text/javascript" src="./script/scriptHomepageProfessor.js"> </script> 	    
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
	    <script src="http://netdna.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 
	</head>
	<body>
		
<% 
	Professor professor = (Professor) session.getAttribute("user");
%>	
		<nav class="navbar navbar-expand-md navbar-dark navbar-fixed-top px-5">
			<div class="container-fluid">
				<button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navHomePageProf">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navHomePageProf">
					<ul class="navbar-nav">	
						<li class="nav-item">
							<span class="navbar-brand"><i id = "iconeProfessor" class="fas fa-chalkboard-teacher"></i></span>
							<div class="itensinfo">
								<span id="nome"> <% out.println(professor.getNome() + " " + professor.getSobrenome()); %> </span>
								<span id="departamento"><% //out.println(professor.getDepartamento().getNome()); %></span>
							</div>
						</li>
					</ul>
					<ul class="navbar-nav ml-auto" id="botoesnavbar">
						<li class = "nav-item mr-3" id = "alterar-cadastro">
		                    <button type = "button" onclick = "redirectCadastro();" class = "btn btn-alterar"> Alterar Dados Cadastrais </button>
		                </li>
		                <li class = "nav-item mr-4" id = "sair">
		                    <button onclick = "sair();" type = "button" class = "btn btn-danger"> Sair </button>
		                </li>
		                <li class = "nav-item">
		                    <span onclick = "redirectMsg();" id = "notif"> <i class="fas fa-envelope"></i> </span> <span class="px-1 pt-0 badge badge-pill badge-danger" id = "notif-num">1</span>
		                    <span onclick = "redirectHome();" id = "notif"> <i class="fas fa-home"></i> </span>
		                </li>
					</ul>
				</div>
			</div>
		</nav>
		<div class = "px-5 container">
	        <br>
	        <br>
	        <h1 class = "mt-5"> Sistema de <br> Orientação Acadêmica </h1> <br>
	        <div class="row">
	        	<div class="col-md-4 col-lg-3">
	        		<button onclick = "redirect();" type = "button" class = "btn btn-block" id = "botaoGrade"> Veja suas solicitações</button>
	        	</div>
	        </div>
	    </div>
   <form method = "post" action = "sair" id = "formSair">
   		<input type = "hidden" id = "sair" name = "sair">
   </form> 
	</body>
</html>