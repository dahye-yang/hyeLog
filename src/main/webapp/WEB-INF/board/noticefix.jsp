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
			<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
			<div style="text-align: center; margin-bottom: 20px">
				<p style="font-size: 40px;">NOTICE</p>
			</div>
		</div>
		<div>
			<form action="${pageContext.servletContext.contextPath }/board/noticefix" method="post">
			<input type="hidden" name="id" value="${noticeone.id }">
				<table style="width: 70%;margin: auto">
					<tr>
						<td class="textcenter">제목</td>
						<td><input type="text" name="title" value="${noticeone.title }"></td>
					</tr>
					<tr>
						<td class="textcenter">작성자</td>
						<td>HYELOG</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="message" rows="3" cols="80">${noticeone.message }</textarea>
						</td>
					</tr>
				</table>
				<div style="text-align: center; padding-top: 20px">
					<button type="submit">수정하기</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>