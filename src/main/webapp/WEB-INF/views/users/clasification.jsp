<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="clasifications"
	invitaciones="${invitaciones}">
	<body>
		<div class="cardtitle">
			<h1>
				<strong>Top 10 usuarios de PlayTogether </strong>
			</h1>
		</div>
		<br>
		<center>
		<h2 class="alert alert-info"
			style="text-align: center;font-size: 20px;width: 50%;"> <i class="fa fa-info-circle"></i> Puedes ganar puntos de las siguientes formas:<br><br>
			- 7 puntos por creación de quedada o equipo.<br>
			- 5 puntos por unirse a una quedada o equipo.<br>
			- 5 puntos por pasar de ronda en un torneo.<br>
			- 10 puntos por ganar un torneo.<br><br>
			Si abandonas un torneo o una quedada perderás los puntos ganados.</h2></center>
		<br>
		<h2 class="alert alert-primary"
			style="text-align: center; margin: 0px 35% 0px 35%">¡Estás en la posición ${posicion}!</h2>
		<div class="cardlist">
		<center>
			<div class="scroll_vertical" id="style_scroll" style="height: 100%;">
			<table class="table-clasification" style="width:75%; text-align: center;">
				<thead>
					<tr class="rowtable">
						<th class="guiz-awards-header-title" style="width: 15%;">Posición</th>
						<th class="guiz-awards-header-title" style="width: 25%;">Nombre</th>
						<th class="guiz-awards-header-title" style="width: 25%;">Nombre
							de usuario</th>
						<th class="guiz-awards-header-title" style="width: 15%;">Puntos</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${topUsuarios}" var="usuario" varStatus="numero">
						<tr class="rowtable">
							<td style="text-align: center;">
								<c:if test="${numero.count == 1}">
									<img style="max-height: 55%; max-width: 55%;" src="/images/goldMedal.png" alt="1" />
								</c:if>
								<c:if test="${numero.count == 2}">
									<img style="max-height: 47%; max-width: 47%;" src="/images/silverMedal.png" alt="2" />
								</c:if>
								<c:if test="${numero.count == 3}">
									<img style="max-height: 38%; max-width: 38%;" src="/images/bronzeMedal.png" alt="3" />
								</c:if>
								<c:if test="${numero.count > 3}">
									<c:out value="${numero.count}" />
								</c:if>	
							</td>
							<td><c:out value="${usuario.name}" /></td>
							<td><spring:url value="/usuarios/{usuarioId}"
									var="usuario2Url">
									<spring:param name="usuarioId" value="${usuario.id}" />
								</spring:url> <a class="" href="${fn:escapeXml(usuario2Url)}"><c:out
										value="${usuario.user.username}" /></a></td>
							<td><c:out value="${usuario.puntos}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			</center>
		</div>
	</body>
	</html>
</playtogether:layout>