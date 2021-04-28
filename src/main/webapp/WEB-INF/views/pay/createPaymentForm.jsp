<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<playtogether:layout pageName="championships" invitaciones="${invitaciones}">

	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
		rel="stylesheet" />
	<div class="thirteen">
		<c:if test="${newChampionship==true}">
			<h1>Pago por crear torneo</h1>
		</c:if>
		<c:if test="${newTeam == true}">
			<h1>Pago para inscripción en ${teamName}</h1>
		</c:if>
		<c:if test="${premium==true}">
			<h1>Pago para suscripción en plan premium</h1>
			<br>
			<br>
			<center>
				<h4>¿Conoces las ventajas de nuestro plan premium?</h4>
			</center>

		</c:if>
	</div>

	<br>
	<br>
	<center>
		<c:if test="${payCham==true}">

			<div class="Card1Meeting">
				<div class="photo"
					style="background-image: url(/images/trophies.png);"></div>
				<div class="description" style="font-size: 20px">
					<h2 style="color: black; font-weight: bold;">${order.price}${order.currency}</h2>
					<br>
					<h2 style="color: black; text-transform: none;">${order.description}</h2>
				</div>
			</div>
		</c:if>
		<c:if test="${premium==true}">
			<div class="table-container">
				<div class="tableWIV">
					<div class="table-headWIV">
						<div class="columnWIV blank end">&nbsp;</div>
						<div class="columnWIV">&nbsp;</div>
						<div class="columnWIV title">Usuario básico</div>
						<div class="columnWIV title light wiivv">Usuario premium</div>
						<div class="columnWIV blank end">&nbsp;</div>
					</div>
					<div class="rowWIV">
						<div class="columnWIV light blank end">&nbsp;</div>
						<div class="columnWIV light col-title">
							<p>Precio</p>
						</div>
						<div class="columnWIV light pricing">Gratuito</div>
						<div class="columnWIV light pricing text--green">5€/mes</div>
						<div class="columnWIV light blank end">&nbsp;</div>
					</div>
					<div class="rowWIV">
						<div class="columnWIV light blank end">&nbsp;</div>
						<div class="columnWIV light col-title">
							<p>Crear actividades ilimitadas</p>
						</div>
						<div class="columnWIV light blank">
							<i class="fa fa-times cancel"></i>

						</div>
						<div class="columnWIV dark">
							<i class="material-icons text--green">check</i>
						</div>
						<div class="columnWIV light blank"></div>
						<div class="columnWIV light blank end">&nbsp;</div>
					</div>
					<div class="rowWIV">
						<div class="columnWIV light blank end">&nbsp;</div>
						<div class="columnWIV light col-title">
							<p>Crear y unirse a torneos gratuitamente</p>
						</div>
						<div class="columnWIV light blank">
							<i class="fa fa-times cancel"></i>

						</div>
						<div class="columnWIV dark">
							<i class="material-icons text--green">check</i>
						</div>
						<div class="columnWIV light blank"></div>
						<div class="columnWIV light blank end">&nbsp;</div>
					</div>
					<div class="rowWIV">
						<div class="columnWIV light blank end">&nbsp;</div>
						<div class="columnWIV light col-title">
							<p>Invitar usuarios a tu equipo o quedada</p>
						</div>
						<div class="columnWIV light blank">
							<i class="fa fa-times cancel"></i>
						</div>
						<div class="columnWIV light">
							<i class="material-icons text--green">check</i>
						</div>
						<div class="columnWIV light blank"></div>
						<div class="columnWIV light blank end">&nbsp;</div>
					</div>
					<div class="rowWIV">
						<div class="columnWIV light blank end">&nbsp;</div>
						<div class="columnWIV light col-title">
							<p>Aceptar/rechazar usuarios en tu actividad</p>
						</div>
						<div class="columnWIV light blank">
							<i class="fa fa-times cancel"></i>
						</div>
						<div class="columnWIV dark">
							<i class="material-icons text--green">check</i>
						</div>
						<div class="columnWIV light blank"></div>
						<div class="columnWIV light blank end">&nbsp;</div>
					</div>
					<div class="rowWIV">
						<div class="columnWIV light blank end">&nbsp;</div>
						<div class="columnWIV light col-title">
							<p>Participar y ver la clasificación de deportes</p>
						</div>
						<div class="columnWIV light blank">
							<i class="fa fa-times cancel"></i>
						</div>
						<div class="columnWIV light wiivv--last">
							<i class="material-icons text--green">check</i>
						</div>
						<div class="columnWIV light blank"></div>
						<div class="columnWIV light blank end">&nbsp;</div>
					</div>
				</div>
			</div>
		</c:if>
		<div style="margin-left: 60%">
			<div class="crearMeeting">
				<form:form style="background-color: white" commandName="order"
					modelAttribute="order" method="post"
					action="/pay?championshipId=${championshipId}&teamId=${teamId}&teamName=${teamName}&newChampionship=${newChampionship}&invitationId=${invitationId}&isInvitation=${isInvitation}">
					<div>
						<form:input type="hidden" path="price" />
						<form:input type="hidden" path="currency" />
						<form:input type="hidden" path="method" />
						<form:input type="hidden" path="intent" />
						<form:input type="hidden" path="description" />

						<div class="form-group">
							<button class="boton" value="Pagar" type="submit">
								<img width="146px" height="36px" src="/images/logoPayPal.png"
									alt="PayPalLOGO">

							</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
				<div class="cardbutton" style="padding-top:10px;">
			<spring:url value="/sports" var="dateUrl">
			</spring:url>
			<div class="botoncito-crear-meeting">
				<a id="createMeeting" href="${fn:escapeXml(dateUrl)}">Volver a la lista</a>
			</div>
		</div>
	</center>

</playtogether:layout>