<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.ArrayList"%>
<%@page import="entity.Persona"%>
<%@page import="controlers.CtrlPersona"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Persona</title>
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

	<h1>Agregar persona</h1>


<form class="form-horizontal" name="agregarPer" action="usuarios" method="post" onsubmit="return checkvalue(this)">
<script type="text/javascript">
function checkvalue() { 
    var mystring = document.getElementById('dni').value;
    var mystring1 = document.getElementById('nombre').value;
    var mystring2 = document.getElementById('apellido').value;
    var mystring3 = document.getElementById('usu').value;
    var mystring4 = document.getElementById('pass').value;
    if(!mystring.match(/\S/) || !mystring1.match(/\S/) || !mystring2.match(/\S/) || !mystring3.match(/\S/) || !mystring4.match(/\S/) || document.getElementById("categ").value == "none" || document.getElementById("habilitado").value == "none") {
        alert ("Complete todos los campos");
        return false;
    } else {

        return true;
    }
}
</script>

		<%	
			Persona pe = (Persona)request.getAttribute("persona");	
		if (pe.getId() != 0){
		%>
		
		
		<input type="hidden" name="editar" id="editar" value="<%=true%>" />
		<input type="hidden" name="idPer" id="idPer" value="<%=pe.getId()%>" />
		
		
	<%
    } else { 
    
    %>
    
    <input type="hidden" name="editar" id="editar"  value="<%=false%>" />
    
    <%
    }
    
    %>
    

  
   <div class="form-group">
    <label class="control-label col-sm-2" for="dni">DNI:</label>
    <div class="col-sm-10">
    <%	
    if (pe.getId() != 0){
    %>
     <input type="text" id="dni" name="dni" value=<%=pe.getDni()%>>
    <%	
   		 } else { 
    %>
    
    <input type="text" id="dni" name="dni">
    
    <%	
   		 } 
    %>
    
     
    </div>
  </div>
  
  
  
     <div class="form-group">
     <label class="control-label col-sm-2" for="nombre">Nombre:</label>
     <div class="col-sm-10">
     
         <%	
    if (pe.getId() != 0){
    %>
    
    <input type="text" id="nombre" name="nombre" value=<%=pe.getNombre()%>>
    
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
    <label class="control-label col-sm-2" for="apellido">Apellido:</label>
    <div class="col-sm-10">
    
             <%	
    if (pe.getId() != 0){
    %>
    
    <input type="text" id="apellido" name="apellido" value=<%=pe.getApellido()%>>
    
        <%	
   		 } else { 
    %>
    
    <input type="text" id="apellido" name="apellido">
    
        <%	
   		 } 
    %>
    
    
     
    </div>
  </div>
  
            <div class="form-group">
    <label class="control-label col-sm-2" for="habilitado">Habilitado:</label>
    <div class="col-sm-10">
    <select id="habilitado"name="habilitado">   
    
             <%	
    if (pe.getId() != 0){
    	
    	if (pe.getHabilitado() == 1) {
    	
    	
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
    <label class="control-label col-sm-2" for="usu">Usuario:</label>
    <div class="col-sm-10">
    
             <%	
    if (pe.getId() != 0){
    %>
    
    <input type="text" id="usu" name="usu" value=<%=pe.getUsuario()%>>
    
        <%	
   		 } else { 
    %>
    
    <input type="text" id="usu" name="usu">
    
        <%	
   		 } 
    %>
    
    
     
    </div>
  </div>
  
         <div class="form-group">
    <label class="control-label col-sm-2" for="pass">Contraseña:</label>
    <div class="col-sm-10">
    
             <%	
    if (pe.getId() != 0){
    %>
    
    <input type="text" id="pass" name="pass" value=<%=pe.getContraseña()%>>
    
        <%	
   		 } else { 
    %>
    
    <input type="text" id="pass" name="pass">
    
        <%	
   		 } 
    %>
    
    
     
    </div>
  </div>

  
          <div class="form-group">
    <label class="control-label col-sm-2" for="categ">Categoría:</label>
    <div class="col-sm-10">
    <select id="categ"name="categ">   
    
             <%	
    if (pe.getId() != 0){
    	
    	if (pe.getCategoria().equals("admin")) {
    	
    	
    %>
    
    <option value="admin">Administrador</option>
    <option value="usuario">Usuario</option>
    <option value="encargado">Encargado</option>
    
    
        <%	
    	} else {  if (pe.getCategoria().equals("usuario")) {
    		
    		 %>
    		    
    		    <option value="usuario">Usuario</option>
    		    <option value="admin">Administrador</option>
    		    <option value="encargado">Encargado</option>
    		    
    		        <%	
    		
    	} else {  
    		
    		%>
		    
		    <option value="encargado">Encargado</option>
		    <option value="admin">Administrador</option>
		    <option value="usuario">Usuario</option>
		    		    
		    
		        <%	
    		
    		
    		
    	}
    	}
    		
    		
    	
        
    	} else { 
    %>
    
    <option value="none"></option> 
    <option value="admin">Administrador</option>
    <option value="usuario">Usuario</option>
    <option value="encargado">Encargado</option>
    
        <%	
   		 } 
    %>

	</select>
    </div>
  </div>
  
 
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" name="param" value="agregarPer" formmethod="post" formaction="usuarios" class="btn btn-primary">Agregar Persona</button>
    </div>
  </div>
</form>

</div>




</body>
</html>