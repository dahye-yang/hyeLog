<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css"
	rel="stylesheet"></link>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HyeLog</title>
</head>
<body>
	<div class="wrap" style="max-width: 960px; margin: auto">
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
	
		<div>
			<h1>HyeLog</h1>
			<h2>회원탈퇴 - hyelog</h2>
		</div>
		<hr/>
		<div style="width: 360px; text-align: left; margin: auto">
			<form action="${pageContext.servletContext.contextPath }/private/delete"
				method="post" style="width: 400px; line-height: 1.2">
				<div style="padding: 5px">
					<p>${found.id }님 정말 떠나시겠습니까?</p>
					<p>지금 떠나신다면 현재 보유중인 ${sum } Point는 사라집니다
					
				</div>
				<div style="padding: 5px">
					<div>✔️ 현재 PASSWORD</div>
					<input style="border-bottom: 1px solid #444; width:100%" class="width100" type="password" name="deletepassword" />
				</div>
				<div style="text-align: center">
					<button style="width: 150px" type="submit">회원탈퇴</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>