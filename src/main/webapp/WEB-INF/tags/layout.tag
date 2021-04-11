<%@ tag trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="pageName" required="true"%>
<%@ attribute name="customScript" required="false" fragment="true"%>

<!doctype html>
<html>
<div style="margin-bottom: 90px">

	<playtogether:htmlHeader />
</div>
<body>
	<playtogether:bodyHeader menuName="${pageName}" />

	<br>

	<div>

		<jsp:doBody />
	<br>
	<br>
	</div>


</body>
</html>
