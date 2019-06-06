<%@ page import ="javax.servlet.*" %>
<%@ page import ="orientacao.*" %>
<%@ page import ="java.util.*" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=width-device, initial-scale=1.0, shrink-to-fit=no">

		<title>Orientação Acadêmica</title>
		<link rel="icon" href="../../../imagens/BrasaoUFBA.png">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">   
	    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	    <link rel="stylesheet" type="text/css" href="./estilo/estiloNavBarProf.css">
	    <link rel="stylesheet" type="text/css" href="./estilo/estiloAlterarCadastro.css">	    

	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
	    <script src="http://netdna.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script> 

    	<script type="text/javascript" src="./script/scriptAlterarCadastro.js"></script>

<% 
	Professor professor = (Professor) session.getAttribute("user");
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
		                    <button type = "button" onclick = "redirectCadastroProf();" class = "btn btn-alterar"> Alterar Dados Cadastrais </button>
		                </li>
		                <li class = "nav-item mr-4" id = "sair">
		                    <button onclick = "sair();" type = "button" class = "btn btn-danger"> Sair </button>
		                </li>
		                <li class = "nav-item">
		                    <span onclick = "redirectMsgProf();" id = "notif"> <i class="fas fa-envelope"></i> </span>
		                    <span onclick = "redirectHomeProf();" id = "notif"> <i class="fas fa-home"></i> </span>
		                </li>
					</ul>
				</div>
			</div>
		</nav>
		<div class = "container-fluid">
	        <div class = "mt-5 py-3 container shadow" id = "container-cadastro">
	            <form name = "form-cadastro" method = "post" action = "alterarCadastro"> 
	                <div class = "row">
	                    <div class = "col-6 form-group">
	                        <label for = "nome"> Nome </label> 
	                        <input type = "text" class = "form-control" id = "nomeAlterar" name = "nomeAlterar" value = "<% out.println(professor.getNome()); %>" required>
	                    </div>
	                    <div class = "col-6 form-group">
	                        <label for = "sobrenome"> Sobrenome </label> 
	                        <input type = "text" class = "form-control" id = "sobrenome" name = "sobrenome" value = "<% out.println(professor.getSobrenome()); %>" required>
	                    </div>
	                </div>
	                <div class = "row">
	                    <div class = "col-6 form-group">
	                        <label for = "email"> E-mail </label>
	                        <input type = "email" class = "form-control" id = "email" value = "<% out.println(professor.getEmail()); %>" required>
	                    </div>
	                    <input type = "hidden" value = "professor" name = "tipo">
	                </div>
	                <hr> 
	                <button type = "submit" class = "mt-2 mb-3 col-12 btn btn-info"> Alterar </button>
	            </form>
	        </div>
	   </div>
   <form method = "post" action = "sair" id = "formSair">
   		<input type = "hidden" id = "sair" name = "sair">
   </form> 	   
	</body>
</html>