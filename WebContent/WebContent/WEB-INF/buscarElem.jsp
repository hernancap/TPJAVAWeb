<%@page import="java.util.ArrayList"%>
<%@page import="entity.Elemento"%>
<%@page import="controlers.CtrlElemento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seleccionar Elemento</title>

<style>
body {
    margin: 50px 50px ;
}
</style>
</head>
<body>

<h2>Seleccionar elemento:</h2>
<form action="reservas" method="post">

<table>
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

</table>
<div class="container">	
<div class="col-sm-2">
<button type="submit" name="param" value="nuevaRes" formmethod="post" formaction="reservas" class="btn btn-primary">Reservar</button>
</div>
</form>
<div class="col-sm-10">
<button type="submit" formmethod="get" formaction="start" class="btn btn-info">Volver</button>
</div>


</body>
</html>