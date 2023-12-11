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
</head>
<body>
	<div class="wrap">
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		<div style="text-align: center; margin-bottom: 20px; margin-top: 50px;">
			<p style="font-size: 40px;">NOTICE</p>
		</div>
		<div>
			<table style= "margin : auto; min-width: 80%">
				<tr style="height:60px">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
				<c:forEach var="one" items="${noticelist }">
					<tr style="height:60px">
						<td class="textcenter">${one.id }</td>				
						<td onclick="location.href='${pageContext.servletContext.contextPath}/view/noticedetail?id=${one.id }'">${one.title}</td>
						<td class="textcenter">HYELOG</td>
					</tr>
				</c:forEach>				
			</table>
			<c:if test="${found.id eq 'admin' }">
				<div style="margin : auto; min-width: 80%; text-align: right;">
					<a href="${pageContext.servletContext.contextPath}/board/notice">
						<button type="button">등록하기</button>				
					</a>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>