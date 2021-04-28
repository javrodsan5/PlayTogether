<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<playtogether:layout pageName="championships"
	invitaciones="${invitaciones}">
	<body>
		<div class="cardtitle">
			<h1>
				<strong>Partidos del torneo</strong>
			</h1>
			<br />
		</div>

		<c:if test="${noParticipa}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡No participas en el torneo para poder crear partidos!</p>
			</div>
		</c:if>
		<c:if test="${noParticipaDate}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡No participas en el partido para añadir una fecha de
					realización!</p>
			</div>
		</c:if>
		<c:if test="${faltaEquipos}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡Faltan equipos por unirse para generar la primera ronda de
					partidos!</p>
			</div>
		</c:if>
		<c:if test="${yagenerado}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡La primera ronda ya está generada!</p>
			</div>
		</c:if>
		<c:if test="${noprimera}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡Aún no se ha generado la primera ronda!</p>
			</div>
		</c:if>
		<c:if test="${faltaresultados}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡No se puede generar una ronda sin los resultados de la
					anterior!</p>
			</div>
		</c:if>

		<c:if test="${nocoinc}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡No se puede generar una ronda sin que los resultados de
					todos los partidos coincidan!</p>
			</div>
		</c:if>

		<c:if test="${yagenerada2}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡La segunda ronda ya está generada!</p>
			</div>
		</c:if>

		<c:if test="${yagenerada3}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡La tercera ronda ya está generada!</p>
			</div>
		</c:if>

		<c:if test="${yagenerada4}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡La cuarta ronda ya está generada!</p>
			</div>
		</c:if>

		<c:if test="${nosegunda}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡Aún no se ha generado la segunda ronda!</p>
			</div>
		</c:if>
		<c:if test="${notercera}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡Aún no se ha generado la tercera ronda!</p>
			</div>
		</c:if>
		<c:if test="${noUser}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>No se encontró al usuario deseado.</p>
			</div>
		</c:if>
		<c:if test="${noTeam}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡No perteneces a este equipo!</p>
			</div>
		</c:if>
		<c:if test="${faltaParticipantes}">
			<div class="alert alert-danger" style="margin: 1% 20% 1% 20%">
				<p>¡Los equipos no están completos (Faltan jugadores)!</p>
			</div>
		</c:if>


		<c:if test="${nombreGanador != null}">
			<div class="alert alert-success" style="margin: 1% 20% 1% 20%">
				<h1>
					<b> ¡ <c:out value="${nombreGanador}" /> ha ganado el torneo!
						¡Enhorabuena!
					</b>
				</h1>
			</div>
		</c:if>


		<c:if test="${championshipObj.maxTeams==4}">
			<div class="container">
				<div class="tournament-bracket tournament-bracket--rounded">
					<div
						class="tournament-bracket__round tournament-bracket__round--semifinals">
						<h3 class="tournament-bracket__round-title alert alert-primary">Semifinales</h3>
						<ul class="tournament-bracket__list">
							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()==0}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[0].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
											</c:if>
											<fmt:parseDate value="${matches[0].dateTime }"
												pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime0"
												type="both" />
											<fmt:formatDate value="${parsedDateTime0}"
												pattern="dd-MM-yyyy HH:mm" />
										</caption>

										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[0].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[0].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[0].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[0].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[0].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[0].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[0].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[0].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>

							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()==0}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[1].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
											</c:if>
											<fmt:parseDate value="${matches[1].dateTime }"
												pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime1"
												type="both" />
											<fmt:formatDate value="${parsedDateTime1}"
												pattern="dd-MM-yyyy HH:mm" />
										</caption>

										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[1].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[1].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[1].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[1].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[1].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[1].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[1].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[1].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>


						</ul>
					</div>

					<div
						class="tournament-bracket__round tournament-bracket__round--gold">
						<h3 class="tournament-bracket__round-title alert alert-primary">Final</h3>
						<ul class="tournament-bracket__list">
							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()<=2}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()>2}">
												<c:if test="${empty matches[2].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
												<fmt:parseDate value="${matches[2].dateTime }"
													pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime2"
													type="both" />
												<fmt:formatDate value="${parsedDateTime2}"
													pattern="dd-MM-yyyy HH:mm" />
											</c:if>

										</caption>
										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[2].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[2].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[2].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[2].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[2].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[2].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[2].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[2].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>
						</ul>
					</div>
					<div
						class="tournament-bracket__round tournament-bracket__round--gold"
						style="display: none"></div>
				</div>
			</div>
		</c:if>
		<c:if test="${championshipObj.maxTeams==8}">
			<br>
			<div class="container">
				<div class="tournament-bracket tournament-bracket--rounded">
					<div
						class="tournament-bracket__round tournament-bracket__round--quarterfinals">
						<h3 class="tournament-bracket__round-title alert alert-primary">Cuartos
							de final</h3>
						<ul class="tournament-bracket__list">
							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()==0}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[0].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
												<fmt:parseDate value="${matches[0].dateTime }"
													pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime0"
													type="both" />
												<fmt:formatDate value="${parsedDateTime0}"
													pattern="dd-MM-yyyy HH:mm" />
											</c:if>
										</caption>
										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[0].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[0].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[0].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[0].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[0].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[0].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[0].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[0].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>

							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()==0}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[1].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
												<fmt:parseDate value="${matches[1].dateTime }"
													pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime1"
													type="both" />
												<fmt:formatDate value="${parsedDateTime1}"
													pattern="dd-MM-yyyy HH:mm" />
											</c:if>
										</caption>
										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[1].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[1].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[1].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[1].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[1].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[1].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[1].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[1].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>
							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()==0}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[2].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
												<fmt:parseDate value="${matches[2].dateTime }"
													pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime2"
													type="both" />
												<fmt:formatDate value="${parsedDateTime2}"
													pattern="dd-MM-yyyy HH:mm" />
											</c:if>
										</caption>
										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[2].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[2].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[2].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[2].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[2].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[2].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[2].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[2].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>

							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()==0}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[3].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
												<fmt:parseDate value="${matches[3].dateTime }"
													pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime3"
													type="both" />
												<fmt:formatDate value="${parsedDateTime3}"
													pattern="dd-MM-yyyy HH:mm" />
											</c:if>
										</caption>
										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[3].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[3].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[3].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[3].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[3].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[3].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[3].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[3].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>
						</ul>
					</div>
					<div
						class="tournament-bracket__round tournament-bracket__round--semifinals">
						<h3 class="tournament-bracket__round-title alert alert-primary">Semifinales</h3>
						<ul class="tournament-bracket__list">
							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()<=4}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()>4}">
												<c:if test="${empty matches[4].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
												<fmt:parseDate value="${matches[4].dateTime }"
													pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime4"
													type="both" />
												<fmt:formatDate value="${parsedDateTime4}"
													pattern="dd-MM-yyyy HH:mm" />
											</c:if>
										</caption>

										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[4].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[4].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[4].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[4].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[4].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[4].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[4].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[4].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>

							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()<=4}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()>4}">
												<c:if test="${empty matches[5].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
												<fmt:parseDate value="${matches[5].dateTime }"
													pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime5"
													type="both" />
												<fmt:formatDate value="${parsedDateTime5}"
													pattern="dd-MM-yyyy HH:mm" />
											</c:if>
										</caption>

										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[5].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[5].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[5].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[5].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[5].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[5].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[5].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[5].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>


						</ul>
					</div>

					<div
						class="tournament-bracket__round tournament-bracket__round--gold">
						<h3 class="tournament-bracket__round-title alert alert-primary">Final</h3>
						<ul class="tournament-bracket__list">
							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()<=6}">
												<div class="alert alert-danger"
													style="margin: 1% 20% 1% 20%">No existe partido aún.
												</div>
											</c:if>
											<c:if test="${matches.size()>6}">
												<c:if test="${empty matches[6].dateTime}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">Todavía no se ha
														fijado fecha para el partido.</div>
												</c:if>
												<fmt:parseDate value="${matches[6].dateTime }"
													pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime6"
													type="both" />
												<fmt:formatDate value="${parsedDateTime6}"
													pattern="dd-MM-yyyy HH:mm" />
											</c:if>

										</caption>
										<thead class="sr-only">
											<tr>
												<th>Equipo</th>
												<th>Puntuación</th>
											</tr>
										</thead>
										<tbody class="tournament-bracket__content">
											<tr class="tournament-bracket__team">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">
														${matches[6].team1} </abbr> <span
													class="tournament-bracket__flag flag-icon flag-icon-ca"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[6].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[6].puntos1}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[6].puntos1}</span></td>
												</c:if>

											</tr>
											<tr
												class="tournament-bracket__team tournament-bracket__team--winner">
												<td class="tournament-bracket__country"><abbr
													class="tournament-bracket__code">${matches[6].team2}</abbr>
													<span
													class="tournament-bracket__flag flag-icon flag-icon-cz"
													aria-label="Flag"></span></td>
												<c:if test="${empty matches[6].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">-</span></td>
												</c:if>
												<c:if test="${not empty matches[6].puntos2}">
													<td class="tournament-bracket__score"><span
														class="tournament-bracket__number">${matches[6].puntos2}</span></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
							</li>
						</ul>
					</div>
					<div
						class="tournament-bracket__round tournament-bracket__round--gold"
						style="display: none"></div>
				</div>
			</div>
		</c:if>

		<c:if test="${championshipObj.maxTeams==16}">
			<br>
			<div class="container">

				<div class="scroll_vertical" id="style_scroll"
					style="width: 100%; height: 35em">
					<div class="tournament-bracket tournament-bracket--rounded">

						<div
							class="tournament-bracket__round tournament-bracket__round--roundOf16">
							<h3 class="tournament-bracket__round-title alert alert-primary">Octavos
								de final</h3>
							<ul class="tournament-bracket__list">
								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()==0}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()!=0}">
													<c:if test="${empty matches[0].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[0].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime0"
														type="both" />
													<fmt:formatDate value="${parsedDateTime0}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[0].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[0].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[0].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[0].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[0].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[0].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[0].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[0].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>

								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()==0}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()!=0}">
													<c:if test="${empty matches[1].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[1].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime1"
														type="both" />
													<fmt:formatDate value="${parsedDateTime1}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[1].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[1].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[1].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[1].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[1].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[1].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[1].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[1].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>
								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()==0}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()!=0}">
													<c:if test="${empty matches[2].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[2].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime2"
														type="both" />
													<fmt:formatDate value="${parsedDateTime2}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[2].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[2].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[2].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[2].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[2].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[2].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[2].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[2].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>

								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()==0}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()!=0}">
													<c:if test="${empty matches[3].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[3].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime3"
														type="both" />
													<fmt:formatDate value="${parsedDateTime3}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[3].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[3].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[3].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[3].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[3].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[3].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[3].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[3].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>

								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()==0}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()!=0}">
													<c:if test="${empty matches[4].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[4].dateTime}"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime4"
														type="both" />
													<fmt:formatDate value="${parsedDateTime4}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[4].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[4].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[4].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[4].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[4].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[4].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[4].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[4].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>
								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()==0}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()!=0}">
													<c:if test="${empty matches[5].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[5].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime5"
														type="both" />
													<fmt:formatDate value="${parsedDateTime5}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[5].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[5].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[5].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[5].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[5].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[5].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[5].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[5].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>
								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()==0}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()!=0}">
													<c:if test="${empty matches[6].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[6].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime6"
														type="both" />
													<fmt:formatDate value="${parsedDateTime6}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[6].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[6].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[6].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[6].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[6].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[6].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[6].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[6].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>
								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()==0}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()!=0}">
													<c:if test="${empty matches[7].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[7].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime7"
														type="both" />
													<fmt:formatDate value="${parsedDateTime7}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[7].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[7].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[7].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[7].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[7].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[7].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[7].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[7].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>

							</ul>
						</div>

						<div
							class="tournament-bracket__round tournament-bracket__round--quarterfinals">
							<h3 class="tournament-bracket__round-title alert alert-primary">Cuartos
								de final</h3>
							<ul class="tournament-bracket__list">
								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()<=8}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()>8}">
													<c:if test="${empty matches[8].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[8].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime8"
														type="both" />
													<fmt:formatDate value="${parsedDateTime8}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[8].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[8].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[8].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[8].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[8].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[8].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[8].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[8].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>

								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()<=8}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()>8}">
													<c:if test="${empty matches[9].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[9].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime9"
														type="both" />
													<fmt:formatDate value="${parsedDateTime9}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[9].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[9].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[9].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[9].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[9].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[9].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[9].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[9].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>
								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()<=8}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()>8}">
													<c:if test="${empty matches[10].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[10].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime10"
														type="both" />
													<fmt:formatDate value="${parsedDateTime10}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[10].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[10].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[10].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[10].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[10].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[10].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[10].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[10].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>

								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()<=8}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()>8}">
													<c:if test="${empty matches[11].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[11].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime11"
														type="both" />
													<fmt:formatDate value="${parsedDateTime11}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[11].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[11].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[11].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[11].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[11].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[11].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[11].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[11].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>

							</ul>
						</div>
						<div
							class="tournament-bracket__round tournament-bracket__round--semifinals">
							<h3 class="tournament-bracket__round-title alert alert-primary">Semifinales</h3>
							<ul class="tournament-bracket__list">
								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()<=12}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()>12}">
													<c:if test="${empty matches[12].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[12].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime12"
														type="both" />
													<fmt:formatDate value="${parsedDateTime12}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[12].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[12].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[12].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[12].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[12].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[12].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[12].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[12].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>


								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()<=12}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()>12}">
													<c:if test="${empty matches[13].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[13].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime13"
														type="both" />
													<fmt:formatDate value="${parsedDateTime13}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>
											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[13].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[13].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[13].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[13].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[13].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[13].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[13].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[13].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>
							</ul>
						</div>

						<div
							class="tournament-bracket__round tournament-bracket__round--gold">
							<h3 class="tournament-bracket__round-title alert alert-primary">Final</h3>
							<ul class="tournament-bracket__list">
								<li class="tournament-bracket__item">
									<div class="tournament-bracket__match" tabindex="0">
										<table class="tournament-bracket__table">
											<caption class="tournament-bracket__caption">
												<c:if test="${matches.size()<=14}">
													<div class="alert alert-danger"
														style="margin: 1% 20% 1% 20%">No existe partido aún.</div>
												</c:if>
												<c:if test="${matches.size()>14}">
													<c:if test="${empty matches[14].dateTime}">
														<div class="alert alert-danger"
															style="margin: 1% 20% 1% 20%">Todavía no se ha
															fijado fecha para el partido.</div>
													</c:if>
													<fmt:parseDate value="${matches[14].dateTime }"
														pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime14"
														type="both" />
													<fmt:formatDate value="${parsedDateTime14}"
														pattern="dd-MM-yyyy HH:mm" />
												</c:if>

											</caption>
											<thead class="sr-only">
												<tr>
													<th>Equipo</th>
													<th>Puntuación</th>
												</tr>
											</thead>
											<tbody class="tournament-bracket__content">
												<tr class="tournament-bracket__team">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">
															${matches[14].team1} </abbr> <span
														class="tournament-bracket__flag flag-icon flag-icon-ca"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[14].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[14].puntos1}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[14].puntos1}</span></td>
													</c:if>

												</tr>
												<tr
													class="tournament-bracket__team tournament-bracket__team--winner">
													<td class="tournament-bracket__country"><abbr
														class="tournament-bracket__code">${matches[14].team2}</abbr>
														<span
														class="tournament-bracket__flag flag-icon flag-icon-cz"
														aria-label="Flag"></span></td>
													<c:if test="${empty matches[14].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">-</span></td>
													</c:if>
													<c:if test="${not empty matches[14].puntos2}">
														<td class="tournament-bracket__score"><span
															class="tournament-bracket__number">${matches[14].puntos2}</span></td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
								</li>
							</ul>
						</div>
						<div
							class="tournament-bracket__round tournament-bracket__round--gold"
							style="display: none"></div>
					</div>
				</div>
			</div>
		</c:if>


		<br>
		<br>

		<div class="cardlist" align="center" style="margin-top: 5%">
			<c:if test="${championshipObj.matches.size() > 0 }">
				<center>
					<h2 class="alert alert-info"
						style="text-align: center; font-size: 20px; width: 90%;">
						<i class="fa fa-info-circle"></i> Para que el resultado del
						partido se considere válido, deben coincidir los resultados
						aportados por cada equipo
					</h2>
				</center>

				<div class="scroll_vertical" id="style_scroll">
					<table id="matchTable" class="table">
						<thead style="text-align: center">
							<tr class="rowtable">
								<th style="width: 15%;">Fecha y hora</th>
								<th style="width: 20%;">Equipo local</th>
								<th style="width: 15%;">Resultado (Según equipo local)</th>
								<th style="width: 20%;">Equipo visitante</th>
								<th style="width: 20%;">Resultado (Según equipo visitante)</th>
								<th style="width: 5%;">Añadir/editar fecha</th>
								<th style="width: 5%;">Añadir/editar resultado</th>
							</tr>
						</thead>

						<tbody style="text-align: center">
							<c:forEach items="${matches}" var="match">
								<tr class="rowtable">

									<td><fmt:parseDate value="${match.dateTime }"
											pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<fmt:formatDate value="${parsedDateTime}"
											pattern="dd-MM-yyyy HH:mm" /></td>
									<td><c:out value="${match.team1.name}" /></td>
									<td><c:out value="${match.puntos1} - ${match.puntos2}" /></td>
									<td><c:out value="${match.team2.name}" /></td>
									<td><c:out value="${match.puntos3} - ${match.puntos4}" /></td>

									<td><c:if test="${match.dateTime == null}">
											<c:if
												test="${match.team1.participants.contains(usuarioLog) || match.team2.participants.contains(usuarioLog)}">
												<c:if test="${match.ronda==rondaActual}">
													<spring:url
														value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/date"
														var="date2Url">
														<spring:param name="championshipId"
															value="${championship}" />
														<spring:param name="deporte" value="${deporte}" />
														<spring:param name="matchId" value="${match.id}" />
													</spring:url>
													<div class="botoncitores1">
														<a href="${fn:escapeXml(date2Url)}"><i
															style="font-size: 33px" class="fa fa-plus-circle"
															aria-hidden="true"></i></a>
													</div>
												</c:if>
											</c:if>
										</c:if> <c:if test="${match.dateTime != null }">
											<c:if
												test="${match.team1.participants.contains(usuarioLog) || match.team2.participants.contains(usuarioLog)}">
												<c:if test="${match.ronda==rondaActual}">
													<spring:url
														value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/date"
														var="date2Url">
														<spring:param name="championshipId"
															value="${championship}" />
														<spring:param name="deporte" value="${deporte}" />
														<spring:param name="matchId" value="${match.id}" />
													</spring:url>
													<div class="botoncitores1">
														<a href="${fn:escapeXml(date2Url)}"><i
															style="font-size: 33px" class="fa fa-pencil-square"
															aria-hidden="true"></i></a>
													</div>
												</c:if>
											</c:if>
										</c:if></td>
									<td><c:if test="${match.dateTime != null}">
											<c:if test="${match.ronda==rondaActual}">
												<c:if
													test="${match.team1.participants.contains(usuarioLog)}">
													<spring:url
														value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/result/{team}"
														var="result2Url">
														<spring:param name="championshipId"
															value="${championship}" />
														<spring:param name="deporte" value="${deporte}" />
														<spring:param name="matchId" value="${match.id}" />
														<spring:param name="team" value="team1" />
													</spring:url>
													<div class="botoncitores1">
														<a href="${fn:escapeXml(result2Url)}"><i
															style="font-size: 33px; display: inline;"
															class="fa fa-pencil-square-o" aria-hidden="true"></i><i
															class="fa fa-home"
															style="font-size: 33px; display: inline;"
															aria-hidden="true"></i> </a>
													</div>
												</c:if>
											</c:if>
										</c:if> <c:if test="${match.team2.participants.contains(usuarioLog)}">
											<spring:url
												value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/result/{team}"
												var="result2Url">
												<spring:param name="championshipId" value="${championship}" />
												<spring:param name="deporte" value="${deporte}" />
												<spring:param name="matchId" value="${match.id}" />
												<spring:param name="team" value="team2" />
											</spring:url>

											<div class="botoncitores2">
												<a href="${fn:escapeXml(result2Url)}"><i
													style="font-size: 33px; display: inline;"
													class="fa fa-pencil-square-o" aria-hidden="true"></i><i
													style="font-size: 33px; display: inline;"
													class="fa fa-plane" aria-hidden="true"></i></a>
											</div>
										</c:if>
								</tr>
								<div></div>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</div>

		<c:if test="${championshipObj.matches.size() == 0 }">

			<spring:url
				value="/sports/{deporte}/championships/{championshipId}/match/generate1"
				var="dateUrl">
				<spring:param name="deporte" value="${deporte}" />
				<spring:param name="championshipId" value="${championship}" />
			</spring:url>
			<div class="botoncito-crear-meeting">
				<a id="createMatch" href="${fn:escapeXml(dateUrl)}">Generar
					primera ronda partidos</a>
			</div>

		</c:if>

		<c:if
			test="${  championshipObj.maxTeams == 4 && championshipObj.matches.size() ==2 || 
 					championshipObj.maxTeams == 8 && championshipObj.matches.size() ==4 ||
 					championshipObj.maxTeams == 16 && championshipObj.matches.size() == 8   }">

			<spring:url
				value="/sports/{deporte}/championships/{championshipId}/match/generate2"
				var="dateUrl">
				<spring:param name="deporte" value="${deporte}" />
				<spring:param name="championshipId" value="${championship}" />
			</spring:url>
			<div class="botoncito-crear-meeting">
				<a id="createMatch" href="${fn:escapeXml(dateUrl)}">Generar
					segunda ronda partidos</a>
			</div>

		</c:if>

		<c:if test="${championshipObj.maxTeams>4}">
			<c:if
				test="${	
 					championshipObj.maxTeams == 8 && championshipObj.matches.size() == 6 ||
 					championshipObj.maxTeams == 16 && championshipObj.matches.size() == 12	}">
				<spring:url
					value="/sports/{deporte}/championships/{championshipId}/match/generate3"
					var="dateUrl">
					<spring:param name="deporte" value="${deporte}" />
					<spring:param name="championshipId" value="${championship}" />
				</spring:url>
				<div class="botoncito-crear-meeting">
					<a id="createMatch" href="${fn:escapeXml(dateUrl)}">Generar
						tercera ronda partidos</a>
				</div>
			</c:if>

			<c:if test="${championshipObj.maxTeams>8}">
				<c:if
					test="${	championshipObj.maxTeams == 16 && championshipObj.matches.size() == 14	}">
					<spring:url
						value="/sports/{deporte}/championships/{championshipId}/match/generate4"
						var="dateUrl">
						<spring:param name="deporte" value="${deporte}" />
						<spring:param name="championshipId" value="${championship}" />
					</spring:url>
					<div class="botoncito-crear-meeting">
						<a id="createMatch" href="${fn:escapeXml(dateUrl)}">Generar
							cuarta ronda partidos</a>
					</div>
				</c:if>
			</c:if>
		</c:if>





		<div class="form-group">

			<button class="botonMeeting"
				onclick="location.href='/sports/${deporte}/championships/${championship}';"
				type="button">
				<b>Volver a torneo</b>
			</button>

		</div>
	</body>
	</html>
</playtogether:layout>