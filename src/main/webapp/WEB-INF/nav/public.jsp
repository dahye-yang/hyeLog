<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div style="display: flex; justify-content: space-between;">
			<div>
				<a href = "${pageContext.servletContext.contextPath }/view/main">
				<img src="/hyeLog/resource/image/title.jpg"/>
				</a>
			</div>
			<div style="display: flex; justify-content: end">
			
					<div>
						<ul style="display: flex;justify-content: space-between;">
							<li><a href="${pageContext.servletContext.contextPath }/view/login">LOGIN</a></li>
							<li><a href="${pageContext.servletContext.contextPath }/view/join">JOIN</a></li>
							<li><a href="${pageContext.servletContext.contextPath }/board/notice">NOTICE</a></li>
							<li><a href="${pageContext.servletContext.contextPath }/board/qna">Q&A</a></li>
							<li><a href="#">REVIEW</a></li>
						</ul>	
					</div>
			</div>



