var codigosmateriasselecionadas = [];
var materiasinseridas = [];
var nomesmateriasselecionadas = [];
var nomesmateriasinseridas = [];
var chmateriasinseridas = [];
var grade = [];
var nomesGrade = [];
var chGrade = [];
var orientadores = [];
var tipoMateria = [];

function sair(){
	document.getElementById('sair').value = "a";
	sessionStorage.clear();
	document.getElementById('formSair').submit();
}
function redirectMsg(){
	sessionStorage.clear();	
	location.href="./inbox.jsp" 
}

if(sessionStorage.getItem("grade") != null){
	grade = JSON.parse(sessionStorage.getItem("grade"));
	nomesGrade = JSON.parse(sessionStorage.getItem("nomesGrade"));
	tipoMateria = JSON.parse(sessionStorage.getItem("tipoMateria"));
	chGrade = JSON.parse(sessionStorage.getItem("chGrade"));
	console.log(grade.length);
	console.log(nomesGrade.length);
}

function redirectHome(){
	sessionStorage.clear();
	location.href="./homepageAluno.jsp" 
}

function redirectBack(){
	location.href="./montarGrade.jsp" 
}

function expandir_botao(id, obj){
	botao = document.getElementById(id + 'Exp');
	nomemateria = obj.querySelector("h6").innerHTML;
	var titulo = nomemateria.split(" - ");
	
	if(botao.style.display == "block"){
		botao.style.display = "none";
		obj.style.backgroundColor = "#B7B7B7";
		obj.style.color = "black";
		
		$(obj).hover(function(){
			$(this).css("background-color", "#68BBF1");
		}, function(){
			$(this).css("background-color", "#B7B7B7");
		});

		var index = codigosmateriasselecionadas.indexOf(titulo[0]);
		if (index > -1) {
		  codigosmateriasselecionadas.splice(index, 1);
		  nomesmateriasselecionadas.splice(index, 1);
		  chmateriasinseridas.splice(index, 1);
		}
	}
	else{
		botao.style.display = "block";
		obj.style.backgroundColor = "#68BBF1";
		obj.style.color = "#2b4654";
		botao.style.backgroundColor = "#68BBF1";
		botao.style.color = "#2b4654";

		$(obj).hover(function(){
			$(this).css("background-color", "#68BBF1");
		}, function(){
			$(this).css("background-color", "#68BBF1");
		});

		codigosmateriasselecionadas.push(titulo[0]);
		nomesmateriasselecionadas.push(titulo[1]);
	}
}

function confirmaMaterias(item){
	if (materiasinseridas.indexOf(item) < 0) { // não encontrou o item no array de materias inseridas
		var indice = codigosmateriasselecionadas.indexOf(item);
		var str = "removeelement('";
		str+= item;
		str+= "');";

		x = document.getElementById("inserir-materias");
		x.innerHTML += '<div id = "' + item + '"> <div class="materias-selecionadas mt-2 ml-3 container-fluid"><div class="row"><button class="offset-11" type="button" onclick="' + str + '">&times;</button></div><p class="codigo">' + item + '</p> <p class="titulomateria">' + nomesmateriasselecionadas[indice] + '</p> </div> <select style = "width: 11.5em; margin-left: 1em; font-size: 0.9em" class = "px-0 form-control mt-1" id = "expectativa' + item + '" name = "expectativa' + item + '"> <option> Acho que vou passar </option> <option> Acho que vou reprovar </option> </select> </div>';
		materiasinseridas.push(item);
		chmateriasinseridas.push(document.getElementById("carga"+item).innerHTML);
		nomesmateriasinseridas.push(document.getElementById("nome"+item).innerHTML);
	}
}

function removeelement(id){
	x = document.getElementById(id);
	x.remove();
	console.log(id);
	var botao = document.getElementById(id + 'Exp');
	botao.style.display = "none";
	botao.style.backgroundColor = "#B7B7B7";
	botao.style.color = "black";
	document.getElementById(id + 'Div').style.backgroundColor = "#B7B7B7";
	document.getElementById(id + 'Div').style.color = "black";
	
	$(document.getElementById(id + 'Div')).hover(function(){
		$(this).css("background-color", "#68BBF1");
	}, function(){
		$(this).css("background-color", "#B7B7B7");
	});	
	
	var index = materiasinseridas.indexOf(id);
	if(index >= 0){
		materiasinseridas.splice(index, 1);
		nomesmateriasinseridas.splice(index, 1);
		chmateriasinseridas.splice(index, 1);		
	}
	index = codigosmateriasselecionadas.indexOf(id);
	if (index > -1) {
	  codigosmateriasselecionadas.splice(index, 1);
	  nomesmateriasselecionadas.splice(index, 1);
	}	
}

