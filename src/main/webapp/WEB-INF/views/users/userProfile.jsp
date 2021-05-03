<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Caveat&display=swap"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<playtogether:layout pageName="profile" invitaciones="${invitaciones}">
	<div class="thirteen">
		<h1>Datos de mi perfil</h1>
		<c:if test="${confirmationDelete}">
			<div class="card" style="margin: 1% 35% 1% 35%">
				<div class="card-header"
					style="background-color: #9ec1c1; font-family: 'Recursive', sans-serif; text-align: center">Confirmación
					de eliminar perfil</div>
				<div class="card-body" style="margin: auto;">
					<h3 class="alert alert-warning" style="text-align: center">¿Estás
						seguro de que quieres borrar tu perfil?</h3>

					<div align="center">
						<div style="display: inline-block;">
							<a class="btn btn-primary"
								href="/confirmationRequestDeleteMyProfile">Sí</a>
						</div>
						<div style="display: inline-block;">
							<a class="btn btn-primary" href="/myprofile">No</a>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${confirmationData}">
			<div class="card" style="margin: 1% 35% 1% 35%">
				<div class="card-header"
					style="background-color: #9ec1c1; font-family: 'Recursive', sans-serif; text-align: center">Confirmación
					de solicitar tus datos</div>
				<div class="card-body" style="margin: auto;">
					<h3 class="alert alert-warning" style="text-align: center">¿Quieres
						solicitar tus datos?</h3>

					<div align="center">
						<div style="display: inline-block;">
							<a class="btn btn-primary"
								href="/confirmationRequestDataMyProfile">Sí</a>
						</div>
						<div style="display: inline-block;">
							<a class="btn btn-primary" href="/myprofile">No</a>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${confirmatedData}">
			<h3 class="alert alert-success"
				style="margin: 1% 30% 1% 30%; text-align: center">Hemos
				recibido tu solicitud de petición de datos.</h3>
		</c:if>
		<c:if test="${confirmatedDelete}">
			<h3 class="alert alert-success"
				style="margin: 1% 30% 1% 30%; text-align: center">Hemos
				recibido tu solicitud de eliminar tu perfil.</h3>
		</c:if>
		
		<c:if test="${incidenceCorrect}">
			<h3 class="alert alert-success"
				style="margin: 1% 30% 1% 30%; text-align: center">Se ha enviado su incidencia. Contactaremos lo antes posible con usted. ¡Muchas gracias!</h3>
		</c:if>
		
		<h2>
			Tienes <span class="pointsuser"><c:out value="${user.puntos}" /></span>
			puntos
		</h2>
	</div>
	<c:if test="${invitacionesQuedadas!=0 or invitacionesTorneos !=0}">
		<div class="alert alert-primary" style="margin: 1% 20% 1% 20%">
			<c:if test="${invitacionesQuedadas!=0 and invitacionesTorneos ==0}">
				<c:if test="${invitacionesQuedadas==1}">
					<h2 style="text-align: center;">
						Tienes
						<c:out value="${invitacionesQuedadas}" />
						invitación a una quedada nueva
					</h2>
				</c:if>
				<c:if test="${invitacionesQuedadas!=1}">
					<h2 style="text-align: center;">
						Tienes
						<c:out value="${invitacionesQuedadas}" />
						invitaciones a quedadas nuevas
					</h2>
				</c:if>
			</c:if>
			<c:if test="${invitacionesQuedadas==0 and invitacionesTorneos !=0}">
				<c:if test="${invitacionesTorneos==1}">
					<h2 style="text-align: center;">
						Tienes
						<c:out value="${invitacionesTorneos}" />
						invitación a un torneo nuevo
					</h2>
				</c:if>
				<c:if test="${invitacionesTorneos!=1}">
					<h2 style="text-align: center;">
						Tienes
						<c:out value="${invitacionesTorneos}" />
						invitaciones a torneos nuevos
					</h2>
				</c:if>
			</c:if>
			<c:if test="${invitacionesQuedadas!=0 and invitacionesTorneos !=0}">
				<c:if test="${invitacionesTorneos==1 and invitacionesQuedadas==1}">
					<h2 style="text-align: center;">
						Tienes
						<c:out value="${invitacionesQuedadas}" />
						invitación a una quedada nueva y
						<c:out value="${invitacionesTorneos}" />
						invitación a un torneo nuevo
					</h2>
				</c:if>
				<c:if test="${invitacionesTorneos!=1 and invitacionesQuedadas==1}">
					<h2 style="text-align: center;">
						Tienes
						<c:out value="${invitacionesQuedadas}" />
						invitación a una quedada nueva y
						<c:out value="${invitacionesTorneos}" />
						invitaciones a torneos nuevos
					</h2>
				</c:if>
				<c:if test="${invitacionesTorneos==1 and invitacionesQuedadas!=1}">
					<h2 style="text-align: center;">
						Tienes
						<c:out value="${invitacionesQuedadas}" />
						invitaciones a quedadas nuevas y
						<c:out value="${invitacionesTorneos}" />
						invitación a un torneo nuevo
					</h2>
				</c:if>
				<c:if test="${invitacionesTorneos!=1 and invitacionesQuedadas!=1}">
					<h2 style="text-align: center;">
						Tienes
						<c:out value="${invitacionesQuedadas}" />
						invitaciones a quedadas nuevas y
						<c:out value="${invitacionesTorneos}" />
						invitaciones a torneos nuevos
					</h2>
				</c:if>
			</c:if>
		</div>

	</c:if>
	<body>
		<div class="body-container" style="display: inline-block; width: 25%">

			<div class="dashboard" style="width: 100%">
				<div class="grid-container" style="width: 360px">
					<div class="profile grid-area" style="border: grey 1px solid">
						<div class="img">
							<img src="/images/avatar.png">
							<h5 class="username" style="font-size: 24px">
								<c:out value="${user.user.username}" />
							</h5>
						</div>
						<center>
							<h3>
								<c:out value="${user.name}" />
							</h3>
						</center>
						<div class="profile-data">
							<div class="data-details" style="margin-right: 22%">
								<h5>Fecha de nacimiento</h5>
								<h4>
									<fmt:parseDate value="${user.birthdate }" pattern="yyyy-MM-dd"
										var="parsedDateStart" type="both" />
									<fmt:formatDate value="${parsedDateStart}" pattern="dd-MM-yyyy" />
								</h4>
							</div>
							<div class="data-details">
								<h5>Tipo de usuario</h5>
								<h4>
									<c:out value="${user.type}" />
								</h4>
							</div>
						</div>
						<div class="profile-data">
							<div class="data-details" style="margin-right: 15%">
								<h5>Teléfono</h5>
								<h4>
									<c:out value="${user.phone}" />
								</h4>
							</div>
							
						</div>
						<div class="profile-data">
						<div class="data-details">
								<h5>Correo electrónico</h5>
								<h4>
									<c:out value="${user.correo}"></c:out>
								</h4>

							</div>
	</div>
						<spring:url value="/myprofile/edit" var="editUser2Url">
						</spring:url>
						<center>
							<br> <a class="btn btn-primary"
								href="${fn:escapeXml(editUser2Url)}">Editar</a> <br> <br>
							<a href="/invitations/listInvitations" class="btn btn-primary">Ver
								invitaciones</a> <br> <br> <a href="/myprofile/paysRecord"
								class="btn btn-primary">Historial de pagos</a> <br> <br>
							<a href="/myprofile/meetingsRecord" class="btn btn-primary">Historial
								de quedadas</a>

							<spring:url value="/myprofile/championshipsRecord"
								var="championshipRecord2Url">

							</spring:url>
							<br> <br> <a class="btn btn-primary"
								href="${fn:escapeXml(championshipRecord2Url)}">Historial de
								torneos</a> <br> <br> <a class="btn btn-primary"
								href="/requestDeleteMyProfile">Solicitar borrar mi perfil</a> <br>
							<br> <a class="btn btn-primary" href="/requestDataMyProfile">Solicitar
								datos de mi perfil</a> <br> <br>
								
								<spring:url value="/myprofile/incidence" var="incidenceUrl">
						</spring:url>
						<a class="btn btn-primary"
								href="${fn:escapeXml(incidenceUrl)}">Enviar incidencia</a> <br> <br>
						</center>

					</div>

				</div>
			</div>
		</div>


		<div class="charts">
			<br>
			<c:if test="${user.description==null || user.description==''}">
				<center>
					<button onclick="location.href='/myprofile/description'"
						style="font-size: 30px" class="btn btn-info" type="button">
						<b>Añadir descripción</b>
					</button>
				</center>
				<br>
			</c:if>
			<c:if test="${user.description!=null && user.description!=''}">
				<div class="paper blue">
					<div class="top-tape"></div>
					<p>
						<c:out value="${user.description}" />
					</p>
					<spring:url value="/myprofile/description" var="descriptionUrl">
					</spring:url>
					<a style="color: #206b77; position: absolute; bottom: 0; right: 0;"
						href="${fn:escapeXml(descriptionUrl)}"><i class="fa fa-edit"></i></a>
				</div>
			</c:if>


			<center>
				<div id="mensaje"></div>
				<div id="chart1" style="width: 400px; text-align: center"></div>
			</center>
			<br>
			<div id="chart2"></div>
			<div id="chart3"></div>
			<input type="hidden" id="myvar" value="${quedadasTorneos}"> <input
				type="hidden" id="myvar2" value="${quedadasPorMes}"> <input
				type="hidden" id="myvar3" value="${torneosPorMes}">
		</div>
		<script type="text/javascript">
				var datos = document.getElementById("myvar").value;	
				var datos2 = datos.replace('[',"");
				var datos3 = datos2.replace(']',"");
				var datos4 = datos3.replace(' ',"");
				var datos5 = datos4.split(",");
				
				var arr=[];
		
				for(var i of datos5){
					var b = parseInt(i);
					arr.push(b);		
				}
		
		        var options1 = {
						series: arr,
						labels: ["Quedadas", "Torneos"],
		                chart: {
		                type: 'donut',
		              },
		              responsive: [{
		                breakpoint: 480,
		                options: {
		                  chart: {
		                    width: 200
		                  },
		                  legend: {
		                    position: 'bottom'
		                  }
		                }
		              }]
		              };
		
				var datosMes = document.getElementById("myvar2").value;	
				var datosMes2 = datosMes.replace('[',"");
				var datosMes3 = datosMes2.replace(']',"");
				var datosMes4 = datosMes3.replaceAll(' ',"");
				var datosMes5 = datosMes4.split(',');
		
				var arr2=[];
		
				for(var i of datosMes5){
					var b = parseInt(i);
					arr2.push(b);		
				}
		
		        var options2 = {
		                series: [{
		                  name: "Quedadas",
		                  data: arr2
		              }],
		                chart: {
		                height: 350,
		                type: 'line',
		                zoom: {
		                  enabled: false
		                }
		              },
		              dataLabels: {
		                enabled: false
		              },
		              stroke: {
		                curve: 'straight'
		              },
		              title: {
		                text: 'Quedadas creadas por mes',
		                align: 'left'
		              },
		              grid: {
		                row: {
		                  colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
		                  opacity: 0.5
		                },
		              },
		              xaxis: {
		                categories: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ags', 'Sep','Oct','Nov','Dic'],
		              }
		              };
		
		        var datosMesTorneo = document.getElementById("myvar3").value;	
				var datosMesTorneo2 = datosMesTorneo.replace('[',"");
				var datosMesTorneo3 = datosMesTorneo2.replace(']',"");
				var datosMesTorneo4 = datosMesTorneo3.replaceAll(' ',"");
				var datosMesTorneo5 = datosMesTorneo4.split(',');
		
				var arr3=[];
		
				for(var i of datosMesTorneo5){
					var b = parseInt(i);
					arr3.push(b);		
				}
		
		        var options3 = {
		                series: [{
		                  name: "Torneos",
		                  data: arr3
		              }],
		                chart: {
		                height: 350,
		                type: 'line',
		                zoom: {
		                  enabled: false
		                }
		              },
		              dataLabels: {
		                enabled: false
		              },
		              stroke: {
		                curve: 'straight'
		              },
		              title: {
		                text: 'Torneos creados por mes',
		                align: 'left'
		              },
		              grid: {
		                row: {
		                  colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
		                  opacity: 0.5
		                },
		              },
		              xaxis: {
		                categories: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ags', 'Sep','Oct','Nov','Dic'],
		              }
		              };

					if(datos=="[0,0]"){
						document.getElementById("mensaje").innerHTML="Quedadas y torneos en los que he participado";
						document.getElementById("chart1").innerHTML="No hay datos";
					}else{
						document.getElementById("mensaje").innerHTML="Quedadas y torneos en los que he participado";
						var chart = new ApexCharts(document.querySelector("#chart1"), options1);
						chart.render();
					}
		              var chart2 = new ApexCharts(document.querySelector("#chart2"), options2);
		              var chart3 = new ApexCharts(document.querySelector("#chart3"), options3);
		              chart2.render();
		              chart3.render();
	</script>
	</body>


</playtogether:layout>
