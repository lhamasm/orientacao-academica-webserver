function redirectMsg(){
	 location.href="./inbox.jsp";
}

function redirectHome(){
	 location.href="./homepageAluno.jsp";
}

function redirectCadastro(){
	location.href="./alterarCadastroAluno.jsp";
}

function sair(){
	document.getElementById('sair').value = "a";
	document.getElementById('formSair').submit();
}