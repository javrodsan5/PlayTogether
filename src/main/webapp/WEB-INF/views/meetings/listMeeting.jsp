<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<playtogether:layout pageName="meetings" invitaciones="${invitaciones}">
	<body>
		<div class="cardtitle">
			<h1 class="list-meeting-championship-title">
				<strong>Quedadas de ${nombreDeporte}</strong>
			</h1>
		</div>
		<c:if test="${limiteMes}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>
					Has llegado al límite de quedadas por mes para usuario básico. <a
						class="cta-button" href="/pay/premium">Suscríbete</a>
				</p>
			</div>
		</c:if>
		<br>
		<div style="display: inline-flex; margin-left: 10%;">
			<p>Filtar por categoría: </p><a href="/sports/${deporte}/meetings?category=Todas"><p>Todas</p></a> <c:out value="	|	" /> <a href="/sports/${deporte}/meetings?category=Principiante"><p>Principiante</p></a> <c:out value=" | " />
			<a href="/sports/${deporte}/meetings?category=Intermedio"><p>Intermedio</p></a> <c:out value=" | " /> <a href="/sports/${deporte}/meetings?category=Avanzado"><p>Avanzado</p></a>
		</div>
		<div class="cardlist meeting-and-championship-list">
			<c:if test="${meetings != null && meetings.size() <= 3 && meetings.size() > 0}">
				<div class="scroll_vertical" id="style_scroll" style="height: 20em">
					<table id="meetingTable" class="table ">
						<thead>
							<tr class="rowtable">
								<th class="guiz-awards-header-title" style="width: 18%;">Dirección</th>
								<th class="guiz-awards-header-title" style="width: 10%;">Ciudad</th>
								<th class="guiz-awards-header-title" style="width: 17%;">Fecha</th>
								<th class="guiz-awards-header-title" style="width: 9%;">Participantes</th>
								<th class="guiz-awards-header-title" style="width: 9%;">Categoría</th>
								<th class="guiz-awards-header-title" style="width: 10%;">Anfitrión</th>
								<th class="guiz-awards-header-title" style="width: 4%;">Participación</th>
								<th class="guiz-awards-header-title" style="width: 10%;"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${meetings}" var="meeting">
								<tr class="rowtable">
									<td><c:out value="${meeting.address}" /></td>
									<td><c:out value="${meeting.city}" /></td>
									<td><fmt:parseDate value="${meeting.date }"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<fmt:formatDate value="${parsedDateTime}"
											pattern="dd-MM-yyyy HH:mm" /></td>
									<td><center>
											<c:out
												value="${meeting.participants.size()}/${meeting.numberOfPlayers}" />
										</center></td>
									<td><c:out value="${meeting.category.name}" /></td>
									<td><spring:url value="/usuarios/{userId}"
											var="userdetails">
											<spring:param name="userId"
												value="${meeting.meetingCreator.id}" />
										</spring:url> <a href="${fn:escapeXml(userdetails)}"> <c:out
												value="${meeting.meetingCreator.user.username}" /></a></td>
									<c:if
										test="${meeting.participants != null && meeting.participants.contains(usuario_logueado)}">
										<td style="text-align: center"><i
											class="fa fa-check-circle" style="color: green;"> </i></td>
									</c:if>
									<c:if
										test="${ meeting.participants == null || meeting.participants.contains(usuario_logueado)!=true }">
										<td style="text-align: center"><i
											class="fa fa-times-circle"
											style="color: red; text-align: center !important;"> </i></td>
									</c:if>
									<td><center>
											<spring:url value="/sports/{deporte}/meetings/{meetingId}"
												var="meeting2Url">
												<spring:param name="meetingId" value="${meeting.id}" />
												<spring:param name="deporte" value="${deporte}" />
											</spring:url>
											<div class="botoncito">
												<a href="${fn:escapeXml(meeting2Url)}">Ver más</a>
											</div>
										</center></td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>
			</c:if>
			<c:if test="${meetings != null && meetings.size() > 3}">
				<div class="scroll_vertical" id="style_scroll" style="height: 20em">
					<table id="meetingTable" class="table ">
						<thead>
							<tr class="rowtable">
								<th class="guiz-awards-header-title" style="width: 18%;">Dirección</th>
								<th class="guiz-awards-header-title" style="width: 10%;">Ciudad</th>
								<th class="guiz-awards-header-title" style="width: 17%;">Fecha</th>
								<th class="guiz-awards-header-title" style="width: 9%;">Participantes</th>
								<th class="guiz-awards-header-title" style="width: 10%;">Anfitrión</th>
								<th class="guiz-awards-header-title" style="width: 4%;">Participación</th>
								<th class="guiz-awards-header-title" style="width: 10%;"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${meetings}" var="meeting">
								<tr class="rowtable">
									<td><c:out value="${meeting.address}" /></td>
									<td><c:out value="${meeting.city}" /></td>
									<td><fmt:parseDate value="${meeting.date }"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<fmt:formatDate value="${parsedDateTime}"
											pattern="dd-MM-yyyy HH:mm" /></td>
									<td><center>
											<c:out
												value="${meeting.participants.size()}/${meeting.numberOfPlayers}" />
										</center></td>
									<td><spring:url value="/usuarios/{userId}"
											var="userdetails">
											<spring:param name="userId"
												value="${meeting.meetingCreator.id}" />
										</spring:url> <a href="${fn:escapeXml(userdetails)}"> <c:out
												value="${meeting.meetingCreator.user.username}" /></a></td>
									<c:if
										test="${meeting.participants != null && meeting.participants.contains(usuario_logueado)}">
										<td style="text-align: center"><i
											class="fa fa-check-circle" style="color: green;"> </i></td>
									</c:if>
									<c:if
										test="${ meeting.participants == null || meeting.participants.contains(usuario_logueado)!=true }">
										<td style="text-align: center"><i
											class="fa fa-times-circle"
											style="color: red; text-align: center !important;"> </i></td>
									</c:if>
									<td><center>
											<spring:url value="/sports/{deporte}/meetings/{meetingId}"
												var="meeting2Url">
												<spring:param name="meetingId" value="${meeting.id}" />
												<spring:param name="deporte" value="${deporte}" />
											</spring:url>
											<div class="botoncito">
												<a href="${fn:escapeXml(meeting2Url)}">Ver más</a>
											</div>
										</center></td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>
			</c:if>
			<c:if test="${meetings != null && meetings.size() == 0}">
				<div class="scroll_vertical" id="style_scroll" style="height: 10em">
					<table id="meetingTable" class="table ">
						<thead>
							<tr class="rowtable">
								<th class="guiz-awards-header-title" style="width: 18%;">Dirección</th>
								<th class="guiz-awards-header-title" style="width: 10%;">Ciudad</th>
								<th class="guiz-awards-header-title" style="width: 17%;">Fecha</th>
								<th class="guiz-awards-header-title" style="width: 9%;">Participantes</th>
								<th class="guiz-awards-header-title" style="width: 10%;">Anfitrión</th>
								<th class="guiz-awards-header-title" style="width: 4%;">Participación</th>
								<th class="guiz-awards-header-title" style="width: 10%;"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${meetings}" var="meeting">
								<tr class="rowtable">
									<td><c:out value="${meeting.address}" /></td>
									<td><c:out value="${meeting.city}" /></td>
									<td><fmt:parseDate value="${meeting.date }"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<fmt:formatDate value="${parsedDateTime}"
											pattern="dd-MM-yyyy HH:mm" /></td>
									<td><center>
											<c:out
												value="${meeting.participants.size()}/${meeting.numberOfPlayers}" />
										</center></td>
									<td><spring:url value="/usuarios/{userId}"
											var="userdetails">
											<spring:param name="userId"
												value="${meeting.meetingCreator.id}" />
										</spring:url> <a href="${fn:escapeXml(userdetails)}"> <c:out
												value="${meeting.meetingCreator.user.username}" /></a></td>
									<c:if
										test="${meeting.participants != null && meeting.participants.contains(usuario_logueado)}">
										<td style="text-align: center"><i
											class="fa fa-check-circle" style="color: green;"> </i></td>
									</c:if>
									<c:if
										test="${ meeting.participants == null || meeting.participants.contains(usuario_logueado)!=true }">
										<td style="text-align: center"><i
											class="fa fa-times-circle"
											style="color: red; text-align: center !important;"> </i></td>
									</c:if>
									<td><center>
											<spring:url value="/sports/{deporte}/meetings/{meetingId}"
												var="meeting2Url">
												<spring:param name="meetingId" value="${meeting.id}" />
												<spring:param name="deporte" value="${deporte}" />
											</spring:url>
											<div class="botoncito">
												<a href="${fn:escapeXml(meeting2Url)}">Ver más</a>
											</div>
										</center></td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>
			</c:if>
		</div>
		<div class="cardbutton">
			<spring:url value="/sports/{deporte}/meetings/add" var="dateUrl">
				<spring:param name="deporte" value="${deporte}" />
			</spring:url>
			<div class="botoncito-crear-meeting">
				<a id="createMeeting" href="${fn:escapeXml(dateUrl)}">Crear
					quedada</a>
			</div>
		</div>
		<div class="cardbutton" style="padding-top: 10px;">
			<spring:url value="/sports" var="dateUrl">
			</spring:url>
			<div class="botoncito-crear-meeting">
				<a id="createMeeting" href="${fn:escapeXml(dateUrl)}">Volver a
					la lista</a>
			</div>
		</div>
</playtogether:layout>

</body>
</html>