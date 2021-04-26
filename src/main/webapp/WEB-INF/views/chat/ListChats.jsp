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

<playtogether:layout pageName="chats">
	<body>
		<div class="cardtitle">
			<h1>
				<strong>Chats privados</strong>
			</h1>
			<br />
		</div>
		<div class="cardlist">
			<table id="meetingTable" class="table ">
				<thead>
					<tr class="rowtable">
						<th class="guiz-awards-header-title" style="width: 20%;">Usuario</th>
						<th class="guiz-awards-header-title" style="width: 60%;">Último mensaje</th>
						<th class="guiz-awards-header-title" style="width: 20%;"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${chats}" var="chat">
						<tr class="rowtable">
							<td><c:out value="${chat.user1.user.username == principalUsername ? chat.user2.user.username : chat.user1.user.username}" /></td>
							<td><b><c:out value="${chat.messages[chat.messages.size()-1].usuario.user.username == principalUsername ? 'Tú' : chat.messages[chat.messages.size()-1].usuario.user.username}:" /></b>
                                <c:out value="${chat.messages[chat.messages.size()-1].message}" /></td>
                            <td><button class="btn btn-success"
                                style="    margin-left: 25%; margin-bottom:3%;"
                                onclick="location.href='/chat/${chat.id}/messages';"
                                type="button">
                                Chat <i class="fa fa-weixin" aria-hidden="true"></i>
                        
                            </button></td>                            
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
</playtogether:layout>

</body>
</html>