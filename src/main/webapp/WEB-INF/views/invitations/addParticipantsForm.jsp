<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<link href='https://fonts.googleapis.com/css?family=Crete Round'
	rel='stylesheet'>

<playtogether:layout pageName="teams">
	<body>
		<div class="thirteen">
			<h1>Invitar participantes al equipo</h1> 
			<h2>(Máximo ${teamSize} participantes)</h2>
		</div>
		<div style="margin-left: 45px">
			<div class="crearMeeting">
			
				<c:if test="${limitedTeamSize==true}">
					<p>No queda hueco en el equipo</p>
				</c:if>
				<c:if test="${noUser==true}">
					<p>No se encontró al usuario</p>
				</c:if>
				<c:if test="${notMoreUsers==true}">
					<p>No se encontraron más usuarios</p>
				</c:if>
				<c:if test="${alreadyInvited==true}">
					<p>Ya invitó al usuario</p>
				</c:if>
				<c:if test="${alreadyInChampionshipTeam==true}">
					<p>El usuario ya se encuentra en un equipo de este torneo</p>
				</c:if>
				<c:if test="${championshipIsFinished==true}">
					<p>El torneo ha finalizado, no se pueden mandar invitaciones</p>
				</c:if>
				<c:if test="${cantSelfInvite==true}">
					<p>No te puedes mandar una invitación a tí mismo</p>
				</c:if>
				<c:if test="${loggedUserIsNotTheTeamOwner==true}">
					<p>No puedes enviar invitaciones si no eres el creador del equipo</p>
				</c:if>
				
				<form:form id="survey-form" action="/invitations/team/${teamId}/send_invitation" method="GET">
					<div>
						<br>
						<div class="col-sm-10">	
		            		<input type="text" class="form-control" name="search" required placeholder="Introduzca el nombre"><br> <br>
		            	</div>		            							
						<div class="form-group">
							<button class="botonMeeting" type="submit">
								<b>Buscar</b>
							</button>
						</div>
						<div class="form-group">
							<button class="botonMeeting" style="font-size: 0.8em; margin-left: 22.72em; " onclick="location.href='/sports/${team.championship.sport.id}/championships/${team.championship.id}';" type="button">
								<b>Volver a torneo</b>
							</button>
						</div>
						<br>
						<br>
					</div><br>
				</form:form>
			</div>
		
			<div class="cardlist">
				<table id="meetingTable" class="table table-striped">
				    <c:choose>
					    <c:when test="${team_participants.isEmpty()}">
						    <p>Aún no hay participantes en el equipo</p>
						</c:when>
						
						<c:otherwise>
						    <thead>
						        <tr class="rowtable" style="background-color: #9ec1c1;" >				        
						        	<th style="width: 10%;"></th>
						      		<th style="width: 30%;">Nombre de usuario</th>
						            <th style="width: 30%;">Nombre</th>
						            <th style="width: 30%;">Fecha de nacimiento</th>
						        </tr>
						    </thead>
					        <tbody>
					        	<c:set var="i" value="${1}"/>
						        <c:forEach items="${team_participants}" var="user">
						            <tr class="rowtable">
						            	<td><c:out value="${i}" /></td>
						            	<td><c:out value="${user.user.username}" /></td>
						                <td><c:out value="${user.name}" /></td>		       				            
						                <td><c:out value="${user.birthdate}" /></td>
						                <c:set var="i" value="${i+1}"/>
						            </tr>
						        </c:forEach>
						    </tbody>
					    </c:otherwise>
					</c:choose>
				</table>
			</div>
		</div>
	</body>
</playtogether:layout>	