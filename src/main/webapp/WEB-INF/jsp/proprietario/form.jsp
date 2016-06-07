<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proprietario</title>
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
	<center>
		<img src="img\head body.jpg" class="img-responsive">
	</center>
	<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<ul class="nav nav-pills">
			<li role="presentation"><a href="#">Home</a></li>
			<li role="presentation"><a href="#">Lançar Despesas</a></li>
			<li role="presentation"><a href="#">Gerenciar Condominio</a></li>
			<li role="presentation" class="active"><a href="#">Manter
					Proprietario</a></li>
			<li role="presentation"><a href="#">Manter Apartamento</a></li>
			<li role="presentation"><a href="#">Vincular Apartamento</a></li>
		</ul>
	</div>
	</nav>

	<label for="nome">Nome:</label>
	<input type="text" id="nome" name="nome" class="form-control" />

	<label for="telefone">Telefone:</label>
	<input type="text" id="telefone" name="telefone" class="form-control" />
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