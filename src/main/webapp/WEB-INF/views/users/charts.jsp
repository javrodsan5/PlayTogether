<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>


<playtogether:layout pageName="charts">
	
	<body>
	<h2> Puntos conseguidos: <c:out value="${puntos}" /></h2>
		<div id="chart" style="width:20%;"></div>
		<input type="hidden" id="myvar" value="${quedadasTorneos}">
		<script type = "text/javascript">
		var datos = document.getElementById("myvar").value;
		var datos2 = datos.replace('[',"");
		var datos3 = datos2.replace(']',"");
		var datos4 = datos3.replace(',',"");
		var datos5 = datos4.replace(' ',"");
		var datos6 = Array.from(datos5)
        var options = {
                series: datos6,
                chart: {
                width: 380,
                type: 'pie',
              },
              labels: ['Quedadas', 'Torneos'],
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


              var chart = new ApexCharts(document.querySelector("#chart"), options);
              chart.render();
	</script>
	</body>

</playtogether:layout>