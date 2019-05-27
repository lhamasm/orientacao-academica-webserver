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
	<script type="text/javascript" src="./script/scriptGrade2.js"></script>

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
    
    <div class="container">
    	<button type="button" class="btn" id="voltar" data-toggle="tooltip" title="Voltar a página anterior" onclick="redirectBack();"><</button>
    	<form autocomplete="off">
	    	<h6>MATÉRIAS QUE ESTOU CURSANDO ESTE SEMESTRE</h6>
	    	<div class="row form-group">
	    		<div id="inserir-materias" class="row">
	    		</div>
	    		<button type="button" class="btn mt-2 ml-3" id="adicionar-materias" data-toggle="modal" data-target="#lista-de-materias" data-backdrop="static" keybord="false"><i class="fas fa-plus"></i></button>
	    	</div>

	    	<h6 class="mt-4">ESCOLHA O ORIENTADOR</h6>
	    	<div class="row form-group autocomplete">
	    		<input type="text" name="orientador" id="input-orientador" class="form-control col-11">
	    		<button type="button" class="btn btn-secondary"><i class="fas fa-plus"></i></button>
	    	</div>

	    	<h6 class="mt-4">ALGO A ACRESCENTAR?</h6>
	    	<TEXTAREA class="form-control" cols="10"></TEXTAREA>
	    	
	    	<div id="subirForm">
	    		<button class="btn mt-4 btn-outline-primary" type="submit">ENVIAR</button>
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
		    			<li>
		    				<div class="btn materias" onclick="expandir_botao('MATA61', this);">
		    					<h6>MATA61 - Compiladores</h6>
			    				<div class="botao-materia-expandida" id="MATA61">
			    					<hr>
			    					<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Carga Horária:</b> 00h</span> <br>
		    						
		    						<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Desbloqueia:</b> Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software </span>
			    				</div>
		    				</div>
		    			</li>
		    			<li>
		    				<div class="btn materias" onclick="expandir_botao('MATA60',this);">
		    					<h6>MATA60 - Banco de Dados</h6>
		    					<div class="botao-materia-expandida" id="MATA60">
			    					<hr>
			    					<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Carga Horária:</b> 00h</span> <br>
		    						
		    						<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Desbloqueia:</b> Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software </span>
			    				</div> 
		    				</div>
		    			</li>
		    			<li>
		    				<div class="btn materias" onclick="expandir_botao('MATA63',this);">
		    					<h6>MATA63 - Engenharia de Software II</h6>
		    					<div class="botao-materia-expandida" id="MATA63">
			    					<hr>
			    					<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Carga Horária:</b> 00h</span> <br>
		    						
		    						<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Desbloqueia:</b> Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software </span>
			    				</div> 
		    				</div>
		    			</li>
		    			<li>
		    				<div class="btn materias" onclick="expandir_botao('MATC84',this);">
		    					<h6>MATC84 - Laboratório Web</h6>
		    					<div class="botao-materia-expandida" id="MATC84">
			    					<hr>
			    					<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Carga Horária:</b> 00h</span> <br>
		    						
		    						<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Desbloqueia:</b> Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software </span>
			    				</div>
		    				</div>
		    			</li>
		    			<li>
		    				<div class="btn materias" onclick="expandir_botao('MATE48',this);">
		    					<h6>MATE48 - LIBRAS I- LÍNGUA BRASILEIRA DE SINAIS NIVEL I</h6>
		    					<div class="botao-materia-expandida" id="MATE48">
			    					<hr>
			    					<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Carga Horária:</b> 00h</span> <br>
		    						
		    						<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Desbloqueia:</b> Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software </span>
			    				</div> 
		    				</div>
		    			</li>
		    			<li>
		    				<div class="btn materias" onclick="expandir_botao('MATF34',this);">
		    					<h6>MATF34 - ACCS:PROGRAMAÇÃO COMPETITIVA-COMPUTAÇÃO PARA ALUNOS</h6> 
		    					<div class="botao-materia-expandida" id="MATF34">
			    					<hr>
			    					<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Carga Horária:</b> 00h</span> <br>
		    						
		    						<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Desbloqueia:</b> Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software </span>
			    				</div>
		    				</div>
		    			</li>
		    			<li>
		    				<div class="btn materias" onclick="expandir_botao('MATA39',this);">
		    					<h6>MATA39 - SEMINÁRIOS DE INTRODUÇÃO AO CURSO</h6>
		    					<div class="botao-materia-expandida" id="MATA39">
			    					<hr>
			    					<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Carga Horária:</b> 00h</span> <br>
		    						
		    						<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Desbloqueia:</b> Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software </span>
			    				</div>
		    				</div>
		    			</li>
		    			<li>
		    				<div class="btn materias"  onclick="expandir_botao('MAT236',this);">
		    					<h6>MAT236 - MÉTODOS ESTATÍSTICOS</h6>
		    					<div class="botao-materia-expandida" id="MAT236">
			    					<hr>
			    					<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Carga Horária:</b> 00h</span> <br>
		    						
		    						<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Desbloqueia:</b> Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software </span>
			    				</div>
		    				</div>
		    			</li>
		    			<li>
		    				<div class="btn materias" onclick="expandir_botao('FCHC45', this);">
		    					<h6>FCHC45 - METODOLOGIA E EXPRESSÃO TÉCNICO-CIENTÍFICA</h6> 
		    					<div class="botao-materia-expandida" id="FCHC45">
			    					<hr>
			    					<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Carga Horária:</b> 00h</span> <br>
		    						
		    						<span class="col-3 col-sm-3 col-lg-3 col-xl-3"><b>Desbloqueia:</b> Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software Engenharia de Software </span>
			    				</div>
		    				</div>
		    			</li>
		    		</ul>
		    	</div>
    	        <div class="modal-footer">
          			<button type="button" class="btn btn-success" data-dismiss="modal" id = "fechaModal" onclick = "codigosmateriasselecionadas.forEach(confirmaMaterias);">Confirmar</button>
        		</div>
		    </div>
		</div>
    </div> 
</body>
</html>