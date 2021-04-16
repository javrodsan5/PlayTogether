<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="championships">
	<body>
		<div class="cardtitle">
			<h1>
				<strong>Partidos del torneo</strong>
			</h1>
			<br />
		</div>

		<c:if test="${nombreGanador != null}">
			<h1>
				¡
				<c:out value="${nombreGanador}" />
				ha ganado el torneo! ¡Enhorabuena!
			</h1>
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[0].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
											</c:if>
											${matches[0].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[1].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
											</c:if>
											${matches[1].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>2}">
												<c:if test="${empty matches[2].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[2].dateTime}
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
			<div class="container" style="margin-left: 4%">
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[0].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[0].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[1].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[1].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[2].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[2].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[3].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[3].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>4}">
												<c:if test="${empty matches[4].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[4].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>4}">
												<c:if test="${empty matches[5].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[5].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>6}">
												<c:if test="${empty matches[6].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[6].dateTime}
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
			<div class="container" style="margin-left: 4%">
				<div class="tournament-bracket tournament-bracket--rounded">

					<div
						class="tournament-bracket__round tournament-bracket__round--roundOf16">
						<h3 class="tournament-bracket__round-title alert alert-primary">Octavos
							de final de final</h3>
						<ul class="tournament-bracket__list">
							<li class="tournament-bracket__item">
								<div class="tournament-bracket__match" tabindex="0">
									<table class="tournament-bracket__table">
										<caption class="tournament-bracket__caption">
											<c:if test="${matches.size()==0}">
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[0].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[0].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[1].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[1].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[2].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[2].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[3].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[3].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[4].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[4].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[5].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[5].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[6].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[6].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()!=0}">
												<c:if test="${empty matches[7].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[7].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>8}">
												<c:if test="${empty matches[8].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[8].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>8}">
												<c:if test="${empty matches[9].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[9].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>8}">
												<c:if test="${empty matches[10].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[10].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>8}">
												<c:if test="${empty matches[11].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[11].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>12}">
												<c:if test="${empty matches[12].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[12].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>12}">
												<c:if test="${empty matches[13].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[13].dateTime}
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
												No existe partido aún.
												</c:if>
											<c:if test="${matches.size()>14}">
												<c:if test="${empty matches[14].dateTime}">
												Todavía no se ha fijado fecha para el partido. 
												</c:if>
												${matches[14].dateTime}
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
		</c:if>


		<br>
		<br>
		<div class="cardlist">

			<table id="matchTable" class="table">
				<thead>
					<tr class="rowtable">
						<th style="width: 10%;">Fecha y hora</th>
						<th style="width: 10%;">Primer equipo</th>
						<th style="width: 15%;">Puntos 1º equipo (Anotados por 1º
							equipo)</th>
						<th style="width: 15%;">Puntos 2º equipo (Anotados por 1º
							equipo)</th>
						<th style="width: 10%;">Segundo equipo</th>
						<th style="width: 15%;">Puntos 1º equipo (Anotados por 2º
							equipo)</th>
						<th style="width: 15%;">Puntos 2º equipo (Anotados por 2º
							equipo)</th>

					</tr>
				</thead>

				<tbody>
					<c:forEach items="${matches}" var="match">
						<tr class="rowtable">
							<td><c:out value="${match.dateTime}" /></td>
							<td><c:out value="${match.team1.name}" /></td>
							<td><c:out value="${match.puntos1}" /></td>
							<td><c:out value="${match.puntos2}" /></td>
							<td><c:out value="${match.team2.name}" /></td>
							<td><c:out value="${match.puntos3}" /></td>
							<td><c:out value="${match.puntos4}" /></td>

							<td><c:if test="${match.dateTime == null}">
									<c:if test="${match.ronda==rondaActual}">
										<spring:url
											value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/date"
											var="date2Url">
											<spring:param name="championshipId" value="${championship}" />
											<spring:param name="deporte" value="${deporte}" />
											<spring:param name="matchId" value="${match.id}" />
										</spring:url>
										<div class="botoncitores1">
											<a href="${fn:escapeXml(date2Url)}">Añadir fecha
												realización</a>
										</div>
									</c:if>
								</c:if> <c:if test="${match.dateTime != null }">
									<c:if test="${match.ronda==rondaActual}">
										<spring:url
											value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/date"
											var="date2Url">
											<spring:param name="championshipId" value="${championship}" />
											<spring:param name="deporte" value="${deporte}" />
											<spring:param name="matchId" value="${match.id}" />
										</spring:url>
										<div class="botoncitores1">
											<a href="${fn:escapeXml(date2Url)}">Modificar fecha
												realización</a>
										</div>
									</c:if>
								</c:if></td>
						</tr>
						<tr>
							<td><c:if test="${match.dateTime != null}">
									<c:if test="${match.ronda==rondaActual}">

										<td><spring:url
												value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/result/{team}"
												var="result2Url">
												<spring:param name="championshipId" value="${championship}" />
												<spring:param name="deporte" value="${deporte}" />
												<spring:param name="matchId" value="${match.id}" />
												<spring:param name="team" value="team1" />
											</spring:url>
											<div class="botoncitores1">
												<a href="${fn:escapeXml(result2Url)}">Añadir resultados
													de Equipo 1</a>
											</div> <spring:url
												value="/sports/{deporte}/championships/{championshipId}/match/{matchId}/result/{team}"
												var="result2Url">
												<spring:param name="championshipId" value="${championship}" />
												<spring:param name="deporte" value="${deporte}" />
												<spring:param name="matchId" value="${match.id}" />
												<spring:param name="team" value="team2" />
											</spring:url>
											<div class="botoncitores2">
												<a href="${fn:escapeXml(result2Url)}">Añadir resultados
													de Equipo 2</a>
											</div></td>
									</c:if>
								</c:if>
						</tr>
						<tr>
							<td>

								<div>
									<c:if
										test="${match.puntos1 != null && match.puntos2 != null && match.puntos3 != null
								&& match.puntos4 != null && match.puntos1 == match.puntos3 && match.puntos2 == match.puntos4}">
										<c:choose>
											<c:when
												test="${match.puntos1 > match.puntos2 && match.puntos3 > match.puntos4}">
												<p>¡${match.team1.name} ganador!</p>
											</c:when>
											<c:when
												test="${match.puntos1 < match.puntos2 && match.puntos3 < match.puntos4 }">
												<p>¡${match.team2.name} ganador!</p>
											</c:when>
											<c:when
												test="${match.puntos1 == match.puntos2 && match.puntos3 == match.puntos4 }">
												<p>¡El resultado del partido no debe ser empate!</p>
											</c:when>
										</c:choose>
									</c:if>
									<c:if
										test="${match.puntos1 != match.puntos3 || match.puntos2 != match.puntos4}">
										<p>¡Los resultados no coinciden!</p>
									</c:if>
								</div>
							</td>
						</tr>

					</c:forEach>
				</tbody>

			</table>
		</div>

		<c:if test="${championshipObj.matches.size() == 0 }">

			<spring:url
				value="/sports/{deporte}/championships/{championshipId}/match/generate1"
				var="dateUrl">
				<spring:param name="deporte" value="${deporte}" />
				<spring:param name="championshipId" value="${championship}" />
			</spring:url>
			<div class="botoncitocrear">
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
			<div class="botoncitocrear">
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
				<div class="botoncitocrear">
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
					<div class="botoncitocrear">
						<a id="createMatch" href="${fn:escapeXml(dateUrl)}">Generar
							cuarta ronda partidos</a>
					</div>
				</c:if>
			</c:if>
		</c:if>





		<div class="form-group">

			<c:if test="${noParticipa}">
				<p>¡No participas en el torneo para poder crear partidos!</p>
			</c:if>
			<c:if test="${noParticipaDate}">
				<p>¡No participas en el partido para añadir una fecha de
					realización!</p>
			</c:if>
			<c:if test="${faltaEquipos}">
				<p>¡Faltan equipos por unirse para generar la primera ronda de
					partidos!</p>
			</c:if>
			<c:if test="${yagenerado}">
				<p>¡La primera ronda ya está generada!</p>
			</c:if>
			<c:if test="${noprimera}">
				<p>¡Aún no se ha generado la primera ronda!</p>
			</c:if>
			<c:if test="${faltaresultados}">
				<p>¡No se puede generar una ronda sin los resultados de la
					anterior!</p>
			</c:if>

			<c:if test="${nocoinc}">
				<p>¡No se puede generar una ronda sin que los resultados de
					todos los partidos coincidan!</p>
			</c:if>

			<c:if test="${yagenerada2}">
				<p>¡La segunda ronda ya está generada!</p>
			</c:if>

			<c:if test="${yagenerada3}">
				<p>¡La tercera ronda ya está generada!</p>
			</c:if>

			<c:if test="${yagenerada4}">
				<p>¡La cuarta ronda ya está generada!</p>
			</c:if>


			<c:if test="${nosegunda}">
				<p>¡Aún no se ha generado la segunda ronda!</p>
			</c:if>
			<c:if test="${notercera}">
				<p>¡Aún no se ha generado la tercera ronda!</p>
			</c:if>
			<c:if test="${noUser}">
				<p>No se encontró al usuario deseado.</p>
			</c:if>
			<c:if test="${noTeam}">
				<p>¡No perteneces a este equipo!</p>
			</c:if>
			<c:if test="${faltaParticipantes}">
				<p>¡Los equipos no están completos (Faltan jugadores)!</p>
			</c:if>



			<button class="botonMeeting"
				style="display: block; font-size: 0.8em; width: 17%;"
				onclick="location.href='/sports/${deporte}/championships/${championship}';"
				type="button">
				<b>Volver a torneo</b>
			</button>








		</div>
	</body>
	</html>
</playtogether:layout>