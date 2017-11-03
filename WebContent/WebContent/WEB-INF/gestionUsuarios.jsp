<%@page import="java.util.ArrayList"%>
<%@page import="entity.Persona"%>
<%@page import="controlers.CtrlPersona"%>
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
<title>Gestión Personas</title>
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap-grid.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
</head>
<body>

<div class="container">	
	<h2>Personas:</h2>
		
<form action="usuarios" method="post" onsubmit="return myFunction(this)">

<table class="table table-striped" id="tabla">
	<thead>
		<tr>
        <th>ID</th>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Habilitado</th>
        <th>usuario</th>
        <th>Contraseña</th>
        <th>Categoría</th>
     	</tr>
	</thead>     
	
	
	<tbody>
	
	
		<%	
			CtrlPersona ctrlPer = new CtrlPersona();
			ArrayList<Persona>listaPer = ctrlPer.mostrarPersona();
			for(Persona p : listaPer){ 	
		%>
	
	
	<tr>
     <td> <%=p.getId()%></td>
     <td> <%=p.getDni() %></td>
     <td> <%=p.getNombre() %></td>
     <td> <%=p.getApellido() %></td>
     <td> <%=p.getHabilitado() %></td>
     <td> <%=p.getUsuario() %></td>
     <td> <%=p.getContraseña() %></td>
     <td> <%=p.getCategoria()%></td>
     
    <td><div class="form-group">
			
			<input type="radio" name="idPer" id="idPer" value=<%=p.getId() %>><br>
			
	</div></td>
    
    </tr>
    	<%
			}
		%>
    
    </tbody>

</table>
<div class="col-sm-2">



  		<button type="submit" name="param" value="baja" formmethod="post" formaction="usuarios" class="btn btn-primary">Eliminar Persona</button>
		
</div>

<div class="col-sm-2">



  		<button type="submit" name="param" value="modifPer" formmethod="post" formaction="usuarios" class="btn btn-primary">Editar Persona</button>
		
</div>




<script>
function myFunction() {
    var radios = document.getElementsByName("idPer");
    var i;
    var ID = null;
    for (i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            ID = radios[i].value;
        }
    }
    if (ID == null) { alert ("Seleccione una persona");
    						 return false;} else {
    return true;; }
}
</script>

</form>


		<div class="col-sm-8">
		<form action="tipos" method="post">

  		<button type="submit" name="param" value="alta" formmethod="post" formaction="usuarios" class="btn btn-primary">Agregar Persona</button>
		</form>
</div>
</div>