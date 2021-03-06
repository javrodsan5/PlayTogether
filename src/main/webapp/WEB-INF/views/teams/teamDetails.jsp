<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<playtogether:layout pageName="users" invitaciones="${invitaciones}">

	<body>
		<div class="cardtitle" style="margin-bottom: 2em;">
			<h1 class="list-meeting-championship-title">
				<strong>Detalles del equipo ${team.name}</strong>
			</h1>
			<br />
		</div>

		<c:if test="${loggedUserIsNotTheMeetingCreator}">
			<div class="alert alert-danger" style="margin: 0% 20% 5% 20%">
				<p style="color: black; font-size: 20px; font-weight: bolder;">No
					tienes permisos para hacer esto.</p>
			</div>
		</c:if>

		<div class="cardlist">
			<c:if test="${userToDeleteIsTeamOwner}">
				<div class="alert alert-danger" style="margin: 0% 20% 5% 20%">
					<p>No se puede eliminar al creador del equipo</p>
				</div>
			</c:if>

			<c:if test="${eliminado}">
				<div class="alert alert-danger" style="margin: 0% 20% 5% 20%">
					<p>Se ha eliminado el jugador correctamente.</p>
				</div>
			</c:if>
			<c:if test="${loggedUserIsNotTheTeamOwner}">
				<div class="alert alert-danger" style="margin: 0% 20% 5% 20%">
					<p>No tienes permisos para hacer esto.</p>
				</div>
			</c:if>
			<div class="cardlist meeting-and-championship-list" >
				<table id="championshipTable" class="table ">

					<h4>
						Miembros del equipo (Nº participantes:
						<c:out
							value="${team.participants.size()} / ${team.teamSize} jugadores" />
						)
					</h4>
					<thead>
						<tr class="rowtable">
							<th class="guiz-awards-header-title" style="width: 20%; style="font-size: 80%;">Nombre</th>
							<th class="guiz-awards-header-title" style="width: 20%;style="font-size: 80%;">Nombre
								de usuario</th>
							<sec:authorize access="hasAuthority('premium')">
								<th class="guiz-awards-header-title" style="width: 10%;">Puntos</th>
							</sec:authorize>
							<th class="guiz-awards-header-title" style="width: 20%;">Edad
								(años)</th>
							<c:if test="${participant.id != userId}">
								<th class="guiz-awards-header-title" style="width: 10%;"></th>
							</c:if>
						</tr>
					</thead>
					<c:forEach items="${team.participants}" var="participant">
						<tr class="rowtable">
							<td><c:out value="${participant.name}" /></td>
							<td><spring:url value="/usuarios/{userId}" var="userDetails">
									<spring:param name="userId" value="${participant.id}" />
								</spring:url> <a href="${fn:escapeXml(userDetails)}"><c:out
										value="${participant.user.username}" /></a></td>
							<sec:authorize access="hasAuthority('premium')">
							<td><c:out value="${participant.puntos}" /></td>
							</sec:authorize>
							<td><c:out value="${participant.edadUsuario()}" /></td>

							<c:if test="${participant.id != userId}">
								<td><button class="botoncito" style="margin-bottom: 3%;"
										onclick="location.href='/chat/0/${participant.user.username}';"
										type="button">
										Chat <i class="fa fa-weixin" aria-hidden="true"></i>
									</button></td>
							</c:if>

							<c:if
								test="${puedeEliminar == true && participant.id!=team.user.id}">
								<td><spring:url
										value="/championships/{championshipId}/teams/{teamId}/{userId}/delete"
										var="deleteUser">
										<spring:param name="championshipId" value="${championship.id}" />
										<spring:param name="teamId" value="${team.id}" />
										<spring:param name="userId" value="${participant.id}" />

									</spring:url>

									<div class="boto">
										<a href="${fn:escapeXml(deleteUser)}">Eliminar jugador</a>
									</div></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
			</div>

			<spring:url
				value="/championships/{championshipId}/teams/{teamId}/leave"
				var="leaveTeam">
				<spring:param name="championshipId" value="${championship.id}" />
				<spring:param name="teamId" value="${team.id}" />
			</spring:url>

			<div class="boto">
				<c:if test="${leave && championship.matches.size() == 0}">
					<center>
						<a class="btn btn-danger" href="${fn:escapeXml(leaveTeam)}">Abandonar
							equipo</a>
				</c:if>
				<c:if test="${leave}">
					<c:if test="${team.participants.size()>1}">

						<button class="btn btn-success"
							onclick="location.href='/chat/${chatId}/messages';" type="button">
							Ir al chat de equipo <i class="fa fa-weixin" aria-hidden="true"></i>
						</button>
						</center>
					</c:if>
				</c:if>
			</div>

			<c:if test="${leave && championship.matches.size() != 0}">
				<div class="alert alert-danger" style="margin: 1% 20% 5% 20%">
					<p style="color: black; font-size: 15px; font-weight: bolder;">El
						torneo ha comenzado, no se puede abandonar el equipo.</p>
				</div>
			</c:if>
			<div class="cardlist meeting-and-championship-list">

				<table id="championshipTable" class="table ">
					<h4>Lista de partidos del equipo</h4>
					<thead>
						<tr class="rowtable">
							<th class="guiz-awards-header-title" style="width: 20%;">Equipo
								1</th>
							<th class="guiz-awards-header-title" style="width: 20%;">Equipo
								2</th>
							<th class="guiz-awards-header-title" style="width: 20%;">Fecha
								del partido</th>
						</tr>
					</thead>
					<c:forEach items="${matches}" var="match">
						<tr class="rowtable">
							<td><c:out value="${match.team1}" /></td>
							<td><c:out value="${match.team2}" /></td>
							<td><fmt:parseDate value="${match.dateTime }"
									pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
								<fmt:formatDate value="${parsedDateTime}"
									pattern="dd-MM-yyyy HH:mm" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<br>
		<div class="form-group">
			<button class="botonTorneos"
				onclick="location.href='/sports/${championship.sport.id}/championships/${championship.id}';"
				type="button">
				<b>Volver al torneo</b>
			</button>
		</div>
	</body>

</playtogether:layout>