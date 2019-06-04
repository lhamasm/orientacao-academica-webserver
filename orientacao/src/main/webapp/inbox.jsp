<%@ page import ="javax.servlet.*" %>
<%@ page import ="orientacao.*" %>

<!doctype html>

<html>

<head>
	<meta charset="utf-8">
	<meta name="view-port" content="width=width-device, initial-scale=1.0, shrink-to-fit=no">
	<title>Orientação Academica</title>
    <link rel="icon" href="./imagens/BrasaoUFBA.png">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">   
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">     
	<link rel="stylesheet" type="text/css" href="./estilo/estiloInbox.css">
    <link rel="stylesheet" type="text/css" href="./estilo/estiloNavBarAluno.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>  
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type = "text/javascript" src="./script/scriptInbox.js"> </script>
</head>

<% 
	Aluno aluno = (Aluno) session.getAttribute("user");
    ArrayList<Orientacao> respostas = aluno.recuperarNotificacoes();
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
                    <span id = "sem"> <% out.println(aluno.getSemestre() + "� Semestre"); %> </span>
                </li>
            </ul>
            <ul class = "navbar-nav">
                <li class = "nav-item mr-3" id = "alterar-cadastro">
                    <button type = "button" class = "btn btn-alterar"> Alterar Dados Cadastrais </button>
                </li>
                <li class = "nav-item">
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
   	<div class = "mt-3 container">
        <h2 id ="title">Caixa de Entrada</h2>
    	<table class = "table">
    		<thread>
        		<tr>
					<th>Remetente</th>
					<th>Veredicto</th>
					<th>Data</th>
					<th>Hora </th>
    			</tr>
    		</thread>
    		<tbody id = "cxMensagens">
                <% for (int i = 0; i < respostas.size() ; i++) {
                    ArrayList<Boolean> vindividual = respostas.get(i).getAprovado();
                    
                    String veredicto = "aprovado";
                    for(int j = 0; j < vindividual.size(); j++){
                        if(!vindividual.get(j)){
                            veredicto = "reprovado";
                        }
                    }
                    
                    out.println("<tr class = \"btn-msg-" + veredicto + "\" data-toggle=\"modal\" data-target=\"#m" + i + "\">");
                    out.println("<td id = \"msg" + i + "remetente\">" + respostas.get(i).getRemetente().getNome() + " " + respostas.get(i).getRemetente().getSobrenome() + "</td>");
                    out.println("<td class=\"veredicto\" id = \"msg" + i + "veredicto\"><b>" + veredicto + "</b></td>");
                    out.println("<td id = \"msg" + i + "data\">" + respostas.get(i).getData() + "</td>");
                    out.println("<td id = \"msg" + i + "hora\">" + respostas.get(i).getHorario() + "</td></tr>");

                    out.println("<div class=\"modal fade\" id=\"m" + i + "\" role=\"dialog\" tabindex=\"-1\"><div class=\"modal-dialog\"><div class=\"modal-content\"><div class=\"modal-header\"><button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button><h4 class=\"modal-title\"></h4></div><div class=\"modal-body\"><div class=\"container\">");
                    out.println("<table><thead>");
                    for(int j = 0; j < vindividual.size(); j++){
                        if(vindividual.get(j)){
                            out.println("<th class=\"aprovado\">");
                        }
                        else{
                            out.println("<th class=\"reprovado\">");
                        }
                        out.println(respostas.get(i).getDisciplinas().get(j).getNome() + "</th>");
                    }
                    out.println("</thead><tbody></tbody></table><p>");
                    out.println(respostas.get(i).getObservacao());
                    out.println("</p></div><div class=\"modal-footer\"><button type=\"button\" class=\"fechaModal btn btn-danger\" data-dismiss=\"modal\">Close</button></div></div></div></div>");
                } %>
            </tbody>
    	</table>
     </div>
    <form method = "post" action = "sair" id = "formSair">
        <input type = "hidden" id = "sair" name = "sair">
    </form> 
</body>
</html>