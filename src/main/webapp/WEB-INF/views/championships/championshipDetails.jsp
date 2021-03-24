<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">

 <h2>Información de torneos</h2>

<div class="card">

    <table class="table table-striped">
        <tr>
            <th>Ciudad</th>
            <td><b><c:out value="${championship.city}"/></b></td>
        </tr>
        <tr>
            <th>Fecha Inicio:</th>
            <td><c:out value="${championship.startDate}"/></td>
        </tr>
        <tr>
            <th>Fecha Fin:</th>
            <td><c:out value="${championship.finishDate}"/></td>
        </tr>
        <tr>
            <th>Descripción:</th>
            <td><c:out value="${championship.description}"/></td>
        </tr>
        <tr>
            <th>Deporte:</th>
            <td><c:out value="${championship.sport.name}"/></td>
        </tr>
    </table>
</div>
 <spring:url value="/sports/{deporte}/championships/{championshipId}/matches" var="dateUrl">
	                         <spring:param name="deporte" value="${championship.sport.id}"/>
	                         <spring:param name="championshipId" value="${championship.id}" />
	                     </spring:url>
	<a id="listMatch" href="${fn:escapeXml(dateUrl)}">Ver partidos</a>
<body>

</html>
</playtogether:layout>