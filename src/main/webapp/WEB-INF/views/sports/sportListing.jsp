<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="sports">


	<style>
#cards_landscape_wrap-2 {
	text-align: center;
	background: #F7F7F7;
}

#cards_landscape_wrap-2 .container {
	padding-top: 80px;
	padding-bottom: 100px;
}

#cards_landscape_wrap-2 a {
	text-decoration: none;
	outline: none;
}

#cards_landscape_wrap-2 .card-flyer {
	border-radius: 5px;
}

#cards_landscape_wrap-2 .card-flyer .image-box {
	background: #ffffff;
	overflow: hidden;
	box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.50);
	border-radius: 5px;
}

#cards_landscape_wrap-2 .card-flyer .image-box img {
	-webkit-transition: all .9s ease;
	-moz-transition: all .9s ease;
	-o-transition: all .9s ease;
	-ms-transition: all .9s ease;
	width: 100%;
	height: 200px;
}

#cards_landscape_wrap-2 .card-flyer:hover .image-box img {
	opacity: 0.7;
	-webkit-transform: scale(1.15);
	-moz-transform: scale(1.15);
	-ms-transform: scale(1.15);
	-o-transform: scale(1.15);
	transform: scale(1.15);
}

#cards_landscape_wrap-2 .card-flyer .text-box {
	text-align: center;
}

#cards_landscape_wrap-2 .card-flyer .text-box .text-container {
	padding: 30px 18px;
}

#cards_landscape_wrap-2 .card-flyer {
	background: #FFFFFF;
	margin-top: 50px;
	-webkit-transition: all 0.2s ease-in;
	-moz-transition: all 0.2s ease-in;
	-ms-transition: all 0.2s ease-in;
	-o-transition: all 0.2s ease-in;
	transition: all 0.2s ease-in;
	box-shadow: 0px 3px 4px rgba(0, 0, 0, 0.40);
}

#cards_landscape_wrap-2 .card-flyer:hover {
	background: #fff;
	box-shadow: 0px 15px 26px rgba(0, 0, 0, 0.50);
	-webkit-transition: all 0.2s ease-in;
	-moz-transition: all 0.2s ease-in;
	-ms-transition: all 0.2s ease-in;
	-o-transition: all 0.2s ease-in;
	transition: all 0.2s ease-in;
	margin-top: 50px;
}

#cards_landscape_wrap-2 .card-flyer .text-box p {
	margin-top: 10px;
	margin-bottom: 0px;
	padding-bottom: 0px;
	font-size: 14px;
	letter-spacing: 1px;
	color: #000000;
}

#cards_landscape_wrap-2 .card-flyer .text-box h6 {
	margin-top: 0px;
	margin-bottom: 4px;
	font-size: 18px;
	font-weight: bold;
	text-transform: uppercase;
	font-family: 'Roboto Black', sans-serif;
	letter-spacing: 1px;
	color: #00acc1;
}
</style>

	<body>

		<div id="cards_landscape_wrap-2">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://www.mallorcadiario.com/fotos/1/418525_tenis.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/1/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>

										<spring:url value="/sports/1/championships"
											var="championshipUrl">
										</spring:url>
										<a href="${fn:escapeXml(championshipUrl)}"
											class="btn btn-outline-warning">Torneos</a>

										<spring:url value="/sports/1/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>



					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://static2.abc.es/media/bienestar/2019/09/17/futbol-1-kU3C--620x349@abc.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/2/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>

										<spring:url value="/sports/2/championships"
											var="championshipUrl">
										</spring:url>
										<a href="${fn:escapeXml(championshipUrl)}"
											class="btn btn-outline-warning">Torneos</a>

										<spring:url value="/sports/2/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>




					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://mundoentrenamiento.com/wp-content/uploads/2017/05/doble-penalti-en-futbol-sala-800x500.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/3/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>

										<spring:url value="/sports/3/championships"
											var="championshipUrl">
										</spring:url>
										<a href="${fn:escapeXml(championshipUrl)}"
											class="btn btn-outline-warning">Torneos</a>

										<spring:url value="/sports/3/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>



					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://www.bbva.com/wp-content/uploads/2017/08/bbva-balon-futbol-2017-08-11-1024x622.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/4/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>

										<spring:url value="/sports/4/championships"
											var="championshipUrl">
										</spring:url>
										<a href="${fn:escapeXml(championshipUrl)}"
											class="btn btn-outline-warning">Torneos</a>

										<spring:url value="/sports/4/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://estaticos-cdn.prensaiberica.es/clip/5f7e23a1-65fa-4220-86b7-d6139534d1fc_16-9-aspect-ratio_default_0.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/5/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>

										<spring:url value="/sports/5/championships"
											var="championshipUrl">
										</spring:url>
										<a href="${fn:escapeXml(championshipUrl)}"
											class="btn btn-outline-warning">Torneos</a>

										<spring:url value="/sports/5/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://www.laresistenciadelpalau.com/posts/ver-baloncesto-en-directo-gratis.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/6/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>

										<spring:url value="/sports/6/championships"
											var="championshipUrl">
										</spring:url>
										<a href="${fn:escapeXml(championshipUrl)}"
											class="btn btn-outline-warning">Torneos</a>

										<spring:url value="/sports/6/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img src="https://wallpaperaccess.com/full/480568.jpg" alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/7/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>



										<spring:url value="/sports/7/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://media.telemundochicago.com/2020/09/GettyImages-723511349.jpg?quality=85&strip=all&crop=5px%2C406px%2C5616px%2C3159px&resize=1200%2C675"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/8/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>

										<spring:url value="/sports/8/championships"
											var="championshipUrl">
										</spring:url>
										<a href="${fn:escapeXml(championshipUrl)}"
											class="btn btn-outline-warning">Torneos</a>

										<spring:url value="/sports/8/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://news.mondoiberica.com.es/wp-content/uploads/2018/11/sq.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/9/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>

										<spring:url value="/sports/9/championships"
											var="championshipUrl">
										</spring:url>
										<a href="${fn:escapeXml(championshipUrl)}"
											class="btn btn-outline-warning">Torneos</a>

										<spring:url value="/sports/9/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://www.topcomparativas.com/wp-content/uploads/2019/07/mancuernas_chico-571x292.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/10/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>



										<spring:url value="/sports/10/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://blogmedia.evbstatic.com/wp-content/uploads/wpmulti/sites/21/2019/05/centros-yoga-barcelona.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/11/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>



										<spring:url value="/sports/11/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://i.blogs.es/9d5334/26767480173_469a7ece33_z/450_1000.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/12/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>



										<spring:url value="/sports/12/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
					</div>

					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<a href="">
							<div class="card-flyer">
								<div class="text-box">
									<div class="image-box">
										<img
											src="https://as.com/masdeporte/imagenes/2020/01/16/balonmano/1579184369_779853_1579190374_noticia_normal.jpg"
											alt="" />
									</div>
									<div class="text-container">
										<spring:url value="/sports/13/meetings" var="meetingUrl">
										</spring:url>
										<a href="${fn:escapeXml(meetingUrl)}"
											class="btn btn-outline-primary">Quedadas</a>

										<spring:url value="/sports/13/championships"
											var="championshipUrl">
										</spring:url>
										<a href="${fn:escapeXml(championshipUrl)}"
											class="btn btn-outline-warning">Torneos</a>

										<spring:url value="/sports/13/statistics" var="statisticUrl">
										</spring:url>
										<a href="${fn:escapeXml(statisticUrl)}"
											class="btn btn-outline-danger">Clasificación</a>

									</div>
								</div>
							</div>
						</a>
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
