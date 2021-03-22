<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="sports">

	<div style="margin-left: 45px">
		<h2>Crear una quedada.</h2>

		<form:form modelAttribute="meeting" commandName="meeting">
			<div>
				<playtogether:inputField label="Fecha" name="date" />
				<playtogether:inputField label="Ciudad" name="city" />
				<playtogether:inputField label="Dirección" name="address" />
				<playtogether:inputField label="Descripción" name="description" />
				<label for="deportes">Deportes:</label><br> <br> <select
					name="sport">
					<c:forEach var="item" items="${listaDeportes}">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select> <input type="submit" value="Enviar">
			</div>
		</form:form>

	</div>
</playtogether:layout>