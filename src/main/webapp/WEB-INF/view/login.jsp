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
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		<div style="padding-left: 20%; padding-right: 20%; margin-top: 130px;">
			<div>
				<h2 style="font-size: 70px">LOGIN</h2>
			</div>
			<div>
				<c:if test="${error }">
					<div style="background-color: red">
					아이디 또는 비밀번호가 일치하지 않습니다.</div>
				</c:if>
			</div>
			<div  style="padding-top: 30px">
				<form action="${pageContext.servletContext.contextPath }/view/login"
					method="post" style="width: 400px; line-height: 1.2">
					<div style="padding: 5px; border-bottom: 1px solid #88AB8E">
						<div>✔️ ID</div>
						<input style="width: 100%" type="text" name="loginId" />
					</div>
					<div style="padding: 5px; border-bottom: 1px solid #88AB8E; margin-top: 10px ">
						<div>✔️ PASSWORD</div>
						<input style="width: 100%" type="password" name="loginPassword" />
					</div>
					<div style=" margin-top: 30px; margin-bottom: 30px">
						<button style="width: 100%; background-color:#F2F1EB; border: none; " type="submit">login</button>
					</div>
					<hr style="color: #88AB8E"/>
					<div style="margin-top: 30px">
						<div><h4>HYELOG의 멤버쉽 혜택</h4></div>
						<div>
							알찬 쇼핑이 되실 수 있도록<br/>
							적립금과 쿠폰, 이벤트가 준비됩니다.<br/>
							등급별 혜택과 연말 선물을 받아보세요!
						</div>
						<div style=" margin-top: 30px">
							<a href="${pageContext.servletContext.contextPath }/view/join">
								<button type="button" style="width: 100%; background-color:#F2F1EB;  border: none;">join</button>
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>