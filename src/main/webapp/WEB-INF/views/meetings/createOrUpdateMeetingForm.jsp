<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<playtogether:layout pageName="meetings">
	<h2>
		<c:if test="${!meeting['new']}">
				Editar una quedada.</c:if>
	</h2>
	<h2>
		<c:if test="${meeting['new']}">
				Crear una quedada.</c:if>
	</h2>
	<div style="margin-left: 45px">

		<form:form modelAttribute="meeting" commandName="meeting">
			<div>
				<playtogether:localDateTime label="Fecha" name="date" id="date"></playtogether:localDateTime>
				<playtogether:inputField label="Ciudad" name="city" />
				<playtogether:inputField label="Lugar de encuentro" name="address" />
				<playtogether:inputField label="Descripción" name="description" />
				<input type="hidden" name="sport" value="${deporte}" />
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<c:choose>
							<c:when test="${meeting['new']}">
								<button class="btn btn-primary" type="submit">Crear</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-primary" type="submit">Actualizar
									datos</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</form:form>
	</div>

	<section class="contact-wrap">
		<form action="" class="contact-form">
			<div class="col-sm-6">
				<div class="input-block">
					<label for="">First Name</label> <input type="text"
						class="form-control">
				</div>
			</div>
			<div class="col-sm-6">
				<div class="input-block">
					<label for="">Last Name</label> <input type="text"
						class="form-control">
				</div>
			</div>
			<div class="col-sm-12">
				<div class="input-block">
					<label for="">Email</label> <input type="text" class="form-control">
				</div>
			</div>
			<div class="col-sm-12">
				<div class="input-block">
					<label for="">Message Subject</label> <input type="text"
						class="form-control">
				</div>
			</div>
			<div class="col-sm-12">
				<div class="input-block textarea">
					<label for="">Drop your message here</label>
					<textarea rows="3" type="text" class="form-control"></textarea>
				</div>
			</div>
		</form>
	</section>
</playtogether:layout>