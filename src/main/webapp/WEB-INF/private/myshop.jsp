<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.wrapper1 {
  display: flex;
  text-align: center;
  
  
}

	.wrapper1 > div {
  flex: 1;
  text-align: center;
  padding :20px;
}
	.wrapper > div {
  border-radius: 5px;

  text-align:center;
  margin:auto;
  padding: 1em;
}

.wrapper2 {
  display: grid;
  border: solid gray;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 100px 100px;
  grid-gap: 10px;
  width: 100%;
  height: 100px;
}

.box1 {
  display:inline-block
  grid-column: 1 / 4;
  grid-row: 1;
}

.box2 {
  display:inline-block
    grid-column: 1 / 4;
  grid-row: 1;
}

.box3 {
   display:inline-block
    grid-column: 1 / 4;
  grid-row: 1;
}

.box4 {
   display:inline-block
    grid-column: 1 / 4;
  grid-row: 1;
  
.wrapper3 {
  display: grid;
}
/*이미지와 글씨 중앙정렬*/
  .wrapper3 > div {
  border-radius: 5px;
  padding: 1em;
  text-align:center;
  margin: auto;
}

  
}
</style>
<link
	href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css"
	rel="stylesheet"></link>
<meta charset="UTF-8">
<meta name="viewport" content="width=divice-width, initial-scale=1.0">
<title>HYELOG</title>
</head>
<body>
	<div class="wrap align-center">
		<div>
			<c:import url="/nav" />
		</div>

		<br>
		
		<div class="wrapper1">
  		<div class="box1">
		<h2>
			<img src="${pageContext.servletContext.contextPath }${sessionScope.logonUser.level.levelImg }" style="width: 30px;" /> ${logonUser.nickName } 님, 안녕하세요			
		</h2>

		<div>당신의 현재 등급은 <b> ${logonUser.levelId }LV </b> 입니다.🤍</div>
	
		<p>

		</div>
 		<div class="box2"><a href="${pageContext.servletContext.contextPath }/private/changeUser"> 회원정보 수정하기 </a></div>
	</div>
	<br>
	
		
		
		
		
		
		
		<div class="wrapper2">
		  <div class="box1">
		  	<p class="align-center">  
		  	<a href="${pageContext.servletContext.contextPath }/private/delete">
			<div class="wrapper3">
			<div class="b1"><img src="${pageContext.servletContext.contextPath }/resource/image/user.png" style="width: 40px"/></div>
			<div class="b2">회원탈퇴</div>
			</div>
		</a>		
		  </div>
		  

		  <div class="box2">		  
		  	<a href="${pageContext.servletContext.contextPath }/private/myshop/coupon">
			<div class="wrapper3">
			<div class="b1"> <img src="${pageContext.servletContext.contextPath }/resource/image/coupon.png" style="width: 40px" /></div>
			<div class="b2"> 쿠폰 확인</div>
			</div>
			</a>		
		  </div>
		    
		  
		  <div class="box3">
		  	 <a href="${pageContext.servletContext.contextPath }/private/myshop/point">
			<div class="wrapper3">
			<div class="b1"> <img src="${pageContext.servletContext.contextPath }/resource/image/dollar.png" style="width: 40px" /></div>
			<div class="b2"> 적립금 확인</div>
			</div>
			</a>		
		  </div>
		  

		 <div class="box4">
		  	 <a href="${pageContext.servletContext.contextPath }/private/myshop/orderlist">
			<div class="wrapper3">
			<div class="b1"> <img src="${pageContext.servletContext.contextPath }/resource/image/order.png" style="width: 40px" /></div>
			<div class="b2"> 주문조회 </div>
		</div>
			</a>		
		  </div>
   
		  </div>

		</div>
		
</body>
</html>