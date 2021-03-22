<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="sports">

	<body>

		<%-- <div class="sport_type_button">
			<spring:url value="/sports/1/meetings" var="groupsUrl">
			</spring:url>
			<a class="btn btn-1" href="${fn:escapeXml(groupsUrl)}">En grupo</a>
			<spring:url value="/sports/1/meetings" var="individualUrl">
			</spring:url>
			<a class="btn btn-2" href="${fn:escapeXml(individualUrl)}">Individual</a>
		</div> --%>

		<div id="cards_landscape_wrap-2">
			<div class="card">
				<div class="row">
					<c:forEach items="${sports}" var="sport">
						<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
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
											<!-- <div class="text-container"> -->
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

												<spring:url value="/sports/{sportId}/statistics"
													var="statisticUrl">
													<spring:param name="sportId" value="${sport.id}" />
												</spring:url>
												<a href="${fn:escapeXml(statisticUrl)}"
													class="custom-btn btn-4">Clasificación</a>

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
