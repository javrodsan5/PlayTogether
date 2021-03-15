<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/css/style.css">
<title>Playtogether</title>
</head>
<body>
<img src="<s:url value="/images/logo.png" />" />
<h1>Descripción del proyecto</h1>
<div>
<p>El objetivo principal del proyecto es desarrollar un sistema de información que permita a los usuarios encontrar personas con gustos similares a estos 
en relación a la actividad física y poder realizar encuentros  para practicar deporte conjuntamente u organizar torneos entre diferentes equipos o usuarios. 
Estas búsquedas estarán basadas en las habilidades y la implicación de los usuarios en las actividades deseada. La idea principal de negocio reside en la diferenciación de dos 
tipos de usuarios, el básico y el premium, donde el premium adquiere un gran número de ventajas sobre el básico en el uso de la aplicación, como se puede apreciar en la siguiente tabla: </p>
<table style="width:100%">
  <tr>
    <th>Funcionalidad/Tipo de usuario</th>
    <th>Usuario básico</th>
    <th>Usuario premium</th>
  </tr>
  <tr>
    <td>Crear actividad</td>
    <td>1 al mes</td>
    <td>Ilimitado</td>
  </tr>
  <tr>
    <td>Unirte a actividad</td>
    <td>Ilimitado</td>
    <td>Ilimitado</td>
   <tr>
    <td>Crear y unirte a torneos</td>
    <td>De pago</td>
    <td>Sin pago extra</td>
   </tr>
  </tr>
     <tr>
    <td>Invitar a usuarios a tu actividad</td>
    <td>No</td>
    <td>Si</td>
   </tr>
  </tr>
     <tr>
    <td>Aceptar o rechazar usuarios en tu sesión</td>
    <td>No</td>
    <td>Si</td>
   </tr>
  </tr>
     <tr>
    <td>Ver y participar en clasificación de usuarios por deportes</td>
    <td>No</td>
    <td>Si</td>
   </tr>
  </tr>
</table>
</div>
<h1>¿Quiénes somos?</h1>
<div>
<p> Somos un equipo formado por 12 personas con distintas habilidades en el ámbito de la programación, cuyo Project Manager es Javier Rodríguez Santiago 
y que está subdividido en dos equipos diferentes: Un subequipo formado por 6 personas especializado en el desarrollo BackEnd y el Testing y que tiene a 
Carlos Santos Tirado como Team Manager, y un subequipo formado por 6 personas especializado en el FrontEnd y el Testing, con Manuel Bueno Gómez como Team Manager.
</div>
<h1>Equipo BackEnd</h1>
<div class="row">
  <div class="column">
    <img src="/images/javiCircle.png" alt="Javier" style="width:100%">
    <p>Javier Rodríguez Santiago</p>
    <p>Project Manager</p>
  </div>
  <div class="column">
    <img src="/images/carlosCircle.png" alt="Carlos" style="width:100%">
    <p>Carlos Santos Tirado</p>
    <p>Team Manager</p>
  </div>
  <div class="column">
    <img src="/images/guerreroCircle.png" alt="Gonzalo" style="width:100%">
    <p>Gonzalo Guerrero Luna</p>
    <p>Analista</p>
  </div>
   <div class="column">
    <img src="/images/carolinaCircle.png" alt="Carolina" style="width:100%">
    <p>Carolina Carrasco Díaz</p>
    <p>Analista</p>
  </div>
    <div class="column">
    <img src="/images/andresCircle.png" alt="Andrés" style="width:100%">
    <p>Andrés Fernández Rodríguez</p>
    <p>Programador</p>
  </div>
   <div class="column">
    <img src="/images/marioCircle.png" alt="Mario" style="width:100%">
    <p>Mario David Jiménez Hartman</p>
    <p>Programador</p>
  </div>
</div>
<h1>Equipo FrontEnd</h1>
<div class="row">
  <div class="column">
    <img src="/images/manuelCircle.png" alt="Manuel" style="width:100%">
    <p>Manuel Bueno Gómez</p>
    <p>Team Manager</p>
  </div>
  <div class="column">
    <img src="/images/alejandroCircle.png" alt="Alejandro" style="width:100%">
    <p>Alejandro Manzano Dorado</p>
    <p>Analista</p>
  </div>
  <div class="column">
    <img src="/images/cuberoCircle.png" alt="Pablo" style="width:100%">
    <p>Pablo Cubero Cruz</p>
    <p>Analista</p>
  </div>
  <div class="column">
    <img src="/images/conchiCircle.png" alt="Concepción" style="width:100%">
    <p>Concepción Márquez Redondo</p>
    <p>Programadora</p>
  </div>
  <div class="column">
    <img src="/images/fernandoCircle.png" alt="Fernando" style="width:100%">
    <p>Fernando Calvo Durán</p>
    <p>Programador</p>
  </div>
  <div class="column">
    <img src="/images/pabloCircle.png" alt="PabloG" style="width:100%">
    <p>Pablo García Cerrejón</p>
    <p>Programador</p>
  </div>
</div>
<h1>¿Te interesa la aplicación? Introduce aquí tu correo</h1>
<div class="correo">
<form action="/addUser" method="POST">
<div>
  <label for="correo">Correo:</label><br><br>
  <input type="text" id="correo" name="correo"><br><br>
  <input type="submit" value="Enviar">
</div>
</form>
<div>
${exit}
</div>
<!-- <h1>Usuarios registrados</h1> -->
<%-- <c:forEach items="${usr}" var="item"> --%>
<!-- 	<div> -->
<%--     <c:out value="${item.correo}"/><br> --%>
<!--     </div> -->
<%-- </c:forEach> --%>
<br><br><br>
</div>
</body>
<footer>
  <p>Playtogether</p><br>
</footer>
</html>