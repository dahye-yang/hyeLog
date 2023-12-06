<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<div style="display: flex; justify-content: space-between;">
			<div>
				<a href="${pageContext.servletContext.contextPath }/view/main">
				<img src="${pageContext.servletContext.contextPath }/resource/image/title.jpg"/>
				</a>
			</div>
			<div style="display: flex; justify-content: end">
				<c:choose>
					<c:when test="${result }">
					<div>
						<ul style="display: flex;justify-content: space-between;">
							<li><a href="${pageContext.servletContext.contextPath }/private/logout">LOGOUT</a></li>
							<li><a href="$#">MYPAGE</a></li>
							<li><a href="$#">CART</a></li>
							<li><a href="#">NOTICE</a></li>
							<li><a href="#">Q&A</a></li>
							<li><a href="#">REVIEW</a></li>
						</ul>
					</div>
					</c:when>
					<c:otherwise>
					<div>
						<ul style="display: flex;justify-content: space-between;">
							<li><a href="${pageContext.servletContext.contextPath }/view/login">LOGIN</a></li>
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
		<div>
			<div>
				<img src="${pageContext.servletContext.contextPath }${item.itemImg[0].itemimgUrl}">
			</div>
		</div>
	</div>
</body>
</html>