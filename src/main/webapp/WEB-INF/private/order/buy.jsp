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
	<div class="wrap">
		<div>
			<div style="text-align: center; margin-bottom: 20px">
				<a href="${pageContext.servletContext.contextPath }/view/main">
				<p style="font-size: 40px;">HYELOG</p></a>
			</div>
			<div style="text-align: right">
				<a href="${pageContext.servletContext.contextPath }/private/myshop">
					<img src="${pageContext.servletContext.contextPath }/resource/image/mypage.png">
				</a> 
				<a href="${pageContext.servletContext.contextPath }/private/order/cartmain">
					<img src="${pageContext.servletContext.contextPath }/resource/image/cart.png">
				</a>
			</div>
		</div>
		<div style="">
			<div>주문정보</div>
			<div>주문자 : ${found.nickName } </div>
		</div>
		<div style="display: flex; justify-content: flex-start;">
			<div>
				<img style="width: 100px; height: 100px" alt="상품사진" src="${pageContext.servletContext.contextPath }${two.item.image}">
			</div>
			<div>
				<div>${two.item.name }</div>
				<div>수량 : ${two.cartPiece }</div>
				<div>${two.cartPiece * two.item.price }</div>
			</div>
		</div>
		<form action="${pageContext.servletContext.contextPath }/private/order/buy" method="post">
		<input type="hidden" name="itemcode" value="${code }">
		<input type="hidden" name="piece" value="${piece}">
			<div>
				<div>결제정보</div>
				<div>
					<div style="display: flex;justify-content: flex-start;">
						<div>주문상품</div>
						<div>
							<input type="hidden" name="price" value="${two.item.price * piece }" /> 
							<fmt:formatNumber value="${two.item.price * piece }" pattern="#,###"/>
						</div>
					</div>
					<div>
						<div>할인금액</div>
						<div>할인금액을 넣어줘여기다가</div>
					</div>
				</div>
				<div>
					<div>최종결제금액</div>
					<div><fmt:formatNumber value="${two.item.price * piece }" pattern="#,###"/></div>
				</div>
			</div>
			<div style="display: flex;justify-content: flex-start">
				<div>적립 혜택</div>
				<div>
				<input type="hidden" name="point" value="${(two.item.price * piece) / 100 }"/>
				<fmt:formatNumber value="${(two.item.price * piece) / 100 }" pattern="#,###"/>
				</div>
			</div>
			<button type="submit">
				<div>
					<fmt:formatNumber value="${two.item.price * piece }" pattern="#,###"/> 결제하기
				</div>
			</button>
		</form>
	</div>
</body>
</html>