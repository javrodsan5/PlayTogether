<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="sports">

	<body>

		<div id="cards_landscape_wrap-2">
			<div class="card">
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://www.mallorcadiario.com/fotos/1/418525_tenis.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">

										<h4 class="card-title">Tenis</h4>
										<br> <br> <br> <br>

										<div class="text-container">
											<spring:url value="/sports/1/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/1/championships"
												var="championshipUrl">
											</spring:url>
											<a href="${fn:escapeXml(championshipUrl)}"
												class="btn btn-warning">Torneos</a>

											<spring:url value="/sports/1/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>

								</div>

							</div>

						</div>
					</div>



					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://static2.abc.es/media/bienestar/2019/09/17/futbol-1-kU3C--620x349@abc.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Fútbol 11</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/2/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/2/championships"
												var="championshipUrl">
											</spring:url>
											<a href="${fn:escapeXml(championshipUrl)}"
												class="btn btn-warning">Torneos</a>

											<spring:url value="/sports/2/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>




					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://canalshowsport.com.ar/wp-content/uploads/2020/10/futsal.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Fútbol sala</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/3/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/3/championships"
												var="championshipUrl">
											</spring:url>
											<a href="${fn:escapeXml(championshipUrl)}"
												class="btn btn-warning">Torneos</a>

											<spring:url value="/sports/3/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>



					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://www.bbva.com/wp-content/uploads/2017/08/bbva-balon-futbol-2017-08-11-1024x622.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Fútbol 7</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/4/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/4/championships"
												var="championshipUrl">
											</spring:url>
											<a href="${fn:escapeXml(championshipUrl)}"
												class="btn btn-warning">Torneos</a>

											<spring:url value="/sports/4/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://estaticos-cdn.prensaiberica.es/clip/5f7e23a1-65fa-4220-86b7-d6139534d1fc_16-9-aspect-ratio_default_0.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Pádel</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/5/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/5/championships"
												var="championshipUrl">
											</spring:url>
											<a href="${fn:escapeXml(championshipUrl)}"
												class="btn btn-warning">Torneos</a>

											<spring:url value="/sports/5/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://www.laresistenciadelpalau.com/posts/ver-baloncesto-en-directo-gratis.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Baloncesto</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/6/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/6/championships"
												var="championshipUrl">
											</spring:url>
											<a href="${fn:escapeXml(championshipUrl)}"
												class="btn btn-warning">Torneos</a>

											<spring:url value="/sports/6/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img src="https://wallpaperaccess.com/full/480568.jpg" alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Running</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/7/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>



											<spring:url value="/sports/7/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://media.telemundochicago.com/2020/09/GettyImages-723511349.jpg?quality=85&strip=all&crop=5px%2C406px%2C5616px%2C3159px&resize=1200%2C675"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Voleibol</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/8/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/8/championships"
												var="championshipUrl">
											</spring:url>
											<a href="${fn:escapeXml(championshipUrl)}"
												class="btn btn-warning">Torneos</a>

											<spring:url value="/sports/8/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://news.mondoiberica.com.es/wp-content/uploads/2018/11/sq.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Hockey</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/9/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/9/championships"
												var="championshipUrl">
											</spring:url>
											<a href="${fn:escapeXml(championshipUrl)}"
												class="btn btn-warning">Torneos</a>

											<spring:url value="/sports/9/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://www.topcomparativas.com/wp-content/uploads/2019/07/mancuernas_chico-571x292.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Gimnasio</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/10/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>



											<spring:url value="/sports/10/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://blogmedia.evbstatic.com/wp-content/uploads/wpmulti/sites/21/2019/05/centros-yoga-barcelona.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Yoga</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/11/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/11/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://i.blogs.es/9d5334/26767480173_469a7ece33_z/450_1000.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Senderismo</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/12/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>



											<spring:url value="/sports/12/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="card-flyer">
							<div class="text-box">
								<div class="image-box">
									<img
										src="https://as.com/masdeporte/imagenes/2020/01/16/balonmano/1579184369_779853_1579190374_noticia_normal.jpg"
										alt="" />
									<div
										class="card-img-overlay text-white d-flex flex-column justify-content-center">
										<h4 class="card-title">Balonmano</h4>
										<br> <br> <br> <br>
										<div class="text-container">
											<spring:url value="/sports/13/meetings" var="meetingUrl">
											</spring:url>
											<a href="${fn:escapeXml(meetingUrl)}" class="btn btn-primary">Quedadas</a>

											<spring:url value="/sports/13/championships"
												var="championshipUrl">
											</spring:url>
											<a href="${fn:escapeXml(championshipUrl)}"
												class="btn btn-warning">Torneos</a>

											<spring:url value="/sports/13/statistics" var="statisticUrl">
											</spring:url>
											<a href="${fn:escapeXml(statisticUrl)}"
												class="btn btn-danger">Clasificación</a>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<%-- <table id="sportsTable" class="table table-striped">
			<tbody>
				<c:forEach items="${sports}" var="sport">
					<tr>
						<td><c:out value="${sport.name}" /></td>

						<td><spring:url value="/sports/{sportId}/meetings"
								var="meetingUrl">
								<spring:param name="sportId" value="${sport.id}" />
							</spring:url> <a href="${fn:escapeXml(meetingUrl)}">Quedadas</a></td>

						<td><spring:url value="/sports/{sportId}/championships"
								var="championshipUrl">
								<spring:param name="sportId" value="${sport.id}" />
							</spring:url> <a href="${fn:escapeXml(championshipUrl)}">Torneos</a></td>

						<td><spring:url value="/sports/{sportId}/statistics"
								var="statisticUrl">
								<spring:param name="sportId" value="${sport.id}" />
							</spring:url> <a href="${fn:escapeXml(statisticUrl)}">Clasificación</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table> --%>
	</body>
</playtogether:layout>
