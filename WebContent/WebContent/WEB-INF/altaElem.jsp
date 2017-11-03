<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
    
    <%@page import="java.util.ArrayList"%>
<%@page import="entity.TipoElemento"%>
<%@page import="entity.Elemento"%>
<%@page import="controlers.CtrlTipoElem"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Elemento</title>
<style>
body {
    margin: 50px 50px ;
}
</style>

</head>
<body>


<div class="container">

	<h1>Agregar elemento</h1>


<form class="form-horizontal" name="agregarElem" action="elementos" method="post" onsubmit="return checkvalue(this)">
<script type="text/javascript">
function checkvalue() { 
    var mystring = document.getElementById('nombre').value;
    if(!mystring.match(/\S/) || document.getElementById("nomTipo").value == "none") {
        alert ("Complete todos los campos");
        return false;
    } else {

        return true;
    }
}
</script>

		<%	
			Elemento elem = (Elemento)request.getAttribute("elemento");	
		if (elem.getIdElem() != 0){
		%>
		
		
		<input type="hidden" name="editar" id="editar" value="<%=true%>" />
		<input type="hidden" name="idElem" id="idElem" value="<%=elem.getIdElem()%>" />
		
	<%
    } else { 
    
    %>
    
    <input type="hidden" name="editar" id="editar"  value="<%=false%>" />
    
    <%
    }
    
    %>
    

<div class="form-group">
    <label class="control-label col-sm-2" for="nomTipo">Tipo de elemento:</label>
    <div class="col-sm-10">
    <select id="nomTipo" name="nomTipo">   
    
    <%	
    if (elem.getIdElem() != 0){
    	
    %>
    <option value=<%=elem.getTipo().getNombreTipo()%>><%=elem.getTipo().getNombreTipo()%></option> 	
    
    <%
    
    } else { 
    %>
    	
    	<option value="none"></option>
    	
    <%
    }
    
    %>
    
		<%
			CtrlTipoElem ctrlTipo = new CtrlTipoElem();	
			ArrayList<TipoElemento> tipos = new ArrayList<TipoElemento>();
			tipos = ctrlTipo.mostrarTipos();
			for(TipoElemento te : tipos) {
		%>	

	<option value=<%=te.getNombreTipo()%>><%=te.getNombreTipo()%></option>
	<% 
	} 
	%>
	</select>
    </div>
  </div>
  
   <div class="form-group">
    <label class="control-label col-sm-2" for="nombre">Nombre:</label>
    <div class="col-sm-10">
    <%	
    if (elem.getIdElem() != 0){
    %>
     <input type="text" id="nombre" name="nombre" value=<%=elem.getNombre()%>>
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
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" name="param" value="agregarElem" formmethod="post" formaction="elementos" class="btn btn-primary">Agregar Elemento</button>
    </div>
  </div>
</form>

</div>




</body>
</html>