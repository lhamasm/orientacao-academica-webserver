function redirect(){
	location.href="./inboxProf.jsp";
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

function sair(){
	document.getElementById('sair').value = "a";
	document.getElementById('formSair').submit();
}