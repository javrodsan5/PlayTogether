<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">

<h2>Crear un Partido para el torneo</h2>

<form:form  commandName="match" modelAttribute="match">
	<div>
	 <c:if test="${match['new']}">
		<playtogether:inputField label="Fecha realización" name="dateTime" />
		<playtogether:inputField label="Equipo 1" name="team1" />
		<playtogether:inputField label="Equipo 2" name="team2" />
	 </c:if>
		<playtogether:inputField label="Puntos Equipo 1" name="puntos1" />
		<playtogether:inputField label="Puntos Equipo 2" name="puntos2" />
		<input type="hidden" name="championship" value="${championship}"/>
		<input type="submit" value="Enviar">
	</div>
</form:form>
</playtogether:layout>