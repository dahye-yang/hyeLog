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
			<c:import url="/nav" />
		</div>
		<div style="text-align: center; margin-bottom: 20px">
			<p style="font-size: 40px;">NOTICE</p>
		</div>
		<div>
			<table style="width: 70%;margin: auto">
					<tr>
						<td class="textcenter">제목</td>
						<td colspan="2">${one.title }</td>
					</tr>
					<tr>
						<td class="textcenter">작성자</td>
						<td colspan="2">HYELOG</td>
					</tr>
					<tr>
						<td colspan="3">
							${one.message }
						</td>
					</tr>
			</table>
		</div>
		<div style="width: 70%;margin: auto; padding-top: 10px; display: flex;justify-content: space-between;">
			<div>
				<a href="${pageContext.servletContext.contextPath }/view/noticemain">
					<button type="button">목록으로</button>
				</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath }/board/noticefix?id=${one.id}">
					<button type="button">수정하기</button>
				</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath }/board/noticedelete?id=${one.id}">
					<button type="button">삭제하기</button>
				</a>
			</div>
		</div>
	</div>
</body>
</html>