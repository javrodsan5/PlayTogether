<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<link href='https://fonts.googleapis.com/css?family=Crete Round'
	rel='stylesheet'>

<center>
<c:if test="${teamView == true}">
	<playtogether:layout pageName="teams">
		<body>
			<div class="thirteen">
				<h1>Invitar participantes al equipo ${team.name}</h1>
				<h2>(Máximo ${teamSize} participantes)</h2>
			</div>
			<div class="crearMeeting" style="display: inline-block">

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
					<p>No puedes enviar invitaciones si no eres el creador del
						equipo</p>
				</c:if>
				<c:if test="${invited==true}">
					<p>Se ha invitado al usuario</p>
				</c:if>

				<form:form id="survey-form"
					action="/invitations/team/${teamId}/send_invitation" method="GET"
					style="padding:0rem 0rem; width:60%">
					<center>
						<div>
							<br>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="search" required
									placeholder="Introduzca el nombre" style="width: 90%"><br>
								<br>
							</div>
							<div class="form-group">
								<button class="btn btn-success" type="submit"
									style="margin-right: 10%">
									<b>Buscar</b>
								</button>
								<button class="btn btn-success"
									onclick="location.href='/sports/${team.championship.sport.id}/championships/${team.championship.id}';"
									type="button">
									<b>Volver a torneo</b>
								</button>
							</div>
							<br>
						</div>
					</center>
				</form:form>
			</div>

			<div style="display: inline-block">
				<table id="meetingTable" width=40% class="table table-striped">
					<c:choose>
						<c:when test="${team_participants.isEmpty()}">
							<p>Aún no hay participantes en el equipo</p>
						</c:when>

						<c:otherwise>
							<thead>
								<tr class="rowtable" style="background-color: #9ec1c1;">
									<th style="width: 10%;"></th>
									<th style="width: 30%;">Nombre de usuario</th>
									<th style="width: 30%;">Nombre</th>
									<th style="width: 30%;">Fecha de nacimiento</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="i" value="${1}" />
								<c:forEach items="${team_participants}" var="user">
									<tr class="rowtable">
										<td><c:out value="${i}" /></td>
										<td><c:out value="${user.user.username}" /></td>
										<td><c:out value="${user.name}" /></td>
										<td><c:out value="${user.birthdate}" /></td>
										<c:set var="i" value="${i+1}" />
									</tr>
								</c:forEach>
							</tbody>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
		</body>
	</playtogether:layout>
</c:if>


<c:if test="${meetingView == true}">
	<playtogether:layout pageName="meetings">
		<body>
			<div class="thirteen">
				<h1>Invitar participantes a quedada</h1>
				<h2>(Máximo ${meetingSize} participantes)</h2>
			</div>
			<div class="crearMeeting" style="display: inline-block">

				<c:if test="${limitedMeetingSize==true}">
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
				<c:if test="${alreadyInMeetingTeam==true}">
					<p>El usuario ya se encuentra en un equipo de este torneo</p>
				</c:if>
				<c:if test="${meetingIsFinished==true}">
					<p>El torneo ha finalizado, no se pueden mandar invitaciones</p>
				</c:if>
				<c:if test="${cantSelfInvite==true}">
					<p>No te puedes mandar una invitación a tí mismo</p>
				</c:if>
				<c:if test="${loggedUserIsNotTheMeetingOwner==true}">
					<p>No puedes enviar invitaciones si no eres el creador del
						equipo</p>
				</c:if>
				<c:if test="${invited==true}">
					<p>Se ha invitado al usuario</p>
				</c:if>

				<form:form id="survey-form"
					action="/invitations/meeting/${meeting.id}/send_invitation"
					method="GET" style="padding:0rem 0rem; width:60%">
					<center>
						<div>
							<br>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="search" required
									placeholder="Introduzca el nombre" style="width: 90%"><br>
								<br>
							</div>
							<div class="form-group">
								<button class="btn btn-success" type="submit"
									style="margin-right: 10%">
									<b>Buscar</b>
								</button>
								<button class="btn btn-success"
									onclick="location.href='/sports/${meeting.sport.id}/meetings';"
									type="button">
									<b>Volver a quedada</b>
								</button>
							</div>

							<br>
						</div>
					</center>

				</form:form>

			</div>

			<div style="display: inline-block">
				<table id="meetingTable" width=30% class="table table-striped">
					<c:choose>
						<c:when test="${meeting_participants.isEmpty()}">
							<p>Aún no hay participantes en la quedada</p>
						</c:when>

						<c:otherwise>
							<thead>
								<tr class="rowtable" style="background-color: #9ec1c1;">
									<th style="width: 10%;"></th>
									<th style="width: 30%;">Nombre de usuario</th>
									<th style="width: 30%;">Nombre</th>
									<th style="width: 30%;">Fecha de nacimiento</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="i" value="${1}" />
								<c:forEach items="${meeting_participants}" var="user">
									<tr class="rowtable">
										<td><c:out value="${i}" /></td>
										<td><c:out value="${user.user.username}" /></td>
										<td><c:out value="${user.name}" /></td>
										<td><c:out value="${user.birthdate}" /></td>
										<c:set var="i" value="${i+1}" />
									</tr>
								</c:forEach>
							</tbody>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
		</body>
	</playtogether:layout>
</c:if>
</center>