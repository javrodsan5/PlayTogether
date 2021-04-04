<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
 
<playtogether:layout pageName="championships">
<div class="thirteen">
<h1>Crear un Partido</h1>
<br>
<h2>El torneo comienza el ${championshipObj.startDate} y finaliza el ${championshipObj.finishDate}</h2>
</div>
<div style="margin-left: 45px">
			<div class="crearMeeting">
<form:form  commandName="match" modelAttribute="match">
	<div>
	 <c:if test="${match['new'] && !noPuntos}">
		<playtogether:localDateTime label="Fecha realización" name="dateTime" id="dateTime" ></playtogether:localDateTime>
		<div class="selectorPartido">
		<label>Equipo 1</label><br>
		<select name="team1">
           <c:forEach var="item" items="${equipos}">
             <option value="${item.id}">${item.name}</option>
           </c:forEach>
         </select>
         </div>
         <div class="selectorPartido">
         		<label>Equipo 2</label><br>
		<select name="team2">
           <c:forEach var="item" items="${equipos}">
             <option value="${item.id}">${item.name}</option>
           </c:forEach>
         </select>
         </div>
         <div class="errorSelector">${errorSelector}</div>
		
		<input type="hidden" label="Puntos Equipo 1" name="puntos1"  />
		<input type="hidden" label="Puntos Equipo 2" name="puntos2"  />
		<input type="hidden" label="Puntos Equipo 3" name="puntos3"  />
		<input type="hidden" label="Puntos Equipo 4" name="puntos4"  />
	 </c:if>
	 <c:if test="${isPuntos1==true}">
	 	<div class="nomostrar">
		<playtogether:localDateTime label="Fecha realización" name="dateTime" id="dateTime" ></playtogether:localDateTime>
		</div>
		<label for="puntos1">Puntos Equipo 1 (Equipo 1)</label>
		<div class="${cssGroup}">
		<div class="col-sm-10">
		<select label="Puntos Equipo 1 (Equipo 1)" name="puntos1" >
		<c:forEach var="puntos" items="${listaPuntos}">
    		<option value="${puntos}"></option>
		</c:forEach>
		</select>
		</div><br>
		</div>
		<label for="puntos2">Puntos Equipo 2 (Equipo 1)</label>
		<div class="${cssGroup}">
		<div class="col-sm-10">
		<playtogether:selectField label="Puntos Equipo 2 (Equipo 1)" name="puntos2" size="200" names="${listaPuntos }"></playtogether:selectField>
		</div>
		</div>
		<input type="hidden" label="Equipo 1" name="team1" value="${match.team1.id }" />
		<input type="hidden" label="Equipo 2" name="team2" value="${match.team2.id }" /><br><br>
		<c:if test="${noPuntos}">
			<p>Faltan campos por rellenar.</p>
		</c:if>
	 </c:if>
	 <c:if test="${isPuntos1==false}">
	 	<div class="nomostrar">
		<playtogether:localDateTime label="Fecha realización" name="dateTime" id="dateTime" ></playtogether:localDateTime>
		</div>
		<label for="puntos3">Puntos Equipo 1 (Equipo 2)</label>
		<div class="${cssGroup}">
		<div class="col-sm-10">
		<select label="Puntos Equipo 1 (Equipo 2)" name="puntos3" >
		<c:forEach var="puntos" items="${listaPuntos}">
    		<option value="${puntos}">${puntos}</option>
		</c:forEach>
		</select>
		</div><br>
		</div>
		<label for="puntos4">Puntos Equipo 2 (Equipo 2)</label>
		<div class="${cssGroup}">
		<div class="col-sm-10">
		<select label="Puntos Equipo 2 (Equipo 2)" name="puntos4" >
		<c:forEach var="puntos" items="${listaPuntos}">
    		<option value="${puntos}">${puntos}</option>
		</c:forEach>
		</select>
		</div>
		</div>
		<input type="hidden" label="Equipo 1" name="team1" value="${match.team1.id }" />
		<input type="hidden" label="Equipo 2" name="team2" value="${match.team2.id }" /><br><br>
		<c:if test="${noPuntos}">
			<p>Faltan campos por rellenar.</p>
		</c:if>
	 </c:if>
		
		<input type="hidden" name="championship" value="${championship}"/>
		<input type="submit" class="butona" value="Enviar">
	</div>
</form:form>
</div>
</div>
</playtogether:layout>