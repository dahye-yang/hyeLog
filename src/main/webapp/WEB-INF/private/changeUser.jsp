<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<style>
input{
	border: none;
	width : 300px;
	margin-left : 10px;
	outline: none;
	border-collapse: collapse;
}
table,td,tr,th{
	border-left:none;
	border-right: none;
	border-top: none;
	border-radius: 0;
	border-bottom: 1px solid #444;
	border-collapse: collapse;
}
td{
border: none;
}

th{
	background-color: #e9ecef;
}
tr{
	height: 60px
}
div{
border:none;
}
</style>
<link href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css" rel = "stylesheet"></link>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>HYELOG</title>
</head>
<body>
	<div class="wrap">
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		<div style="padding-left: 20%; padding-right: 20%; margin-top: 50px;">
			<h2 style="text-align :center;">회원 정보 변경</h2>
	<form action = " ${pageContext.servletContext.contextPath }/private/changeUser"	method="post"	>
		<table style="width:100%">
		<colgroup>
			<col style="width :150px;">
			<col style="width :auto">
		</colgroup>
	    <tbody>
	    	<tr>
	
		        <th style="text-align: left;">아이디</th>
		
		        <td>${logonUser.id }</td>
		          
	   		 </tr>
	   		 
	   		 <tr >
		        <th style="text-align: left;">비밀번호</th>
		        <td> ${result }</td>      
				<td><button type="button" id="openPassBt">비밀번호 변경</button></td>
	   		 </tr>
	   		 
			<tr style="display:none">
				<th style="text-align: left;">비밀번호</th>
				<td colspan="2">
					<div>현재 비밀번호 <input type ="password" name = "PW"></div>
					<div>신규 비밀번호 <input type ="password" name = "newPW"></div>
					<button>확인</button>
					<button type="button"  id="closePassBt">취소</button>
				</td>
			</tr>
	   		 <tr>
		        <th style="text-align: left;"> 닉네임</th>
		        <td>${logonUser.nickName }</td>      
				<td><button type="button" id="openNickBt">닉네임 변경</button></td>
	   		</tr>
	   		
	   		<tr  style="display:none" >
		   		<th style="text-align: left;">변경할 닉네임</th>
		   		<td colspan="2">
		   		<input type = "text" name = "nickName">
		   		<button>확인</button>
		   		<button type="button"  id="closeNickBt">취소</button>
		   		<td>
	   		</tr>
		</tbody>
	</table>
</form>		
</div>
</div>
<script>
	document.querySelector("#openPassBt").addEventListener("click", function(evt) {
		evt.target.parentNode.parentNode.style.display="none";
		evt.target.parentNode.parentNode.nextElementSibling.style.display="";
	});
	
	document.querySelector("#closePassBt").addEventListener("click", function(evt) {
		evt.target.parentNode.parentNode.style.display="none";
		evt.target.parentNode.parentNode.prevElementSibling.style.display="";
	});
	
	document.querySelector("#openNickBt").addEventListener("click", function(evt) {
		evt.target.parentNode.parentNode.style.display="none";
		evt.target.parentNode.parentNode.nextElementSibling.style.display="";
	});
	
	document.querySelector("#closeNickBt").addEventListener("click", function(evt) {
		evt.target.parentNode.parentNode.style.display="none";
		evt.target.parentNode.parentNode.prevElementSibling.style.display="";
	});
</script>	
</html>