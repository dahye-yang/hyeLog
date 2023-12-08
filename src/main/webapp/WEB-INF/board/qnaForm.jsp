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
<title>Q&A작성</title>
</head>
<body>
<h2> Q&A작성 </h2>

<form action = "${pageContext.servletContext.contextPath }/board/qnaForm" method="post">
	<table>
		<tr>
			<th>문의 분류</th>
			<td>
			<div>
				<select name="qnaType">
					<c:forEach var="one" items="${list}">
						<option >${one.id}.${one.name}</option>
					</c:forEach>
				</select>
			</div>
			
			</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>
				<c:choose>
					<c:when test="${exist }">
						<input name = "writer">
					</c:when>
					
					<c:otherwise>
						${SessionScope.logonUser.nickName}
					</c:otherwise>
				
				</c:choose>
			
			</td>
		</tr>
		
		<tr>
			<th>제목</th>
			<td><input name = "title"></td>
		</tr>
		
		<tr>
		<td>
		<input type="radio" name="check" value="1" id="show"/><label for="show">공개</label></td>
		<td><input type="radio" name="check" value="2" id="secret"/><label for="secret">비공개</label></td>
		</tr>
		
		<tr >
			<td colspan="2"><textarea name = "content"> </textarea> </td>
		</tr>
		
	</table>
		<button>등록</button> <button type ="reset">취소</button>
	
</form>



</body>
</html>