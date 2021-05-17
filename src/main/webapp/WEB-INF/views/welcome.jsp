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
	height: auto;
}
</style>

<playtogether:layout pageName="welcome" invitaciones="${invitaciones}">

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
					<picture>
						<source media="(min-width: 50em)" srcset="/images/home7.png">
						<img src="/images/home_mobile6.jpg" alt="Foto1">
					</picture>
					<div class="carousel-caption">
						<h3>Queda con quien quieras y donde quieras</h3>
						<p class="alert alert-primary">Con PlayTogether no importa donde te encuentres, siempre es buen momento para realizar deporte</p>
					</div>
				</div>
				<div class="carousel-item">
					<picture>
						<source media="(min-width: 50em)" srcset="/images/home14.png">
						<img src="/images/home_mobile4.jpg" alt="Foto2" >
					</picture>
					<div class="carousel-caption">
						<h3>Compite sin límites</h3>
						<p class="alert alert-primary">Disfruta de las quedadas o lánzate a competir en exigentes torneos</p>
					</div>
				</div>
				<div class="carousel-item">
					<picture>
						<source media="(min-width: 50em)" srcset="/images/home15.png">
						<img src="/images/home_mobile2.jpg" alt="Foto3">
					</picture>
					<div class="carousel-caption">
						<h3>Infinidad de actividades</h3>
						<p class="alert alert-primary">Ofrecemos un sinfín de actividades para que siempre encuentres gente con tus mismas aficiones</p>
					</div>
				</div>
				<div class="carousel-item">
					<picture>
						<source media="(min-width: 50em)" srcset="/images/home12.png">
						<img src="/images/home_mobile5.jpg" alt="Foto4">
					</picture>
					<div class="carousel-caption">
						<h3>¿Quieres aprovechar todo el potencial de PlayTogether?</h3>
						<p class="alert alert-primary">¡Suscríbete ya y disfruta de todas las ventajas de ser premium!</p>
					</div>
				</div>
				<div class="carousel-item">
					<picture>
						<source media="(min-width: 50em)" srcset="/images/home11.png">
						<img src="/images/home_mobile3.jpg" alt="Foto5">
					</picture>
					<div class="carousel-caption">
						<h3>Escala en nuestra clasificación participando en quedadas y torneos</h3>
						<p class="alert alert-primary">Brindamos a nuestros usuarios un sistema de clasificación por puntos para conocer a los mejores jugadores</p>
					</div>
				</div>
				<div class="carousel-item">
					<picture>
						<source media="(min-width: 50em)" srcset="/images/home6.png">				
						<img src="/images/home_mobile1.jpg" alt="Foto6">
					<picture>
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

</playtogether:layout>
