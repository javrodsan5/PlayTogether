<%@ page session="false" trimDirectiveWhitespaces="true"%>
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
		<h2>Incluir participantes en el equipo (Límite de participantes: ${teamSize})</h2>
		
		<form:form action="/championships/team/${teamId}/add_partner" method="GET">
			<div>	
            	<input type="text" id="search" name="search" required><br> <br>	
            	<c:if test="${limitedTeamSize==true}">
					<p>No queda hueco en el equipo</p>
				</c:if>
				<c:if test="${noUser==true}">
					<p>No se encontró al usuario</p>
				</c:if>
				<c:if test="${notMoreUsers==true}">
					<p>No se encontraron usuarios</p>
				</c:if>
            							
				<input type="submit" value="Enviar">
			</div>
		</form:form>
		
		<table id="participantsTable" class="table table-striped">
		    <c:choose>
			    <c:when test="${team_participants.isEmpty()}">
				    <p>Debe incluir participantes en el equipo</p>
				</c:when>
				
				<c:otherwise>
				    <thead>
				        <tr>				        
				        	<th style="width: 10%;">Número</th>
				      		<th style="width: 30%;">Nombre de usuario</th>
				            <th style="width: 30%;">Nombre</th>
				            <th style="width: 30%;">Fecha de nacimiento</th>
				        </tr>
				    </thead>
			        <tbody>
			        	<c:set var="i" value="${1}"/>
				        <c:forEach items="${team_participants}" var="user">
				            <tr>
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
	</body>
</playtogether:layout>	