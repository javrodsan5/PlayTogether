<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="championships">

	<h2>Información de torneos</h2>

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
				<c:out
					value="Inicio: ${championship.startDate} Fin: ${championship.finishDate}" />
			</h2>
			<p class="summary">
				<c:out value="${championship.description}" />
			</p>
		</div>
	</div>

	<div style="float: right; margin-right: 50px">
		<h2>
			Nº equipos inscritos:
			<c:out
				value="${championship.teams.size()} / ${championship.maxTeams}" />
		</h2>

		<c:forEach items="${championship.teams}" var="team">

			<div class="row">
				<div class="col-md-12 col-lg-6">
					<div class="member-card">
						<div class="member-card-details">
							<div class="member-name">
								<a style="color: black;" href="#"> <c:out
										value="${team.name}" />
								</a>
								<spring:url
									value="/sports/{deporte}/championships/{championshipId}/join/{teamId}"
									var="joinChampionshipUrl">
									<spring:param name="deporte" value="${championship.sport.id}" />
									<spring:param name="championshipId" value="${championship.id}" />
									<spring:param name="teamId" value="${team.id}" />
								</spring:url>
								<c:if test="${participarEquipo==true}"><a style="font-size: 17px"href="${fn:escapeXml(joinChampionshipUrl)}">Unirse</a></c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

	</div>

	<c:if test="${crearEquipo==true}">
		<spring:url
			value="/championships/{championshipId}/team/create"
			var="createTeam">
			<spring:param name="championshipId" value="${championship.id}" />
		</spring:url>
		<a href="${fn:escapeXml(createTeam)}" class="btn btn-danger">Crear equipo</a>

	</c:if>

	<c:if test="${participarEquipo==false}">
		<h5>¡Ya estás participando en este torneo en el equipo <i><b><c:out
				value="${miEquipo.name}" /></b></i>!</h5>
	</c:if>

	<spring:url
		value="/sports/{deporte}/championships/{championshipId}/matches"
		var="dateUrl">
		<spring:param name="deporte" value="${championship.sport.id}" />
		<spring:param name="championshipId" value="${championship.id}" />
	</spring:url>
	<a style="margin-left: 20px;" id="listMatch" class="btn btn-warning"
		href="${fn:escapeXml(dateUrl)}">Ver partidos</a>

	<body>
	</html>
</playtogether:layout>