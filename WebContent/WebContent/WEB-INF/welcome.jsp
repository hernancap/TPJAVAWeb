<%@page import="java.util.ArrayList"%>
<%@page import="entity.Reserva"%>
<%@page import="entity.Persona"%>
<%@page import="controlers.CtrlReserva"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestión Reservas</title>
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap-grid.css" rel="stylesheet">
    
</head>
<body>
	
	<h1>Bienvenido <%=((Persona)session.getAttribute("user")).getNombre() %></h1>
	
			<%
			CtrlReserva ctrlRes = new CtrlReserva();
			ArrayList<Reserva>listaRes= ctrlRes.mostrarReservas(((Persona)session.getAttribute("user")).getId());
			for(Reserva r : listaRes){ 
		%>

		<div class="container">
  <div class="row">
    <div class="col">
      <%=r.getId() %>
    </div>
    <div class="col">
      <%=r.getTipo().getNombreTipo() %>
    </div>
    <div class="col">
      <%=r.getElemento().getIdElem() %>
    </div>
    <div class="col">
      <%=r.getFechayhora() %>
    </div>
    <div class="col">
      <%=r.getTiempoUso() %>
    </div>
    <div class="col">
      <%=r.getDetalle() %>
    </div>
  </div>
</div>
		<%
			}
		%>



</body>
</html>