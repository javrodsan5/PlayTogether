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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<playtogether:layout pageName="meetings" invitaciones="${invitaciones}">

	<body>
		<center>
		<c:if test="${existe==true}">
			<div class="alert alert-primary"
				style="margin: 1% 25% 1% 25%; width: 50%;">
				<p>¡Ya estás participando en esta quedada!</p>
			</div>
		</c:if>
		<c:if test="${eliminado==true}">
			<div class="alert alert-primary"
				style="margin: 1% 25% 1% 25%; width: fit-content;">
				<p>Has eliminado a este jugador correctamente.</p>
			</div>
		</c:if>
		<c:if test="${estaLlena==true && existe==false}">
			<div class="alert alert-danger"
				style="margin: 1% 20% 1% 20%; width: fit-content;">
				<p>La quedada a la que intenta unirse está completa.</p>
			</div>
		</c:if>
		<c:if test="${userToDeleteIsMeetingCreator}">
			<div class="alert alert-danger"
				style="margin: 1% 20% 1% 20%; width: fit-content;">
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
		</center>
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
					<b>Creador:</b>
					<c:out value="${meeting.meetingCreator}" />
				</h2>
				<h2>
					<b>Fecha y hora: </b>
					<fmt:parseDate value="${meeting.date }"
						pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
					<fmt:formatDate value="${parsedDateTime}"
						pattern="dd-MM-yyyy HH:mm" />
				</h2>
				<h2>
					<b>Categoría:</b>
					<c:out value="${meeting.category.name}" />
				</h2>
				<p class="summary">
					<c:out value="${meeting.description}" />
				</p>
			</div>
		</div>
		<div class="equiposTorneo">
			<h3 class="espacioIzqMovil">
				Nº participantes:
				<c:out
					value="${meeting.participants.size()}/${meeting.numberOfPlayers}" />
			</h3>
			<c:if test="${hayParticipantes==true}">
				<div class="drop">

					<div class="drop__container" id="drop-items">
						<div class="scroll_vertical" id="style_scroll">
							<c:forEach items="${meeting.participants}" var="participant">
								<div class="drop__card">
									<div class="drop__data">
										<div>
											<h2 class="drop__name" style="display: flex">
												<spring:url value="/usuarios/{userId}" var="userdetails">
													<spring:param name="userId" value="${participant.id}" />
												</spring:url>
												<div style="display: flex">
													<a href="${fn:escapeXml(userdetails)}"><span
														class="glyphicon glyphicon-user" aria-hidden="true">${participant.name}
													</span></a> <a
														style="margin-left: 20px; font-size: 25px; align-self: center">
														${participant.edadUsuario()} años</a>
												</div>


												<c:if test="${puedeEliminar == true}">
													<c:if test="${participant.id!=meeting.meetingCreator.id}">
														<br>
														<spring:url
															value="/sports/{sportId}/meetings/{meetingId}/{userId}/delete"
															var="deleteUserMeeting">
															<spring:param name="sportId" value="${meeting.sport.id}" />
															<spring:param name="meetingId" value="${meeting.id}" />
															<spring:param name="userId" value="${participant.id}" />
														</spring:url>
														<a style="margin: 10px;"
															href="${fn:escapeXml(deleteUserMeeting)}"> <i
															class="fa fa-trash" style="color: red"></i></a>
													</c:if>
												</c:if>
											</h2>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>

					<c:if test="${leave}">
						<spring:url value="/sports/{sportId}/meetings/{meetingId}/leave"
							var="leaveMeeting">
							<spring:param name="sportId" value="${meeting.sport.id}" />
							<spring:param name="meetingId" value="${meeting.id}" />
						</spring:url>
						<center>
						<a href="${fn:escapeXml(leaveMeeting)}">
							<button class="btn btn-danger"
								style="margin-top: 5%; margin-left: 1em;">
								<b>Abandonar quedada</b>
							</button>
						</a>
						</center>
					</c:if>

				</div>
			</c:if>

		</div>
		<div class="editarInvitar"
			style="margin-left: auto; margin-right: auto; width: 9.4em; margin-top: 5em; margin-bottom: 0;">

			<c:if test="${esCreador==true}">
				<spring:url value="/sports/{sportId}/meetings/{meetingId}/edit"
					var="meetingUpdateUrl">
					<spring:param name="meetingId" value="${meeting.id}" />
					<spring:param name="sportId" value="${sport.id}" />
				</spring:url>

				<a class="btn btn-primary" href="${fn:escapeXml(meetingUpdateUrl)}"><b>Editar</b></a>
			</c:if>
			<c:if
				test="${meeting.meetingCreator == logged_user && !estaLlena && puedeEliminar && meeting.participants.contains(meeting.meetingCreator)}">
				<td><spring:url value="/invitations/meeting/{meetingId}"
						var="searchPeopleUrl">
						<spring:param name="meetingId" value="${meeting.id}" />
					</spring:url> <a class="btn btn-primary" href="${fn:escapeXml(searchPeopleUrl)}"><b>Invitar</b></a>
			</c:if>
		</div>
		<div class="botonesMeeting">
			<c:if test="${existe==false && estaLlena==false}">
				<spring:url value="/meetings/${meeting.id}/join" var="joinUrl">
				</spring:url>
				<a href="${fn:escapeXml(joinUrl)}"
					class="btn btn-danger botonMeeting"><b>Participar</b></a>
			</c:if>
			<button class="botonMeeting"
				onclick="location.href='/sports/${meeting.sport.id}/meetings?category=Todas';"
				type="button">
				<b>Volver a listado</b>
			</button>
			<c:if test="${leave}">
				<c:if test="${meeting.participants.size()>1}">
					<button class="botonMeeting"
						onclick="location.href='/chat/${chatId}/messages';" type="button">
						<b>Ir al chat de la quedada </b><i class="fa fa-weixin"
							style="font-size: 150%;" aria-hidden="true"></i>
					</button>
				</c:if>
			</c:if>

		</div>


		<br>
	</body>

</playtogether:layout>
