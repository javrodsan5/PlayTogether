<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="sports">

	<div style="margin-left: 45px">
		<h2>Crear una quedada de ${sport.name}</h2>

		<form:form modelAttribute="meeting" commandName="meeting">
			<div>
				<playtogether:inputField label="Fecha" name="date" />
				<playtogether:inputField label="Ciudad" name="city" />
				<playtogether:inputField label="Dirección" name="address" />
				<playtogether:inputField label="Descripción" name="description" />
				<input type="hidden" name="sport" value="${deporte}"/>
				<input type="submit" value="Enviar">
			</div>
		</form:form>

	</div>
</playtogether:layout>