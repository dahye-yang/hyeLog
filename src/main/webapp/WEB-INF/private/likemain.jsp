<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="${pageContext.servletContext.contextPath }/resource/stylesheet/style.css"
	rel="stylesheet"></link>
<style>
table, td, tr, th {
	border-left: none;
	border-right: none;
	border-top: none;
	border-radius: 0;
	border-bottom: 1px solid #444;
	border-collapse: collapse;
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
		<div style="text-align: center; margin-bottom: 20px; margin-top: 50px;">
			<p style="font-size: 40px;">LIKE</p>
		</div>
		<div>
			<form id="kiroko" method="post">
				<table style="margin: auto; min-width: 80%">
					<tr style="height: 60px">
						<th></th>
						<th>이미지</th>
						<th>상품정보</th>
						<th>판매가</th>
						<th>선택</th>
					</tr>
				<c:forEach var="one" items="${list }">
					<tr style="height: 60px">
						<td><input type="checkbox" name="check"/> </td>
						<td style="cursor: pointer; width: 10%"
							onclick="location.href='${pageContext.servletContext.contextPath}/view/detail?code=${one.itemCode }'">
							<img style="width: 100px; height: 100px"
							src="${pageContext.servletContext.contextPath }${one.item.image }">
						</td>
						<td style="cursor: pointer"
							onclick="location.href='${pageContext.servletContext.contextPath}/view/detail?code=${one.itemCode }'">${one.item.name}</td>
						<td id="piece" class="textcenter">
							<input type="hidden" name="price" value="${one.item.price }" />
							<fmt:formatNumber value="${one.item.price }" pattern="#,###"/> 
						</td>
						<!-- 선택버튼 -->
						<td style="text-align: center">	
							<div style="display: block;">
								<div>
									<input type="hidden" name="piece" value="1"/>
									<button type="submit" style="padding-left: 10px; padding-right: 10px"
										onclick="addCartSubmit(${one.item.code})">장바구니에 넣기</button>
								</div>
								<div style="margin-top: 3px;">
									<input type="hidden" name="cartid" value=""/> <!-- 장바구니 value 틀림 -->
									<input type="hidden" name="itemcode" value="${one.item.code }"/>
									<input type="hidden" name="deleteNo" id="deleteNo"/>
									<button type="button" onclick="deleteSubmit(${one.item.code})">삭제하기</button>
								</div>
						 	</div>
						 </td>
						<!-- 총 정보 나열?.. -->
					</tr>
				</c:forEach>
			</table>
			</form>
		</div>
	</div>
	<script>
	// 찜하기에서 삭제하기
	function deleteSubmit(id){
		document.getElementById("deleteNo").value =id;
		document.getElementById("kiroko").action = "${pageContext.servletContext.contextPath}/private/likedelete";
		document.getElementById("kiroko").submit();
	}
	
	function addCartSubmit(id){
		document.getElementById("deleteNo").value =id;
		document.getElementById("kiroko").action = "${pageContext.servletContext.contextPath}/private/order/cart";
		document.getElementById("kiroko").submit();
	}
	</script>
</body>
</html>