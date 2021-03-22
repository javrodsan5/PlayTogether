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
<table id="championshipTable" class="table table-striped">
        <thead>
            <tr>
                <th style="width: 20%;">Deporte</th>
                <th style="width: 30%;">Ciudad</th>
                <th style="width: 30%;">Descripción</th>
                <th style="width: 20%;">Fecha Inicio</th>
                <th style="width: 20%">Ver más</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${championships}" var="championship">
                <tr>
                    <td><c:out value="${championship.sport.id}" /></td>
                    <td><c:out value="${championship.city}" /></td>
                    <td><c:out value="${championship.description}" /></td>
                    <td><c:out value="${championship.startDate}" /></td>
                    <td><spring:url value="/sports/{deporte}/championships/{championshipId}"
                            var="championship2Url">
                            <spring:param name="championshipId" value="${championship.id}" />
                            <spring:param name="deporte" value="${deporte}" />
                        </spring:url> <a href="${fn:escapeXml(championship2Url)}">Ver más</a></td>
                </tr>
            </c:forEach>
        </tbody>

    </table>
    <spring:url value="/sports/{deporte}/championships/add" var="dateUrl">
	                         <spring:param name="deporte" value="${deporte}"/>
	                     </spring:url>
	<a id="createChampionship" href="${fn:escapeXml(dateUrl)}">Crear torneo</a>
</body>
</html>
</playtogether:layout>