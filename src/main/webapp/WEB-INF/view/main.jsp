<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css" rel = "stylesheet"></link>
<title>HYELOG</title>
</head>
<body>
	<div>
		<div>
			<!--<img src="${pageContext.servletContext.contextPath }/resource/image/title.png"/>  -->
		</div>
		<div>
			<c:choose>
				<c:when test="${result }">
				<div style="text-align: right;">
					<ul>
						<li><a href="#">LOGOUT</a></li>
						<li><a href="$#">MYPAGE</a></li>
						<li><a href="$#">CART</a></li>
						<li><a href="#">NOTICE</a></li>
						<li><a href="#">Q&A</a></li>
						<li><a href="#">REVIEW</a></li>
					</ul>
				</div>
				</c:when>
				<c:otherwise>
				<div style="text-align: right;">
					<ul>
						<li><a href="#">LOGIN</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/view/join">JOIN</a></li>
						<li><a href="#">NOTICE</a></li>
						<li><a href="#">Q&A</a></li>
						<li><a href="#">REVIEW</a></li>
					</ul>	
				</div>
				</c:otherwise>	
			</c:choose>
		</div>
	</div>
</body>
</html>