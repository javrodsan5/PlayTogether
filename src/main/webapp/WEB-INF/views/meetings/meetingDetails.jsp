<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<body>
	<h2>Detalles de quedada</h2>
	<div class="boxed">
		<p>
			Dirección:
			<c:out value="${meeting.address}" />
		</p>
		<p>
			Ciudad:
			<c:out value="${meeting.city}" />
		</p>
		<p>
			Fecha:
			<c:out value="${meeting.date}" />
		</p>
		<p>
			Descripción:
			<c:out value="${meeting.description}" />
		</p>
		<p>
			Deporte:
			<c:out value="${meeting.sport.name}" />
		</p>
	</div>
</body>