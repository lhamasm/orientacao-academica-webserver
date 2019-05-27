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
	Aluno aluno = (Aluno) session.getAttribute("aluno");
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
    <div class = "mt-2 row" id = "botoes">
        <button type = "button" class = "btn" id = "obrigatorias" onmouseover = "trocaCor('obg');" onmouseleave = "voltaCor('obg');" onclick = "alterarConteudo('obg');"> Matérias Obrigatórias </button>
        <button type = "button" class = "btn" id = "optativas" onmouseover = "trocaCor('op');" onmouseleave = "voltaCor('op');" onclick = "alterarConteudo('op');"> Matérias Optativas </button>
    </div>
    <div class = "mt-4 container" id = "container-obrigatorias">
        <h6> 1º Semestre </h6>
        <div class = "row" id = "materias">
            <button id = "obg1" type = "button" class = "ml-1 mt-1 btn btn-materias" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscObg(1);"> <b id = "codigoobg1"> MATA42 </b> <br> <span id = "nomeobg1"> Matemática Discreta I </span> </button>
            <button id = "obg2" type = "button" class = "ml-1 mt-1 btn btn-materias" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscObg(2);"> <b id = "codigoobg2"> MATA39 </b> <br> <span id = "nomeobg2"> Seminários de Introdução ao Curso </span> </button> 
            <button id = "obg3" type = "button" class = "ml-1 mt-1 btn btn-materias" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscObg(3);"> <b id = "codigoobg3"> MATA38 </b> <br> <span id = "nomeobg3"> Projeto de Circuitos Lógicos </span> </button> 
            <button id = "obg4" type = "button" class = "ml-1 mt-1 btn btn-materias" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscObg(4);"> <b id = "codigoobg4"> MATA37 </b> <br> <span id = "nomeobg4"> Introdução à  Lógica de Programação </span> </button> 
            <button id = "obg5" type = "button" class = "ml-1 mt-1 btn btn-materias" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscObg(5);"> <b id = "codigoobg5"> MATA02 </b> <br> <span id = "nomeobg5"> Cálculo A </span> </button> 
            <button id = "obg6" type = "button" class = "ml-1 mt-1 btn btn-materias" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscObg(6);"> <b id = "codigoobg6"> MATA01 </b> <br> <span id = "nomeobg6"> Geometria Analítica </span> </button>

        </div>
    </div>
    <div class = "mt-5 container" id = "container-optativas">
        <button class = "text-left col-12 btn btn-light link-optativas" href = "#infoDisc" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscOp(1)" id = "op1"> <b id = "codigoop1"> MATB15 </b> - <span id = "nomeop1"> Validação de Software </span> </button> 
        <button class = "text-left col-12 btn btn-light link-optativas" href = "#infoDisc" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscOp(2)" id = "op2"> <b id = "codigoop2"> MATA83 </b> - <span id = "nomeop2"> Tópicos em Sistemas Operacionais </span> </button> 
        <button class = "text-left col-12 btn btn-light link-optativas" href = "#infoDisc" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscOp(3)" id = "op3"> <b id = "codigoop3"> MATB26 </b> - <span id = "nomeop3"> Tópicos em Sistemas Multimídia </span> </button>
        <button class = "text-left col-12 btn btn-light link-optativas" href = "#infoDisc" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscOp(4)" id = "op4"> <b id = "codigoop4"> MATB06 </b> - <span id = "nomeop4"> Tópicos em Sistemas Distribuídos </span> </button>
        <button class = "text-left col-12 btn btn-light link-optativas" href = "#infoDisc" data-toggle = "modal" data-target = "#infoDisc" onclick = "identificaDiscOp(5)" id = "op5"> <b id = "codigoop5"> MATA86 </b> - <span id = "nomeop5"> Tópicos em Redes de Computadores </span> </button>
    </div>
    <div class="modal fade" role="dialog" id="infoDisc">
        <label id = "discEscolhida"> </label>
        <label id = "operacaoDisc"> </label>
        <label id = "tipoDisc"> </label>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id = "discNome"></h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body" id = "discInfo">
                    <span></span>
                </div>
                <div class="modal-footer">
                    <button id = "botaoModal" type = "button" class = "btn" data-dismiss = "modal" onclick = "alterarMateria();"> Adicionar à  grade </button>
              </div>
            </div>
        </div>
    </div>
    <form id = "disciplinasTotal">
    </form>
    <form id = "nomeDisciplinas">
    </form>

    <div class = "mt-5 row">
        <button type = "button" class = "offset-2 col-2 btn btn-info" data-toggle = "modal" data-target = "#modalGrade" onclick = "preencheGrade();"> Ver grade </button>
        <button type = "button" onclick = "redirect();" class = "offset-4 col-2 btn btn-success"> Continuar </button>    
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
                    <button type = "button" class = "btn btn-danger" data-dismiss = "modal""> Fechar </button>
              </div>
            </div>
        </div>
    </div>

</body>

<script type = "text/javascript" src="./script/scriptGrade1.js"> </script> 

</html>