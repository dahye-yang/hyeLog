<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HYELOG</title>
<link href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css" rel = "stylesheet"></link>
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
			<c:import url="/nav" />
		</div>
		<div style="width: 80%; margin: auto;">
			<div style="text-align: center; margin-bottom: 20px">
				<p style="font-size: 40px;">REVIEW</p>
			</div>
		
				<table style= "margin : auto; min-width: 80%">
					<tr>
						<th>번호</th>
						<th>상품명</th>
						<th>리뷰내용</th>
						<th>평점</th>
						<th>작성자</th>
					</tr>
					<c:forEach var="one" items="${list }">
						<tr style="height:60px">
							<td style="text-align: center; width:5%">${one.id }</td>
							<td style="width: 18%;">${one.item.name }</td>
							<td>${one.message }</td>
							<td style="width: 20%;">
								<c:forEach begin="1" end="${one.score }">
									⭐
								</c:forEach>
							</td>
							<td>${one.writer }</td>
						</tr>
					</c:forEach>
					
				</table>
		</div>
	</div>
</body>
</html>