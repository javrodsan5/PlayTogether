<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>

<playtogether:layout pageName="login">
	<jsp:body>
		<div class="thirteen">
			<h1>
				Inicie sesión
			</h1>
		</div>
		<div class="crearMeeting">
		<c:if test="${param.error == 'true'}">
              <h5 class="alert alert-danger"
					style="text-align:center;margin: 0% 25% 5% 25%"> Usuario o contraseña incorrectos. </h5>
                </c:if>
		<form:form class="form-horizontal" id="add-usuario-form"
				action="/login" method="post">
			 	<label for="username" style="margin-left: 5%"><b>Usuario</b></label>
                <input type="text" id="username" name="username"
					style="width: 90%; padding: 5px; margin-left: 5%; margin-right: 5%" />
                 <br>
                 <br>
                <label for="password" style="margin-left: 5%"><b>Contraseña</b></label>
                <input type="password" id="password" name="password"
					style="width: 90%; padding: 5px; margin-left: 5%; margin-right: 5%" />
                 <br>
                <br>

                
    	<div class="form-group">
				<button class="botonMeeting" type="submit">
						<b>Entrar</b>
					</button>
			</div>
			<br>
		</form:form>
		</div>
	</jsp:body>
</playtogether:layout>