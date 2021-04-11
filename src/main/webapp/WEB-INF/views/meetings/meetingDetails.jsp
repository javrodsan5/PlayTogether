<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>



<playtogether:layout pageName="meetings">

	<body>

		<div class="Card1Meeting">
			<div class="photo"
				style="background-image: url(/images/sportsImages/${sport.id}.jpg);"></div>
			<div class="description">
				<div class="line">
					<h1 class="product_name">
						<c:out value="${meeting.address}" />
					</h1>
					<h1 class="product_price">
						<c:out value="${meeting.city}" />
					</h1>

				</div>
				<h2>
					<c:out value="${meeting.date}" />
				</h2>
				<p class="summary">
					<c:out value="${meeting.description}" />
				</p>
			</div>
		</div>
		<div style="float: right; margin-right: 50px">
			<h2>
				Nº participantes:
				<c:out value="${meeting.participants.size()}" />
				/
				<c:out value="${meeting.numberOfPlayers}" />
			</h2>
			<div class="drop" style="float: right; margin-right: 50px">

				<div class="drop__container" id="drop-items">
					<div class="drop__card">
						<div class="drop__data">
							<c:forEach items="${meeting.participants}" var="participant">
								<div>
									<h2 class="drop__name">
										<spring:url
									value="/usuarios/{userId}"
									var="userdetails">
									<spring:param name="userId" value="${participant.id}" />

								</spring:url>
								
									
										<a style="margin-left: 60px;" href="${fn:escapeXml(userdetails)}"><span
											class="glyphicon glyphicon-user" aria-hidden="true">${participant.name}</span></a>
									</h2>


								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>

		<c:if test="${existe==false && estaLlena==false}">
			<spring:url value="/meetings/${meeting.id}/join" var="joinUrl">
			</spring:url>
			<a href="${fn:escapeXml(joinUrl)}" class="btn btn-danger">Participar</a>

		</c:if>

		<c:if test="${existe==true}">
			<p>¡Ya estás participando en esta quedada!</p>
		</c:if>
		<c:if test="${estaLlena==true}">
			<p>La quedada a la que intenta unirse está completa.</p>
		</c:if>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
	</body>

</playtogether:layout>
