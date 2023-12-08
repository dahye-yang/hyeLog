<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css" rel = "stylesheet"></link>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>HyeLog</title>
</head>
<body>
<div class="wrap">
<c:import url="/nav" />
	<div class="wrap" style="max-width: 960px; margin: auto">
		<div style="width: 360px; text-align: center; margin: auto">
			<h2>Login - hyelog</h2>
		</div>
		
		<div>
				<c:if test="${error }">
					<div style="background-color: red">
					아이디 또는 비밀번호가 일치하지 않습니다.</div>
				</c:if>
		</div>
		
		<div style="width: 360px; text-align: center; margin: auto">
			<form action="${pageContext.servletContext.contextPath }/view/login"
				method="post" style="width: 400px; line-height: 1.2">
				<div style="padding: 5px">
					<div>✔️ ID</div>
					<input class="width100" type="text" name="loginId" />
				</div>
				<div style="padding: 5px">
					<div>✔️ PASSWORD</div>
					<input class="width100" type="password" name="loginPassword" />
				</div>
				<div style="text-align: center">
					<button style="width: 150px" type="submit">로그인</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>