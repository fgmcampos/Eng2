<%@page import="br.com.fatec.model.Proprietario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vincular Apartamento</title>
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
			<li role="presentation"><a href="${linkTo[DespesaController].form()}">Lan�ar Despesas</a></li>
			<li role="presentation"><a href="${linkTo[CondominioController].form()}">Gerenciar Condominio</a></li>
			<li role="presentation"><a href="${linkTo[ProprietarioController].form()}">Manter Proprietario</a></li>
			<li role="presentation"><a href="${linkTo[ApartamentoController].form()}">Manter Apartamento</a></li>
			<li role="presentation" class="active"><a href="${linkTo[VinculaController].form()}">Vincular Apartamento</a></li>
		</ul>
	</div>
	</nav>
<table class="table table-hover">
	<thead>
		<tr>
			<th>Apartamento</th>
			<th>Proprietario</th>
			<th>Remover</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${vincula}" var="v">
			<c:set var="id"  value="${v.id}" scope="request"></c:set>
			<c:set var="pro" value="${v.proprietario}" scope="request"></c:set>
			<tr>
				<td>${v.apartamento}</td>
				<c:forEach items="${proprietario}" var="p">
				<c:if test="${v.proprietario == p.id}">
				<td>${p.nome}</td>
				</c:if>
				</c:forEach>
				<td>
					<form action="${linkTo[VinculaController].apaga(id)}" method="post">
						<input name="vincula.id" value="${vincula}" type="hidden" />						� �
						<button type="submit" name="_method" value="DELETE" >Remover</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>



<form action="${linkTo[VinculaController].form()}" method="post">
			<input type="submit"
			value="Voltar" class="btn" size="50"
			style="width: 151px; height: 28px;" />
			
	</form>
</body>
</html>