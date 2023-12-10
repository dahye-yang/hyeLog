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
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		<div style="margin-top: 30px ;width: 70%; margin: auto; display: flex;justify-content: center;">
			<div style="width: 30%"><!-- 아이템사진 -->
				<img style="width: 100%" src="${pageContext.servletContext.contextPath }${item.itemImg[0].itemimgUrl }"> 
			</div>
			<div style="padding-left: 30px">
				<div><!-- 아이템정소 -->
					${item.name }
				</div>
				<div>
					${item.detail }
				</div>
			</div>
		</div>
		<div>
			<form action="${pageContext.servletContext.contextPath }/private/reviewfix" method="post">
				<input type="hidden" name="id" value="${one.id}">
				<input type="hidden" name="categoryId" value="${categoryId}">
				<input type="hidden" name="itemCode" value="${one.itemCode}">
				<table style="width: 70%;margin: auto">
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer" value="${writer }" placeholder="${writer }"></td>
					</tr>
					<tr> <!-- score 적용해서 파라미터넘기구 -->
						<td>평점</td>
						<td><input type="radio" name="score" value="1">⭐
							<input type="radio" name="score" value="2">⭐⭐
							<input type="radio" name="score" value="3">⭐⭐⭐
							<input type="radio" name="score" value="4">⭐⭐⭐⭐
							<input type="radio" name="score" value="5">⭐⭐⭐⭐⭐
						</td>
					</tr>
					<tr> <!-- 리뷰상세내용도 파라미터로 넘겨주기 -->
						<td>상세리뷰</td>
						<td>
							<textarea name="message" rows="3" cols="80">${one.message }</textarea>
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