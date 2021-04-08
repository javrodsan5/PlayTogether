<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">
<div class="thirteen">
<h1>Pago</h1>
</div>
<div style="margin-left: 45px">
			<div class="crearMeeting">
<form:form  commandName="order" modelAttribute="order" method="post"  action="/pay?championshipId=${championshipId}&teamId=${teamId}&teamName=${teamName}">
	<div>
		<div>
			<playtogether:inputField label="Precio" name="price"/>
			<playtogether:inputField label="Moneda" name="currency" />
			<playtogether:inputField label="Método" name="method" />
			<playtogether:inputField label="Asunto" name="intent" />
			<playtogether:inputField label="Descripción" name="description" />

		<input class="butona" type="submit" value="Pagar">

	</div>
	
	
</form:form>
</div>
	</div>
</playtogether:layout>