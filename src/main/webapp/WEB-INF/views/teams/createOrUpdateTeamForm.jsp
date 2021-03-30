<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<link href='https://fonts.googleapis.com/css?family=Crete Round'
	rel='stylesheet'>


<playtogether:layout pageName="teams">
	<body>
	<div class="thirteen">
		<h1>Crear un equipo</h1>
	</div>
	<div style="margin-left: 45px" >
		<div class="crearMeeting">
		<form:form id="survey-form" modelAttribute="team" commandName="team">
			<playtogether:inputField label="Nombre de equipo" name="name" /><br> <br>
			<center>
			<div class="form-group">
				<input class="button-meeting" type="submit"  value="Crear"/>
			</div>
			</center>
		</form:form>
		</div>
	</div>
	</body>
</playtogether:layout>	
	
	