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
		<h1 style= "font-size: 65px">¡GRACIAS!</h1>
		<i class="fa fa-check success" style="font-size: 10rem"></i>
		<br>
		<div class="alert alert-warning" style="margin: 0% 20% 5% 20%">
			<h3>¡Sólo un paso más! <a href="/logout">Cierre</a> e inicie sesión para disfrutar de sus nuevas ventajas.</h3>
		</div>
	</center>
</playtogether:layout>