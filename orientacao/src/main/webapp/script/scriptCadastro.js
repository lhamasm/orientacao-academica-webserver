$("#cpf").mask("00000000000");

function redirectIndex(){
   location.href="./index.jsp" 
}

function alteraConteudo() {
	var e = document.getElementById("tipo");
	var itemSelecionado = e.options[e.selectedIndex].value;
	if (itemSelecionado == "Estudante") {
		document.getElementById("prof-departamento").style.display="none";
		document.getElementById("aluno-semestre").style.display="block";
		document.getElementById("aluno-curso").style.display="block";
	}
	else if (itemSelecionado == "Docente") {
		document.getElementById("prof-departamento").style.display="block";
		document.getElementById("aluno-semestre").style.display="none";
		document.getElementById("aluno-curso").style.display="none";
	}
	else{
		document.getElementById("prof-departamento").style.display="none";
		document.getElementById("aluno-semestre").style.display="none";
		document.getElementById("aluno-curso").style.display="none";
	}
}

function validar() {
    var soma;
    var resto;
    soma = 0;
   	if(document.getElementById("senha").value == "" || document.getElementById("repsenha").value == "" || 
   		document.getElementById("cpf").value == "" || document.getElementById("email").value == "" || 
   		document.getElementById("nome").value == "" || document.getElementById("sobrenome").value == "" || 
   		document.getElementById("matricula").value == ""){   		
   		alert("Preencha todos os dados");
   		return false;
   	}
    if(document.getElementById("cpf").value.length != 11 || document.getElementById("cpf").value == "00000000000"
    	|| document.getElementById("cpf").value == "11111111111" || document.getElementById("cpf").value == "22222222222"
    	|| document.getElementById("cpf").value == "33333333333" || document.getElementById("cpf").value == "44444444444"
    	|| document.getElementById("cpf").value == "55555555555" || document.getElementById("cpf").value == "66666666666"
    	|| document.getElementById("cpf").value == "77777777777" || document.getElementById("cpf").value == "88888888888"
    	|| document.getElementById("cpf").value == "99999999999"){
		alert("CPF inválido");
		return false;    
	}  
	for (i=1; i<=9; i++){
  		soma = soma + parseInt(document.getElementById("cpf").value.substring(i-1, i)) * (11 - i);
	}
  	resto = (soma * 10) % 11;
  	// verificando primeiro digito
    if ((resto == 10) || (resto == 11)){
    	resto = 0;
    }
    if (resto != parseInt(document.getElementById("cpf").value.substring(9, 10))){
    	alert("CPF inválido");
		return false;
    }
  	soma = 0;
    for (i = 1; i <= 10; i++){
    	soma = soma + parseInt(document.getElementById("cpf").value.substring(i-1, i)) * (12 - i);
    }
    resto = (soma * 10) % 11;
    if ((resto == 10) || (resto == 11)){  
    	resto = 0;
    }
    // verificando segundo digito
    if (resto != parseInt(document.getElementById("cpf").value.substring(10, 11))){ 
		alert("CPF inválido");
		return false;
    }
	if(document.getElementById("senha").value != document.getElementById("repsenha").value){
		alert("As senhas não coincidem");
		return false;
	}
}
