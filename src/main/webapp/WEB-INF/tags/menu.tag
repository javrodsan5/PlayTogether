<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Opciones del menu: inicio, deportes,iniciar sesión"%>
<%@ attribute name="invitaciones" required="false" rtexprvalue="true"
	description="Opciones del menu: inicio, deportes,iniciar sesión"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">

<div class="navigation-wrap bg start-header">
	<nav class="navbar navbar-expand-md">

		<a class="navbar-brand"><img src="/images/logo_opt.png"
			alt="Logo app"></a><b><i class="title_logo">PlayTogether</i></b>



		<ul class="navbar-nav ml-auto" style="flex-direction: row">


			<li
				class="${name=='welcome' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active desktop' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 desktop'}">
				<a class="nav-link" href="/">Inicio <i class="fa fa-home"></i></a>
			</li>
			<li
				class="${name=='welcome' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active mobile' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 mobile'}">
				<a class="nav-link" href="/"> <i class="fa fa-home"></i></a>
			</li>
			<li
				class="${name=='sports' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active desktop' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 desktop'}">
				<a class="nav-link" href="/sports">Deportes <i
					class="fa fa-futbol-o"></i></a>
			</li>

			<li
				class="${name=='sports' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active mobile' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 mobile'}">
				<a class="nav-link" href="/sports"> <i class="fa fa-futbol-o"></i></a>
			</li>

			<sec:authorize access="!isAuthenticated()">
				<li
					class="${name=='login' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active desktop' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 desktop'}"><a
					class="nav-link" href="/login">Login <i class="fa fa-sign-in"></i></a></li>
				<li
					class="${name=='login' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active mobile' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 mobile'}"><a
					class="nav-link" href="/login"> <i class="fa fa-sign-in"></i></a></li>
				<li
					class="${name=='register' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active desktop' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 desktop'}">
					<a class="nav-link" href="/registro"> Registro <i
						class="fa fa-user-plus"></i></a>
				</li>
				<li
					class="${name=='register' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active mobile' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 mobile'}">
					<a class="nav-link" href="/registro"> <i
						class="fa fa-user-plus"></i></a>
				</li>
			</sec:authorize>
			<sec:authorize access="hasAuthority('premium')">
				<sec:authentication property="principal.username" var="principal" />

				<li
					class="${name=='clasifications' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active desktop' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 desktop'}">
					<a class="nav-link" href="/clasification">Clasificacion <i
						class="fa fa-sort-amount-asc"></i></a>
				</li>

				<li
					class="${name=='clasifications' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active mobile' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 mobile'}">
					<a class="nav-link" href="/clasification"><i
						class="fa fa-sort-amount-asc"></i></a>
				</li>

			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.username" var="principal" />
				<li
					class="${name=='chats' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active desktop' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 desktop'}">
					<a class="nav-link" href="/chats">Mis chats <i class="fa fa-weixin"></i></a>
				</li>

				<li
					class="${name=='chats' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active mobile' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 mobile'}">
					<a class="nav-link" href="/chats"><i class="fa fa-weixin"></i></a>
				</li>
				<c:if test="${invitaciones!=0}">
					<li
						class="${name=='profile' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active desktop' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 desktop'}">
						<a class="nav-link" href="/myprofile"> Perfil
							<div class="wrapperNotif">
								<div class="circulo">${invitaciones}</div>
							</div>
					</a>

					</li>
					<li
						class="${name=='profile' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active mobile' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 mobile'}">
						<a class="nav-link" href="/myprofile"><div class="perfilNotif">
								<i class="fa fa-envelope"></i>
							</div></a>
					</li>
				</c:if>
				<c:if test="${invitaciones==0}">
					<li
						class="${name=='profile' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active desktop' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 desktop'}">
						<a class="nav-link" href="/myprofile"> Perfil <i
							class="fa fa-user-circle"></i></a>

					</li>
					<li
						class="${name=='profile' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active mobile' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 mobile'}">
						<a class="nav-link" href="/myprofile"><i
							class="fa fa-user-circle"></i></a>
					</li>
				</c:if>
				<li
					class="${name=='logout' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active desktop' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 desktop'}">
					<a class="nav-link" href="/logout"> Logout <i
						class="fa fa-sign-out"></i></a>
				</li>
				<li
					class="${name=='logout' ? 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 active mobile' : 'nav-item pl-4 pl-md-0 ml-0 ml-md-4 mobile'}">
					<a class="nav-link" href="/logout"> <i class="fa fa-sign-out"></i></a>
				</li>
			</sec:authorize>

		</ul>

	</nav>
</div>