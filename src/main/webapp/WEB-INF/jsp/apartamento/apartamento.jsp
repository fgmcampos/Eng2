<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apartamento</title>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
    rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>

<body>
<center><img src="img\head body.jpg" class="img-responsive" ></center>
<nav class="navbar navbar-default navbar-static-top">
  <div class="container">
     <ul class="nav nav-pills">
  <li role="presentation"><a href="#">Home</a></li>
  <li role="presentation"><a href="#">Lan�ar Despesas</a></li>
  <li role="presentation"><a href="#">Gerenciar Condominio</a></li>
  <li role="presentation"><a href="#">Manter Propriet�rio</a></li>
  <li role="presentation" class="active"><a href="#">Manter Apartamento</a></li>
  <li role="presentation"><a href="#">Vincular Apartamento</a></li>
</ul>
 
  </div>
</nav>

<label for="nome">N�mero:</label>
	<input type="text" id="numero" name="numero" class="form-control" />

	<label for="telefone">Quantidade de quartos:</label>
	<input type="text" id="qtdquartos" name="qtdquartos" class="form-control" />
	
	<li><label for="telefone">Ocupa��o:</label>
	<select>
 <option value="volvo">Propriet�rio</option>
  <option value="saab">Vazio</option>
  <option value="mercedes">Inquilino</option>
</select></li>

		<input type="submit" value="Gravar" class="btn" size="50"
			style="width: 151px; height: 28px;" /> 
			<input type="submit"
			value="Excluir" class="btn" size="50"
			style="width: 151px; height: 28px;" />
			<input type="submit"
			value="Alterar" class="btn" size="50"
			style="width: 151px; height: 28px;" />
	
</body>
</html>