<%@ tag trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<%@ attribute name="pageName" required="true"%>
<%@ attribute name="invitaciones" required="false" rtexprvalue="true" %>
<%@ attribute name="customScript" required="false" fragment="true"%>

<!doctype html>
<html>
<div><playtogether:htmlHeader /></div>

<body id="real-body">

	<div class="navigation-wrap bg start-header" style="height: 5.625em; margin-bottom: 1px;">
		<playtogether:bodyHeader menuName="${pageName}" invitaciones="${invitaciones}"/>
	</div>

	<div style="margin-top: 1px; height: calc(100% - 5.625em); padding: 1px;">
		<c:if test="${pageName=='welcome'}">
			<div style="margin-top: 4.5%; margin-bottom: 2%; min-height: calc(100% - 8em); padding: 1px;">
				<jsp:doBody />
			</div>
		</c:if>
		
		<c:if test="${pageName!='welcome'}">
			<sec:authorize access="!hasAuthority('premium')">
				<playtogether:banner></playtogether:banner>
				<div style="margin-top: 3em; margin-bottom: 3em; min-height: calc(100% - 20em); padding: 1px;">
					<jsp:doBody />
				</div>
			</sec:authorize>
			
			<sec:authorize access="hasAuthority('premium')">
				<div style="margin-top: 3em; margin-bottom: 3em; min-height: calc(100% - 5em); padding: 1px;">
					<jsp:doBody />
				</div>
			</sec:authorize>
		</c:if>

		
		
		<footer class="footerLanding real-footer" style="margin-top: 1px; min-height: 5em; padding: 1px;">
			<a class="navbar-brand"><img style="display: inline-block"
				src="/images/logo_opt.png" alt="Logo app"></a> <b><i
				style="display: inline-block" class="title_logo">PlayTogether</i></b>
			<a href="/about-us" style="margin-left: 12.5em; display: inline-block; color: white"><b>Acerca de nosotros</b></a>
			<a href="/terms-and-conditions" style="margin-left: 200px; display: inline-block; color: white"><b>Términos y condiciones</b></a>
			<a href="/cookies-policy" style="margin-left: 200px; display: inline-block; color: white"><b>Política de cookies</b></a>
		</footer>
	</div>
</body>

</html>
