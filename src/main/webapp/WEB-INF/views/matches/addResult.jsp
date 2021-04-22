<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships" invitaciones="${invitaciones}">

<h2>Crear un Partido para el torneo</h2>

<form:form  commandName="match" modelAttribute="match">
	<div>


		<playtogether:inputField label="Puntos Equipo 1" name="puntos1" />
		<playtogether:inputField label="Puntos Equipo 2" name="puntos2" />
		<input type="hidden" name="dateTime" value="${match.dateTime}"/>
		<input type="hidden" name="team1" value="${match.team1}"/>
		<input type="hidden" name="team2" value="${match.team2}"/>
		<input type="submit" value="Enviar">
	</div>
</form:form>
</playtogether:layout>