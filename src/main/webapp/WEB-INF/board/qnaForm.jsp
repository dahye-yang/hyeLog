<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
input{
	border: none;
	width : 300px;
	margin-left : 10px;
	
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
<link
	href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css"
	rel="stylesheet"></link>
<meta charset="UTF-8">
<meta name="viewport" content="width=divice-width, initial-scale=1.0">

<title>Q&A작성</title>
</head>
<body>
	<div class="wrap">
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		<div style="margin-top: 50px; width: 70%;">
			<h2>Q&A작성</h2>
			<form action="${pageContext.servletContext.contextPath }/board/qnaForm"
				method="post">
				<table>
					<tr>
						<th>문의 분류</th>
						<td>
							<div>
								<select name="qnaType">
									<c:forEach var="one" items="${list}">
										<option>${one.id}.${one.name}</option>
									</c:forEach>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><c:choose>
								<c:when test="${exist }">
									<input name="writer">
								</c:when>
								<c:otherwise>
								${one.nickName}
							</c:otherwise>
							</c:choose></td>
					</tr>
		
					<tr>
						<th>제목</th>
						<td><input name="title"></td>
					</tr>
		
					<tr>
						<td><input type="radio" name="check" value="1" id="show" checked/><label
							for="show">공개</label></td>
						<td><input type="radio" name="check" value="2" id="secret" /><label
							for="secret">비공개</label></td>
					</tr>
		
					<tr>
						<td colspan="2"><textarea name="content"> </textarea></td>
					</tr>
		
				</table>
				<button style="margin-top: 10px">등록</button>
				<button style="margin-top: 10px" type="reset">취소</button>
		
			</form>
		</div>
	</div>
</body>
</html>