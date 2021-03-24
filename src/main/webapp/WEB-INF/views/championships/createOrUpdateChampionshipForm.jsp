<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">

<h2>Crear un torneo</h2>

<form:form  commandName="championship" modelAttribute="championship">
	<div>

		<playtogether:inputField label="Fecha inicio" name="startDate" />
		<playtogether:inputField label="Fecha fin" name="finishDate" />
		<playtogether:inputField label="Ciudad" name="city" />
		<playtogether:inputField label="Descripción" name="description" />
		<input type="hidden" name="sport" value="${deporte}"/>
		<input type="submit" value="Enviar">
	</div>
</form:form>
</playtogether:layout>