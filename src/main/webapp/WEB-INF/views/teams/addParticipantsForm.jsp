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
			<h1>Incluir participantes en equipo (Máximo ${teamSize} participantes)</h1>
		</div>
		<div style="margin-left: 45px">
			<div class="crearMeeting">
				<form:form id="survey-form" action="/championships/team/${teamId}/add_partner" method="GET">
					<div>
						<div class="col-sm-10">	
		            		<input type="text" class="form-control" name="search" required><br> <br>
		            	</div>	
		            	<c:if test="${limitedTeamSize==true}">
							<p>No queda hueco en el equipo</p>
						</c:if>
						<c:if test="${noUser==true}">
							<p>No se encontró al usuario</p>
						</c:if>
						<c:if test="${notMoreUsers==true}">
							<p>No se encontraron usuarios</p>
						</c:if>
		            							
						<div class="form-group">
							<button class="botonMeeting" type="submit">
								<b>Buscar</b>
							</button>
						</div>
						<br>
						<br>
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
						            	<td><c:out value="${user.username}" /></td>
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