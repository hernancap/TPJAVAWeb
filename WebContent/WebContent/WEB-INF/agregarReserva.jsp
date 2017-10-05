<%@page import="java.util.ArrayList"%>
<%@page import="entity.TipoElemento"%>
<%@page import="controlers.CtrlTipoElem"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <link href="style/bootstrap.css" rel="stylesheet">

<title>Agregar reserva</title>
</head>
<body>

<form class="form-horizontal" name="agregarRes" action="reservas" method="post">

<div class="form-group">
    <label class="control-label col-sm-2" for="nomTipo">Nombre Tipo:</label>
    <div class="col-sm-10">
    <select id="nomTipo"name="nomTipo">   
    <option value="none"></option> 	
		<%
			CtrlTipoElem ctrlTipo = new CtrlTipoElem();	
			ArrayList<TipoElemento> tipos = new ArrayList<TipoElemento>();
			tipos = ctrlTipo.mostrarTipos();
			for(TipoElemento te : tipos) {
		%>	

	<option value=<%=te.getNombreTipo()%>><%=te.getNombreTipo()%></option>
	<% 
	} 
	%>
	</select>
    </div>
  </div>
   <div class="form-group">
    <label class="control-label col-sm-2" for="tiempoUso">Tiempo de uso:</label>
    <div class="col-sm-10">
      <input class="form-control" id="tiempoUso">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Buscar elementos</button>
    </div>
  </div>
</form>

</body>
</html>