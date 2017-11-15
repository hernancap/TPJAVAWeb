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
  
  <style>
body {
    margin: 50px 50px ;
}
</style>

<title>Problema</title>
</head>
<body>

		<%	
			String motivError = (String)request.getAttribute("motivoError");	
			String nomTipo	= 	(String)request.getAttribute("nomTipo");

		if(motivError.equals("validarAntip")){
					
		%>
		
		<div class="alert alert-warning">
 		 <strong>Problema!</strong> No se puede reservar <%=nomTipo%> con tan pocos días de anticipación.
		</div>
		
		<%
		
		} else { if(motivError.equals("validarCantMaxRes")){
		
		%>
		
		<div class="alert alert-warning">
 		 <strong>Problema!</strong> Ya alcanzó la cantidad máxima de reservas de <%=nomTipo%>.
		</div>		
		
		
	 	<%
		
		} else { if(motivError.equals("validarEncarg")){
		
		%>
		
		<div class="alert alert-warning">
 		 <strong>Problema!</strong> Solo un encargado puede reservar <%=nomTipo%>.
		</div>		
		
		
		<%
		
		} else { if(motivError.equals("tiempoUso")){
		
		%>

		<div class="alert alert-warning">
 		 <strong>Problema!</strong> La cantidad de tiempo seleccionado para la reserva supera el límite máximo para <%=nomTipo%>.
		</div>		
		
		
		<%
		
		} } } }
		
		%>
		
		

</body>
</html>