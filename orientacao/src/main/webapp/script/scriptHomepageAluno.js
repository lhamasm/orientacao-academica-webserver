function redirect(){
	location.href="./montarGrade.jsp";
}
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
	sessionStorage.clear();
	document.getElementById('formSair').submit();
}