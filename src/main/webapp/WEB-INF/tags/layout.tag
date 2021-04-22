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
<div style="margin-bottom: 90px">

	<playtogether:htmlHeader />
</div>
<body>
	<sec:authorize access="!hasAuthority('premium')">
	<c:if test="${pageName!='welcome'}">

		<playtogether:banner></playtogether:banner>
		</c:if>
	</sec:authorize>
	<playtogether:bodyHeader menuName="${pageName}" invitaciones="${invitaciones}"/>

	<br>

	<div>

		<jsp:doBody />
		<br> <br>
	</div>


</body>
<footer class="footerLanding">
	<a class="navbar-brand"><img style="display: inline-block"
		src="/images/logo_opt.png" alt="Logo app"></a> <b><i
		style="display: inline-block" class="title_logo">PlayTogether</i></b>
	<a href="/about-us" style="padding-left: 200px; display: inline-block; color: white"><b>Acerca de nosotros</b></a>
</footer>
</html>
