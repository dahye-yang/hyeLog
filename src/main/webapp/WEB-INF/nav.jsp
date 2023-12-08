<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div style="display: flex; justify-content: space-between;">
		<div>
			<a href="${pageContext.servletContext.contextPath }/view/main"> <img
				src="${pageContext.servletContext.contextPath }/resource/image/title.jpg" /></a>
		</div>
		<div style="display: flex; justify-content: end">
			<c:choose>
				<c:when test="${found ne null }">
					<div>
						<ul style="display: flex; justify-content: space-between;">
							<li><a
								href="${pageContext.servletContext.contextPath }/private/logout">LOGOUT</a></li>
							<li><a
								href="${pageContext.servletContext.contextPath }/private/myshop">MYPAGE</a></li>
							<li><a href="#">CART</a></li>
							<li><a href="#">NOTICE</a></li>
							<li><a href="${pageContext.servletContext.contextPath }/board/qna">Q&A</a></li>
							<li><a
								href="${pageContext.servletContext.contextPath }/view/reviewmain">REVIEW</a></li>
						</ul>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<ul style="display: flex; justify-content: space-between;">
							<li><a
								href="${pageContext.servletContext.contextPath }/view/login">LOGIN</a></li>
							<li><a
								href="${pageContext.servletContext.contextPath }/view/join">JOIN</a></li>
							<li><a href="#">NOTICE</a></li>
							<li><a href="${pageContext.servletContext.contextPath }/board/qna">Q&A</a></li>
							<li><a
								href="${pageContext.servletContext.contextPath }/view/reviewmain">REVIEW</a></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div style="display: flex; justify-content: space-between;">
		<div>
			<ul style="display: flex; justify-content: center; gap: 30px">
				<c:forEach var="one" items="${listx }">
					<li><a
						href="${pageContext.servletContext.contextPath }/product/categorylist?categoryId=${one.id}">
							${one.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div>
			<a href="${pageContext.servletContext.contextPath }/private/myshop">
				<img src="${pageContext.servletContext.contextPath }/resource/image/mypage.png">
			</a> 
			<a href="${pageContext.servletContext.contextPath }/private/order/cart">
				<img src="${pageContext.servletContext.contextPath }/resource/image/cart.png">
			</a>
		</div>
	</div>
</div>
