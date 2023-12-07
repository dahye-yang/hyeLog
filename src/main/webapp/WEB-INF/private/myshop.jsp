<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css" rel = "stylesheet"></link>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>myshop</title>
</head>
<body>

<%@ include file="/WEB-INF/nav/private.jsp" %>

<br>
<h2>
<a href = "${pageContext.servletContext.contextPath }/private/changeUser">
<img src="${pageContext.servletContext.contextPath }${sessionScope.logonUser.level.levelImg }" style="width: 30px;"/>
 ${logonUser.nickName } 님, 안녕하세요</a>
</h2>


<div>당신의 현재 등급은 ${logonUser.levelId }LV 입니다. </div>
<div>다음 등급까지는 </div>
<p>


<span class="align-center" >

<a href ="${pageContext.servletContext.contextPath }/private/order/cart"> 
<img src="${pageContext.servletContext.contextPath }/resource/image/shopping-cart.png" style ="width: 40px"/>장바구니</a>
</span>


<span class="align-center">
<a  href ="${pageContext.servletContext.contextPath }/private/myshop/coupon">
<img src="${pageContext.servletContext.contextPath }/resource/image/coupon.png" style ="width: 40px"/>쿠폰 확인</a>
</span>
<span class="align-center">
<a  href ="${pageContext.servletContext.contextPath }/private/myshop/point">
<img src="${pageContext.servletContext.contextPath }/resource/image/dollar.png" style ="width: 40px"/>적립금 확인</a>
</span>


<span class="align-center">
<a  href ="${pageContext.servletContext.contextPath }/private/myshop/orderlist">
<img src="${pageContext.servletContext.contextPath }/resource/image/order.png" style ="width: 40px"/>주문조회</a>
</span>

</p>


</body>
</html>