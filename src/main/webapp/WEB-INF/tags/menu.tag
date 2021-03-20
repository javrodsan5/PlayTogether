<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Opciones del menu: inicio, deportes,iniciar sesión"%>

<style>
</style>


<nav class="navbar navbar-expand-lg navbar-light bg-info">
	<div class="container">
		<a href="#" class="navbar-brand mb-0 h1">PlayTogether</a>
		<!--<span class="navbar-brand mb-0 h1"> Meu navbar</span>-->
		<!--<a href="#" class="navbar-brand">
                <img src="assets/star.svg" width="30px" height="30px" class="d-inline-block align-top mr-2" alt="">
                Meu Navbar
            </a>-->

		<button type="button" data-toggle="collapse" data-target="#meuNavbar"
			class="navbar-toggler">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="meuNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a href="#" class="nav-link active">Inicio</a>
				</li>
				<li class="nav-item"><a href="#" class="nav-link active">Deportes</a></li>
				<li class="nav-item"><a href="#" class="nav-link">Regístrate</a>
				</li>
				<li class="nav-item"><a href="#" class="nav-link">Iniciar sesión</a>
				</li>
			</ul>
		</div>
	</div>

</nav>
