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
				type="text" id="date" name="date"><br> <br> <label
				for="city">Ciudad:</label><br> <br> <input type="text"
				id="city" name="city"><br> <br> <label
				for="address">Dirección:</label><br> <br> <input
				type="text" id="address" name="address"><br> <br>
			<label for="description">Description:</label><br> <br> <input
				type="text" id="description" name="description"><br> <br>
			<input type="submit" value="Enviar">
		</div>
	</form:form>

</div>