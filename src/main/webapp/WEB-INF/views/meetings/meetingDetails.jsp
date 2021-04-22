<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<playtogether:layout pageName="meetings" invitaciones="${invitaciones}">

	<body>
		<c:if test="${existe==true}">
			<div class="alert alert-primary" style="margin: 1% 35% 1% 35%">
				<p>¡Ya estás participando en esta quedada!</p>
			</div>
		</c:if>
		<c:if test="${eliminado==true}">
			<div class="alert alert-primary" style="margin: 1% 35% 1% 35%">
				<p>Has eliminado a este jugador correctamente.</p>
			</div>
		</c:if>
		<c:if test="${estaLlena==true && existe==false}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>La quedada a la que intenta unirse está completa.</p>
			</div>
		</c:if>
		<c:if test="${userToDeleteIsMeetingCreator}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>No se puede eliminar al creador de la quedada.</p>
			</div>
		</c:if>

		<c:if test="${loggedUserIsNotTheMeetingCreator}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p style="color: black; font-size: 20px; font-weight: bolder;">No
					tienes permisos para hacer esto.</p>
			</div>
		</c:if>

		<c:if test="${loggedUserIsNotTheChampionshipTeamOwner}">
			<div class="alert alert-primary" style="margin: 1% 20% 1% 20%">
				<p>Ha aceptado la invitación</p>
			</div>
		</c:if>
		<div class="Card1Meeting">
			<div class="photo"
				style="background-image: url(/images/sportsImages/${sport.id}.jpg);"></div>
			<div class="description">
				<div class="line">
					<h1 class="product_name">
						<c:out value="${meeting.address}" />
					</h1>
					<h1 class="product_price">
						<c:out value="${meeting.city}" />
					</h1>

				</div>
				<h2>
					<fmt:parseDate value="${meeting.date }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
         			 <fmt:formatDate value = "${parsedDateTime}" pattern = "dd-MM-yyyy HH:mm"  />
				</h2>
				<p class="summary">
					<c:out value="${meeting.description}" />
				</p>
			</div>
		</div>
		<div style="float: right; margin-right: 5%; width: 35%">
			<h2>
				Nº participantes:
				<c:out value="${meeting.participants.size()}" />
				/
				<c:out value="${meeting.numberOfPlayers}" />
			</h2>
			<div class="drop">

				<div class="drop__container" id="drop-items">
					<div class="scroll_vertical" id="style_scroll">
						<c:forEach items="${meeting.participants}" var="participant">
							<center>
								<div class="drop__card">
									<div class="drop__data">
										<div>
											<h2 class="drop__name">
												<spring:url value="/usuarios/{userId}" var="userdetails">
													<spring:param name="userId" value="${participant.id}" />
												</spring:url>
												<a style="margin-left: 60px;"
													href="${fn:escapeXml(userdetails)}"><span
													class="glyphicon glyphicon-user" aria-hidden="true">${participant.name}</span></a>
												<c:if test="${puedeEliminar == true}">
													<c:if test="${participant.id!=meeting.meetingCreator.id}">
														<spring:url
															value="/sports/{sportId}/meetings/{meetingId}/{userId}/delete"
															var="deleteUserMeeting">
															<spring:param name="sportId" value="${meeting.sport.id}" />
															<spring:param name="meetingId" value="${meeting.id}" />
															<spring:param name="userId" value="${participant.id}" />
														</spring:url>
														<a style="margin-left: 60px"
															href="${fn:escapeXml(deleteUserMeeting)}"> <i
															class="fa fa-trash" style="color: red"></i></a>
													</c:if>
												</c:if>
											</h2>
										</div>
									</div>
								</div>
							</center>
						</c:forEach>
					</div>
				</div>

				<spring:url value="/sports/{sportId}/meetings/{meetingId}/leave"
					var="leaveMeeting">
					<spring:param name="sportId" value="${meeting.sport.id}" />
					<spring:param name="meetingId" value="${meeting.id}" />
				</spring:url>

				<c:if test="${leave}">
					<a href="${fn:escapeXml(leaveMeeting)}" style="color: white">
						<button class="btn btn-danger" style="margin-top: 5%">
							<b>Abandonar quedada</b>
						</button>

					</a>
				</c:if>

			</div>
		</div>

		<c:if test="${esCreador==true}">
			<td><spring:url
					value="/sports/{sportId}/meetings/{meetingId}/edit"
					var="meetingUpdateUrl">
					<spring:param name="meetingId" value="${meeting.id}" />
					<spring:param name="sportId" value="${sport.id}" />
				</spring:url> <a class="btn btn-primary" href="${fn:escapeXml(meetingUpdateUrl)}">Editar</a>
		</c:if>

		<c:if
			test="${meeting.meetingCreator == logged_user && !estaLlena && puedeEliminar && meeting.participants.contains(meeting.meetingCreator)}">
			<td><spring:url value="/invitations/meeting/{meetingId}"
					var="searchPeopleUrl">
					<spring:param name="meetingId" value="${meeting.id}" />
				</spring:url> <a class="btn btn-primary" href="${fn:escapeXml(searchPeopleUrl)}">Invitar</a>
		</c:if>

		<div class="form-group">
			<c:if test="${existe==false && estaLlena==false}">
				<spring:url value="/meetings/${meeting.id}/join" var="joinUrl">
				</spring:url>
				<a href="${fn:escapeXml(joinUrl)}" class="btn btn-danger">Participar</a>
			</c:if>
			<button class="botonMeeting"
				style="font-size: 0.8em; margin: 0% 3% 0% 3%;"
				onclick="location.href='/sports/${meeting.sport.id}/meetings';"
				type="button">
				<b>Volver a listado</b>
			</button>
			<c:if test="${leave}">
				<button class="botonMeeting"
					style="font-size: 0.8em; margin: 0% 3% 0% 3%;"
					onclick="location.href='/chat/${chatId}/messages';"
					type="button">
					<b>Ir al chat de la quedada  </b><i class="fa fa-weixin" style="font-size: 150%;" aria-hidden="true"></i>
				</button>
			</c:if>

		</div>

		<br>

		<br>
	</body>

</playtogether:layout>
