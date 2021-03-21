<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<playtogether:layout pageName="sports">
<body>
	<h2>Detalles de quedada</h2>
	<div class="boxed">
		<div class="imp">
		<p>
			Dirección:
			<c:out value="${meeting.address}" />
		</p>
		</div>
		<div class="par">
		<p>
			Ciudad:
			<c:out value="${meeting.city}" />
		</p>
		</div>
		<div class="imp">
		<p>
			Fecha:
			<c:out value="${meeting.date}" />
		</p>
		</div>
		<div class="par">
		<p>
			Descripción:
			<c:out value="${meeting.description}" />
		</p>
		</div>
		<div class="imp">
		<p>
			Deporte:
			<c:out value="${meeting.sport.name}" />
		</p>
		</div>
	</div>
</playtogether:layout>
</body>