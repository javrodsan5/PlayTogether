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
				Crear quedada de
				<c:out value="${sport.name}" />
			</h1>
		</div>
			<div class="crearMeeting">
				<form:form modelAttribute="meeting" commandName="meeting"
					id="survey-form">
	
						<playtogether:inputField label="Fecha y hora" name="date" />
						<playtogether:inputField label="Ciudad" name="city" />
					
					<div class="col-sm-12">
						<label>Número de participantes</label><br> <select name="numberOfPlayers">
							<c:forEach var="number" items="${numbers}">
								<option value="${number}">${number}</option>
							</c:forEach>
						</select>
					</div>
					<br>
					<div class="col-sm-12">
						<label>Categoría</label><br> <select name="category.id">
							<c:forEach var="categ" items="${categories}">
								<option value="${categ.id}">${categ.name}</option>
							</c:forEach>
						</select>
					</div>
					<br>
					<playtogether:inputField label="Dirección" name="address" />
					<playtogether:inputField label="Descripción" name="description" />
					<input type="hidden" name="sport" value="${sportId}"/>

					<div class="form-group">
						<button class="botonMeeting" type="submit">
							<b>Crear</b>
						</button>
					</div>
								<div class="form-group">
						<button class="botonMeeting" onclick="location.href='/sports/${sport.id}/meetings?category=Todas';" type="button">
							<b>Volver a listado</b>
						</button>
					</div>
				</form:form>
			</div>

	</body>
</playtogether:layout>