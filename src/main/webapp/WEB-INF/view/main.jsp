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
		<c:import url="/nav" />
		<div> <!-- 나중에 item findAll 해서 가지고오면 확장for문으로 뽑기 -->
			<div><img src="${pageContext.servletContext.contextPath }${img[0] }"></div>
			<div>${item.name }</div>
			<div>${item.detail }</div>
			<div><fmt:formatNumber value="${item.price }" pattern="#,###"/></div>
		</div>
	</div>
</body>
</html>