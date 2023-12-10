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
<script>
</script>
</head>
<body>
	<div class="wrap">
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		<div style="margin-top: 50px;">
			<div>주문정보</div>
			<div>주문자 : ${found.nickName } </div>
		</div>
	<form action="${pageContext.servletContext.contextPath }/private/order/buyall" method="post">
		<c:forEach var="one" items="${list }">
		<input type="hidden" name="cartId" value="${one.id}">
		<input type="hidden" name="itemCode" value="${one.itemCode}">
			<div style="display: flex; justify-content: flex-start;">
				<div>
					<img style="width: 100px; height: 100px" alt="상품사진" src="${pageContext.servletContext.contextPath }${one.item.image}">
				</div>
				<div>
					<div>${one.item.name }</div>
					<div>수량 : ${one.cartPiece }개</div>
					<div><fmt:formatNumber value="${one.cartPiece * one.item.price }" pattern="#,###"/> </div>
				</div>
			</div>
		</c:forEach>
			<div>
				<div>결제정보</div>
				<div>
					<div style="display: flex;justify-content: flex-start;">
						<div>주문상품</div>
						<div>
							<input type="hidden" name="price" value="${sum }" /> 
							<fmt:formatNumber value="${sum }" pattern="#,###"/>
						</div>
					</div>
					<div>
						<div>할인금액</div>
						<div>할인금액을 넣어줘여기다가</div>
					</div>
				</div>
				<div>
					<div>최종결제금액</div>
					<div><fmt:formatNumber value="${sum }" pattern="#,###"/></div>
				</div>
			</div>
			<div style="display: flex;justify-content: flex-start">
				<div>적립 혜택</div>
				<div>
				<input type="hidden" name="point" value="${sum / 100 }"/>
				<fmt:formatNumber value="${sum / 100 }" pattern="#,###"/>
				</div>
			</div>
			<button type="submit">
				<div>
					<fmt:formatNumber value="${sum }" pattern="#,###"/> 결제하기
				</div>
			</button>
		</form>
	</div>
</body>
</html>