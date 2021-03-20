<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body>
<table id="meetingTable" class="table table-striped">
        <thead>
            <tr>
                <th style="width: 20%;">Dirección</th>
                <th style="width: 30%;">Ciudad</th>
                <th style="width: 30%;">Descripción</th>
                <th style="width: 20%;">Fecha</th>
                <th style="width: 20%">Deporte</th>
                <th style="width: 20%">Ver más</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${meetings}" var="meeting">
                <tr>
                    <td><c:out value="${meeting.address}" /></td>
                    <td><c:out value="${meeting.city}" /></td>
                    <td><c:out value="${meeting.description}" /></td>
                    <td><c:out value="${meeting.date}" /></td>
                    <td><c:out value="${meeting.sport.id}" /></td>
                    <td><spring:url value="/meetings/{meetingId}"
                            var="meeting2Url">
                            <spring:param name="meetingId" value="${meeting.id}" />
                        </spring:url> <a href="${fn:escapeXml(meeting2Url)}">Ver más</a></td>
                </tr>
            </c:forEach>
        </tbody>

    </table>
</body>
</html>