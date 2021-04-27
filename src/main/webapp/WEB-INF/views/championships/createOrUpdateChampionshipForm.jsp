<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="championships"
	invitaciones="${invitaciones}">
	<div class="thirteen">
		<h1>Crear un torneo</h1>
	</div>
	<div style="margin-left: 45px">
		<div class="crearMeeting">
			<form:form commandName="championship" modelAttribute="championship">
				<div>
					<div style="display: inline-flex;">
						<playtogether:inputField label="Fecha Inicio" name="startDate" />
						<playtogether:inputField label="Fecha Fin" name="finishDate" />

					</div>
					<playtogether:inputField label="Nombre" name="name" />
					<playtogether:inputField label="Ciudad" name="city" />
					<playtogether:inputField label="Descripción" name="description" />
					<playtogether:inputField label="Dirección" name="address" />
					<playtogether:selectField label="Número de equipos" name="maxTeams"
						size="3" names="${maximoEquipos}"></playtogether:selectField>
					<input type="hidden" name="sport" value="${deporte}" />

					<div class="form-group">
						<button class="botonMeeting" type="submit">
							<b>Crear</b>
						</button>
					</div>
					<div class="form-group">
						<button class="botonMeeting"
							onclick="location.href='/sports/${deporte}/championships';"
							type="button">
							<b>Volver a listado</b>
						</button>
					</div>
				</div>


			</form:form>
		</div>
	</div>
</playtogether:layout>