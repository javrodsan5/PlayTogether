<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>



<playtogether:layout pageName="users">

	<body>

			<div class="description">
				<div class="line">
					<h1 class="product_name">
						<c:out value="${user.name}" />
					</h1>
					<h1 class="product_name">
						<c:out value="${user.username}" />
					</h1>
					<h1 class="product_price">
						<c:out value="${user.birthdate}" />
					</h1>

				</div>
				<h2>
					<c:out value="${user.correo}" />
				</h2>

			</div>


	</body>

</playtogether:layout>
