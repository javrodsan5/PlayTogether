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
	<body>
			<div class="cardtitle">
				<h1>
					<strong>Torneos de ${nombreDeporte}</strong>
				</h1>
			</div>
		<div class="cardlist meeting-and-championship-list">
      <div class="scroll_vertical" id="style_scroll" style="height: 500px;">
			<table id="championshipTable" class="table ">
				<thead>
					<tr class="rowtable">
						<th class="guiz-awards-header-title" style="width: 10%;">Ciudad</th>
						<th class="guiz-awards-header-title" style="width: 20%;">Descripción</th>
						<th class="guiz-awards-header-title" style="width: 20%;">Dirección</th>
						<th class="guiz-awards-header-title" style="width: 10%;">Fecha
							Inicio</th>
						<th class="guiz-awards-header-title" style="width: 10%;">Fecha
							Fin</th>
						<th class="guiz-awards-header-title" style="width: 5%;">Equipos
							inscritos</th>
						<th class="guiz-awards-header-title" style="width: 10%;">Creador</th>
						<th class="guiz-awards-header-title" style="width: 20 !important%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${championships}" var="championship">
						<tr class="rowtable">
							<th class="guiz-awards-header-title" style="width: 15%;">Ciudad</th>
							<th class="guiz-awards-header-title" style="width: 25%;">Dirección</th>
							<th class="guiz-awards-header-title" style="width: 15%;">Fecha
								Inicio</th>
							<th class="guiz-awards-header-title" style="width: 15%;">Fecha
								Fin</th>
							<th class="guiz-awards-header-title" style="width: 5%;">Equipos
								inscritos</th>
							<th class="guiz-awards-header-title" style="width: 10%;">Creador</th>
							<th class="guiz-awards-header-title" style="width: 5%;">Participación</th>
							<th class="guiz-awards-header-title" style="width: 20% !important"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${championships}" var="championship">
							<tr class="rowtable">
								<td><c:out value="${championship.city}" /></td>
								<td><c:out value="${championship.address}" /></td>
	
								<td><fmt:parseDate value="${championship.startDate }"
										pattern="yyyy-MM-dd" var="parsedDateStart" type="both" /> <fmt:formatDate
										value="${parsedDateStart}" pattern="dd-MM-yyyy" /></td>
								<td><fmt:parseDate value="${championship.finishDate }"
										pattern="yyyy-MM-dd" var="parsedDateEnd" type="both" /> <fmt:formatDate
										value="${parsedDateEnd}" pattern="dd-MM-yyyy" /></td>
								<td><center>
										<c:out
											value="${championship.teams.size()} / ${championship.maxTeams}" />
									</center></td>
								<td><spring:url value="/usuarios/{userId}" var="userdetails">
										<spring:param name="userId" value="${championship.user.id}" />
									</spring:url> <a href="${fn:escapeXml(userdetails)}"> <c:out
											value="${championship.user.user.username}" /></a></td>							
								<td>
									<c:set var = "isInTeam" value = "${false}"/>
									<c:forEach items="${championship.teams}" var="equiposLoggeado">			
										<c:if test="${!isInTeam && equiposLoggeado != null && equiposLoggeado.participants != null}">
											<c:set var = "isInTeam" value = "${equiposLoggeado.participants.contains(usuario_logueado)}"/>																			</c:if>						
									</c:forEach>
									<c:if test="${isInTeam}">
										<center><i class="fa fa-check-circle" style="color: green;"></center></i>
									</c:if>
									<c:if test="${!isInTeam}">
										<center><i class="fa fa-times-circle" style="color: red; text-align: center !important;"></center></i>
									</c:if>
								</td>
								<td><spring:url
										value="/sports/{deporte}/championships/{championshipId}"
										var="championship2Url">
										<spring:param name="championshipId" value="${championship.id}" />
										<spring:param name="deporte" value="${deporte}" />
	
									</spring:url>
									<div class="botoncito">
										<a class="" href="${fn:escapeXml(championship2Url)}">Ver
											más</a>
									</div></td>
							</tr>
						</c:forEach>
					</tbody>
	
				</table>
			</div>
		</div>
		<div class="cardbutton">
			<spring:url value="/sports/{deporte}/championships/add" var="dateUrl">
				<spring:param name="deporte" value="${deporte}" />

			</spring:url>
			<div class="botoncito-crear-meeting">
				<a id="createChampionship" href="${fn:escapeXml(dateUrl)}">Crear
					torneo</a>
			</div>
		</div>
	</body>
	</html>
</playtogether:layout>