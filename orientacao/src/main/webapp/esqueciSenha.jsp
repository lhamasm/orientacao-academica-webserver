<!doctype html>

<html>

<head>
    <meta charset="utf-8">
    <meta name="view-port" content="width=width-device, initial-scale=1.0, shrink-to-fit=no">
    <title>Orientação Acadêmica</title>
    <link rel="icon" href="./imagens/BrasaoUFBA.png">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet">   
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">     
    <link rel="stylesheet" type="text/css" href="./estilo/estilo.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>     
</head>

<body>
    <div class = "container-fluid">
        <span class = "text-center"> <i class="mt-4 col-12 fas fa-graduation-cap" id = "icone-login"></i> </span>   
        <h3 class = "mt-1 text-center" id = "titulo"> Orientação Acadêmica </h3>
        <div class = "mt-3 container shadow" id = "container-login">
            <form method = "post" action = "recuperarSenha"> 
                <div class = "form-group">
                    <label for = "matricula" class = "mt-3"> Número de Matrícula </label> 
                    <input required type = "text" class = "form-control input-sm" id = "matricula" name = "matricula">
                </div>
                <div class = "form-group">
                    <label for = "email"> Email </label>
                    <input required type = "text" class = "form-control input-sm" id = "email" name = "email">
                </div>
                <button type = "submit" class = "mt-2 mb-3 col-12 btn btn-info"> Recuperar Senha </button>        
            </form>
        </div>
   </div>
</body>

</html>