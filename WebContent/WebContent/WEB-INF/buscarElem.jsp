<%@page import="java.util.ArrayList"%>
<%@page import="entity.Elemento"%>
<%@page import="controlers.CtrlElemento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Seleccionar Elemento</title>

<style>
body {
    margin: 50px 50px ;
}
table, td {
    border:1px solid black;
}
</style>
</head>
<body>

<h2>Seleccionar elemento:</h2>



<form action="reservas" method="post" onsubmit="return myFunction(this)">

<table class="table table-striped" id="tabla">
<thead>
		<tr>
        <th>Elemento</th>
        <th>Seleccionar</th>
     	</tr>
	</thead>     

	<tbody>


		<%
			ArrayList<Elemento>listaElem = (ArrayList<Elemento>)request.getAttribute("listaElementos");
			for(Elemento e : listaElem){
		%>
		<tr>
		
		
			<td><%=e.getNombre() %></td>
			<td><div class="form-group">
			
			<input type="radio" name="idElem" value=<%=e.getIdElem() %>><br>
			
			</div></td>
			
		</tr>
		<%
			}
		%>
    </tbody>
</table>
<div class="container">	
<div class="col-sm-2">
<button type="submit" name="param" value="nuevaRes" formmethod="post" formaction="reservas" class="btn btn-primary">Reservar</button>
</div>
<script>
function myFunction() {
    var radios = document.getElementsByName("idElem");
    var i;
    var ID = null;
    for (i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            ID = radios[i].value;
        }
    }
    if (ID == null) { alert ("Seleccione un elemento");
    						 return false;} else {
    return true;; }
}
</script>



</form>
<div class="col-sm-10">
<form action="reservas" method="post">
  		<button type="submit" name="param" value="welcome" formmethod="post" formaction="reservas" class="btn btn-info">Volver</button>
  		</form>
</div>
</div>



</body>
</html>