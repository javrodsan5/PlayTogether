<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="invitations">
	<body>
	
		<div class="">
			<h1><strong>Invitaciones</strong></h1>
			<br/>
			<p>Aquí se muestran las invitaciones recibidas a futuros torneos o en progreso. Las invitaciones a torneos pasados se descartarán automáticamente</p>
		</div>
		
		<div class="">
			<table id="" class="table ">
				<c:if test="${areTeamInvitations==true}">  			<!-- Para diferencia invitaciones a torneo y a quedadas cuando se implementen las de quedadas -->
					
					<c:if test="${joined}"><p>Ha aceptado la invitación</p></c:if>
					<c:if test="${teamIsFull}"><p>La invitación ha sido rechazada y eliminada debido a que el equipo ha alcanzado el límite de jugadores</p></c:if>
					<c:if test="${notJoined}"><p>Ha rechazado la invitación</p></c:if>
					<c:if test="${isInChampionshipTeam}"><p>La invitación ha sido rechazada y eliminada debido a que ya participa en un equipo del mismo torneo</p></c:if>
					<c:if test="${noPermission}"><p>No puede modificar una invitación que no le pertenece </p></c:if>
					<c:if test="${noInvitation}"><p>No existe la invitación </p></c:if>
					
					<thead>
						<tr class="rowtable">
							<th class="" >Nombre de torneo</th>
							<th class="" >Nombre de equipo</th>
							<th class="" >Ciudad</th>
							<th class="" >Fecha inicio</th>
							<th class="" >Fecha finalización</th>
							<th class="" >Más información</th>
							<th class="" >Aceptar</th>	
							<th class="" >Rechazar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${invitations}" var="invitation">
							<tr class="rowtable">
								<td><c:out value="${invitation.team.championship.name}" /></td>
								<td><c:out value="${invitation.team.name}" /></td>
								<td><c:out value="${invitation.team.championship.city}" /></td>
								<td><c:out value="${invitation.team.championship.startDate}" /></td>
								<td><c:out value="${invitation.team.championship.finishDate}" /></td>
								
								<td>
									<spring:url value="/sports/{sportId}/championships/{championshipId}" var="championshipDetail2Url">
										<spring:param name="sportId" value="${invitation.team.championship.sport.id}" />
										<spring:param name="championshipId" value="${invitation.team.championship.id}" />
									</spring:url>
									<div class="botoncito">
										<a href="${fn:escapeXml(championshipDetail2Url)}">Ver más</a>
									</div>
								</td>
								<td>
									<spring:url	value="/invitations/{invitationId}/?accepted=true"	var="acceptInvitationUrl">
										<spring:param name="invitationId" value="${invitation.id}" />
									</spring:url>
									<div class="botoncito">
										<a class="" href="${fn:escapeXml(acceptInvitationUrl)}">Aceptar</a>
									</div>
								</td>
								<td>
									<spring:url	value="/invitations/{invitationId}/?accepted=false"	var="declineInvitationUrl">
										<spring:param name="invitationId" value="${invitation.id}" />
									</spring:url>
									<div class="botoncito">
										<a class="" href="${fn:escapeXml(declineInvitationUrl)}">Rechazar</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</c:if>		
			</table>
		</div>
			
	</body>
</playtogether:layout>

</html>