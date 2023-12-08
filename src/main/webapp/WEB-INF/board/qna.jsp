<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css"
	rel="stylesheet"></link>
<meta charset="UTF-8">
<meta name="viewport" content="width=divice-width, initial-scale=1.0">
<title>Q&A</title>
</head>
<body>
	<form action="${pageContext.servletContext.contextPath }/board/qnaForm">

		<table style="width: 90%">
			<tr>
				<th>NO.</th>
				<th>분류<th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>view</th>
			</tr>
			<c:choose>
				<c:when test="${list != null && list.size() >0 }">
					<c:forEach var="one" items="${list }">
						<input type = "hidden" name = "id" value = " ${one.id}"> 
						<tr class="openContent">
							<td>${one.id }</td>
							<td>${one.qnaCategory.name}</td>
							<td>${one.title }</td>
							<td>${one.writer }</td>
							<td>${one.regiDate }</td>
							<td>${one.viewCnt }</td>
						</tr>
						<tr style="display: none">
							<td colspan="4">${one.question }</td>
							<td><button type="submit" formaction= "#">수정</button></td>
							<td><button>삭제</button></td>
						</tr>
					</c:forEach>
					
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">현재 등록된 게시글이 없습니다.
						<td>
					</tr>
				</c:otherwise>
			</c:choose>


		</table>
		<button>글쓰기</button>
	</form>

	<script>
	[...document.querySelectorAll(".openContent")].forEach(function(elm) {
		elm.addEventListener("click",
				function(evt) {
			const $nextSibling = evt.target.parentNode.nextElementSibling;
			console.log($nextSibling);
			if ($nextSibling.style.display == 'none') {
				$nextSibling.style.display = '';
			} else {
				$nextSibling.style.display = 'none';
			}
		});
	});
	</script>

</body>
</html> 