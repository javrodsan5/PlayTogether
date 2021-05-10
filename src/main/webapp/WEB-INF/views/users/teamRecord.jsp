<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<playtogether:layout pageName="users" invitaciones="${invitaciones}">

	<body>
		<div class="cardtitle">

			<h1>
				<strong>Equipos en los que participas</strong>
			</h1>
			<br />

		</div>
		<div class="cardlist" style="margin-left:18%; margin-right: 18%; ">
			<c:if test="${noRecords}">
				<div class="alert alert-danger" style="margin: 0% 20% 1% 20%">
					<p>No participa en ningún equipo.</p>
				</div>
			</c:if>
			<c:if test="${noRecords == null}">
				<table id="userChampionshipTable" class="table mis-equipos">
					<thead>
						<tr class="rowtable">
							<th class="guiz-awards-header-title" style="width: 20%;">Equipo</th>
							<th class="guiz-awards-header-title" style="width: 20%;">Torneo</th>
							<th class="guiz-awards-header-title" style="width: 20%;">Ciudad</th>
							<th class="guiz-awards-header-title" style="width: 20%;">Anfitrión</th>
							<th class="guiz-awards-header-title" style="width: 20%;">Ver más</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${teams}" var="team">
							<spring:url
								value="/championships/{championshipId}/teams/{teamId}"
								var="teamDetail2Url">
								<spring:param name="championshipId" value="${team.championship.id}" />
								<spring:param name="teamId" value="${team.id}" />
							</spring:url>
							<tr class="rowtable">
								<td><c:out value="${team.name}" /></td>
								<td><c:out value="${team.championship.name}" /></td>
								<td><c:out value="${team.championship.city}" /></td>
								<td><c:out value="${team.championship.user}" /></td>

								<td>
									<div class="botoncito" style="margin-left: 0;">
										<a href="${fn:escapeXml(teamDetail2Url)}">Ver más</a>
									</div>
								</td>

							</tr>
						</c:forEach>
					</tbody>

				</table>
			</c:if>
		</div>
		<br>
				<div class="form-group">
						<button class="botonMeeting" onclick="location.href='/myprofile';" type="button">
							<b>Volver a perfil</b>
						</button>
					</div>
	</body>
	</html>
</playtogether:layout>