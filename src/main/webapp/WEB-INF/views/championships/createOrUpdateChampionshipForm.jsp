<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">
<div class="thirteen">
<h1>Crear un torneo</h1>
</div>
<div style="margin-left: 45px">
			<div class="crearMeeting">
<form:form  commandName="championship" modelAttribute="championship">
	<div>
		<div style="display: inline-flex;">
		<playtogether:localDate label="Inicio" name="startDate" id="startDte" ></playtogether:localDate>
		<playtogether:localDate label="Fin" name="finishDate" id="finishDate" ></playtogether:localDate>
		</div>
		<playtogether:inputField label="Ciudad" name="city" />
		<playtogether:inputField label="Descripción" name="description" />
		<playtogether:selectField label="Número de equipos" 
		name="maxTeams" size="3" names="${maximoEquipos}"></playtogether:selectField>
		<input type="hidden" name="sport" value="${deporte}"/>

		<input class="butona" type="submit" value="Crear">

	</div>
	
	
</form:form>
</div>
	</div>
</playtogether:layout>