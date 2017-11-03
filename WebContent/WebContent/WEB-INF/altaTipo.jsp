<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.ArrayList"%>
<%@page import="entity.TipoElemento"%>
<%@page import="controlers.CtrlTipoElem"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Tipo</title>
<style>
body {
    margin: 50px 50px ;
}
</style>

<link rel="icon" href="http://getbootstrap.com/favicon.ico">
    <!-- Bootstrap core CSS -->
    <link href="style/bootstrap-grid.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>


<div class="container">

	<h1>Agregar tipo de elemento</h1>


<form class="form-horizontal" name="agregarTipo" action="tipos" method="post" onsubmit="return checkvalue(this)">
<script type="text/javascript">
function checkvalue() { 
    var mystring = document.getElementById('nombre').value;
    var mystring1 = document.getElementById('cantMaxRes').value;
    var mystring2 = document.getElementById('horasMax').value;
    var mystring3 = document.getElementById('diasAntic').value;
    if(!mystring.match(/\S/) || !mystring1.match(/\S/) || !mystring2.match(/\S/) || !mystring3.match(/\S/) || document.getElementById("soloEncarg").value == "none") {
        alert ("Complete todos los campos");
        return false;
    } else {

        return true;
    }
}
</script>

		<%	
			TipoElemento te = (TipoElemento)request.getAttribute("tipo");	
		if (te.getIdTipo() != 0){
		%>
		
		
		<input type="hidden" name="editar" id="editar" value="<%=true%>" />
		<input type="hidden" name="idTipo" id="idTipo" value="<%=te.getIdTipo()%>" />
		
		
	<%
    } else { 
    
    %>
    
    <input type="hidden" name="editar" id="editar"  value="<%=false%>" />
    
    <%
    }
    
    %>
    

  
   <div class="form-group">
    <label class="control-label col-sm-2" for="nombre">Nombre:</label>
    <div class="col-sm-10">
    <%	
    if (te.getIdTipo() != 0){
    %>
     <input type="text" id="nombre" name="nombre" value=<%=te.getNombreTipo()%>>
    <%	
   		 } else { 
    %>
    
    <input type="text" id="nombre" name="nombre">
    
    <%	
   		 } 
    %>
    
     
    </div>
  </div>
  
  
  
     <div class="form-group">
     <label class="control-label col-sm-2" for="cantMaxRes">Cant. Máx. de Reservas:</label>
     <div class="col-sm-10">
     
         <%	
    if (te.getIdTipo() != 0){
    %>
    
    <input type="text" id="cantMaxRes" name="cantMaxRes" value=<%=te.getCantMaxRes()%> onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    
        <%	
   		 } else { 
   			 
   			%>	 
   			 
    <input type="text" id="cantMaxRes" name="cantMaxRes" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    
        <%	
   		 } 
    
         %>
 
    </div>
  </div>
  
       <div class="form-group">
    <label class="control-label col-sm-2" for="horasMax">Horas Máx. de Reserva:</label>
    <div class="col-sm-10">
    
             <%	
    if (te.getIdTipo() != 0){
    %>
    
    <input type="text" id="horasMax" name="horasMax" value=<%=te.getHorasMax()%> onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    
        <%	
   		 } else { 
    %>
    
    <input type="text" id="horasMax" name="horasMax" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    
        <%	
   		 } 
    %>
    
    
     
    </div>
  </div>
  
       <div class="form-group">
    <label class="control-label col-sm-2" for="diasAntic">Máx. Días de Anticip.:</label>
    <div class="col-sm-10">
    
             <%	
    if (te.getIdTipo() != 0){
    %>
    
    <input type="text" id="diasAntic" name="diasAntic" value=<%=te.getMaxDiasAnticip()%> onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    
        <%	
   		 } else { 
    %>
    
    <input type="text" id="diasAntic" name="diasAntic" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    
        <%	
   		 } 
    %>
    
     
    </div>
  </div>
  
          <div class="form-group">
    <label class="control-label col-sm-2" for="soloEncarg">Solo Encargado:</label>
    <div class="col-sm-10">
    <select id="soloEncarg"name="soloEncarg">   
    
             <%	
    if (te.getIdTipo() != 0){
    	
    	if (te.getSoloEncarg() == 1) {
    	
    	
    %>
    
    <option value="true">Si</option>
    <option value="false">No</option>
    
        <%	
    	} else {
    		
    		 %>
    		    
    		    <option value="false">No</option>
    		    <option value="true">Si</option>
    		    
    		        <%	
    		
    	}
        
    	} else { 
    %>
    
    <option value="none"></option> 
    <option value="false">No</option>
	<option value="true">Si</option>
    
        <%	
   		 } 
    %>

	</select>
    </div>
  </div>
  
 
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" name="param" value="agregarTipo" formmethod="post" formaction="tipos" class="btn btn-primary">Agregar Tipo</button>
    </div>
  </div>
</form>

</div>




</body>
</html>