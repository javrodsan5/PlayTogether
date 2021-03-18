<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<h2>Crear una quedada.</h2>

<form class="form-horizontal" id="add-meeting-form">
	<div>
		<label for="date">Fecha:</label><br> <br> <input type="datetime-local"
			id="date" name="date"><br> <br> 
			<label for="city">Ciudad:</label><br>
		<br> <input type="text" id="city" name="city"><br> <br>
		<label for="address">Dirección:</label><br> <br> <input
			type="text" id="address" name="address"><br> <br> <label
			for="description">Description:</label><br> <br> <input
			type="text" id="description" name="description"><br> <br>
		<input type="submit" value="Enviar">
	</div>
</form>

