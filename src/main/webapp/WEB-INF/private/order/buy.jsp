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
	<div class="wrap">
		<div>
			<div style="text-align: center; margin-bottom: 20px">
				<p style="font-size: 40px;">HYELOG</p>
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
</body>
</html>