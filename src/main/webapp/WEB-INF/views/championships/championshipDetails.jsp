<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<playtogether:layout pageName="championships"
	invitaciones="${invitaciones}">

	<c:if test="${participarEquipo==false}">
		<div class="alert alert-primary" style="margin: 0% 20% 1% 20%">
			<h5>
				¡Ya estás participando en este torneo en el equipo <i><b><c:out
							value="${miEquipo.name}" /></b></i>!
			</h5>
		</div>
	</c:if>

	<div class="Card1Meeting">
		<div class="photo"
			style="background-image: url(/images/sportsImages/${championship.sport.id}.jpg);"></div>
		<div class="description">
			<div class="line">
				<h1 class="product_name">
					<c:out value="${championship.name}" />
				</h1>
				<h1 class="product_price">
					<c:out value="${championship.city}" />
				</h1>

			</div>
			<h2>
				<b>Creador:</b> <c:out value="${championship.user}" /><br>
				<fmt:parseDate value="${championship.startDate }"
					pattern="yyyy-MM-dd" var="parsedDateStart" type="both" />
				<b>Inicio:</b>
				<fmt:formatDate value="${parsedDateStart}" pattern="dd-MM-yyyy" />

				<br>
				<fmt:parseDate value="${championship.finishDate }"
					pattern="yyyy-MM-dd" var="parsedDateEnd" type="both" />
				<b>Fin:</b>
				<fmt:formatDate value="${parsedDateEnd}" pattern="dd-MM-yyyy" />
				<br><br>
				<b>Dirección:</b> <c:out value="${championship.address}" />
		
			</h2>
			<p class="summary">
				<c:out value="${championship.description}" />
			</p>
			
		</div>
	</div>

	<div class="equiposTorneo">
		<h3 class="espacioIzqMovil">
			Nº equipos inscritos:
			<c:out
				value="${numTeams} / ${championship.maxTeams}" />
		</h3>
		<h3 class="espacioIzqMovil">
			<c:out
				value="${championship.sport.numberOfPlayersInTeam} jugadores por equipo" />
		</h3>
		<c:if test="${crearEquipo==true}">
			<spring:url value="/championships/{championshipId}/team/create"
				var="createTeam">
				<spring:param name="championshipId" value="${championship.id}" />
			</spring:url>
			<a href="${fn:escapeXml(createTeam)}"
				class="btn btn-danger rightDesktop" style="margin-top: 1em;" >Crear equipo</a>
		</c:if>
		<c:if test="${hayEquipos==true}">
			<div class="scroll_vertical" id="style_scroll">
				<c:forEach items="${championship.teams}" var="team">

					<center>
						<div class="member-card">
							<div class="member-card-details">
								<div class="member-name">
									<spring:url
										value="/championships/{championshipId}/teams/{teamId}"
										var="teamDetails">
										<spring:param name="championshipId" value="${championship.id}" />
										<spring:param name="teamId" value="${team.id}" />
									</spring:url>
									<a style="color: black;" href="${fn:escapeXml(teamDetails)}">
										<c:out value="${team.name}" />
									</a>
									<spring:url
										value="/sports/{deporte}/championships/{championshipId}/join/{teamId}"
										var="joinChampionshipUrl">
										<spring:param name="deporte" value="${championship.sport.id}" />
										<spring:param name="championshipId" value="${championship.id}" />
										<spring:param name="teamId" value="${team.id}" />
									</spring:url>
									<c:if
										test="${participarEquipo==true && team.participants.size() < team.teamSize }">
										<a style="font-size: 17px"
											href="${fn:escapeXml(joinChampionshipUrl)}">Unirse</a>
									</c:if>
									<spring:url value="/invitations/team/{teamId}"
										var="searchPeopleUrl">
										<spring:param name="teamId" value="${team.id}" />
									</spring:url>
									<c:if
										test="${team.user == logged_user && team.participants.size() < team.teamSize && premium}">
										<a style="font-size: 17px"
											href="${fn:escapeXml(searchPeopleUrl)}">Invitar</a>
									</c:if>
									<h5>${team.participants.size()} / ${team.teamSize} jugadores</h5>
								</div>
							</div>
						</div>
					</center>
				</c:forEach>
			</div>
		</c:if>
	</div>


	<div class="botonesMeeting" style="height: 9em;">
		<spring:url
			value="/sports/{deporte}/championships/{championshipId}/matches"
			var="dateUrl">
			<spring:param name="deporte" value="${championship.sport.id}" />
			<spring:param name="championshipId" value="${championship.id}" />
		</spring:url>
		<a class="botonPartidosTorneo" style="margin-top: 3.5em; margin-left: 40%; margin-right: 40%; min-width: 6em;" href="${fn:escapeXml(dateUrl)}"><b>Partidos</b></a>
	
		<div class="form-group" >
			<button class="botonTorneos" style=" min-width: 6em;  margin-left: 40%; margin-right: 40%;"
				onclick="location.href='/sports/${championship.sport.id}/championships';"
				type="button">
				<b>Volver al listado</b>
			</button>
		</div>
	</div>

</playtogether:layout>