<%@page import="java.util.ArrayList"%>
<%@page import="entity.Elemento"%>
<%@page import="controlers.CtrlElemento"%>
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
<title>Gestión Elementos</title>
<link rel="icon" href="http://getbootstrap.com/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap-grid.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
</head>
<body>

<div class="container">	
	<h2>Elementos:</h2>
		
<form action="elementos" method="post" onsubmit="return myFunction(this)">

<table class="table table-striped" id="tabla">
	<thead>
		<tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Tipo</th>
     	</tr>
	</thead>     
	
	
	<tbody>
	
	
		<%	
			CtrlElemento ctrlRes = new CtrlElemento();
			ArrayList<Elemento>listaEle = ctrlRes.mostrarElem();
			for(Elemento e : listaEle){ 	
		%>
	
	
	<tr>
     <td> <%=e.getIdElem() %></td>
     <td> <%=e.getNombre() %></td>
     <td> <%=e.getTipo().getNombreTipo() %></td>
    <td><div class="form-group">
			
			<input type="radio" name="idElem" id="idElem" value=<%=e.getIdElem() %>><br>
			
	</div></td>
    
    </tr>
    	<%
			}
		%>
    
    </tbody>

</table>
<div class="col-sm-2">



  		<button type="submit" name="param" value="baja" formmethod="post" formaction="elementos" class="btn btn-primary">Eliminar Elemento</button>
		
</div>

<div class="col-sm-2">



  		<button type="submit" name="param" value="modifElem" formmethod="post" formaction="elementos" class="btn btn-primary">Editar Elemento</button>
		
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


		<div class="col-sm-8">
		<form action="elementos" method="post">

  		<button type="submit" name="param" value="alta" formmethod="post" formaction="elementos" class="btn btn-primary">Agregar Elemento</button>
		</form>
</div>
</div>




		



</body>
</html>