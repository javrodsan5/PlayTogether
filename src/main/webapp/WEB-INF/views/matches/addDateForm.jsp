<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">
<div class="thirteen">
<c:if test="${match['new']}">
<h1>Crear partido</h1>
</c:if>
<c:if test="${!match['new']}">
<h1>Indicar resultado de partido</h1>
</c:if>
<br>
<h2>El torneo comienza el ${championshipObj.startDate} y finaliza el ${championshipObj.finishDate}</h2>
</div>
<div style="margin-left: 45px">
			<div class="crearMeeting">
<form:form  commandName="match" modelAttribute="match">
	<div>
	 
		<playtogether:localDateTime label="Fecha realización" name="dateTime" id="dateTime" ></playtogether:localDateTime>
		
		<input type="hidden" label="Puntos Equipo 1" name="puntos1"  />
		<input type="hidden" label="Puntos Equipo 2" name="puntos2"  />
		<input type="hidden" label="Puntos Equipo 3" name="puntos3"  />
		<input type="hidden" label="Puntos Equipo 4" name="puntos4"  />
	
	 
		<input type="hidden" label="Equipo 1" name="team1" value="${match.team1.id }" />
		<input type="hidden" label="Equipo 2" name="team2" value="${match.team2.id }" /><br><br>
		
		<input type="hidden" name="championship" value="${championship}"/>
		<div class="form-group">
			<button class="botonMeeting" type="submit">
			<b>Enviar</b>
			</button>
			
			<div class="form-group">
						<button class="botonMeeting" style="display:block; font-size: 0.8em; margin-left: 22.72em; " onclick="location.href='/sports/${championshipObj.sport.id}/championships/${championshipObj.id}/matches';" type="button">
							<b>Volver a listado</b>
						</button>
					</div>
		</div>
		<br>
	</div>
</form:form>
</div>
</div>
</playtogether:layout>