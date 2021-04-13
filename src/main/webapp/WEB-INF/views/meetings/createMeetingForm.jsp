<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<link href='https://fonts.googleapis.com/css?family=Crete Round'
	rel='stylesheet'>

<playtogether:layout pageName="meetings">
	<body>
		<div class="thirteen">
			<h1>
				Crear quedada de
				<c:out value="${sport.name}" />
			</h1>
		</div>
		<div style="margin-left: 45px">
			<div class="crearMeeting">
				<form:form modelAttribute="meeting" commandName="meeting"
					id="survey-form">
					<div style="display: inline-flex;">
						<playtogether:localDateTime label="Fecha" name="date" id="date"></playtogether:localDateTime>
						<playtogether:inputField label="Ciudad" name="city" />
					</div>

					<playtogether:inputField label="Dirección" name="address" />
					<playtogether:inputField label="Descripción" name="description"></playtogether:inputField>
					<input type="hidden" name="sport" value="${sportId}"/>

					<div class="form-group">
						<button class="botonMeeting" type="submit">
							<b>Crear</b>
						</button>
					</div>
								<div class="form-group">
						<button class="botonMeeting" style="font-size: 0.8em; margin-left: 22.72em; " onclick="location.href='/sports/${sport.id}/meetings';" type="button">
							<b>Volver a listado</b>
						</button>
					</div>
					<br>
					<br>
				</form:form>
			</div>
		</div>

	</body>
</playtogether:layout>