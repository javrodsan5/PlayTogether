<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">

<h2>Crear un torneo</h2>

<form:form action="/championships/add" method="POST" modelAttribute="championship">
	<div>
		<label for="startDate">Fecha Inicio:</label><br> <br> 
		<input type="text" id="startDate" name="startDate"><br> <br> 
		<label for="finishDate">Fecha Fin:</label><br> <br> 
		<input type="text" id="finishDate" name="finishDate"><br> <br> 
			<label for="city">Ciudad:</label><br>
		<br> <input type="text" id="city" name="city"><br> <br>
		<label for="description">Description:</label><br> <br> <input
			type="text" id="description" name="description"><br> <br>
			
		<label for="deportes">Deportes:</label><br> <br>
		<select name="sport">
          <c:forEach var="item" items="${listaDeportes}">
            <option value="${item.id}">${item.name}</option>
          </c:forEach>
        </select>
		<input type="submit" value="Enviar">
	</div>
</form:form>
</playtogether:layout>