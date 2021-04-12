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
<playtogether:banner></playtogether:banner>
	<body>
		<div class="thirteen">
			<h1>Elegir participante</h1>
		</div>
		<div style="margin-left: 45px">
			<div class="crearMeeting">
				<form:form id="survey-form" action="/invitations/team/${teamId}/send_invitation" method="POST" modelAttribute="searched_users">
					<div>
					<center>
						<div class="col-sm-10">
							<h5 style="color: white;">Nombre - Usuario:</h5> 	
							<select name="selected_participant" style="border-radius:20px; overflow:hidden; margin: 0.5em; background:#F6F6F6; border:1px solid #cccccc;">
					          	<c:forEach var="item" items="${searched_users}">
					            	<option value="${item.id}">${item.name} - ${item.user.username}</option>
					          	</c:forEach>
					        </select>
					    </div>
						<div class="form-group">
							<button class="botonMeeting" type="submit" style="font-size: 0.8em; margin-left: 21.6em; ">
								<b>Invitar</b>
							</button>
						</div>
						<div class="form-group">
							<button class="botonMeeting" style="font-size: 0.8em; margin-left: 21.6em; " onclick="location.href='/invitations/team/${teamId}';" type="button">
								<b>Volver</b>
							</button>
						</div>
						<br>
						<br>
						<br>
					</center>
					</div>	
				</form:form>

			</div>
			<div class="cardlist">
				<table id="meetingTable" class="table table-striped">
				    <c:choose>
					    <c:when test="${team_participants.isEmpty()}">
						    <p>Debe incluir participantes en el equipo</p>
						</c:when>
						
						<c:otherwise>
						    <thead>
						        <tr class="rowtable" style="background-color: #9ec1c1; ">				        
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