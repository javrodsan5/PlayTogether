<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="registroPlaytogether">
	<jsp:body>
	<h2>
		Usuario
	</h2>
	<form:form modelAttribute="usuario" class="form-horizontal"
			id="add-usuario-form">
		<div class="form-group has-feedback">
			<playtogether:inputField label="Nombre" name="name" />
			<playtogether:inputField label="Correo" name="correo" />
			<playtogether:inputField label="Telefono" name="phone" />
			<playtogether:localDate label="Fecha nacimiento" name="birthdate" />
			</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
			<button class="btn btn-default" type="submit">Registrarme</button>
			</div>
		</div>
	</form:form>
	 </jsp:body>
</playtogether:layout>