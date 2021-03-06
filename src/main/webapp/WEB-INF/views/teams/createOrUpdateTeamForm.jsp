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



<playtogether:layout pageName="teams" invitaciones="${invitaciones}">
	<body>
		<div class="thirteen">
			<h1>Crear un equipo</h1>
		</div>
		<div style="margin-left: 45px" >
			<div class="crearMeeting">
				<form:form id="survey-form" modelAttribute="team" commandName="team">
					<playtogether:inputField label="Nombre" name="name" /><br> <br>
					<div class="form-group">
						<button class="botonMeeting" type="submit" >
							<b>Crear</b>
						</button>
					</div>
					<div class="form-group">
						<button class="botonMeeting" onclick="location.href='/sports/${championship.sport.id}/championships/${championship.id}';" type="button">
							<b>Volver a torneo</b>
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</body>
</playtogether:layout>	
	
	