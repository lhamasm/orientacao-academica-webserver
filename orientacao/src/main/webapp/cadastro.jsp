<%@ page import ="javax.servlet.*" %>
<%@ page import ="orientacao.*" %>

<!doctype html>

<html>

<head>
	<meta charset="utf-8">
	<meta name="view-port" content="width=width-device, initial-scale=1.0, shrink-to-fit=no">
	<title>Orientação Acadêmica</title>
	<link rel="icon" href="./imagens/BrasaoUFBA.png">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">   
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">     
	<link rel="stylesheet" type="text/css" href="./estilo/estiloCadastro.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script> 
</head>

<% 
	String erro = (String) session.getAttribute("erro"); 
	session.removeAttribute("erro"); 
%>

<body> 
    <div class = "container-fluid">
        <span class = "text-center" onclick = "redirectIndex();"> <i class="mt-4 col-12 fas fa-graduation-cap" id = "icone-cadastro"></i> </span>   
        <h3 class = "mt-1 text-center" id = "titulo"> Orientação Acadêmica </h3>
        <div class = "mt-3 container shadow" id = "container-cadastro">
            <form name = "form-cadastro" onsubmit = "return validar();" method = "post" action = "cadastro"> 
            	<div class = "form-group mt-3">
                    <label for = "tipo"> Tipo de Usuário </label>
                    <select class = "form-control" id="tipo" name = "tipo" onchange="alteraConteudo();">
                    	<option value="default" id="default" selected="selected">Selecione</option>	
					  	<option value="Estudante" id="t1">Estudante</option>
					  	<option value="Docente" id="t2">Docente</option>
					</select>
                </div>
            	<div class = "row">
	            	<div class = "col-6 form-group">
	                    <label for = "nome"> Nome </label> 
	                    <input name = "nome" type = "text" class = "form-control" id = "nome">
	                </div>
	                <div class = "col-6 form-group">
                    	<label for = "sobrenome"> Sobrenome </label> 
                    	<input name = "sobrenome" type = "text" class = "form-control" id = "sobrenome">
                	</div>
	            </div>
	            <div class = "row">
	                <div class = "col-6 form-group">
	                    <label for = "cpf"> CPF </label>
	                    <input name = "cpf" type = "text" class = "form-control" id = "cpf" min = "11">
	                </div>
	                <div class = "col-6 form-group">
	                    <label for = "email"> E-mail </label>
	                    <input name = "email" type = "email" class = "form-control" id = "email">
                	</div>
	            </div>
                <div class="row">
	                <div class = "col-6 form-group">
	                    <label for = "senha"> Senha </label>
	                    <input name = "senha" type = "password" class = "form-control" id = "senha">
	                </div>
	                <div class = "col-6 form-group">
	                    <label for = "repsenha"> Repita a senha </label>
	                    <input type = "password" class = "form-control" id = "repsenha">
	                </div>
	            </div>
	            <hr> 
	            <div class= "row">
	                <div class = "col-4 form-group">
	                    <label for = "matricula"> Número de Matrícula </label> 
	                    <input name = "matricula" type = "text" class = "form-control" id = "matricula">
	                </div>
	                <div class = "col-6 form-group" id="prof-departamento">
	                    <label for = "dep"> Departamento </label> <br>
                    	<select name = "dep" class = "form-control" id="dep">
                    		<option value="1" id="d1">Departamento de Computação</option>
						</select>
	                </div>
	                <div class = "col-6 form-group" id="aluno-curso">
	                    <label for = "curso"> Curso </label> <br>
                    	<select name = "curso" class = "form-control" id="curso">
                    		<option value="112" id="c1">Bacharelado em Ciência da Computação</option>
						</select>
	                </div>	                               
	            	<div class = "col-2 form-group" id="aluno-semestre">
	                    <label for = "semestre"> Semestre </label>
	                     <input name = "semestre" type = "number" class = "form-control" id = "semestre" min="1">        	
	                </div>
	            </div>
                <button type = "submit" class = "mt-2 mb-3 col-12 btn btn-info"> Sign Up </button>
            </form>
        </div>
   </div>
</body>

<script type = "text/javascript" src="./script/scriptCadastro.js"> </script>  

<script type = "text/javascript"> 
	if(<% out.println("'" + erro + "'"); %> == "errou"){
		alert("Não foi possível realizar o cadastro");		
	}
</script>

</html>