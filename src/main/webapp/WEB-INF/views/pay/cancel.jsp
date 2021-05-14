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
		<h1 style= "font-size: 2rem">Se ha cancelado el pago</h1>
		<h3 class="text-danger">${timeOut}</h3>
		<i class="fa fa-times cancel" style="font-size: 10rem"></i>
		<br>
		<div class="alert alert-danger" style="margin: 0% 20% 5% 20%">
		<h5>Si ha surgido algún problema, vuelva a intentarlo.</h5>
		<h5>En caso de que el problema persista, contacte a esta dirección de correo: playtogetherispp@gmail.com</h5>
		</div>
	</center>
</playtogether:layout>