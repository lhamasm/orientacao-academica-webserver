function redirectMsgAluno(){
	 location.href="./inbox.jsp" 
}

function redirectMsgProf(){
	 location.href="./inboxProf.jsp" 
}

function redirectHomeAluno(){
	 location.href="./homepageAluno.jsp" 
}

function redirectCadastroAluno(){
  location.href="./alterarCadastroAluno.jsp" 
}

function redirectCadastroProf(){
	location.href="./alterarCadastroProf.jsp" 
}

function redirectHomeProf(){
   location.href="./homepageProfessor.jsp" 
}

function sair(){
	document.getElementById('sair').value = "a";
	sessionStorage.clear();
	document.getElementById('formSair').submit();
}

function alteraSenha(){
	if(document.getElementById('senhaNovaCol').style.display != "block"){
		document.getElementById('senhaNovaCol').style.display = "block";
		document.getElementById('senhaAntigaCol').style.display = "block";
	}
	else{
		document.getElementById('senhaNovaCol').style.display = "none";
		document.getElementById('senhaAntigaCol').style.display = "none";
	}
}