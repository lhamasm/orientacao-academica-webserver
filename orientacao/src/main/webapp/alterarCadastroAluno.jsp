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
	<link rel="stylesheet" type="text/css" href="./estilo/estiloAlterarCadastroAluno.css">
    <link rel="stylesheet" type="text/css" href="./estilo/estiloNavBarAluno.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script> 
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-fixed-top px-5" id = "navbar">
        <div class="container-fluid">
            <ul class = "navbar-nav">
                <li class = "nav-item">
                    <span class = "mr-2"> <i class="fas fa-user-graduate" id = "icone-user"></i> </span>
                </li>
                <li class = "nav-item">
                    <span id = "nome"> Bruno Dias da Silva Ferreira </span>
                    <span id = "curso"> Ciência da Computação </span> 
                    <span id = "sem"> 7º Semestre </span>
                </li>
            </ul>
            <ul class = "navbar-nav">
                <li class = "nav-item mr-3" id = "alterar-cadastro">
                    <button type = "button" class = "btn btn-alterar"> Alterar Dados Cadastrais </button>
                </li>
                <li class = "nav-item" id = "sair">
                    <button type = "button" class = "btn btn-danger"> Sair </button>
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
    <div class = "container-fluid">
        <div class = "mt-5 py-3 container shadow" id = "container-cadastro">
            <form name = "form-cadastro" onsubmit = "return validar();"> 
                <div class = "row">
                    <div class = "col-6 form-group">
                        <label for = "nome"> Nome </label> 
                        <input type = "text" class = "form-control" id = "nome">
                    </div>
                    <div class = "col-6 form-group">
                        <label for = "sobrenome"> Sobrenome </label> 
                        <input type = "text" class = "form-control" id = "sobrenome">
                    </div>
                </div>
                <div class = "row">
                    <div class = "col-6 form-group">
                        <label for = "cpf"> CPF </label>
                        <input type = "text" class = "form-control" id = "cpf" min = "11">
                    </div>
                    <div class = "col-6 form-group">
                        <label for = "email"> E-mail </label>
                        <input type = "email" class = "form-control" id = "email">
                    </div>
                </div>
                <hr> 
                <div class= "row">
                    <div class = "col-6 form-group">
                        <label for = "matricula"> Número de Matrícula </label> 
                        <input type = "text" class = "form-control" id = "matricula">
                    </div>          
                    <div class = "col-4 form-group" id="aluno-curso">
                        <label for = "curso"> Curso </label> <br>
                        <select class = "form-control" id="curso">
                            <option value="curso1" id="c1">Ciência da Computação</option>
                        </select>
                    </div>                         
                    <div class = "col-2 form-group" id="aluno-semestre">
                        <label for = "semestre"> Semestre </label>
                         <input type = "number" class = "form-control" id = "semestre" min="1">         
                    </div>
                </div>
                <button type = "submit" class = "mt-2 mb-3 col-12 btn btn-info"> Alterar </button>
            </form>
        </div>
   </div>
</body>

<script type = "text/javascript" src = "./script/scriptAlterarCadastroAluno.js"> </script>

</html>