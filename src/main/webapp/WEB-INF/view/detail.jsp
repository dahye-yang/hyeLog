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
	href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css"
	rel="stylesheet"></link>
<title>HYELOG</title>
</head>
<body>
	<div class="wrap">
		<c:import url="/nav" />
		<div
			style="display: flex; justify-content: center; padding-bottom: 100px; padding-top: 60px">
			<div>
				<img
					src="${pageContext.servletContext.contextPath }${item.itemImg[0].itemimgUrl}">
			</div>
			<div style="text-align: left; padding-left: 88px">
				<div style="margin-bottom: 20px">
					<b>${item.name }</b>
				</div>
				<div>${item.detail }</div>
				<hr />
				<div style="margin-top: 20px">
					판매가 :
					<fmt:formatNumber value="${item.price }" pattern="#,###" />
				</div>
				<div style="margin-bottom: 5px">
					구매수량 : <input type="number" value="0" id="piece">
				</div>
				<hr />
				<div
					style="margin-top: 20px; display: flex; justify-content: space-between;">
					<div>
						<b>Total Price : </b>
					</div>
					<div>26,000원</div>
				</div>
				<div style="display: flex; justify-content: space-between;">
					<a>구매하기</a> <a>장바구니</a> <a>찜콩하기</a>
				</div>
			</div>
		</div>
		<div>
			<div style="padding-bottom: 50px">
				<div style="text-align: center; margin-bottom: 20px">
					<p style="font-size: 40px;">DETAIL</p>
				</div>
				<div style="text-align: center">
					<c:forEach var="one" items="${item.itemImg }">
						<div>
							<img
								src="${pageContext.servletContext.contextPath }${one.itemimgUrl}">
						</div>
					</c:forEach>
				</div>
			</div>
			<div>
				<div style="text-align: center; margin-bottom: 20px">
					<p style="font-size: 40px;">REVIEW</p>
				</div>
				<div style="text-align: right; padding-bottom: 20px">
					<c:choose>
						<c:when test="${found ne null }">
							<a
								href="${pageContext.servletContext.contextPath }/private/review?code=${item.code}">
								<button type="button">리뷰작성</button>
							</a>
						</c:when>
						<c:otherwise>
							<a href="#" onclick="return false;">
								<button type="button">리뷰작성</button>
							</a>
						</c:otherwise>
					</c:choose>
				</div>
				<hr />
				<div style="width:85vw; margin:auto; ">
					<c:forEach var="one" items="${reviewlist }">
						<div style="padding:20px; border-bottom: 1px solid #444">
							<div>						
								<div style="display: flex; justify-content: space-between; align-items: center">
									<div>
										<c:forEach begin="1" end="${one.score }">
											⭐
										</c:forEach>
									</div>
									<div style="text-align: right;">작성자 : ${one.writer }</div>
								</div>
								<div></div>
								<div>${one.message }</div>
							</div>
							<div style="text-align: right;">
								<c:if test="${found.id eq one.writer }">
										<form action="${pageContext.servletContext.contextPath }/private/reviewfix" style="display: inline;">
											<input type="hidden" name = "writer" value="${found.id }">
											<input type="hidden" name = "reviewId" value="${one.id }">
											<input type="hidden" name = "categoryId" value="${item.categoryId }">
											<input type="hidden" name = "code" value="${item.code }">
											<button type="submit">수정하기</button>
										</form>
										<form action="${pageContext.servletContext.contextPath }/private/reviewdelete" style="display: inline;">
											<input type="hidden" name = "reviewId" value="${one.id }"> <!-- 리뷰삭제를 위한 리뷰아이디 -->
											<input type="hidden" name = "categoryId" value="${item.categoryId }"> <!-- 삭제후 해당 상세페이지로 가기 위한 카테고리아이디 -->
											<button type="submit">삭제하기</button>
										</form>
								</c:if>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>