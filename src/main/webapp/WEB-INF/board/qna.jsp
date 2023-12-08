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
<div class="wrap">

	<div>
		<c:import url="/nav" />
	</div>
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
					
					<tr class="${found.nickName eq one.writer || one.openType eq 1 ? 'openContent' : ''} ">
						<td>${one.id }</td>
						<td>${one.qnaCategory.name}</td>
						<td>${one.title }</td>
						<td>${one.writer }</td>
						<td>${one.regiDate }</td>
						
					</tr>
					<c:if test="${found.nickName eq one.writer || one.openType eq 1}">
						<tr style="display: none">
							<td colspan="6">
								<form method="post" style="display: flex;">
									<input type="hidden" name="id" value="${one.id}">
									<div style="flex:1">${one.question }</div>
									
									<c:if test="${found!=null && found.nickName eq one.writer }">
									<button type="submit" onclick="checkDelete(event);"
										formaction="${pageContext.servletContext.contextPath }/board/qnaDelete">삭제</button>
									</c:if>
								</form>
							</td>

						</tr>

					</c:if>
				</c:forEach>

			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6"> 현재 등록된 게시글이 없습니다. <td>
				</tr>
			</c:otherwise>
		</c:choose>


	</table>
	<c:choose>
		<c:when test="${found != null }">
			<button type="button" onclick="location.href='${pageContext.servletContext.contextPath}/board/qnaForm'">글쓰기</button>
		</c:when>
		<c:otherwise>
			<button type="button" onclick="show()">글쓰기</button>
		</c:otherwise>
	</c:choose>
</div>
	<script>
	[...document.querySelectorAll(".openContent")].forEach(function(elm) {
		elm.addEventListener("click",
				function(evt) {
			const $nextSibling = evt.target.parentNode.nextElementSibling;
			// console.log($nextSibling);
			if ($nextSibling.style.display == 'none') {
				$nextSibling.style.display = '';
			} else {
				$nextSibling.style.display = 'none';
			}
		});
	});
	
	function show() {
		if(window.confirm("로그인시 이용 가능합니다.")){
			location.href = './../view/login';
		}
	}
	
	function checkDelete(evt){
		if(!window.confirm("삭제하시겠습니까?")){
			evt.preventDefault();
		}
	}

	
	</script>

</body>
</html>
