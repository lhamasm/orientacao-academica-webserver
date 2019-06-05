document.getElementById("obrigatorias").style.backgroundColor = "rgb(31, 97, 141)";
document.getElementById("obrigatorias").style.color = "white";
document.getElementById("optativas").style.backgroundColor = "white";
document.getElementById("optativas").style.color = "black";
var selecionado = "obg";

if(sessionStorage.getItem("grade") != null){
	grade = JSON.parse(sessionStorage.getItem("grade"));
	nomesGrade = JSON.parse(sessionStorage.getItem("nomesGrade"));
	tipoMateria = JSON.parse(sessionStorage.getItem("tipoMateria"));
	chGrade = JSON.parse(sessionStorage.getItem("chGrade"));
	for(var i=0; i<grade.length; i++){
		if(tipoMateria[i] == "obg"){
			document.getElementById(grade[i]).style.backgroundColor = "#41BA9E";
		}
		else{
			document.getElementById(grade[i]).style.backgroundColor = "#C0E5DC";
		}
	}
}