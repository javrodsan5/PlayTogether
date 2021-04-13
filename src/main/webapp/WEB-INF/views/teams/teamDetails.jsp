<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="users">

	<body>
		<div class="cardtitle">
			<h1>
				<strong>Detalles del equipo ${team.name}</strong>
			</h1>
			<br/>
		</div>
		<div class="cardlist">
			<table id="championshipTable" class="table ">

				<h2>
					Lista de componentes del equipo (
					<c:out value="${team.teamSize}" />
					)
				</h2>
				<thead>
					<tr class="rowtable">
						<th class="guiz-awards-header-title" style="width: 20%;">Nombre</th>
						<th class="guiz-awards-header-title" style="width: 20%;">Nombre
							de usuario</th>
						<th class="guiz-awards-header-title" style="width: 20%;">Detalles del jugador</th>
					</tr>
				</thead>
				<c:forEach items="${team.participants}" var="participant">
					<tr class="rowtable">
						<td><c:out value="${participant.name}" /></td>
						<td><c:out value="${participant.user.username}" /></td>
						<td><spring:url value="/usuarios/{userId}" var="userDetails">
								<spring:param name="userId" value="${participant.id}" />
							</spring:url>
							<div class="boto">
								<a href="${fn:escapeXml(userDetails)}">Ver detalles</a>
							</div></td>
					</tr>
				</c:forEach>
			</table>
			<table id="championshipTable" class="table ">
				<h2>Lista de partidos del equipo</h2>
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
						<td><c:out value="${match.dateTime}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>

</playtogether:layout>