<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="clasifications">
	<body>
		<div class="cardtitle">

			<h1>
				<strong>Top 10 usuarios de PlayTogether </strong>
			</h1>


		</div>
		<br>
		<h2 class="alert alert-primary"
			style="text-align: center; margin: 0px 35% 0px 35%">¡Estás en la posición ${posicion}!</h2>
		<div class="cardlist">
		<center>
			<table class="table" style="width:50%">
				<thead>
					<tr class="rowtable">
						<th class="guiz-awards-header-title" style="width: 33%;">Nombre</th>
						<th class="guiz-awards-header-title" style="width: 33%;">Nombre
							de usuario</th>
						<th class="guiz-awards-header-title" style="width: 33%;">Puntos</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${topUsuarios}" var="usuario">
						<tr class="rowtable">
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
			</center>
		</div>
	</body>
	</html>
</playtogether:layout>