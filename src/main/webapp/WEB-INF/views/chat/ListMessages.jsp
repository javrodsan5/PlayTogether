<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<playtogether:layout pageName="messages">


	<body>
 <c:if test="${vacio}">
		<br>
		<div class="alert alert-danger" style="margin: 0% 20% 1% 20%">
			<p>El mensaje no puede estar vacío.</p>
		</div>
	</c:if>
	<c:if test="${spam}">
		<br>
		<div class="alert alert-danger" style="margin: 0% 20% 1% 20%">
			<p>No puedes enviar mensajes con palabras malsonantes.</p>
		</div>
	</c:if>

<section class="msger" style="margin: auto;">
  <header class="msger-header">
    <div class="msger-header-title" style="">
    <i class="fa fa-weixin" aria-hidden="true"></i>
    <c:if test="${ typeId == 1 }" > 
      Chat de la quedada de ${chat.meeting.sport.name} de ${chat.meeting.meetingCreator.user.username}
      </c:if>
      <c:if test="${ chat.chatType.id == 2 }" > 
      Chat del equipo ${chat.team.name}
      </c:if>
      <c:if test="${ chat.chatType.id == 3 && chat.user1.id == usuarioId}" > 
      Chat individual con ${chat.user2.user.username}
      </c:if>
      <c:if test="${ chat.chatType.id == 3 && chat.user2.id == usuarioId}" > 
      Chat individual con ${chat.user1.user.username}
      </c:if>
      										
    </div>
    <div class="msger-header-options">
     
    </div>
  </header>

  <main class="msger-chat">
  <div class="scroll_vertical" id="style_scroll">
  <c:forEach items="${chat.messages}" var="message" >
  
  <c:if test="${ message.usuario.id != usuarioId }" >
    <div class="msg left-msg">
      <div
       class="msg-img"
       style="background-image: url(/images/user3.png)"
      ></div>

      <div class="msg-bubble">
        <div class="msg-info">
          <div class="msg-info-name">${message.usuario.user.username}</div>
   
         
          <div class="msg-info-time">
          <fmt:parseDate value="${ message.date }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
          <fmt:formatDate 
         value = "${parsedDateTime}" pattern = "dd-MM-yyyy HH:mm"  />
         </div>
        </div>

        <div class="msg-text">
          ${message.message}
        </div>
      </div>
    </div>
    </c:if>
    
     <c:if test="${ message.usuario.id == usuarioId }" >
    <div class="msg right-msg">
    
      <div
       class="msg-img"
       style="background-image: url(/images/user1.png)"
      ></div>

      <div class="msg-bubble">
        <div class="msg-info">
          <div class="msg-info-name">${message.usuario.user.username}</div>
          <div class="msg-info-time">
<fmt:parseDate value="${ message.date }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
          <fmt:formatDate 
         value = "${parsedDateTime}" pattern = "dd-MM-yyyy HH:mm"  />
</div>
        </div>

        <div class="msg-text">
          ${message.message}
        </div>
      </div>
    </div>
    </c:if>
    </c:forEach>
    </div>
  </main>
<form:form class="msger-inputarea" style="margin-bottom:0px !important; "  modelAttribute="message" commandName="message" action="/chat/${message.chat.id}/messages/new" method="post">
  
  	
    <input type="text" class="msger-input" name="message" placeholder="Escribe un mensaje...">
    <button type="submit" class="msger-send-btn" >Enviar <i class="fa fa-paper-plane" aria-hidden="true" style="padding:0px !important; font-size=80%"></i></button>
   
  </form:form>
  
  
</section>



		
	
			<div class="form-group" style="  margin-left: 50%; margin-top:2%;">
				<button class="btn btn-success" style="font-size: 0.8em; " onclick="location.href='${urlBack}';" type="button">
					<b>Volver</b>
				</button>
			</div>
			

</playtogether:layout>

</body>
</html>