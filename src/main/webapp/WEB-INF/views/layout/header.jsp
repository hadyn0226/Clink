<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix = "fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
<script src="${pageContext.request.contextPath}/resources/ajax/js/httpRequest.js"></script>
</head>
<body>

<div align="center">
		<div class="header">
 			<c:choose>
				<c:when test="${!empty login }">
					<a href="${pageContext.request.contextPath }/user/mypage">MyPage</a> | 
					<a href="${pageContext.request.contextPath }/logout">Logout</a> | 
					<script type="text/javascript">
						function exit(e) {
							console.log("screepTop",self.screenTop);
							let no = ${login};
							var url = "${pageContext.request.contextPath}/exit";
      						var param = "";
      						sendRequest(url, param, none, "POST");
						}
						function none() {
							
						}
						window.addEventListener('onclose',exit());
					</script>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath }/loginForm">Login</a> | 
					<a href="${pageContext.request.contextPath }/joinForm">Join</a> | 
				</c:otherwise>
			</c:choose>
			<a href="${pageContext.request.contextPath }/product/list">Shop</a> | 
			<a href="${pageContext.request.contextPath }/cart/list">Cart</a> | 
			<a href="${pageContext.request.contextPath }">Home</a> | 
			<a href="${pageContext.request.contextPath }/Korean">Map</a>
		</div>
		<div class="main">
        