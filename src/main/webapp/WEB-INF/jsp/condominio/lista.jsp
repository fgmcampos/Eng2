<%@page import="br.com.fatec.model.Proprietario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gerenciar Condominio</title>
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
	
<table class="table table-hover">
	<thead>
		<tr>
			<th>Apartamento</th>
			<th>Mês</th>
			<th>Despesa</th>
			<th>Valor</th>		
			<th>Remover</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${despesa}" var="d">
			<c:set var="id" value="${d.id}" scope="request"></c:set>
			<tr>
				<td>${d.apartamento}</td>
				<td>${d.mesano}</td>
				<c:forEach items="${tipodespesa}" var="t">
				<c:if test="${d.tipodespesa == t.id}">
				<td>${t.nome}</td>
				</c:if>
				</c:forEach>
				<td>${d.valor}</td>
				<td>
				
					<form action="${linkTo[DespesaController].apaga(id)}" method="post">
						<input name="despesa.id" value="${despesa}" type="hidden" />						   
						<button type="submit" name="_method" value="DELETE" >Remover</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<h2>Valor Total:&nbsp;&nbsp; ${valortotal}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Vencimento: ${vencimento}</h2>
</br>


Caso pago com atraso, será acrescentada uma multa de 2% no valor de ${multa2}</br> se a multa for paga no mes atrasado, e uma multa de 5% no valor de ${multa5} </br>
caso deixar pra para pagar no proximo mes.
</br>
<form action="${linkTo[CondominioController].pagar(null)}" method="post">
</br><input type="checkbox" id="multa" name="multa" value="1" />
		<input type="hidden" id="apartamento" name="apartamento" value="${condominio.apartamento}" />
		<input type="hidden" id="mesano" name="mesano" value="${condominio.mesano}" />
		<input type="hidden" id="valor" name="valor" value="${valortotal}" />
		<input type="hidden" id="id" name="id" value="${condominio.id}" />
	&nbsp;&nbsp;Caso vencimento tenha passado, deseja pagar a multa agora?
	</br></br>
	<c:set var="multaagora" value="multa" scope="request"></c:set>
								<input type="submit" value="Pagar" class="btn" size="50"
			style="width: 200px; height: 35px;" />
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<a href="${linkTo[CondominioController].form()}"> Voltar</a>
					</form>
					
</body>
</html>