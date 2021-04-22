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



<playtogether:layout pageName="users">

	<div class="thirteen">
		<h1>
			Datos del perfil de
			<c:out value="${user.name}" />
		</h1>

		<h2>
			Tiene <span class="pointsuser"><c:out value="${user.puntos}" /></span>
			puntos
		</h2>

	</div>

	<body>
		<c:if test="${tipoUsuario==false}">
			<h3 class="alert alert-warning"
				style="text-align: center; margin: 2% 15% 0px 15%">
				<a href="/pay/premium">Suscríbete</a> al plan premium para poder
				visualizar las estadísticas de
				<c:out value="${user.name}" />
			</h3>
		</c:if>
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
					</div>
				</div>
			</div>
		</div>

		<div class="charts" style="margin-top: 2%">
			<c:if test="${user.description!=null && user.description!=''}">

				<div class="paper blue">
					<div class="top-tape"></div>
					<p>
						<c:out value="${user.description}" />
					</p>
				</div>
		</c:if>
		
		<c:if test="${tipoUsuario==true }">
				<br>
				<center>
					<div id="mensaje"></div>
					<div id="chart1" style="width: 400px; text-align: center"></div>
				</center>
				<br>
				<div id="chart2"></div>
				<div id="chart3"></div>
				<input type="hidden" id="myvar" value="${quedadasTorneos}">
				<input type="hidden" id="myvar2" value="${quedadasPorMes}">
				<input type="hidden" id="myvar3" value="${torneosPorMes}">
		</c:if>
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
