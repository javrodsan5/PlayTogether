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
				<strong>Tu historial de torneos</strong>
			</h1>
			<br />

		</div>
		<div class="cardlist">
			<table id="userChampionshipTable" class="table ">
				<thead>
					<tr class="rowtable">
						<th class="guiz-awards-header-title" style="width: 20%;">Torneo</th>
						<th class="guiz-awards-header-title" style="width: 20%;">Ciudad</th>
						<th class="guiz-awards-header-title" style="width: 20%;">Fecha
							Inicio</th>
						<th class="guiz-awards-header-title" style="width: 20%;">Fecha
							Fin</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${championships}" var="championship">
						<spring:url
								value="/sports/{sportId}/championships/{championshipId}"
								var="championshipDetail2Url">
							<spring:param name="sportId"
									value="${championship.sport.id}" />
							<spring:param name="championshipId"
									value="${championship.id}" />
						</spring:url>
						<tr class="rowtable">
							<td><a href="${fn:escapeXml(championshipDetail2Url)}"><b>${championship.name}</b></a></td>
							<td><c:out value="${championship.city}" /></td>
							<td><c:out value="${championship.startDate}" /></td>
							<td><c:out value="${championship.finishDate}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</body>
	</html>
</playtogether:layout>