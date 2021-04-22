<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="messages">
	<body>
		<div class="cardtitle">
			<h1>
				<strong>Mensajes</strong>
			</h1>
			<br />
		</div>
		<div class="cardlist">
			<table id="messageTable" class="table ">
				<thead>
					<tr class="rowtable">
						<th class="guiz-awards-header-title" style="width: 20%;">Fecha</th>
						<th class="guiz-awards-header-title" style="width: 10%;">Mensaje</th>
						<th class="guiz-awards-header-title" style="width: 25%;">Usuario</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${messages}" var="message">
						<tr class="rowtable">
							<td><c:out value="${message.date}" /></td>
							<td><c:out value="${message.message}" /></td>
							<td><c:out value="${message.usuario.user.username}" /></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
		<div style="margin-left: 45px" >
			<div class="crearMeeting">
				<form:form id="survey-form" modelAttribute="message" commandName="message" action="/chat/${message.chat.id}/messages/new" method="post">
					<playtogether:inputField label="Mensaje" name="message" />
					<c:if test="${vacio}">
						<br>
						<div class="alert alert-danger" style="margin: 0% 20% 5% 20%">
							<p>El mensaje no puede estar vacío.</p>
						</div>
					</c:if>
					<c:if test="${spam}">
						<br>
						<div class="alert alert-danger" style="margin: 0% 20% 5% 20%">
							<p>No puedes enviar mensajes con palabas malsonantes.</p>
						</div>
					</c:if>
					<div class="form-group">
						<button class="botonMeeting" type="submit" >
							<b>Enviar</b>
						</button>
					</div>
				</form:form>
			</div>
			<div class="form-group">
				<button class="botonMeeting" style="font-size: 0.8em; margin-left: 22.72em; " onclick="location.href='${urlBack}';" type="button">
					<b>Volver</b>
				</button>
			</div>
		</div>
</playtogether:layout>

</body>
</html>