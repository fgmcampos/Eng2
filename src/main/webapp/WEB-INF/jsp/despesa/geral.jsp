<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nona despesa geral</title>
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
<% Date data = new Date();
SimpleDateFormat data_formatada = new SimpleDateFormat("dd/MM/yyyy");
String mostra_data = data_formatada.format(data);%>
<form action="${linkTo[DespesaController].adicionaGeral(null)}" method="post">
<h4>
<label>Despesa Individual:</label></br></br>
Data:<%=mostra_data%>
		</br>
		</br>
		Despesa:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<select name = despesa.tipodespesa style="width: 200px; height: 28px;" >
		<option value=""> </option>	
		<c:forEach  var="tipodespesa" items="${tipodespesa}">
        <option value="${tipodespesa.id}">${tipodespesa.nome}</option>
    	 </c:forEach>
		</select> <a href="${linkTo[TipodespesaController].form()}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nova Despesa</a>
		</br>
		</br>
		
Mês de Referência:</br></br>
<input  type="month" id="mesano" name="despesa.mesano" class="form-control" value="${despesa.mesano}" style="width: 200px; height: 28px;" />
</br>
Valor:
</br>
</br>
<input  type="text" id="valor" name="despesa.valor" class="form-control" value="${despesa.valor}" style="width: 200px; height: 28px;" />
</br></br>		
		<input type="submit" value="Gravar" class="btn" size="50"
			style="width: 151px; height: 28px;" /> 
			
			<a href="${linkTo[DespesaController].form()}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Voltar</a>

</h4>
</form>

</body>
</html>