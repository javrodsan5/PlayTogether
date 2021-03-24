<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<body>
	<playtogether:layout pageName="meetings">
		<h1>
			<strong>Quedadas de ${nombreDeporte}</strong>
		</h1>
		<br />
		<table id="meetingTable" class="table table-striped">
			<thead>
				<tr>
					<th style="width: 20%;">Dirección</th>
					<th style="width: 30%;">Ciudad</th>
					<th style="width: 30%;">Descripción</th>
					<th style="width: 20%;">Fecha</th>
					<th style="width: 20%">Ver más</th>
					<th style="width: 20%">Editar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${meetings}" var="meeting">
					<tr>
						<td><c:out value="${meeting.address}" /></td>
						<td><c:out value="${meeting.city}" /></td>
						<td><c:out value="${meeting.description}" /></td>
						<td><c:out value="${meeting.date}" /></td>
						<td><spring:url
								value="/sports/{deporte}/meetings/{meetingId}" var="meeting2Url">
								<spring:param name="meetingId" value="${meeting.id}" />
								<spring:param name="deporte" value="${deporte}" />
							</spring:url> <a href="${fn:escapeXml(meeting2Url)}">Ver más</a></td>
						<td><spring:url
								value="/sports/{deporte}/meetings/{meetingId}/edit"
								var="meetingUpdateUrl">
								<spring:param name="meetingId" value="${meeting.id}" />
								<spring:param name="deporte" value="${deporte}" />
							</spring:url> <a href="${fn:escapeXml(meetingUpdateUrl)}">Editar</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<spring:url value="/sports/{deporte}/meetings/add" var="dateUrl">
			<spring:param name="deporte" value="${deporte}" />
		</spring:url>
		<a id="createMeeting" href="${fn:escapeXml(dateUrl)}">Crear
			quedada</a>
	</playtogether:layout>
</body>
</html>