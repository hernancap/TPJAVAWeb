<%@page import="java.util.ArrayList"%>
<%@page import="entity.TipoElemento"%>
<%@page import="controlers.CtrlTipoElem"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<style>
table, td {
    border:1px solid black;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestión Tipos</title>
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap-grid.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
</head>
<body>

<div class="container">	
	<h2>Tipos de elementos:</h2>
		
<form action="tipos" method="post" onsubmit="return myFunction(this)">

<table class="table table-striped" id="tabla">
	<thead>
		<tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Cant. Máx. de Reservas</th>
        <th>Horas Máx. de Reserva</th>
        <th>Máx. Días de Anticip.</th>
        <th>Solo Encargado</th>
        <th>Seleccionar</th>
     	</tr>
	</thead>     
	
	
	<tbody>
	
	
		<%	
			CtrlTipoElem ctrlTipo = new CtrlTipoElem();
			ArrayList<TipoElemento>listaTipo = ctrlTipo.mostrarTipos();
			for(TipoElemento t : listaTipo){ 	
		%>
	
	
	<tr>
     <td> <%=t.getIdTipo()%></td>
     <td> <%=t.getNombreTipo() %></td>
     <td> <%=t.getCantMaxRes() %></td>
     <td> <%=t.getHorasMax() %></td>
     <td> <%=t.getMaxDiasAnticip() %></td>
     <td> <%=t.getSoloEncarg() %></td>
     
    <td><div class="form-group">
			
			<input type="radio" name="idTipo" id="idTipo" value=<%=t.getIdTipo() %>><br>
			
	</div></td>
    
    </tr>
    	<%
			}
		%>
    
    </tbody>

</table>
<div class="col-sm-2">



  		<button type="submit" name="param" value="baja" formmethod="post" formaction="tipos" class="btn btn-primary">Eliminar Tipo</button>
		
</div>

<div class="col-sm-2">



  		<button type="submit" name="param" value="modifTipo" formmethod="post" formaction="tipos" class="btn btn-primary">Editar Tipo</button>
		
</div>




<script>
function myFunction() {
    var radios = document.getElementsByName("idTipo");
    var i;
    var ID = null;
    for (i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            ID = radios[i].value;
        }
    }
    if (ID == null) { alert ("Seleccione un tipo de elemento");
	 return false;
	} 
else 
	{
	if (confirm("¿Está seguro que desea eliminar este tipo de elemento?") == true) {
		return true;
		  } else {
			 return false;
			 }
	 }
}
</script>

</form>


		<div class="col-sm-2">
		<form action="tipos" method="post">

  		<button type="submit" name="param" value="alta" formmethod="post" formaction="tipos" class="btn btn-primary">Agregar Tipo</button>
		</form>
</div>
		<div class="col-sm-6">
<form action="start" method="get">

  		<button type="submit" formmethod="get" formaction="start" class="btn btn-info">Volver</button>
		</form>
</div>

</div>
