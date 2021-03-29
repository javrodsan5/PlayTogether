<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<link href='https://fonts.googleapis.com/css?family=Crete Round'
	rel='stylesheet'>


<playtogether:layout pageName="teams">
	<body>
	<h2>Crear un equipo</h2>
	
	<form:form modelAttribute="team" commandName="team">
		<div>
			<playtogether:inputField label="Nombre de equipo" name="name"/><br> <br>
				
			<input type="submit"  value="Crear"/>
		</div>
	</form:form>
	</body>
</playtogether:layout>	
	
	