function trocaCor(tipo){
	if(tipo == 'obg'){
		if(document.getElementById("obrigatorias").style.backgroundColor == "white"){
			document.getElementById("obrigatorias").style.backgroundColor = "rgb(31, 97, 141)";
			document.getElementById("obrigatorias").style.color = "white";
			document.getElementById("optativas").style.backgroundColor = "white";
			document.getElementById("optativas").style.color = "black";	
		}
	}
	else if (tipo == 'op'){
		if(document.getElementById("optativas").style.backgroundColor == "white"){
			document.getElementById("optativas").style.backgroundColor = "rgb(31, 97, 141)";
			document.getElementById("optativas").style.color = "white";
			document.getElementById("obrigatorias").style.backgroundColor = "white";
			document.getElementById("obrigatorias").style.color = "black";	
		}
	}
}

function voltaCor(tipo){
	if(tipo == 'obg'){
		if(document.getElementById("obrigatorias").style.backgroundColor == "rgb(31, 97, 141)" && selecionado == "op"){
			document.getElementById("obrigatorias").style.backgroundColor = "white";
			document.getElementById("obrigatorias").style.color = "black";
			document.getElementById("optativas").style.backgroundColor = "rgb(31, 97, 141)";
			document.getElementById("optativas").style.color = "white";	
		}
	}
	else{
		if(document.getElementById("optativas").style.backgroundColor == "rgb(31, 97, 141)" && selecionado == "obg"){
			document.getElementById("optativas").style.backgroundColor = "white";
			document.getElementById("optativas").style.color = "black";
			document.getElementById("obrigatorias").style.backgroundColor = "rgb(31, 97, 141)";
			document.getElementById("obrigatorias").style.color = "white";
		}
	}
}

function alterarConteudo(tipo){
	if(tipo == 'obg'){
		selecionado = "obg";
		document.getElementById("container-obrigatorias").style.display = "block";
		document.getElementById("container-optativas").style.display = "none";
		document.getElementById("obrigatorias").style.backgroundColor = "rgb(31, 97, 141)";
		document.getElementById("obrigatorias").style.color = "white";
		document.getElementById("optativas").style.backgroundColor = "white";
		document.getElementById("optativas").style.color = "black";	
	}
	else{
		selecionado = "op";
		document.getElementById("container-obrigatorias").style.display = "none";
		document.getElementById("container-optativas").style.display = "block";
		document.getElementById("optativas").style.backgroundColor = "rgb(31, 97, 141)";
		document.getElementById("optativas").style.color = "white";
		document.getElementById("obrigatorias").style.backgroundColor = "white";
		document.getElementById("obrigatorias").style.color = "black";
	}
}

function identificaDiscObg(id){
	document.getElementById("discEscolhida" + id).innerHTML = id;
	document.getElementById("tipoDisc" + id).innerHTML = "obg";
	if(document.getElementById(id).style.backgroundColor != "rgb(65, 186, 158)"){
		document.getElementById("botaoModal" + id).classList.add('btn-success');
		document.getElementById("botaoModal" + id).classList.remove('btn-danger');
		document.getElementById("botaoModal" + id).innerHTML = "Adicionar na grade";
	}else{
		document.getElementById("botaoModal" + id).classList.remove('btn-success');
		document.getElementById("botaoModal" + id).classList.add('btn-danger');
		document.getElementById("botaoModal" + id).innerHTML = "Remover da grade";
	}
}

function identificaDiscOp(id){
	document.getElementById("discEscolhida" + id).innerHTML = id;
	document.getElementById("tipoDisc" + id).innerHTML = "op";
	if(document.getElementById(id).style.backgroundColor != "rgb(192, 229, 220)"){
		document.getElementById("botaoModal" + id).classList.add('btn-success');
		document.getElementById("botaoModal" + id).classList.remove('btn-danger');
		document.getElementById("botaoModal"+ id).innerHTML = "Adicionar na grade";
	}else{
		document.getElementById("botaoModal" + id).classList.remove('btn-success');
		document.getElementById("botaoModal" + id).classList.add('btn-danger');
		document.getElementById("botaoModal" + id).innerHTML = "Remover da grade";
	}
}

