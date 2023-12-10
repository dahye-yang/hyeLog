<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>

</style>
<link href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css" rel = "stylesheet"></link>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>HYELOG</title>
</head>
<body>
	<div class="wrap">
		<div>
			<c:import url="/nav" />
		</div>
	
		<div style="text-align: center; margin-bottom: 20px">
			<p style="font-size: 40px;">구매내역조회</p>
		</div>
		
		

			<table style="margin: auto; min-width: 80%">
				<tr style="height: 60px">
					<th>주문날짜</th>
					<th>주문번호</th>
					<th>이미지</th>
					<th>상품명</th>
					<th>수량</th>
					<th>상품구매금액</th>
				</tr>
				<c:forEach var="one" items="${list }">
					<tr style="height: 60px">
						<td>${one.buyDate}</td>
						<td>${one.id}</td>
						<td><img style="width: 100px; height: 100px" src="${pageContext.servletContext.contextPath }${one.item.image } "></td>
						<td>${one.item.name }</td>
						<td>${one.piece }</td>
						<td>${one.price} </td>
					<tr>
				</c:forEach>
					<tr>
						<td colspan="7"> 총 합계 : ${sum }</td>
					</tr>
			</table>
	
	
	
	</div>

</body>
</html>