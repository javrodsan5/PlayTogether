<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<playtogether:layout pageName="championships" invitaciones="${invitaciones}"> 
	<div class="thirteen">
		<c:if test="${match['new']}">
			<h1>Crear partido</h1>
		</c:if>
		<c:if test="${!match['new']}">
			<h1>Indicar resultado de partido</h1>
		</c:if>
		<br>
		<h2>El torneo comienza el <fmt:parseDate value="${championshipObj.startDate }" pattern="yyyy-MM-dd" var="parsedDateStart" type="both" />
          <fmt:formatDate 
         value = "${parsedDateStart}" pattern = "dd-MM-yyyy"  /> y finaliza el <fmt:parseDate value="${championshipObj.finishDate }" pattern="yyyy-MM-dd" var="parsedDateEnd" type="both" />
          <fmt:formatDate 
         value = "${parsedDateEnd}" pattern = "dd-MM-yyyy"  /></h2>
	</div>
		<div class="crearMeeting">
			<form:form commandName="match" modelAttribute="match">
				<div>
					<c:if test="${match['new'] && !noPuntos}">
						<playtogether:localDateTime label="Fecha realización"
							name="dateTime" id="dateTime"></playtogether:localDateTime>
						<div class="selectorPartido">
							<label>Equipo 1</label><br> <select name="team1">
								<c:forEach var="item" items="${equipos}">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="selectorPartido">
							<label>Equipo 2</label><br> <select name="team2">
								<c:forEach var="item" items="${equipos}">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="errorSelector">${errorSelector}</div>

						<input type="hidden" label="Puntos Equipo 1" name="puntos1" />
						<input type="hidden" label="Puntos Equipo 2" name="puntos2" />
						<input type="hidden" label="Puntos Equipo 3" name="puntos3" />
						<input type="hidden" label="Puntos Equipo 4" name="puntos4" />
						<input type="hidden" label="Ronda" name="ronda" />

					</c:if>
					<c:if test="${isPuntos1==true}">
						<div class="nomostrar">
							<playtogether:localDateTime label="Fecha realización"
								name="dateTime" id="dateTime"></playtogether:localDateTime>
						</div>
						<label for="puntos1">Puntos de ${match.team1.name}</label>
						<div class="${cssGroup}">
							<div class="col-sm-10">
								<select label="Puntos Equipo 1" name="puntos1">
									<c:forEach var="puntos" items="${listaPuntos}">
										<option value="${puntos}">${puntos}</option>
									</c:forEach>
								</select>
							</div>
							<br>
						</div>
						<label for="puntos2">Puntos de ${match.team2.name}</label>
						<div class="${cssGroup}">
							<div class="col-sm-10">

								<select label="Puntos Equipo 2" name="puntos2">
									<c:forEach var="puntos" items="${listaPuntos}">
										<option value="${puntos}">${puntos}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<input type="hidden" label="Equipo 1" name="team1"
							value="${match.team1.id }" />
						<input type="hidden" label="Equipo 2" name="team2"
							value="${match.team2.id }" />
						<br>
						<br>
						<c:if test="${noPuntos}">
							<div class="alert alert-danger" style="margin: 0% 20% 5% 20%">
								<p>Faltan campos por rellenar.</p>
							</div>
						</c:if>
					</c:if>
					<c:if test="${isPuntos1==false}">
						<div class="nomostrar">
							<playtogether:localDateTime label="Fecha realización"
								name="dateTime" id="dateTime"></playtogether:localDateTime>
						</div>
						<label for="puntos3">Puntos de ${match.team1.name}</label>
						<div class="${cssGroup}">
							<div class="col-sm-10">
								<select label="Puntos Equipo 1 (Equipo 2)" name="puntos3">
									<c:forEach var="puntos" items="${listaPuntos}">
										<option value="${puntos}">${puntos}</option>
									</c:forEach>
								</select>
							</div>
							<br>
						</div>
						<label for="puntos4">Puntos de ${match.team2.name}</label>
						<div class="${cssGroup}">
							<div class="col-sm-10">
								<select label="Puntos Equipo 2 (Equipo 2)" name="puntos4">
									<c:forEach var="puntos" items="${listaPuntos}">
										<option value="${puntos}">${puntos}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<input type="hidden" label="Equipo 1" name="team1"
							value="${match.team1.id }" />
						<input type="hidden" label="Equipo 2" name="team2"
							value="${match.team2.id }" />
						<br>
						<br>
						<c:if test="${noPuntos}">
							<div class="alert alert-danger" style="margin: 0% 20% 5% 20%">
								<p>Faltan campos por rellenar.</p>
							</div>
						</c:if>
					</c:if>

					<input type="hidden" name="championship" value="${championship}" />
					<div class="form-group">
						<button class="botonMeeting" type="submit">
							<b>Enviar</b>
						</button>

						<div class="form-group">
							<button class="botonMeeting"
								onclick="location.href='/sports/${championshipObj.sport.id}/championships/${championshipObj.id}/matches';"
								type="button">
								<b>Volver a listado</b>
							</button>
						</div>
					</div>
				</div>
			</form:form>
		</div>
</playtogether:layout>