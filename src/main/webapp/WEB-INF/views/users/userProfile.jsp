<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>


<playtogether:layout pageName="users">

	<div class="thirteen">
		<h1>Datos de mi perfil</h1>
		<h2>Tienes <c:out value="${user.puntos}" /> puntos</h2>
		
	</div>
	<body>
		<div class="body-container" style="display: inline-block; width: 25%">

			<div class="dashboard" style="width: 100%">
				<div class="grid-container" style="width: 360px">
					<div class="profile grid-area" style="border: grey 1px solid">
						<div class="img">
							<img src="/images/avatar.png">

							<h3>
								<c:out value="${user.name}" />
							</h3>
							<h5 class="username">
								<c:out value="${user.user.username}" />
							</h5>
						</div>
						<div class="profile-data">
							<div class="data-details">
								<h5>Fecha de nacimiento</h5>
								<h4>
									<c:out value="${user.birthdate}" />
								</h4>
							</div>
						</div>
						<div class="profile-data">
							<div class="data-details">
								<h5 style="margin-right: 50px">Teléfono</h5>
								<h4 style="margin-right: 50px">
									<c:out value="${user.phone}" />
								</h4>
							</div>
							<div class="data-details">
								<h5 style="margin-left: 20px">Correo electrónico</h5>
								<h4 style="margin-left: 20px">
									<c:out value="${user.correo}"></c:out>
								</h4>

							</div>
						</div>
						<div class="profile-data">
							<div class="data-details">
								<h5>Tipo de usuario</h5>
								<h4>
									<c:out value="${user.type}" />
								</h4>
							</div>
						</div>

						<spring:url value="/myprofile/edit" var="editUser2Url">
						</spring:url>
						<center>
							<a class="btn btn-primary" href="${fn:escapeXml(editUser2Url)}">Editar</a>
							<br>
							<br> <a href="/invitations/championshipInvitations"
								class="btn btn-primary">Ver invitaciones a equipo de torneo</a>
							<br> <br> <a href="/invitations/meetingInvitations"
								class="btn btn-primary">Ver invitaciones a quedadas</a> <br>
							<br> <a href="/myprofile/meetingsRecord"
								class="btn btn-primary">Historial de quedadas</a>

							<spring:url value="/myprofile/championshipsRecord"
								var="championshipRecord2Url">

							</spring:url>
							<br> <br> <a class="btn btn-primary"
								href="${fn:escapeXml(championshipRecord2Url)}">Historial de
								torneos</a> <br>
							<br>
						</center>

					</div>

				</div>
			</div>


		</div>
		<div class="charts"
			style="width: 50%; display: inline-block; margin-left: 5%; margin-right: 5%; float: right">
			<br>
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
				var datos4 = datos3.replace(',',"");
				var datos5 = datos4.replace(' ',"");
				
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
				var datosMes4 = datosMes3.replaceAll(',',"");
				var datosMes5 = datosMes4.replace(' ',"");
		
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
				var datosMesTorneo4 = datosMesTorneo3.replaceAll(',',"");
				var datosMesTorneo5 = datosMesTorneo4.replace(' ',"");
		
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
