<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class=container>
	<h1>친구 목록</h1>
	
<%-- 	<div>${requestScope.friendsList }</div> --%>
	
	<div>
		<table class="table table-hover">
			<thead>
		      <tr>
		        <th>No</th>
		        <th>Name</th>
		        <th>Mobile</th>
		        <th>Address</th>
		      </tr>
		    </thead>
		    
		    <tbody>
		    
		    	<c:forEach var="friend" items="${requestScope.friendsList }">		    	
		    		<tr>
		    			<td>${friend.friendNo }</td>
		    			<td>${friend.friendName }</td>
		    			<td>${friend.mobile }</td>
		    			<td>${friend.addr }</td>
		    		</tr>
		    	</c:forEach>
		    	
		    </tbody>
	
		</table>
	</div>
	
	</div>
</body>
</html>