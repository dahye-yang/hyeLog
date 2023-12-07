<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.servletContext.contextPath }
			/resource/stylesheet/style.css" rel = "stylesheet"></link>
<meta charset="UTF-8">
<meta name = "viewport" content="width=divice-width, initial-scale=1.0">
<title>회원정보 변경</title>
</head>
<body>
	
			<h2>회원 정보 변경</h2>
	<form action = " ${pageContext.servletContext.contextPath }/private/changeUser"	method="post"	>
		<table style="width:100%">
	
	    	<tr>
	
		        <th>아이디</th>
		
		        <td>${logonUser.id }</td>
		          
	   		 </tr>
	   		 
	   		 <tr >
		        <th>비밀번호</th>
		        <td> ${result }</td>      
				<td><button type="button" id="openPassBt">비밀번호 변경</button></td>
	   		 </tr>
	   		 
			<tr style="display:none">
				<th>비밀번호</th>
				<td colspan="2">
					<div>현재 비밀번호 <input type ="password" name = "PW"></div>
					<div>신규 비밀번호 <input type ="password" name = "newPW"></div>
					<button>확인</button>
					<button type="button"  id="closePassBt">취소</button>
				</td>
			</tr>
	   		 <tr>
		        <th>닉네임</th>
		        <td>${logonUser.nickName }</td>      
				<td><button type="button" id="openNickBt">닉네임 변경</button></td>
	   		</tr>
	   		
	   		<tr  style="display:none" >
		   		<th>변경할 닉네임</th>
		   		<td colspan="2">
		   		<input type = "text" name = "nickName">
		   		<button>확인</button>
		   		<button type="button"  id="closeNickBt">취소</button>
		   		<td>
	   		</tr>
	
	</table>
</form>		
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