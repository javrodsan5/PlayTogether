<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<playtogether:layout pageName="meetings" invitaciones="${invitaciones}">
	<body>
		<div class="cardtitle">
			<h1>
				<strong>Quedadas de ${nombreDeporte}</strong>
			</h1>
			<br />
		</div>
		<c:if test="${limiteMes}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>
					Has llegado al límite de quedadas por mes para usuario básico. <a
						class="cta-button" href="/pay/premium">Suscríbete</a>
				</p>
			</div>
		</c:if>
		<div class="cardlist">
			<table id="meetingTable" class="table ">
				<thead>
					<tr class="rowtable">
						<th class="guiz-awards-header-title" style="width: 20%;">Dirección</th>
						<th class="guiz-awards-header-title" style="width: 10%;">Ciudad</th>
						<th class="guiz-awards-header-title" style="width: 20%;">Descripción</th>
						<th class="guiz-awards-header-title" style="width: 10%;">Fecha</th>
						<th class="guiz-awards-header-title" style="width: 10%;">Participantes</th>
						<th class="guiz-awards-header-title" style="width: 10%;">Anfitrión</th>
						<th class="guiz-awards-header-title" style="width: 20%;"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${meetings}" var="meeting">
						<tr class="rowtable">
							<td><c:out value="${meeting.address}" /></td>
							<td><c:out value="${meeting.city}" /></td>
							<td><c:out value="${meeting.description}" /></td>
							<td><fmt:parseDate value="${meeting.date }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
         			 <fmt:formatDate value = "${parsedDateTime}" pattern = "dd-MM-yyyy HH:mm"  /></td>
							<td><center><c:out value="${meeting.participants.size()}/${meeting.numberOfPlayers}" /></center></td>
							<td><spring:url value="/usuarios/{userId}" var="userdetails">
													<spring:param name="userId" value="${meeting.meetingCreator.id}" />
												</spring:url>
												<a href="${fn:escapeXml(userdetails)}">
								<c:out value="${meeting.meetingCreator.user.username}" /></a></td>
							<td><spring:url
									value="/sports/{deporte}/meetings/{meetingId}"
									var="meeting2Url">
									<spring:param name="meetingId" value="${meeting.id}" />
									<spring:param name="deporte" value="${deporte}" />
								</spring:url>
								<div class="botoncito">
									<a href="${fn:escapeXml(meeting2Url)}">Ver más</a>
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