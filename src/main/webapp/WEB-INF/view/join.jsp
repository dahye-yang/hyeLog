<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HyeLog</title>
</head>
<body>
	<div style="max-width: 960px; margin: auto">
		<div style="width: 360px; text-align: center; margin: auto">
			<h1>HyeLog</h1>
			<h2>Join - hyelog</h2>
		</div>
		<div style="width: 360px; text-align: center; margin: auto">
			<form action="${pageContext.servletContext.contextPath }/view/join"
				method="post" style="width: 400px; line-height: 1.2">
				<c:if test="${error }">
					<div style="background-color: red">
					'${sessionScope.found.id }' 은/는 이미 존재하는 사용중인 아이디입니다.</div>
				</c:if>
				<div style="padding: 5px">
					<div>✔️ ID</div>
					<input class="width100" type="text" name="id" />
				</div>
				<div style="padding: 5px">
					<div>✔️ PASSWORD</div>
					<input class="width100" type="password" name="password" />
				</div>
				<div style="padding: 5px">
					✔️ NICKNAME<br/>
					<input class="width100" type="text" name="nickName" /><br />
				</div>	
				<div style="text-align: center">
					<button style="width: 150px" type="submit">회원가입</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>