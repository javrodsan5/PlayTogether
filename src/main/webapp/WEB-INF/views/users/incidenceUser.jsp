<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>

<playtogether:layout pageName="registroPlaytogether">
	<jsp:body>
	<div class="thirteen">
	<h1>
		Incidencia de <c:out value="${usuario.user.username}" />
	</h1>
	</div>
	
	<br>
		<br>
	<div class="datos" style="font-size: 20px; text-align: center">
			<b><c:out value="Nombre: " /> </b>
			<c:out value="${usuario.name} "    />  <br><br>
			<b><c:out value="Correo: " />  </b> 
			<c:out value="${usuario.correo} "   /> <br><br>
			<b><c:out value="Teléfono: " /> </b>
			<c:out value="${usuario.phone}" />
	</div>
			
	<div class="crearMeeting">
	<form:form modelAttribute="usuario" class="form-horizontal"
				id="add-usuario-form">
			
			<label for="descripcion">Descripción:</label>
			<textarea placeholder="Descríbanos el problema..." name="description"  maxlength="300" minlength="5" required="true" style="width: 100%" ></textarea>
			
			
					<div class="ocultar">
			<playtogether:inputField label="Usuario" name="user" />
				</div>
		<div class="form-group">
			<a class="botonMeeting" href='/myprofile/incidence/accepted' type="button">
			<b>Enviar incidencia</b>
			</a>
		</div>
		<div class="form-group">
						<button class="botonMeeting" onclick="location.href='/myprofile';"
						type="button">
							<b>Volver a perfil</b>
						</button>
					</div>
			
			</form:form>
		</div>
	
	 </jsp:body>
</playtogether:layout>