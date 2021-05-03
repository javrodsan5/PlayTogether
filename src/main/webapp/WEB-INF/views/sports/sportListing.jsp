<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="sports" invitaciones="${invitaciones}">

<head>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
</head>
	<body>
		<div id="cards_landscape_wrap-2">
			<div class="card">
				<div class="row">
					<c:forEach items="${sports}" var="sport">
						<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3 sports-general-container">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img src="/images/sportsImages/${sport.id}.jpg" alt="" />
										<div
											class="card-img-overlay text-white d-flex flex-column justify-content-center">
											<h4 class="card-title">
												<c:out value="${sport.name}" />
											</h4>
											<br> <br> <br> <br>
											<div class="frame">
												<spring:url value="/sports/{sportId}/meetings"
													var="meetingUrl">
													<spring:param name="sportId" value="${sport.id}" />
												</spring:url>
												<a href="${fn:escapeXml(meetingUrl)}"
													class="custom-btn btn-4">Quedadas</a>

												<c:if test="${sport.sportType.id == 1}">
													<spring:url value="/sports/{sportId}/championships"
														var="championshipUrl">
														<spring:param name="sportId" value="${sport.id}" />
													</spring:url>
													<a href="${fn:escapeXml(championshipUrl)}"
														class="custom-btn btn-4">Torneos</a>
												</c:if>

												<spring:url value="#" var="statisticUrl">
													<spring:param name="sportId" value="${sport.id}" />
												</spring:url>
												

											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</body>
</playtogether:layout>
