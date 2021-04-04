<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="matches">

 <h2>Información de partido de torneo</h2>

<div class="card">

    <table class="table table-striped">
        <tr>
            <th>Fecha y hora</th>
            <td><c:out value="${match.dateTime}"/></td>
        </tr>
        <tr>
            <th>Equipo 1:</th>
            <td><c:out value="${match.team1.name}"/></td>
        </tr>
        <tr>
            <th>Equipo 2:</th>
            <td><c:out value="${match.team2.name}"/></td>
        </tr>
       
    </table>
</div>

<body>

</html>
</playtogether:layout>