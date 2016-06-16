<%@page import="br.com.fatec.model.Proprietario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de despesas</title>
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
			<li role="presentation"><a href="${linkTo[DespesaController].form()}">Lançar Despesas</a></li>
			<li role="presentation" class="active"><a href="${linkTo[CondominioController].form()}">Gerenciar Condominio</a></li>
			<li role="presentation"><a href="${linkTo[ProprietarioController].form()}">Manter Proprietario</a></li>
			<li role="presentation"><a href="${linkTo[ApartamentoController].form()}">Manter Apartamento</a></li>
			<li role="presentation"><a href="${linkTo[VinculaController].form()}">Vincular Apartamento</a></li>
		</ul>
	</div>
	</nav>
	
<center><h1>Condominio Pago</h1></center>
</br>
<h3>Data de vencimento:&nbsp;&nbsp;${condominio.datavencimento}</br>
</br>
Data de pagamento:&nbsp;&nbsp;${condominio.datapagamento}</br>
</br>
Valor pago:&nbsp;&nbsp;${condominio.valorpago} <c:if test="${condominio.valorpago > condominio.valorapagar}"></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="ff0000"><center>Multa por pagar atrasado no valor de ${condominio.valorpago - condominio.valorapagar}
 paga no mesmo mês!
</font></center></c:if>
</h3>


</br>
<a href="${linkTo[CondominioController].form()}"><h4>Voltar</h4></a></li>
</body>
</html>