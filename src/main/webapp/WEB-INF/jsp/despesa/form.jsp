<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lan�ar Despesa</title>
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
  <li role="presentation" class="active"><a href="#">Lan�ar Despesas</a></li>
  <li role="presentation"><a href="#">Gerenciar Condominio</a></li>
  <li role="presentation"><a href="${linkTo[ProprietarioController].form()}">Manter Propriet�rio</a></li>
  <li role="presentation"><a href="${linkTo[ApartamentoController].form()}">Manter Apartamento</a></li>
  <li role="presentation"><a href="${linkTo[VinculaController].form()}">Vincular Apartamento</a></li>
</ul>
 
  </div>
</nav>


</body>
</html>