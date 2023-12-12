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
<style>
	input[type="number"]::-webkit-outer-spin-button,
	input[type="number"]::-webkit-inner-spin-button {
	    -webkit-appearance: none;
	    margin: 0;
	}
	input{
		border: 2px solid #F2F1EB;
	}

</style>
</head>
<body>
	<div class="wrap" style="max-width: 850px">
		<div>
			<header style="background-color: #F2F1EB">
				<c:import url="/nav" />
			</header>
		</div>
		<div>
			<div style="background-color: #EEF0E5; margin-top: 55px;">
				<div style="font-size: 40px; text-align: center">주문/결제</div>
			</div>
			<div style="border-bottom: 3px solid #F2F1EB">
				<div><b style="font-size: 30px">주문정보</b></div>
				<div>주문자 : ${found.nickName } </div>
			</div>
			<c:forEach var="one" items="${list }">
				<div style="display: flex; justify-content: flex-start; border-bottom: 3px solid #F2F1EB; gap:10px">
					<div>
						<img style="width: 100px; height: 100px" alt="상품사진" src="${pageContext.servletContext.contextPath }${one.item.image}">
					</div>
					<div>
						<div>${one.item.name }</div>
						<div>수량 : ${one.cartPiece }</div>
						<div><fmt:formatNumber value="${one.cartPiece * one.item.price }" pattern="#,###"/> </div>
					</div>
				</div>
			</c:forEach>
			<form action="${pageContext.servletContext.contextPath }/private/order/buy" method="post">
			<c:forEach var="two" items="${list }">
				<input type="hidden" name="itemcode" value="${two.itemCode }"> <!-- 이건 필요해??? -->
				<input type="hidden" name="piece" value="${two.cartPiece}"> <!-- for문 돌릴때 넣어서 셋팅하기 -->
				<input type="hidden" name="cartId" value="${two.id}"> <!-- for문 돌릴때 넣어서 셋팅하기 -->
			</c:forEach>
				<div style="border-bottom: 3px solid #F2F1EB;">
					<div>
						<div><b style="font-size: 30px">할인결제</b></div>
						<div style="padding-bottom: 10px; display: flex; justify-content: space-between;">
							<div>적립금</div>  
							<div><input style="text-align: right" id="use_allPoint" type="number" value="0"/>
							<button type="button" onclick="pointUse(event)" style="padding-left: 10px; padding-right: 10px;">전액사용</button> </div>
						</div>
					</div>
					<div style="text-align: right">보유잔액 : <fmt:formatNumber value="${point.pointsum }" pattern="#,###"/>원 </div>
					<div style="display: flex;justify-content: space-between;">
						<div>쿠폰사용</div>
						<div>
							<select id="coupon" name="coupon">
								<option value="">선택</option>
								<c:forEach var="one" items="${couponList }">
									<option value="${one.no }">${one.coupon.discount }%</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div  style="border-bottom: 3px solid #F2F1EB">
					<div><b style="font-size: 30px">결제정보</b></div>
					<div>
						<div style="display: flex; justify-content: space-between;">
							<div>주문상품</div>
							<div>
								<input type="hidden" name="price" value="${sum }" /> 
								<fmt:formatNumber value="${sum }" pattern="#,###"/>
							</div>
						</div>
						<div style="display: flex; justify-content: space-between;">
							<div>할인결제</div>
							<div><span id="pointDiscount">0</span></div>
						</div>
						<div style="display: flex;justify-content: space-between; border-top: 3px; border-bottom: 3px; border-style: dashed; border-color: #F2F1EB; border-left: none; border-right: none;margin-top: 10px; margin-bottom: 10px" >
							<div style="display: flex; justify-content: flex-start;">
								<div style="width: 15%"><img style="width: 100%" alt="적립금" src="${pageContext.servletContext.contextPath }/resource/image/dollar.png"></div>
								<div>적립금</div>
							</div>
							<div>
								<input id="usePoint" style="text-align:right;" type="hidden" name="point" value="${ sum / 100 }"/>
								<fmt:formatNumber value="${ sum / 100 }" pattern="#,###"/>
							</div>
						</div>
					</div>
					<div style="background-color: #F2F1EB; display: flex; justify-content: space-between; " >
						<div>최종결제금액</div>
						<input type="hidden" name="sum" value="${sum }"/>
						<div id="finalTotal"><fmt:formatNumber value="${sum }" pattern="#,###"/></div>
					</div>
				</div>
				<div style="margin-top: 20px; margin-bottom: 20px" class="textcenter">
					<button type="submit">
						<div id="fifinalTotal">
							<fmt:formatNumber value="${sum }" pattern="#,###"/><span> 결제하기</span> 
						</div>
					</button>
				</div>
			</form>
		</div>
	</div>
	<script>

		var keepPoint = Number('${sum}')/100;
		let $useAllPoint = 	document.querySelector('#use_allPoint');
	
		let allPoint = "<c:out value= '${point.pointsum }'/>";
		//console.log('allPoint의 타입'+ typeof allPoint);
		
		let x = document.querySelector('#finalTotal').firstChild.nodeValue;
		let x2 = x.replace(',','');
		let y = Number(x2); // 최종결제금액 number로 변경
		
		//전액사용 버튼 클릭
		function pointUse(e){
			$useAllPoint.value = allPoint;
			document.querySelector('#use_allPoint').dispatchEvent(new Event("change"));
		}
	
		// 적립금 input change 이벤트
		document.querySelector('#use_allPoint').addEventListener('change', function(e){
			//console.log(e);
			if( e.target.value != '' ){
				let value = e.target.value; // 보유포인트전액
				let value2 = Number(value);
				//console.log('value의 타입'+typeof value2);
				let minusValue = value * -1;
				document.querySelector('#pointDiscount').firstChild.nodeValue = minusValue;
				document.querySelector('#usePoint').nextSibling.nodeValue = minusValue;
				document.getElementById('usePoint').value = minusValue;
				
				
				// 최종결제금액 계산
				let finaltotal = y - value2;
				let finaltotal2 = finaltotal.toLocaleString();
				//console.log('최종결제금액-포인트전액-->'+finaltotal2);
				
				document.querySelector('#finalTotal').firstChild.nodeValue = finaltotal2;
				document.querySelector('#fifinalTotal').firstChild.nodeValue = finaltotal2;
			} else {
				document.querySelector('#pointDiscount').firstChild.nodeValue = '';
				document.querySelector('#usePoint').nextSibling.nodeValue = keepPoint;
				document.getElementById('usePoint').value = keepPoint;
				
				let keepPointFormat = (keepPoint * 100).toLocaleString();
				document.querySelector('#finalTotal').firstChild.nodeValue = keepPointFormat;
				document.querySelector('#fifinalTotal').firstChild.nodeValue = keepPointFormat;
			}
			
		});
		
		
		document.querySelector('#coupon').addEventListener('change', function(e){
				let coupon =  document.getElementById('coupon');
				
				if(coupon.options[coupon.selectedIndex].text !== '선택'){
					console.log(coupon.options[coupon.selectedIndex].text !== '선택');
				document.querySelector('#usePoint').nextSibling.nodeValue = 0;
				document.getElementById('usePoint').value = 0;
				
				let percent = coupon.options[coupon.selectedIndex].text.replace('%','');
				let discount = ((keepPoint * 100) * (percent / 100)) * -1;
				document.querySelector('#pointDiscount').firstChild.nodeValue = discount;
				
				let finaltotal = y + discount;
				let finaltotal2 = finaltotal.toLocaleString();
				//console.log('최종결제금액-포인트전액-->'+finaltotal2);
				
				document.querySelector('#finalTotal').firstChild.nodeValue = finaltotal2;
				document.querySelector('#fifinalTotal').firstChild.nodeValue = finaltotal2;
			}else{
				//선택일 경우에는 할인결제와, 최종결제금액, 결제하기버튼 금액 조정
				document.querySelector('#pointDiscount').firstChild.nodeValue = 0;
				
				let keepPointFormat = (keepPoint * 100).toLocaleString();
				document.querySelector('#finalTotal').firstChild.nodeValue = keepPointFormat;
				document.querySelector('#fifinalTotal').firstChild.nodeValue = keepPointFormat;
				
			}
			
			
		});
	</script>
</body>
</html>