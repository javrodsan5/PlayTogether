<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<playtogether:layout pageName="users" invitaciones="${invitaciones}">

	<body>
		<div class="cardtitle">

			<h1>
				<strong>Tu historial de pagos </strong>
			</h1>
			<br />
		</div>
		<div class="cardlist">
			<c:if test="${noRecords}">
				<div class="alert alert-danger" style="margin: 0% 20% 1% 20%">
					<p>Todavía no has realizado ningun pago.</p>
				</div>
			</c:if>
			<c:if test="${noRecords == null}">
				<table id="userMeetingsTable" class="table ">
					<thead>
						<tr class="rowtable">
							<th class="guiz-awards-header-title" style="width: 15%;">Fecha</th>
							<th class="guiz-awards-header-title" style="width: 10%;">Cantidad</th>
							<th class="guiz-awards-header-title" style="width: 75%;">Pago
								para:</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pays}" var="pay">
							<tr class="rowtable">
								<td><c:out value="${pay.date}" /></td>
								<td><c:out value="${pay.amount}" />€</td>
								<td><c:if
										test="${pay.team.id != null && pay.payType.id == 2 }">
         			 			Participar en equipo:"<c:out value="${pay.team.name}" />", en el torneo: "<c:out
											value="${pay.championship.name}" />"
								</c:if> <c:if test="${pay.payType.id == 1}">
										<c:out value="${pay.payType}" />
									</c:if> <c:if
										test="${pay.team.id == null && pay.championship.id != null}">
								Crear el torneo: "<c:out value="${pay.championship.name}" />"
								</c:if> <c:if test="${pay.payType.id == 3}">
								Invitación para participar en equipo:"<c:out
											value="${pay.team.name}" />",
								en el torneo: "<c:out value="${pay.championship.name}" />"
								</c:if></tr>
						</c:forEach>
					</tbody>

				</table>
			</c:if>
		</div>
		<br>
		<div class="form-group">
			<button class="botonMeeting" onclick="location.href='/myprofile';"
				type="button">
				<b>Volver a perfil</b>
			</button>
		</div>
	</body>
	</html>
</playtogether:layout>