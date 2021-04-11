<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>



<playtogether:layout pageName="users">

	<div class="thirteen">
		<h1>Datos de mi perfil</h1>
	</div>

	<body>
		<div class="body-container">

			<div class="dashboard">
				<div class="grid-container">
					<div class="profile grid-area">
						<div class="img">
							<img src="/images/avatar.png">

							<h3>
								<c:out value="${user.name}" />
							</h3>
							<h5 class="username">
								<c:out value="${user.user.username}" />
							</h5>
							<div class="button">
								<i class="fa fa-pencil"></i>
							</div>
						</div>
						<div class="profile-data">
							<div class="data-details">
								<h5>Fecha de nacimiento</h5>
								<h4>
									<c:out value="${user.birthdate}" />
								</h4>
							</div>
							<div class="data-details">
								<h5>Correo electrónico</h5>
								<h4>
									<c:out value="${user.correo}"></c:out>
								</h4>

							</div>
						</div>
						<div class="profile-data">
							<div class="data-details">
								<h5>Teléfono</h5>
								<h4>
									<c:out value="${user.phone}" />
								</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
	</body>

</playtogether:layout>
