<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<style>
table,td,tr,th{
	border-left: none;
	border-right: none;
	border-top: none;
	border-radius: 0;
	border-bottom: 1px solid #444;
	border-collapse: collapse;
	}
</style>

<link href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css" rel = "stylesheet"></link>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>coupon</title>
</head>
<body>
	<div class="wrap">
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		
			<div style="text-align: center; margin-bottom: 20px">
				<p style="font-size: 40px;">my coupon</p>
			</div>
			
			<div style="text-align:center; font-size: 30px;">
				<P> 🤍 ${user.nickName }님의 총 보유 쿠폰 개수는  <b>총 ${count }개</b>입니다. 🤍</P>
			</div>
			<table style= "margin : auto; min-width: 80%; text-align:center;">
					<tr>
						<th>쿠폰명</th>
						<th>할인금액</th>
						<th>사용기한</th>
					</tr>
					<c:forEach var="one" items="${list }">
						<tr style="height:60px">
							<td style="text-align: center; width:40%">${one.coupon.alt}</td>
							<td style="width:30 %">${one.coupon.discount}%</td>
							<td>~${one.expDate }까지</td>
						</tr>
					</c:forEach>
				</table>
			
			<table>
			</table>
		

	</div>
</body>
</html>