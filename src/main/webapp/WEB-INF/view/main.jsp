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
				<img src="${pageContext.servletContext.contextPath }/resource/image/title.jpg"/>
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
		<div style="display: flex; justify-content: space-between;">
			<div>
				<ul style="display: flex; justify-content: center; gap: 30px">
					<c:forEach var="one" items="${categorys }">
					<li><a href="${pageContext.servletContext.contextPath }/product/categorylist?categoryId=${one.id}">${one.name }</a></li>
					</c:forEach>
				</ul>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath }/private/myshop">
					<img src="${pageContext.servletContext.contextPath }/resource/image/mypage.png"></a>
				<a href="${pageContext.servletContext.contextPath }/private/order/cart">	
					<img src="${pageContext.servletContext.contextPath }/resource/image/cart.png">
				</a>
			</div>
		</div>
		<br/>
		<div> <!-- 나중에 item findAll 해서 가지고오면 확장for문으로 뽑기 -->
			<div><img src="${pageContext.servletContext.contextPath }${img[0] }"></div>
			<div>${item.name }</div>
			<div>${item.detail }</div>
			<div><fmt:formatNumber value="${item.price }" pattern="#,###"/></div>
		</div>
	</div>
</body>
</html>