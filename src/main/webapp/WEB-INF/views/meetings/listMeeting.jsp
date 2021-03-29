<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="meetings">
<body>
<div class="cardtitle">
<h1><strong>Quedadas de ${nombreDeporte}</strong></h1>
<br/>
</div>
<div class="cardlist">
		<table id="meetingTable" class="table ">
				<thead>
					<tr class="rowtable">
						<th class="guiz-awards-header-title" style="width: 15%;">Dirección</th>
						<th class="guiz-awards-header-title" style="width: 15%;">Ciudad</th>
						<th class="guiz-awards-header-title" style="width: 20%;">Descripción</th>
						<th class="guiz-awards-header-title" style="width: 15%;">Fecha</th>
						<th class="guiz-awards-header-title" style="width: 20%;"></th>
						<th class="guiz-awards-header-title" style="width: 15%;"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${meetings}" var="meeting">
						<tr class="rowtable">
							<td><c:out value="${meeting.address}" /></td>
							<td><c:out value="${meeting.city}" /></td>
							<td><c:out value="${meeting.description}" /></td>
							<td><c:out value="${meeting.date}" /></td>
							<td><spring:url
									value="/sports/{deporte}/meetings/{meetingId}"
									var="meeting2Url">
									<spring:param name="meetingId" value="${meeting.id}" />
									<spring:param name="deporte" value="${deporte}" />
								</spring:url>
								<div class="botoncito">
									<a href="${fn:escapeXml(meeting2Url)}">Ver más</a>
								</div></td>
							<td><spring:url
									value="/sports/{deporte}/meetings/{meetingId}/edit"
									var="meetingUpdateUrl">
									<spring:param name="meetingId" value="${meeting.id}" />
									<spring:param name="deporte" value="${deporte}" />
								</spring:url>
								<div class="botoncito">
									<a class="" href="${fn:escapeXml(meetingUpdateUrl)}">Editar</a>
								</div></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
		<div class="cardbutton">
			<spring:url value="/sports/{deporte}/meetings/add" var="dateUrl">
				<spring:param name="deporte" value="${deporte}" />
			</spring:url>
			<div class="botoncitocrear">
				<a id="createMeeting" href="${fn:escapeXml(dateUrl)}">Crear
					quedada</a>
			</div>
		</div>
</playtogether:layout>

</body>
</html>