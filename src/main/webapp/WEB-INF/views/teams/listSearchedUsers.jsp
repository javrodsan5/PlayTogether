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
		<h2>Elegir participante</h2>
		
		<form:form action="/championships/team/${teamId}/add_partner" method="POST" modelAttribute="searched_users">
			
			<div>
				Nombre - Usuario: 	
				<select name="selected_participant">
		          	<c:forEach var="item" items="${searched_users}">
		            	<option value="${item.id}">${item.name} - ${item.username}</option>
		          	</c:forEach>
		        </select>
				<input type="submit" value="Enviar">
			</div>
			
			<input type="button" onclick="history.back()" name="Volver atrás" value="Volver atrás">	
			
		</form:form>
			
		<table id="participantsTable" class="table table-striped">
		    <c:choose>
			    <c:when test="${team_participants.isEmpty()}">
				    <p>Debe incluir participantes en el equipo</p>
				</c:when>
				
				<c:otherwise>
				    <thead>
				        <tr>				        
				      		<th style="width: 30%;">Nombre de usuario</th>
				            <th style="width: 30%;">Nombre</th>
				            <th style="width: 30%;">Fecha de nacimiento</th>
				        </tr>
				    </thead>
			        <tbody>
				        <c:forEach items="${team_participants}" var="user">
				            <tr>
				            	<td><c:out value="${user.username}" /></td>
				                <td><c:out value="${user.name}" /></td>		       				            
				                <td><c:out value="${user.birthdate}" /></td>
				            </tr>
				        </c:forEach>
				    </tbody>
			    </c:otherwise>
			</c:choose>
		</table>
	</body>
</playtogether:layout>	