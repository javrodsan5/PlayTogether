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
<table id="matchTable" class="table">
        <thead>
            <tr class="rowtable">
                <th style="width: 20%;">Fecha y hora</th>
                <th style="width: 20%;">Equipo 1</th>
                <th style="width: 15%;">Puntos equipo 1</th>
                <th style="width: 20%;">Equipo 2</th>
                <th style="width: 15%;">Puntos equipo 2</th>

            </tr>
        </thead>
        <tbody>
            <c:forEach items="${matches}" var="match">
                <tr class="rowtable">
                    <td><c:out value="${match.dateTime}" /></td>
                    <td><c:out value="${match.team1.id}" /></td>
                    <td><c:out value="${match.puntos1}" /></td>
                    <td><c:out value="${match.team2.id}" /></td>
                    <td><c:out value="${match.puntos2}" /></td>
                 
                        
                     <td><spring:url value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/result"
                            var="result2Url">
                            <spring:param name="championshipId" value="${championship}" />
                            <spring:param name="deporte" value="${deporte}" />
                            <spring:param name="matchId" value="${match.id}" />
                        </spring:url><div class="botoncitores"> <a href="${fn:escapeXml(result2Url)}">Añadir resultados</a></div></td>
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