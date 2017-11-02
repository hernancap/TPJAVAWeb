<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Menú Admin</title>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">TP Java</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="Start">Mis Reservas</a></li>
      <li><a href="GestionReservas">Gestión Reservas</a></li>
      <li><a href="Usuarios">Gestión Usuarios</a></li>
      <li><a href="Tipos">Gestión Tipos</a></li>
      <li><a href="Elementos">Gestión Elementos</a></li>
    </ul>
  </div>
</nav>

<jsp:include page="welcome.jsp" />
<jsp:include page="footer.jsp" />

</body>
</html>