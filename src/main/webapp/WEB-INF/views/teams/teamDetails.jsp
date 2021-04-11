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

			<div class="description">
				<div class="line">
					<h1 class="product_name">
						<c:out value="${team.teamSize}" />
					</h1>
					
					<h2>Lista de componentes del equipo</h2>
					
					<c:forEach items="${team.participants}" var="participant">
						<tr class="rowtable">
							<td><c:out value="${participant.user.username}" /></td>
							<td><c:out value="${participant.name}" /></td>
							
							<td><spring:url
									value="/usuarios/{userId}"
									var="userDetails">
									<spring:param name="userId" value="${participant.id}" />
								
								</spring:url>
								<div class="boto">
									<a href="${fn:escapeXml(userDetails)}">Ver detalles jugador</a>
								</div></td>
							
						</tr>
					</c:forEach>
					<h2>Lista de partidos del equipo</h2>
						<c:forEach items="${matches}" var="match">
						<tr class="rowtable">
						<td><c:out value="${match.dateTime}" /></td>
							<td><c:out value="${match.team1}" /></td>
							<td><c:out value="${match.team2}" /></td>
							
							
							
						</tr>
					</c:forEach>


				</div>

			</div>


	</body>

</playtogether:layout>