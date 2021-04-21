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



<playtogether:layout pageName="chat">
	<body>
		<div class="thirteen">
			<h1>Añadir mensaje</h1>
		</div>
		<div style="margin-left: 45px" >
			<div class="crearMeeting">
				<form:form id="survey-form" modelAttribute="message" commandName="message">
					<playtogether:inputField label="Mensaje" name="message" /><br> <br>
					<div class="form-group">
						<button class="botonMeeting" type="submit" >
							<b>Enviar</b>
						</button>
					</div>
					<div class="form-group">
						<button class="botonMeeting" style="font-size: 0.8em; margin-left: 22.72em; " onclick="location.href='/chat/meeting/${message.chat.meeting.id}/messages';" type="button">
							<b>Volver a chat</b>
						</button>
					</div>
					<br>
					<br>
					<br>
					<br>
				</form:form>
			</div>
		</div>
	</body>
</playtogether:layout>	
	
	