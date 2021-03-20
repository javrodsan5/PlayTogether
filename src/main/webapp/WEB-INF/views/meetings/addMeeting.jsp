<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<div>
	<h2>Crear una quedada.</h2>

	<form:form modelAttribute="meeting" commandName="meeting">
		<div>
			<label for="date">Fecha:</label><br> <br> <input
				type="date" id="date.date" name="date.date"><br> <br> 
				<label for="time">Hora:</label><br> <br> <input
				type="time" id="date.time" name="date.time"><br> <br>
				<label for="city">Ciudad:</label><br> <br> <input type="text"
				id="city" name="city"><br> <br> <label
				for="address">Dirección:</label><br> <br> <input
				type="text" id="address" name="address"><br> <br>
			<label for="description">Description:</label><br> <br> <input
				type="text" id="description" name="description"><br> <br>
			<label for="deportes">Deportes:</label><br> <br>
		<select name="deportes">
          <c:forEach var="item" items="${listaDeportes}">
            <option value="${item.name}">${item.name}</option>
          </c:forEach>
        </select>
			<input type="submit" value="Enviar">
		</div>
	</form:form>

</div>