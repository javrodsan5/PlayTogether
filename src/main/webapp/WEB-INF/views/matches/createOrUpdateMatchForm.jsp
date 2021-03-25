<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">
<div class="cardtitle">
<h1 class="auxcard">Crear un Partido</h1>
</div>
<div class ="cardform">
<form:form  commandName="match" modelAttribute="match">
	<div>
	 <c:if test="${match['new']}">
		<playtogether:localDateTime label="Fecha realización" name="dateTime" id="dateTime" ></playtogether:localDateTime>
		<playtogether:inputField label="Equipo 1" name="team1"  />
		<playtogether:inputField label="Equipo 2" name="team2"  />
		<input type="hidden" label="Puntos Equipo 1" name="puntos1"  />
		<input type="hidden" label="Puntos Equipo 2" name="puntos2"  />
	 </c:if>
	 <c:if test="${!match['new']}">
	 	<div class="nomostrar">
		<playtogether:localDateTime label="Fecha realización" name="dateTime" id="dateTime" ></playtogether:localDateTime>
		<playtogether:inputField label="Equipo 1" name="team1"  />
		<playtogether:inputField label="Equipo 2" name="team2"  />
		</div>
		<playtogether:inputField label="Puntos Equipo 1" name="puntos1"   />
		<playtogether:inputField label="Puntos Equipo 2" name="puntos2" />
	 </c:if>
		
		<input type="hidden" name="championship" value="${championship}"/>
		<input type="submit" class="butona" value="Enviar">
	</div>
</form:form>
</div>
</playtogether:layout>