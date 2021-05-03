<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="invitations">
	<body>

		<c:if test="${joined}">
			<div class="alert alert-primary" style="margin: 1% 20% 1% 20%">
				<p>Ha aceptado la invitación</p>
			</div>
		</c:if>
		<c:if test="${meetingIsFull}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>La invitación ha sido rechazada y eliminada debido a que la
					quedada ha alcanzado el límite de jugadores</p>
			</div>
		</c:if>
		<c:if test="${teamIsFull}">
			<div class="alert alert-danger" style="margin: 0% 20% 1% 20%">
				<p>La invitación ha sido rechazada y eliminada debido a que el
					equipo ha alcanzado el límite de jugadores</p>
			</div>
		</c:if>
		<c:if test="${notJoined}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>Ha rechazado la invitación</p>
			</div>
		</c:if>
		<c:if test="${isInMeeting}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>La invitación ha sido rechazada y eliminada debido a que ya
					participa en la quedada</p>
			</div>
		</c:if>
		<c:if test="${isInChampionshipTeam}">
			<div class="alert alert-danger" style="margin: 0% 20% 1% 20%">
				<p>La invitación ha sido rechazada y eliminada debido a que ya
					participa en un equipo del mismo torneo</p>
			</div>
		</c:if>
		<c:if test="${noPermission}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>No puede modificar una invitación que no le pertenece</p>
			</div>
		</c:if>
		<c:if test="${noInvitation}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>No existe la invitación</p>
			</div>
		</c:if>

		<div class="">
			<br>
			<h1>
				<strong>Invitaciones de quedadas</strong>
			</h1>
			<br />
			<p>Aquí se muestran las invitaciones recibidas a futuras
				quedadas. Las invitaciones a quedadas pasadas se descartarán
				automáticamente</p>
		</div>

		<c:if test="${meetingInvitations.size()==0}">
			<h3 class="alert alert-warning"
				style="margin: 0px 35% 0px 35%; text-align: center">No tienes
				invitaciones aún.</h3>
		</c:if>


		<c:if test="${meetingInvitations.size()>0}">
			<div style="margin-left: 2%; margin-right: 2%">
				<table class="table">

					<thead>
						<tr class="rowtable">
							<th class="">Anfitrión</th>
							<th class="">Más información</th>
							<th class="">Aceptar</th>
							<th class="">Rechazar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${meetingInvitations}" var="invitation">
							<spring:url value="/sports/{sportId}/meetings/{meetingId}"
								var="meetingDetail2Url">
								<spring:param name="sportId"
									value="${invitation.meeting.sport.id}" />
								<spring:param name="meetingId" value="${invitation.meeting.id}" />
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
									<div class="botoncito"
										style="margin-left: 0%; background-color: green">
										<a style="color: white;"
											href="${fn:escapeXml(acceptInvitationUrl)}"><i
											class="fa fa-check"></i></a>
									</div></td>
								<td><spring:url
										value="/invitations/meetingInvitations/{invitationId}/?accepted=false"
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

				</table>
			</div>
		</c:if>
		<br>
		<br>
		<br>
		<br>

		<div class="">
			<h1>
				<strong>Invitaciones de torneos</strong>
			</h1>
			<br />
			<p>Aquí se muestran las invitaciones recibidas a futuros torneos
				o en progreso. Las invitaciones a torneos pasados se descartarán
				automáticamente</p>
		</div>

		<c:if test="${championshipInvitations.size()==0}">
			<h3 class="alert alert-warning"
				style="margin: 0px 35% 0px 35%; text-align: center">No tienes
				invitaciones aún.</h3>
		</c:if>

		<c:if test="${championshipInvitations.size()>0}">
			<div style="margin-left: 2%; margin-right: 2%">
				<table class="table">

					<thead>
						<tr class="rowtable">
							<th class="">Nombre de torneo</th>
							<th class="">Nombre de equipo</th>
							<th class="">Dirección</th>
							<th class="">Administrador de equipo</th>
							<th class="">Aceptar</th>
							<th class="">Rechazar</th>
						</tr>

					</thead>
					<tbody>
						<c:forEach items="${championshipInvitations}" var="invitation">
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
								<td><c:out value="${invitation.team.championship.address}" /></td>
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

				</table>
			</div>
		</c:if>
		<br>
			<div class="form-group">
						<button class="botonMeeting" onclick="location.href='/myprofile';" type="button">
							<b>Volver a perfil</b>
						</button>
					</div>

	</body>
</playtogether:layout>



</html>