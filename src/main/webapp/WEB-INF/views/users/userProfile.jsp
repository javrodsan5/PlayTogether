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
						<c:out value="${user.user.username}" />
					</h1>
					<h1 class="product_price">
						<c:out value="${user.birthdate}" />
					</h1>
					<h1 class="product_price">
						<c:out value="${user.phone}" />
					</h1>
			
					<h1 class="product_price">
						<c:out value="${user.type}" />
						
					</h1>
					<h1 class="product_price">
						<c:out value="${user.statistics}" />
					</h1>

				</div>
				<h2>
					<c:out value="${user.correo}" />
				</h2>
				
				<td><spring:url value="/myprofile/{usuarioId}/edit"
	                            var="editUser2Url">
	                            <spring:param name="usuarioId" value="${user.id}" />
	                 
	                        </spring:url><div class="botoncito"> <a class="" href="${fn:escapeXml(editUser2Url)}">Editar</a></div></td>
				
				
				<a href="/invitations/championshipInvitations" class="btn btn-danger">Ver invitaciones a equipo de torneo</a>
				
				<td><spring:url value="/myprofile/{usuarioId}/championshipsRecord"
	                            var="championshipRecord2Url">
	                            <spring:param name="usuarioId" value="${user.id}" />
	                 
	                        </spring:url><div class="botoncito"> <a class="" href="${fn:escapeXml(championshipRecord2Url)}">Historial de torneos</a></div></td>

			</div>


	</body>

</playtogether:layout>
