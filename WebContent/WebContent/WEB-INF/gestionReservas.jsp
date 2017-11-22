<%@page import="java.util.ArrayList"%>
<%@page import="entity.Reserva"%>
<%@page import="entity.Persona"%>
<%@page import="controlers.CtrlReserva"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<title>Gestión Reservas</title>
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap-grid.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
</head>
<body>

<div class="container">	
	<h2>Reservas:</h2>
		
<form action="GestionReservas" method="post" onsubmit="return myFunction(this)">

<table class="table table-striped" id="tabla">
	<thead>
		<tr>
        <th>ID</th>
        <th>Tipo</th>
        <th>ID Elemento</th>
        <th>Fecha y Hora</th>
        <th>Tiempo de Uso (Horas)</th>
        <th>Detalle</th>
        <th>Seleccionar</th>
     	</tr>
	</thead>     
	
	
	<tbody>
	
	
		<%	
			CtrlReserva ctrlRes = new CtrlReserva();
			ArrayList<Reserva>listaRes = ctrlRes.mostrarReservas();
			
			for(Reserva r : listaRes){ 	
		%>
	
	
	<tr>
     <td> <%=r.getId() %></td>
     <td> <%=r.getTipo().getNombreTipo() %></td>
     <td> <%=r.getElemento().getIdElem() %></td>
    <td>  <%=r.getFechayhora() %></td>
    <td>  <%=r.getTiempoUso() %></td>
    <td>  <%=r.getDetalle() %></td>
    <td><div class="form-group">
			
			<input type="radio" name="idRes" id="idRes" value=<%=r.getId() %>><br>
			
	</div></td>
    
    </tr>
    	<%
			}
		%>
    
    </tbody>

</table>
<div class="col-sm-2">



  		<button type="submit" name="param" value="baja" formmethod="post" formaction="GestionReservas" class="btn btn-primary">Eliminar Reserva</button>
		
				</div>
<script>
function myFunction() {
    var radios = document.getElementsByName("idRes");
    var i;
    var ID = null;
    for (i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            ID = radios[i].value;
        }
    }
    if (ID == null) { alert ("Seleccione la reserva a eliminar");
	 return false;
	} 
else 
	{
	if (confirm("¿Está seguro que desea eliminar esta reserva?") == true) {
		return true;
		  } else {
			 return false;
			 }
	 }
}
</script>

</form>


<div class="col-sm-10">
<form action="start" method="get">

  		<button type="submit" formmethod="get" formaction="start" class="btn btn-info">Volver</button>
		</form>
</div>

</div>

		



</body>
</html>