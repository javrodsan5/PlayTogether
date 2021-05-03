<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<style>
/* Make the image fully responsive */
.carousel-inner img {
	width: 100%;
	height: 100%;
}
</style>

<html>
<playtogether:layout pageName="welcome" invitaciones="${invitaciones}">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>Playtogether</title>
	</head>
	<body>
		<div id="demo" class="carousel slide" data-ride="carousel" style="margin-bottom: 2.5em;">
			<ul class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
				<li data-target="#demo" data-slide-to="3"></li>
				<li data-target="#demo" data-slide-to="4"></li>
				<li data-target="#demo" data-slide-to="5"></li>
			</ul>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="/images/home7.png" alt="Foto1" width="1100" height="500">
					<div class="carousel-caption">
						<h3>Queda con quien quieras y donde quieras</h3>
						<p class="alert alert-primary">Con PlayTogether no importa donde te encuentres, siempre es buen momento para realizar deporte</p>
					</div>
				</div>
				<div class="carousel-item" style="padding-top: 3.5em; margin-bottom: 2.5em;">
					<img src="/images/home14.png" alt="Foto2" width="1100" height="500">
					<div class="carousel-caption">
						<h3>Compite sin límites</h3>
						<p class="alert alert-primary">Disfruta de las quedadas o lánzate a competir en exigentes torneos</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="/images/home15.png" alt="Foto3" width="1100" height="500">
					<div class="carousel-caption">
						<h3>Infinidad de actividades</h3>
						<p class="alert alert-primary">Ofrecemos un sinfín de actividades para que siempre encuentres gente con tus mismas aficiones</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="/images/home12.png" alt="Foto4" width="1100" height="500">
					<div class="carousel-caption">
						<h3>¿Quieres aprovechar todo el potencial de PlayTogether?</h3>
						<p class="alert alert-primary">¡Suscríbete ya y disfruta de todas las ventajas de ser premium!</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="/images/home11.png" alt="Foto5" width="1100" height="500">
					<div class="carousel-caption">
						<h3>Escala en nuestra clasificación participando en quedadas y torneos</h3>
						<p class="alert alert-primary">Brindamos a nuestros usuarios un sistema de clasificación por puntos para conocer a los mejores jugadores</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="/images/home6.png" alt="Foto6" width="1100" height="500">
					<div class="carousel-caption">
						<h3>¿A qué esperas?</h3>
						<p class="alert alert-primary">Regístrate ya y practica deporte como nunca antes lo habías hecho</p>
					</div>
				</div>
			</div>
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#demo" data-slide="next">
				<span class="carousel-control-next-icon"></span>
			</a>
		</div>

	</body>

</playtogether:layout>
</html>