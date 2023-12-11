<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css"
	rel="stylesheet"></link>
<title>HYELOG</title>
<style>
input{
	border: 1px solid #444;
	width : 300px;
	margin-left : 10px;
	outline: black;
}
table,td,tr,th{
	border-left:none;
	border-right: none;
	border-radius: 0;
	border-bottom: 1px solid #444;
	border-collapse: collapse;
}
th{
	background-color: #e9ecef;
}
tr{
	height: 60px
}
</style>
</head>
<body>
	<div class="wrap">
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		<div style="padding-left: 20%; padding-right: 20%; margin-top: 100px;">
			<div style="padding-top: 30px">
				<div>
					<h2 style="font-size: 70px">JOIN</h2>
				</div>
				<div>
					회원가입을 하시면 편하게 쇼핑하실 수 있습니다.
				</div>
			</div>
			<div style="padding-top: 30px">
				<form action="${pageContext.servletContext.contextPath }/view/join" method="post">
					<c:if test="${error }">
						<div style="background-color: red">
						'${sessionScope.found.id }' 은/는 이미 존재하는 사용중인 아이디입니다.</div>
					</c:if>
					<div>
						<table style="width:100%;">
							<colgroup>
								<col style="width :150px;">
								<col style="width :auto">
							</colgroup>
							<tbody>
								<tr>
									<th style="text-align: left;"><span style="color: red">*</span> 아이디 </th>
									<td>
										<input type="text" name="id" />
									</td>
								</tr>
								<tr>
									<th style="text-align: left;"><span style="color: red">*</span> 비밀번호 </th>
									<td>
										<input type="password" name="password" />
									</td>
								</tr>
								<tr>
									<th style="text-align: left;"><span style="color: red">*</span> 닉네임</th>
									<td>
										<input type="text" name="nickName" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>	
					<div style="text-align: center; margin-top: 20px">
						<button style="width: 150px" type="submit">회원가입</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>