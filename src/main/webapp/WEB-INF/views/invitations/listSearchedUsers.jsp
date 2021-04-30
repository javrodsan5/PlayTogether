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
		<playtogether:layout pageName="teams" invitaciones="${invitaciones}">
			<body>

				<div class="thirteen invitation-search-title">
					<center>
						<h1>Elegir participante</h1>
						<h4>
							Nº participantes en el equipo:
							<c:out value="${team_participants.size()}" />
							/
							<c:out value="${team.teamSize}" />
						</h4>
					</center>
				</div>
				<div class="crearMeeting send-invitation-form">
					<form:form id="survey-form" class="search-user-form"
						action="/invitations/team/${teamId}/send_invitation" method="POST"
						modelAttribute="searched_users"
						style="padding:0rem 0rem; width:60%">
						<center>
							<div>
								<div class="col-sm-10">
									<h5 style="color: white;">Nombre - Usuario:</h5>
								</div>
								<div class="select invitation-select">
									<select name="selected_participant">
										<c:forEach var="item" items="${searched_users}">
											<option value="${item.id}">${item.name}-
												${item.user.username}</option>
										</c:forEach>
									</select>
								</div>
	
								<div class="form-group">
									<div class="search-person-button">
										<button class="btn btn-success" type="submit"
											style="margin-right: 10%"">
											<b>Invitar</b>
										</button>
									</div>
									<div class="return-search-person-button">
										<button class="btn btn-success"
											onclick="location.href='/invitations/team/${teamId}';"
											type="button">
											<b>Volver</b>
										</button>
									</div>
								</div>
							</div>
						</center>
					</form:form>
				</div>
				
				<div class="invitation-tables-complete-block">
					<div class="table-title-block">
						<div class="cardtitle invitation-table-title">
							<h3 class="list-meeting-championship-title">
								Participantes
							</h3>
						</div>
						
						<div class="invitation-table scroll_vertical" id="style_scroll">
							<table id="meetingTable" class="table">
								<c:choose>
									<c:when test="${team_participants.isEmpty()}">
										<div class="alert alert-primary" style="margin: 1% 20% 1% 20%">
											<p>Debe incluir participantes en el equipo</p>
										</div>
									</c:when>
		
									<c:otherwise>
										<thead>
											<tr class="rowtable">
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
					</div>
					
					<div class="table-title-block">
						<div class="cardtitle invitation-table-title">
							<h3 class="list-meeting-championship-title">
								Invitaciones
							</h3>
						</div>
						
						<div class="invitation-table scroll_vertical" id="style_scroll">
						<table id="meetingTable" class="table">
							<thead>
								<tr class="rowtable">
									<th style="width: 10%;"></th>
									<th style="width: 30%;">Nombre de usuario</th>
									<th style="width: 30%;">Nombre</th>
									<th style="width: 30%;">Fecha de nacimiento</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="i" value="${1}" />
								<c:forEach items="${championshipInvitations}" var="invitation">
									<tr class="rowtable">
										<td><c:out value="${i}" /></td>
										<td><c:out value="${invitation.receiver.user.username}" /></td>
										<td><c:out value="${invitation.receiver.name}" /></td>
										<td><c:out value="${invitation.receiver.birthdate}" /></td>
										<c:set var="i" value="${i+1}" />
									</tr>
								</c:forEach>
							</tbody>
	
						</table>
					</div>  
					
				</div>
			</body>
		</playtogether:layout>
	</c:if>

	<c:if test="${meetingView == true}">
		<playtogether:layout pageName="meetings" invitaciones="${invitaciones}">
			<body>
				<div class="thirteen invitation-search-title">
					<h1>Elegir participante</h1>
				</div>
				<div class="crearMeeting send-invitation-form">
					<form:form id="survey-form" class="search-user-form"
						action="/invitations/meeting/${meetingId}/send_invitation"
						method="POST" modelAttribute="searched_users"
						style="padding:0rem 0rem; width:60%">
						<center>
							<div class="col-sm-10">
								<h5 style="color: white;">Nombre - Usuario:</h5>
								<div class="select">
									<select name="selected_participant">
										<c:forEach var="item" items="${searched_users}">
											<option value="${item.id}">${item.name}-
												${item.user.username}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="search-person-button">
									<button class="btn btn-success" type="submit"
										style="margin-right: 10%">
										<b>Invitar</b>
									</button>
								</div>
								<div class="search-person-button">
									<button class="btn btn-success"
										onclick="location.href='/invitations/meeting/${meetingId}';"
										type="button">
										<b>Volver</b>
									</button>
								</div>
							</div>
						</center>
					</form:form>

				</div>
				<div class="invitation-tables-complete-block">
					<div class="table-title-block">
						<div class="cardtitle invitation-table-title">
							<h3 class="list-meeting-championship-title">
								Participantes
							</h3>
						</div>
	
						<div class="invitation-table scroll_vertical" id="style_scroll">
							<table id="meetingTable" class="table">
								<c:choose>
									<c:when test="${meeting_participants.isEmpty()}">
										<div class="alert alert-primary" style="margin: 1% 20% 1% 20%">
											<p>Debe incluir participantes en el equipo</p>
										</div>
									</c:when>
		
									<c:otherwise>
										<thead>
											<tr class="rowtable">
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
					</div>
					
					<div class="table-title-block">
						<div class="cardtitle invitation-table-title">
							<h3 class="list-meeting-championship-title">
								Invitaciones
							</h3>
						</div>
						
						<div class="invitation-table scroll_vertical" id="style_scroll">
							<table id="meetingTable" class="table">
								<thead>
									<tr class="rowtable">
										<th style="width: 10%;"></th>
										<th style="width: 30%;">Nombre de usuario</th>
										<th style="width: 30%;">Nombre</th>
										<th style="width: 30%;">Fecha de nacimiento</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="i" value="${1}" />
									<c:forEach items="${meetingInvitations}" var="invitation">
										<tr class="rowtable">
											<td><c:out value="${i}" /></td>
											<td><c:out value="${invitation.receiver.user.username}" /></td>
											<td><c:out value="${invitation.receiver.name}" /></td>
											<td><c:out value="${invitation.receiver.birthdate}" /></td>
											<c:set var="i" value="${i+1}" />
										</tr>
									</c:forEach>
								</tbody>
		
							</table>
						</div>
					</div> 
					
				</div>
			</body>
		</playtogether:layout>
	</c:if>
</center>

