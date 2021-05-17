<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<playtogether:layout pageName="users" invitaciones="${invitaciones}">

	<body>
		<div class="cardtitle" style="margin-bottom: 2.5em;">

			<h1>
				<strong>Tu historial de quedadas </strong>
			</h1>
			<br />
		</div>
		<div class="cardlist">
			<c:if test="${noRecords}">
				<div class="alert alert-danger" style="margin: 0% 20% 1% 20%">
					<p>Todavía no has participado en ninguna quedada.</p>
				</div>
			</c:if>
			<c:if test="${noRecords == null}">
				<table id="userMeetingsTable" class="table " style="width: 75%; margin-left: 13%;">
					<thead>
						<tr class="rowtable">
							<th class="guiz-awards-header-title" style="width: 33%;">Dirección</th>
							<th class="guiz-awards-header-title" style="width: 33%;">Fecha</th>
							<th class="guiz-awards-header-title" style="width: 33%;"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${meetings}" var="meeting">
							<tr class="rowtable">
								<td><c:out value="${meeting.address}" /></td>
								<td><fmt:parseDate value="${meeting.date }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
         			 <fmt:formatDate value = "${parsedDateTime}" pattern = "dd-MM-yyyy HH:mm"  /></td>
								<td><spring:url
										value="/sports/{deporte}/meetings/{meetingId}"
										var="meeting2Url">
										<spring:param name="meetingId" value="${meeting.id}" />
										<spring:param name="deporte" value="${meeting.sport.id}" />

									</spring:url>
									<div class="botoncito" style="margin-left: 0%">
										<a class="" href="${fn:escapeXml(meeting2Url)}"><i class="fa fa-eye"></i></a>
									</div></td>

							</tr>
						</c:forEach>
					</tbody>

				</table>
			</c:if>
		</div>
		<br>
				<div class="form-group">
						<button class="botonMeeting" onclick="location.href='/myprofile';" type="button">
							<b>Volver a perfil</b>
						</button>
					</div>
	</body>
	</html>
</playtogether:layout>