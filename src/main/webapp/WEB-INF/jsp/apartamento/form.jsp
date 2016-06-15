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
  <li role="presentation"><a href="${linkTo[IndexController].index()}">Home</a></li>
  <li role="presentation"><a href="${linkTo[DespesaController].form()}">Lançar Despesas</a></li>
  <li role="presentation"><a href="${linkTo[CondominioController].form()}">Gerenciar Condominio</a></li>
  <li role="presentation"><a href="${linkTo[ProprietarioController].form()}">Manter Proprietário</a></li>
  <li role="presentation" class="active"><a href="#">Manter Apartamento</a></li>
  <li role="presentation"><a href="${linkTo[VinculaController].form()}">Vincular Apartamento</a></li>
</ul>
 
  </div>
</nav>
<form action="${linkTo[ApartamentoController].adiciona(null)}" method="post">
<label for="nome">Número:</label>
	<input type="text" id="id" name="apartamento.id" class="form-control" value="${apartamento.id}" required = "required"/>

	<label for="telefone">Quantidade de quartos:</label>
	<input type="text" id="quartos" name="apartamento.quartos" class="form-control" value="${apartamento.quartos}" required = "required"/>
	
	<p><label for="telefone">Ocupação:</label>
	<select name="apartamento.ocupacao" class="form-control" required = "required" >
	<option value=""></option>
  <option value="1">Proprietário</option>
  <option value="2">Vazio</option>
  <option value="3">Inquilino</option>
</select></p>

		<input type="submit" value="Gravar" class="btn" size="50"
			style="width: 151px; height: 28px;" /> 
			<a href="${linkTo[ApartamentoController].lista()}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Listar Apartamentos Cadastrados</a>
</form>	
	
</body>
</html>