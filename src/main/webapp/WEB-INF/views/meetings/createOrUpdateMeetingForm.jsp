<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="meetings">
	<body>
		<h2>
			<c:if test="${!meeting['new']}">
				Editar una quedada.</c:if>
		</h2>
		<h2>
			<c:if test="${meeting['new']}">
				Crear una quedada.</c:if>
		</h2>
		<div style="margin-left: 45px">
			<div class="crearMeeting">
				<form:form modelAttribute="meeting" commandName="meeting"
					id="survey-form">
					<playtogether:localDateTime label="Fecha" name="date" id="date"></playtogether:localDateTime>
					<playtogether:inputField label="Ciudad" name="city">

					</playtogether:inputField>
					<playtogether:inputField label="Lugar de encuentro" name="address" />
					<playtogether:inputField label="Descripción" name="description" />
					<input type="hidden" name="sport" value="${deporte}" />
					<center>
					<div class="form-group">
						<c:choose>
							<c:when test="${meeting['new']}">
								<button class="botonMeeting" type="submit">Crear</button>
							</c:when>
							<c:otherwise>
								<button class="botonMeeting" type="submit">Actualizar
									datos</button>
							</c:otherwise>
						</c:choose>
					</div>
					</center>
				</form:form>
			</div>
		</div>

	</body>
</playtogether:layout>