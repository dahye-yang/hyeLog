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
<style>
table,td,tr,th{
	border-left: none;
	border-right: none;
	border-top: none;
	border-radius: 0;
	border-bottom: 1px solid #444;
	border-collapse: collapse;
	text-align: center;
}
</style>
<title>HYELOG</title>
</head>
<body>
	<div class="wrap">
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		<div style="width: 100%; margin-top: 50px;">
			<div style="text-align: center; margin-bottom: 20px">
				<p style="font-size: 40px;">Q&A</p>
			</div>
		<div>
			<table style= "margin : auto; min-width: 80%; text-align: center;">
				<tr>
					<th>NO.</th>
					<th>분류<th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:choose>
					<c:when test="${list != null && list.size() >0 }">
						<c:forEach var="one" items="${list }">
							
							
							<tr class="${found.nickName eq one.writer || one.openType eq 1 ? 'openContent' : ''} " style="height:60px;">
								<td style="text-align: center; width:5%">${one.id }</td>
								<td style = "width: 15%;">${one.qnaCategory.name}</td>
								<td>${one.title }</td>
								<td style = "width: 15%;">${one.writer }</td>
								<td style = "width: 25%;" >${one.regiDate }</td>
								
							</tr>
							<c:if test="${found.nickName eq one.writer || one.openType eq 1}">
								<tr style="display: none">
									<td colspan="6">
										<form method="post" style="display: flex;">
											<input type="hidden" name="id" value="${one.id}">
											<div style="flex:1; height:100px; margin: auto"> <!-- 펼쳤을때 -->
											<p>🤍질문 내용🤍</p> 
											
											<p>${one.question }</p>
											</div>
											
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
		</div>
		<div style= "margin : auto; text-align: right; margin-top: 15px; margin-left: 10%; margin-right: 10%">
			<c:choose>
				<c:when test="${found != null }">
					<div>
						<button type="button" onclick="location.href='${pageContext.servletContext.contextPath}/board/qnaForm'">글쓰기</button>
					</div>
				</c:when>
				<c:otherwise>
					<div style="display: inline-block; margin: 0 5px;  float: right;">
						<button type="button" onclick="show()" class="align-right">글쓰기</button>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
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
