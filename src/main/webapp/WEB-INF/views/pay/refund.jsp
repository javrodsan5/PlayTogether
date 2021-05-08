<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="pay" invitaciones="${invitaciones}">
	<center>
		<h1 style= "font-size: 65px">${title}</h1>
		<img style="width: 10%; height: 10%; margin-top: 2%;" src="/images/refund.png" />
		<div class="alert alert-success" style="margin: 2% 20% 2% 20%">
		<h4>Se está procesando su devolución de 2€. La recibirá en un plazo máximo de 72 horas.</h4>
		<h4>En caso contrario, contacte a esta dirección de correo: playtogetherispp@gmail.com</h4>
		</div>
        <button class="botonTorneos" onclick="location.href='${button}';" type="button">
			<b>${messageButton}</b>
		</button>
	</center>
</playtogether:layout>