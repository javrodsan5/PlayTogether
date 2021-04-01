<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">
<div class="thirteen">
<h1>Crear un Partido</h1>
<br>
<h2>El torneo comienza el ${championshipObj.startDate} y finaliza el ${championshipObj.finishDate}</h2>
</div>
<div style="margin-left: 45px">
			<div class="crearMeeting">
<form:form  commandName="match" modelAttribute="match">
	<div>
	 <c:if test="${match['new'] && !noPuntos}">
		<playtogether:localDateTime label="Fecha realización" name="dateTime" id="dateTime" ></playtogether:localDateTime>
		<playtogether:selectField label="Equipo 1" name="team1" size="8" names="${equipos }"></playtogether:selectField>
		<playtogether:selectField label="Equipo 2" name="team2" size="8" names="${equipos }"></playtogether:selectField>
		<input type="hidden" label="Puntos Equipo 1" name="puntos1"  />
		<input type="hidden" label="Puntos Equipo 2" name="puntos2"  />
		<input type="hidden" label="Puntos Equipo 3" name="puntos3"  />
		<input type="hidden" label="Puntos Equipo 4" name="puntos4"  />
	 </c:if>
	 <c:if test="${isPuntos1==true}">
	 	<div class="nomostrar">
		<playtogether:localDateTime label="Fecha realización" name="dateTime" id="dateTime" ></playtogether:localDateTime>
		</div>
		<playtogether:inputField label="Puntos Equipo 1 (Equipo 1)" name="puntos1"   />
		<playtogether:inputField label="Puntos Equipo 2 (Equipo 1)" name="puntos2" />
		<input type="hidden" label="Equipo 1" name="team1" value="${match.team1.id }" />
		<input type="hidden" label="Equipo 2" name="team2" value="${match.team2.id }" />
		<c:if test="${noPuntos}">
			<p>Faltan campos por rellenar.</p>
		</c:if>
	 </c:if>
	 <c:if test="${isPuntos1==false}">
	 	<div class="nomostrar">
		<playtogether:localDateTime label="Fecha realización" name="dateTime" id="dateTime" ></playtogether:localDateTime>
		</div>
		<playtogether:inputField label="Puntos Equipo 1 (Equipo 2)" name="puntos3"   />
		<playtogether:inputField label="Puntos Equipo 2 (Equipo 2)" name="puntos4" />
		<input type="hidden" label="Equipo 1" name="team1" value="${match.team1.id }" />
		<input type="hidden" label="Equipo 2" name="team2" value="${match.team2.id }" />
		<c:if test="${noPuntos}">
			<p>Faltan campos por rellenar.</p>
		</c:if>
	 </c:if>
		
		<input type="hidden" name="championship" value="${championship}"/>
		<input type="submit" class="butona" value="Enviar">
	</div>
</form:form>
</div>
</div>
</playtogether:layout>