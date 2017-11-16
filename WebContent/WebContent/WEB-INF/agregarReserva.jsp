<%@page import="java.util.ArrayList"%>
<%@page import="entity.TipoElemento"%>
<%@page import="controlers.CtrlTipoElem"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  
  


<head>

<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        

		<link rel="icon" type="image/png" href="/favicon-196x196.png" sizes="196x196">
		<link rel="icon" type="image/png" href="/favicon-160x160.png" sizes="160x160">
		<link rel="icon" type="image/png" href="/favicon-96x96.png" sizes="96x96">
		<link rel="icon" type="image/png" href="/favicon-16x16.png" sizes="16x16">
		<link rel="icon" type="image/png" href="/favicon-32x32.png" sizes="32x32">
		<meta name="msapplication-TileColor" content="#2b5797">
		<meta name="msapplication-TileImage" content="/mstile-144x144.png">


        <link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link href="./css/prettify-1.0.css" rel="stylesheet">
        <link href="./css/base.css" rel="stylesheet">
        <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">

  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">

    <link href="style/bootstrap.css" rel="stylesheet">
    
    		<script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
			<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
			<script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
			
			<style>
body {
    margin: 50px 50px ;
}
</style>
    
       

<title>Agregar reserva</title>
</head>
<body>

<div class="container">

	<h1>Agregar reserva</h1>
	
	<div class="panel panel-default">


<form class="form-horizontal" name="agregarRes" action="reservas" method="post" onsubmit="return checkvalue(this)">
<script type="text/javascript">
function checkvalue() { 
    var mystring = document.getElementById('tiempoUso').value;
    if(!mystring.match(/\S/) || document.getElementById("nomTipo").selectedIndex == 0 || document.getElementById("horaRes").selectedIndex == 0) {
        alert ("Complete todos los campos");
        return false;
    } else {
        return true;
    }
}
</script>

<div class="form-group">
    <label class="control-label col-sm-2" for="nomTipo">Tipo de elemento:</label>
    <div class="col-sm-10">
    <select id="nomTipo" name="nomTipo">   
    <option value="none"></option> 	
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
    <label class="control-label col-sm-2" for="tiempoUso">Tiempo de reserva:</label>
    <div class="col-sm-10">
     <input type="text" id="tiempoUso" name="tiempoUso" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
    </div>
  </div>
  

<div class="form-group">
<label class="control-label col-sm-2" for="datetimepicker">Fecha de reserva:</label>
    <div class="col-sm-10">
  <input data-format="yyyy-MM-dd" type="text" id="datetimepicker" name="datetimepicker"/>
  </div>
</div>
<script type="text/javascript">
  $(function() {

    $('#datetimepicker').datetimepicker({
      format: 'YYYY-MM-DD',
      minDate: moment()
      
    });
  });
</script>
<script src="./js/prettify-1.0.min.js"></script>
        <script src="./js/base.js"></script>
        
        
        <div class="form-group">
    <label class="control-label col-sm-2" for="horaRes">Hora reserva:</label>
    <div class="col-sm-10">
    <select id="horaRes"name="horaRes">   
    <option value="none"></option> 	
	<option value="06:00">06:00</option>
	<option value="07:00">07:00</option>
	<option value="08:00">08:00</option>
	<option value="09:00">09:00</option>
	<option value="10:00">10:00</option>
	<option value="11:00">11:00</option>
	<option value="12:00">12:00</option>
	<option value="13:00">13:00</option>
	<option value="14:00">14:00</option>
	<option value="15:00">15:00</option>
	<option value="16:00">16:00</option>
	<option value="17:00">17:00</option>
	<option value="18:00">18:00</option>
	<option value="19:00">19:00</option>
	<option value="20:00">20:00</option>
	<option value="21:00">21:00</option>
	<option value="22:00">22:00</option>
	<option value="23:00">23:00</option>
	
	

	</select>
    </div>
  </div>
  
  <div class="form-group">
    <label class="control-label col-sm-2" for="detalle">Detalle:</label>
    <div class="col-sm-10">
     <textarea class="form-control" rows="3" id="detalle" name="detalle"></textarea>
    </div>
  </div>
  
        
  
<div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" name="param" value="buscarElem" formmethod="post" formaction="reservas" class="btn btn-primary">Buscar elementos</button>
    </div>
  </div>
</form>

</div>

</div>



</body>
</html>