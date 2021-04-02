<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">
<body>
<div class="cardtitle">
<h1><strong>Partidos del torneo</strong></h1>
<br/>
</div>
<div class="cardlist">
<c:if test="${noUser}">
	<p>No se encontró al usuario deseado.</p>
</c:if>
<c:if test="${noTeam}">
	<p>¡No perteneces a este equipo!</p>
</c:if>
<table id="matchTable" class="table">
        <thead>
            <tr class="rowtable">
                <th style="width: 20%;">Fecha y hora</th>
                <th style="width: 20%;">Equipo 1</th>
                <th style="width: 10%;">Puntos equipo 1 (Equipo 1)</th>
                <th style="width: 10%;">Puntos equipo 2 (Equipo 1)</th>
                <th style="width: 20%;">Equipo 2</th>
                <th style="width: 10%;">Puntos equipo 1 (Equipo 2)</th>
                <th style="width: 10%;">Puntos equipo 2 (Equipo 2)</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach items="${matches}" var="match">
                <tr class="rowtable">
                    <td><c:out value="${match.dateTime}" /></td>
                    <td><c:out value="${match.team1.id}" /></td>
                    <td><c:out value="${match.puntos1}" /></td>
                    <td><c:out value="${match.puntos2}" /></td>
                    <td><c:out value="${match.team2.id}" /></td>
                    <td><c:out value="${match.puntos3}" /></td>
                 	<td><c:out value="${match.puntos4}" /></td>
                        
                     <td><spring:url value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/result/{team}?search=Antonio"
                            var="result2Url">
                            <spring:param name="championshipId" value="${championship}" />
                            <spring:param name="deporte" value="${deporte}" />
                            <spring:param name="matchId" value="${match.id}" />
                            <spring:param name="team" value="team1" />
                        </spring:url>
                        <div class="botoncitores1"> <a href="${fn:escapeXml(result2Url)}">Añadir resultados de Equipo 1</a></div>
                        
                        <spring:url value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/result/{team}?search=Antonio"
                            var="result2Url">
                            <spring:param name="championshipId" value="${championship}" />
                            <spring:param name="deporte" value="${deporte}" />
                            <spring:param name="matchId" value="${match.id}" />
                            <spring:param name="team" value="team2" />
                        </spring:url>
                        <div class="botoncitores2"> <a href="${fn:escapeXml(result2Url)}">Añadir resultados de Equipo 2</a></div>
                        </td>
                        </tr>
                        <tr>
                        <td>
	                        <div>
		                        <c:if test="${match.puntos1 != null && match.puntos2 != null && match.puntos3 != null
								&& match.puntos4 != null && match.puntos1 == match.puntos3 && match.puntos2 == match.puntos4}">
		                			<c:choose>
										<c:when test="${match.puntos1 > match.puntos2 && match.puntos3 > match.puntos4}">
											<p> ¡Equipo 1 ganador! </p>
										</c:when>
										<c:when test="${match.puntos1 < match.puntos2 && match.puntos3 < match.puntos4 }">
											<p> ¡Equipo 2 ganador! </p>
										</c:when>
										<c:when test="${match.puntos1 == match.puntos2 && match.puntos3 == match.puntos4 }">
											<p> ¡El resultado del partido no debe ser empate! </p>
										</c:when>
									</c:choose>
								</c:if>
								<c:if test="${match.puntos1 != match.puntos3 || match.puntos2 != match.puntos4}">
                					<p> ¡Los resultados no coinciden! </p>
                				</c:if>
							</div>
						</td>
                        </tr>
				
            </c:forEach>
        </tbody>

    </table>
    </div>
    <spring:url value="/sports/{deporte}/championships/{championshipId}/match/add" var="dateUrl">
	                         <spring:param name="deporte" value="${deporte}"/>
	                         <spring:param name="championshipId" value="${championship}" />
	                     </spring:url>
	                     <div class="botoncitocrear">
	<a id="createMatch"  href="${fn:escapeXml(dateUrl)}">Añadir partido</a></div>
</body>
</html>
</playtogether:layout>