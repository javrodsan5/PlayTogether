<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body>

<p>Ciudad:</p><c:out value="${championship.city}" />
<p>Fecha Inicio:</p><c:out value="${championship.startDate}" />
<p>Fecha Fin:</p><c:out value="${championship.finishDate}" />
<p>Descripción:</p><c:out value="${championship.description}" />
<p>Deporte:</p><c:out value="${championship.sport.name}" />
</body>
</html>