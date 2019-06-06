function redirectMsg(){
	sessionStorage.clear();
	location.href="./inbox.jsp";
}

function redirectHome(){
	sessionStorage.clear();	
	 location.href="./homepageAluno.jsp";
}

function redirectCadastro(){
	sessionStorage.clear();
	location.href="./alterarCadastroAluno.jsp";
}

function sair(){
	document.getElementById('sair').value = "a";
	sessionStorage.clear();
	document.getElementById('formSair').submit();
}

