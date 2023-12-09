<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<div>
			<ul style="display: flex; justify-content: center; padding:0; flex-wrap: wrap;">
				<c:forEach var="one" items="${itemlist }">
					<li style="width:30%; margin:0;  padding:15px;">
						<div>
							<a
								href="${pageContext.servletContext.contextPath }/view/detail?code=${one.code}">
								<img style="width: 100%" src="${pageContext.servletContext.contextPath }${one.itemImg[0].itemimgUrl}">
							</a>
						</div>
						<div><a style="text-decoration: none;" href="${pageContext.servletContext.contextPath }/view/detail?code=${one.code}">
						${one.name }</a></div>
						<div>${one.detail }</div>
						<div>
							<fmt:formatNumber value="${one.price }" pattern="#,###" />
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>