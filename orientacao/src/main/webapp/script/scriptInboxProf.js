function sair(){
	document.getElementById('sair').value = "a";
	document.getElementById('formSair').submit();
}

function redirectMsg(){
	 location.href="./inboxProf.jsp"; 
}

function redirectHome(){
	 location.href="./homepageProfessor.jsp"; 
}

function redirectCadastro(){
	 location.href="./alterarCadastroProf.jsp"; 
}

function selecionaOri(i){
	document.getElementById("indexOri").value = i;
	document.getElementById("selecOri").submit();
}