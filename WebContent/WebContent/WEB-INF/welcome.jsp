<%@page import="java.util.ArrayList"%>
<%@page import="entity.Reserva"%>
<%@page import="entity.Persona"%>
<%@page import="controlers.CtrlReserva"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table, td {
    border:1px solid black;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestión Reservas</title>
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap-grid.css" rel="stylesheet">
    
</head>
<body>
	
	<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>
	<h2>Mis reservas:</h2>
		
		<%	
			CtrlReserva ctrlRes = new CtrlReserva();
			ArrayList<Reserva>listaRes = ctrlRes.mostrarReservas(((Persona)session.getAttribute("user")).getId());
			for(Reserva r : listaRes){ 	
		%>

<table>
	<tr>
     <td> <%=r.getId() %></td>
     <td> <%=r.getTipo().getNombreTipo() %></td>
     <td> <%=r.getElemento().getIdElem() %></td>
    <td>  <%=r.getFechayhora() %></td>
    <td>  <%=r.getTiempoUso() %></td>
    <td>  <%=r.getDetalle() %></td>
    </tr>
</table>

		<%
			}
		%>
		

		<form action="reservas" method="post">
  		<button type="submit" formmethod="post" formaction="reservas">Agregar Reserva</button>
		</form>


</body>
</html>