function alterarMateria(id){
	var materiaEscolhida = document.getElementById("discEscolhida" + id).innerHTML;
	if(document.getElementById("tipoDisc" + id).innerHTML == "obg"){
		if(document.getElementById(materiaEscolhida).style.backgroundColor != "rgb(65, 186, 158)"){
			document.getElementById(materiaEscolhida).style.backgroundColor = "#41BA9E";
			document.getElementById("operacaoDisc" + id).innerHTML = "adicionar";
		}
		else{
			document.getElementById(materiaEscolhida).style.backgroundColor = "#B7B7B7";
			document.getElementById("operacaoDisc" + id).innerHTML = "remover";
		}
	}
	else{
		if(document.getElementById(materiaEscolhida).style.backgroundColor != "rgb(192, 229, 220)"){
			document.getElementById(materiaEscolhida).style.backgroundColor = "#C0E5DC";
			document.getElementById("operacaoDisc" + id).innerHTML = "adicionar";
		}
		else{
			document.getElementById(materiaEscolhida).style.backgroundColor = "#D0D5FA";
			document.getElementById("operacaoDisc" + id).innerHTML = "remover";			
		}
	}
	if(document.getElementById("operacaoDisc" + id).innerHTML == "adicionar"){
		document.getElementById("disciplinasTotal").innerHTML += "<input type = 'text' name = 'escolhida" + 
		document.getElementById("discEscolhida" + id).innerHTML + "' id = 'escolhida" + document.getElementById("discEscolhida" + id).innerHTML + "' value = '" + 
		document.getElementById("codigo" + materiaEscolhida).innerHTML + "'> <br>";
		document.getElementById("nomeDisciplinas").innerHTML += "<input type = 'text' name = 'nomeEscolhida" + 
		document.getElementById("discEscolhida" + id).innerHTML + "' id = 'nomeEscolhida" + document.getElementById("discEscolhida" + id).innerHTML + "' value = '" + 
		document.getElementById("nome" + materiaEscolhida).innerHTML + "'> <br>";
		grade.push(document.getElementById("codigo" + materiaEscolhida).innerHTML);
		nomesGrade.push(document.getElementById("nome" + materiaEscolhida).innerHTML);
		chGrade.push(document.getElementById("carga" + materiaEscolhida).innerHTML);
		if(document.getElementById("tipoDisc" + id).innerHTML == "obg"){
			tipoMateria.push("obg");
		}
		else{
			tipoMateria.push("op");
		}
	}
	else{
		var index = grade.indexOf(document.getElementById("codigo" + materiaEscolhida).innerHTML);
		if(index >= 0){
			grade.splice(index, 1);
			nomesGrade.splice(index, 1);
			chGrade.splice(index, 1);
			tipoMateria.splice(index, 1);
		}		
		document.getElementById("escolhida" + materiaEscolhida).remove();
		document.getElementById("nomeEscolhida" + materiaEscolhida).remove();
		
	}
}

function preencheGrade(){
	document.getElementById("gradeInfo").innerHTML = "";
	for(var i=0; i<grade.length; i++){
		document.getElementById("gradeInfo").innerHTML += "<b>" + grade[i] + " </b> - " + nomesGrade[i] + "<br>";
	}
}

function redirectGrade1(){
	sessionStorage.setItem("grade", JSON.stringify(grade));
	sessionStorage.setItem("nomesGrade", JSON.stringify(nomesGrade));
	sessionStorage.setItem("tipoMateria", JSON.stringify(tipoMateria));
	sessionStorage.setItem("chGrade", JSON.stringify(chGrade));
	location.href="./montarGrade2.jsp";
}

function selecionaOrientador(id){
	if(!document.getElementById('orientador' + id).classList.contains('active')){
		orientadores.push(id);
		document.getElementById('orientador' + id).classList.add('active');
		document.getElementById('orientador' + id).classList.remove('list-group-item-action');
		document.getElementById('orientador' + id).classList.add('text-left');
	}
	else{
		var index = orientadores.indexOf(id);
		if(index >= 0){
			orientadores.splice(index, 1);
		}
		document.getElementById('orientador' + id).classList.remove('active');
		document.getElementById('orientador' + id).classList.add('list-group-item-action');		
	}
}

function juntaMaterias(){
	if(grade.length == 0 || materiasinseridas.length == 0 || orientadores.length == 0){
		console.log(grade.length);
		console.log(materiasinseridas.length);
		console.log(orientadores.length);
		return false;
	}
	for(var i=0; i<grade.length; i++){
		document.getElementById("codigosGrade").value += grade[i] + "/";
		document.getElementById("nomesGrade").value += nomesGrade[i] + "/";
		document.getElementById("chGrade").value += chGrade[i] + "/";
	}
	for(var i=0; i<materiasinseridas.length; i++){
		document.getElementById("codigosAtual").value += materiasinseridas[i] + "/";
		document.getElementById("nomesAtual").value += nomesmateriasinseridas[i] + "/";
		document.getElementById("chAtual").value += chmateriasinseridas[i] + "/";
	}
	for(var i=0; i<orientadores.length; i++){
		document.getElementById("orientadores").value += orientadores[i] + "/";
	}
	return true;
}