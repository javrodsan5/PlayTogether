<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<c:if test="${areTeamInvitations==true}">
	<playtogether:layout pageName="invitations">
		<body>

			<div class="">
				<h1>
					<strong>Invitaciones</strong>
				</h1>
				<br />
				<p>Aquí se muestran las invitaciones recibidas a futuros torneos
					o en progreso. Las invitaciones a torneos pasados se descartarán
					automáticamente</p>
			</div>

			<div style="margin-left: 2%; margin-right: 2%">
				<table class="table">


					<c:if test="${joined}">
						<p>Ha aceptado la invitación</p>
					</c:if>
					<c:if test="${teamIsFull}">
						<p>La invitación ha sido rechazada y eliminada debido a que el
							equipo ha alcanzado el límite de jugadores</p>
					</c:if>
					<c:if test="${notJoined}">
						<p>Ha rechazado la invitación</p>
					</c:if>
					<c:if test="${isInChampionshipTeam}">
						<p>La invitación ha sido rechazada y eliminada debido a que ya
							participa en un equipo del mismo torneo</p>
					</c:if>
					<c:if test="${noPermission}">
						<p>No puede modificar una invitación que no le pertenece</p>
					</c:if>
					<c:if test="${noInvitation}">
						<p>No existe la invitación</p>
					</c:if>

					<c:if test="${invitations.size() == 0}">
						<p>No hay invitaciones pendientes</p>
					</c:if>
					<c:if test="${invitations.size() > 0}">
					<thead>
						<tr class="rowtable">
							<th class="">Nombre de torneo</th>
							<th class="">Nombre de equipo</th>
							<th class="">Administrador de equipo</th>
							<th class="">Aceptar</th>
							<th class="">Rechazar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${invitations}" var="invitation">
							<spring:url
								value="/sports/{sportId}/championships/{championshipId}"
								var="championshipDetail2Url">
								<spring:param name="sportId"
									value="${invitation.team.championship.sport.id}" />
								<spring:param name="championshipId"
									value="${invitation.team.championship.id}" />
							</spring:url>
							<tr class="rowtable">
								<td><a href="${fn:escapeXml(championshipDetail2Url)}"><b>${invitation.team.championship.name}</b></a></td>
								<td><c:out value="${invitation.team.name}" /></td>
								<td><c:out value="${invitation.team.user.user.username}" /></td>

								<td><spring:url
										value="/invitations/championshipInvitations/{invitationId}/?accepted=true"
										var="acceptInvitationUrl">
										<spring:param name="invitationId" value="${invitation.id}" />
									</spring:url>
									<div class="botoncito"
										style="margin-left: 0%; background-color: green">
										<a style="color: white;"
											href="${fn:escapeXml(acceptInvitationUrl)}"><i
											class="fa fa-check"></i></a>
									</div></td>
								<td><spring:url
										value="/invitations/championshipInvitations/{invitationId}/?accepted=false"
										var="declineInvitationUrl">
										<spring:param name="invitationId" value="${invitation.id}" />
									</spring:url>
									<div class="botoncito"
										style="margin-left: 0%; background-color: red">
										<a style="color: white;"
											href="${fn:escapeXml(declineInvitationUrl)}"><i
											class="fa fa-close"></i></a>
									</div></td>
							</tr>
						</c:forEach>
					</tbody>
					</c:if>

				</table>
			</div>

		</body>
	</playtogether:layout>
</c:if>

<c:if test="${areMeetingInvitations==true}">
	<!-- Para diferencia invitaciones a torneo y a quedadas cuando se implementen las de quedadas -->
	<playtogether:layout pageName="invitations">
		<body>

			<div class="">
				<h1>
					<strong>Invitaciones</strong>
				</h1>
				<br />
				<p>Aquí se muestran las invitaciones recibidas a futuros torneos
					o en progreso. Las invitaciones a torneos pasados se descartarán
					automáticamente</p>
			</div>

			<div style="margin-left: 2%; margin-right: 2%">
				<table class="table">

					<c:if test="${joined}">
						<p>Ha aceptado la invitación</p>
					</c:if>
					<c:if test="${meetingIsFull}">
						<p>La invitación ha sido rechazada y eliminada debido a que la
							quedada ha alcanzado el límite de jugadores</p>
					</c:if>
					<c:if test="${notJoined}">
						<p>Ha rechazado la invitación</p>
					</c:if>
					<c:if test="${isInMeeting}">
						<p>La invitación ha sido rechazada y eliminada debido a que ya
							participa en la quedada</p>
					</c:if>
					<c:if test="${noPermission}">
						<p>No puede modificar una invitación que no le pertenece</p>
					</c:if>
					<c:if test="${noInvitation}">
						<p>No existe la invitación</p>
					</c:if>


					<c:if test="${invitations.size() == 0}">
						<p>No hay invitaciones pendientes</p>
					</c:if>
					<c:if test="${invitations.size() > 0}">
					<thead>
						<tr class="rowtable">
							<th class="">Anfitrión</th>
							<th class="">Más información</th>
							<th class="">Aceptar</th>
							<th class="">Rechazar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${invitations}" var="invitation">
							<spring:url
								value="/sports/{sportId}/meetings/{meetingId}"
								var="meetingDetail2Url">
								<spring:param name="sportId"
									value="${invitation.meeting.sport.id}" />
								<spring:param name="meetingId"
									value="${invitation.meeting.id}" />
							</spring:url>

							<tr class="rowtable">
								<td><c:out
										value="${invitation.meeting.meetingCreator.user.username}" /></td>

								<td>
									<div class="botoncito" style="margin-left: 0%;">
										<a href="${fn:escapeXml(meetingDetail2Url)}">Ver más</a>
									</div>
								</td>
								<td><spring:url
										value="/invitations/meetingInvitations/{invitationId}/?accepted=true"
										var="acceptInvitationUrl">
										<spring:param name="invitationId" value="${invitation.id}" />
									</spring:url>
									<div class="botoncito" style="margin-left: 0%; background-color: green">
										<a style="color:white;" href="${fn:escapeXml(acceptInvitationUrl)}"><i class="fa fa-check"></i></a>
									</div></td>
								<td><spring:url
										value="/invitations/meetingInvitations/{invitationId}/?accepted=false"
										var="declineInvitationUrl">
										<spring:param name="invitationId" value="${invitation.id}" />
									</spring:url>
									<div class="botoncito" style="margin-left: 0%; background-color: red">
										<a style="color:white;" href="${fn:escapeXml(declineInvitationUrl)}"><i class="fa fa-close"></i></a>
									</div></td>
							</tr>
						</c:forEach>
					</tbody>
					</c:if>

				</table>
			</div>

		</body>
	</playtogether:layout>
</c:if>


</html>