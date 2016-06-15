<%@page import="br.com.fatec.model.Proprietario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<li role="presentation"><a href="${linkTo[IndexController].index()}">Home</a></li>
			<li role="presentation" class="active"><a href="${linkTo[DespesaController].form()}">Lançar Despesas</a></li>
			<li role="presentation"><a href="${linkTo[CondominioController].form()}">Gerenciar Condominio</a></li>
			<li role="presentation"><a href="${linkTo[ProprietarioController].form()}">Manter Proprietario</a></li>
			<li role="presentation"><a href="${linkTo[ApartamentoController].form()}">Manter Apartamento</a></li>
			<li role="presentation"><a href="${linkTo[VinculaController].form()}">Vincular Apartamento</a></li>
		</ul>
	</div>
	</nav>
<table class="table table-hover">
	<thead>
		<tr>
			<th>Nome</th>
			<th>Cobrado por quarto?</th>
			<th>Remover</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${tipodespesa}" var="t">
			<c:set var="id" value="${t.id}" scope="request"></c:set>
			<tr>
				<td>${t.nome}</td>
				<td><c:if test="${t.valorporquarto==1}">Sim</c:if><c:if test="${t.valorporquarto==0}">Não</c:if></td>
				<td>
					<form action="${linkTo[TipodespesaController].apaga(id)}" method="post">
						<input name="tipodespesa.id" value="${tipodespesa}" type="hidden" />						   
						<button type="submit" name="_method" value="DELETE" >Remover</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<form action="${linkTo[TipodespesaController].form()}" method="post">
			<input type="submit"
			value="Voltar" class="btn" size="50"
			style="width: 151px; height: 28px;" />
			
	</form>
</body>
</html>