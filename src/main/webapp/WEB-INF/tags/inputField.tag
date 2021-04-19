<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of corresponding property in bean object"%>
<%@ attribute name="label" required="true" rtexprvalue="true"
	description="Label appears in red color if input is considered as invalid after submission"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<spring:bind path="${name}">
	<c:set var="cssGroup"
		value="form-group ${status.error ? 'has-error' : '' }" />
	<c:set var="valid"
		value="${not status.error and not empty status.actualValue}" />
	<div class="${cssGroup}">
		<c:if test="${label != 'Fecha y hora'}">
		<label class="col-sm-2 control-label">${label}</label>
		</c:if>
		
		<c:if test="${label == 'Fecha y hora'}">
		<label class="col-sm-2 control-label" style="display: table-cell;
    padding-bottom: 7% !important;">${label}</label>
		</c:if>
		
		
		
		<div class="col-sm-10">
			<c:if test="${label=='*Contraseña'}">
				<form:input class="form-control" path="${name}" type="password" />
			</c:if>
			<c:if test="${label!='*Contraseña' && label !='Fecha Inicio' && label != 'Fecha Fin' && label!='Fecha Nacimiento' && label!='*Fecha Nacimiento'
			
			&& label!='Fecha y hora' && label!='Fecha' && label!='Hora' }">
				<form:input class="form-control" path="${name}"/>
			</c:if>
			<c:if test="${label=='Fecha Inicio'}">
				<form:input class="form-control" path="${name}" type="date" style="width: 115%;" />
			</c:if>
			<c:if test="${label=='Fecha Fin'}">
				<form:input class="form-control" path="${name}" type="date" style="width: 115%;" />
			</c:if>
			<c:if test="${label=='Fecha Nacimiento'}">
				<form:input class="form-control" path="${name}" type="date" />
			</c:if>
			<c:if test="${label=='*Fecha Nacimiento'}">
				<form:input class="form-control" path="${name}" type="date" />
			</c:if>
			<c:if test="${label=='Fecha y hora'}">
				<form:input class="form-control" name="${name}" path="${name}" type="datetime-local" style="width:110%;"/>
			</c:if>
			<c:if test="${label=='Fecha'}">
				<form:input class="form-control" path="${name}" type="date" />
			</c:if>
			<c:if test="${label=='Hora'}">
				<form:input class="form-control" path="${name}" type="time" />
			</c:if>
			<c:if test="${valid}">
				<span class="glyphicon glyphicon-ok form-control-feedback"
					aria-hidden="true"></span>
			</c:if>
			<c:if test="${status.error}">
				<span class="glyphicon glyphicon-remove form-control-feedback"
					aria-hidden="true"></span>
				<span class="help-inline" style="color: white;">${status.errorMessage}</span>
			</c:if>
		</div>
	</div>
</spring:bind>