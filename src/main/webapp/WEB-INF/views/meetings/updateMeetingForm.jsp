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

<playtogether:layout pageName="meetings" invitaciones="${invitaciones}">
	<body>
		<div class="thirteen">
			<h1>
				Editar quedada de
				<c:out value="${sport.name}" />
			</h1>
		</div>

		<div style="margin-left: 45px">
			<div class="crearMeeting">
				<form:form modelAttribute="meeting" commandName="meeting"
					id="survey-form">
					<div style="display: inline-flex;">
						<playtogether:inputField label="Fecha y hora" name="date" />
						<playtogether:inputField label="Ciudad" name="city" />
					</div>

					<div class="col-sm-12">
						<label>Número de participantes</label><br> 
						<select name="numberOfPlayers"> 
							<c:forEach var="number" items="${numbers}">
								<option value="${number}" ${numberPlayers == number ? 'selected="selected"' : ''}>${number}</option>
							</c:forEach>
						</select>
						<br>
						<h9 style="color: white;">${errorPlayers}</h9>
					</div>
					<br>
					<playtogether:inputField label="Dirección" name="address" />
					<playtogether:inputField label="Descripción" name="description"></playtogether:inputField>
					<input type="hidden" name="sport" value="${sportId}" />
					
					<div class="form-group">
						<button class="botonMeeting" style="font-size: 0.8em; margin-left: 22.72em; "type="submit">
							<b>Editar</b>
						</button>
						<div class="form-group">
						<button class="botonMeeting" style="font-size: 0.8em; margin-left: 22.72em; " onclick="location.href='/sports/${sportId}/meetings/${meeting.id}';" type="button">
							<b>Volver a quedada</b>
						</button>
					</div>
					</div>
					<br>
					<br>
				</form:form>
			</div>
		</div>

	</body>
</playtogether:layout>