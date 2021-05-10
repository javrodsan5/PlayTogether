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
		Editar mi descripción
	</h1>
	</div>
	<div class="crearMeeting">
	<form:form modelAttribute="usuario" class="form-horizontal"
				id="add-usuario-form">
		
			<textarea placeholder="Defínete para el resto de usuarios..."  name="description" maxlength="150" style="width:100%"/>${usuario.description}</textarea>
			
					<div class="ocultar">
			<playtogether:inputField label="Usuario" name="user" />
				</div>
		<div class="form-group">
			<button class="botonMeeting" type="submit">
			<b>Guardar</b>
			</button>
		</div>
		<div class="form-group">
						<button class="botonMeeting" onclick="location.href='/myprofile';" type="button">
							<b>Volver a perfil</b>
						</button>
					</div>
		<br>
			
			</form:form>
		</div>
	
	 </jsp:body>
</playtogether:layout>