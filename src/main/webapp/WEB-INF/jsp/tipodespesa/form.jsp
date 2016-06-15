<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nova Despesa</title>
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
  <li role="presentation" class="active"><a href="${linkTo[DespesaController].form()}">Lançar Despesas</a></li>
  <li role="presentation"><a href="${linkTo[CondominioController].form()}">Gerenciar Condominio</a></li>
  <li role="presentation"><a href="${linkTo[ProprietarioController].form()}">Manter Proprietário</a></li>
  <li role="presentation"><a href="${linkTo[ApartamentoController].form()}">Manter Apartamento</a></li>
  <li role="presentation"><a href="${linkTo[VinculaController].form()}">Vincular Apartamento</a></li>
</ul>
 
  </div>
</nav>

<form action="${linkTo[TipodespesaController].adiciona(null)}" method="post">
<h4>
	<label for="nome">Nome:</label>
	<input type="text" id="nome" name="tipodespesa.nome" class="form-control" value="${tipodespesa.nome}" required = "required"/>	
	</br>
	<input type="checkbox" id="valorporquarto" name="tipodespesa.valorporquarto" value="1" />
	&nbsp;&nbsp; Valor deve ser dividido por quartos
	</br>
	</br>
	<input type="submit" value="Gravar" class="btn" size="50" style="width: 151px; height: 28px;" /> 
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<a href="${linkTo[TipodespesaController].lista()}"> Ver Lista</a>
			&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<a href="${linkTo[DespesaController].form()}"> Voltar</a>
			</h4></form> 
	
			
			
</body>
</html>