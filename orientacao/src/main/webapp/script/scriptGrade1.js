document.getElementById("obrigatorias").style.backgroundColor = "rgb(31, 97, 141)";
document.getElementById("obrigatorias").style.color = "white";
document.getElementById("optativas").style.backgroundColor = "white";
document.getElementById("optativas").style.color = "black";
var selecionado = "obg";

function redirectMsg(){
	 location.href="./inbox.html";
}

function redirectHome(){
	 location.href="./homepageAluno.html";
}

function redirectCadastro(){
	location.href="./alterarCadastroAluno.html";
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
	document.getElementById("discEscolhida").innerHTML = "obg" + id;
	document.getElementById("tipoDisc").innerHTML = "obg";
	document.getElementById("discNome").innerHTML = document.getElementById("codigoobg" + id).innerHTML.bold() + ' - ' + document.getElementById("nomeobg" + id).innerHTML;
	if(document.getElementById("obg" + id).style.backgroundColor != "rgb(65, 186, 158)"){
		document.getElementById("botaoModal").classList.add('btn-success');
		document.getElementById("botaoModal").classList.remove('btn-danger');
		document.getElementById("botaoModal").innerHTML = "Adicionar à grade";
	}else{
		document.getElementById("botaoModal").classList.remove('btn-success');
		document.getElementById("botaoModal").classList.add('btn-danger');
		document.getElementById("botaoModal").innerHTML = "Remover da grade";
	}
}

function identificaDiscOp(id){
	document.getElementById("discEscolhida").innerHTML = "op" + id;
	document.getElementById("tipoDisc").innerHTML = "op";
	document.getElementById("discNome").innerHTML = document.getElementById("op" + id).innerHTML;
	if(document.getElementById("op" + id).style.backgroundColor != "rgb(192, 229, 220)"){
		document.getElementById("botaoModal").classList.add('btn-success');
		document.getElementById("botaoModal").classList.remove('btn-danger');
		document.getElementById("botaoModal").innerHTML = "Adicionar à grade";
	}else{
		document.getElementById("botaoModal").classList.remove('btn-success');
		document.getElementById("botaoModal").classList.add('btn-danger');
		document.getElementById("botaoModal").innerHTML = "Remover da grade";
	}
}

function alterarMateria(){
	var materiaEscolhida = document.getElementById("discEscolhida").innerHTML;
	if(document.getElementById("tipoDisc").innerHTML == "obg"){
		if(document.getElementById(materiaEscolhida).style.backgroundColor != "rgb(65, 186, 158)"){
			document.getElementById(materiaEscolhida).style.backgroundColor = "#41BA9E";
			document.getElementById("operacaoDisc").innerHTML = "adicionar";
		}
		else{
			document.getElementById(materiaEscolhida).style.backgroundColor = "#B7B7B7";
			document.getElementById("operacaoDisc").innerHTML = "remover";
		}
	}
	else{
		if(document.getElementById(materiaEscolhida).style.backgroundColor != "rgb(192, 229, 220)"){
			document.getElementById(materiaEscolhida).style.backgroundColor = "#C0E5DC";
			document.getElementById("operacaoDisc").innerHTML = "adicionar";
		}
		else{
			document.getElementById(materiaEscolhida).style.backgroundColor = "#D0D5FA";
			document.getElementById("operacaoDisc").innerHTML = "remover";			
		}
	}
	if(document.getElementById("operacaoDisc").innerHTML == "adicionar"){
		document.getElementById("disciplinasTotal").innerHTML += "<input type = 'text' name = 'escolhida" + 
		document.getElementById("discEscolhida").innerHTML + "' id = 'escolhida" + document.getElementById("discEscolhida").innerHTML + "' value = '" + 
		document.getElementById("codigo" + materiaEscolhida).innerHTML + "'> <br>";
		document.getElementById("nomeDisciplinas").innerHTML += "<input type = 'text' name = 'nomeEscolhida" + 
		document.getElementById("discEscolhida").innerHTML + "' id = 'nomeEscolhida" + document.getElementById("discEscolhida").innerHTML + "' value = '" + 
		document.getElementById("nome" + materiaEscolhida).innerHTML + "'> <br>";
	}
	else{
		document.getElementById("escolhida" + materiaEscolhida).remove();
		document.getElementById("nomeEscolhida" + materiaEscolhida).remove();
	}
}

function preencheGrade(){
	document.getElementById("gradeInfo").innerHTML = "";
	for(var i=0; i<document.getElementById("disciplinasTotal").elements.length; i++){
		document.getElementById("gradeInfo").innerHTML += "<b>" + document.getElementById("disciplinasTotal").elements[i].value + " </b> - " + document.getElementById("nomeDisciplinas").elements[i].value + "<br>";
	}
}

function redirect(){
	location.href="./montarGrade2.html";
}