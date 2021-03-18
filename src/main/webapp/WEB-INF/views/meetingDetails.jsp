<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body>
<p>Dirección:</p><c:out value="${meeting.address}" />
<p>Ciudad:</p><c:out value="${meeting.city}" />
<p>Fecha:</p><c:out value="${meeting.date}" />
<p>Descripción:</p><c:out value="${meeting.description}" />
<p>Deporte:</p><c:out value="${meeting.sport.name}" />
</body>
</html>