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
		<div
			style="text-align: center; margin-bottom: 20px; margin-top: 50px;">
			<p style="font-size: 40px;">장바구니</p>
		</div>
		<div
			style="width: 80%; margin: auto; display: flex; justify-content: flex-start; gap: 50px; border: 3px solid #B6BBC4; padding-left: 10px">
			<div>
				가용포인트 :
				<fmt:formatNumber value="${one.pointsum }" pattern="#,###" />
				원
			</div>
			<div>쿠폰 : ${size }개</div>
		</div>
		<div>
			<form id="kiroko" method="get">
				<table style="margin: auto; min-width: 80%">
					<tr style="height: 60px">
						<th><input type="checkbox" onclick="checktoggle(event);" /></th>
						<th>이미지</th>
						<th>상품명</th>
						<th>수량</th>
						<th>상품구매금액</th>
						<th>적립포인트</th>
						<th>선택</th>
					</tr>
					<c:forEach var="one" items="${list }">
				
						<tr style="height: 60px">
							<td style="text-align: center">
								<input class="checkBoxId" id="checkBoxId" type="checkbox" name="check" value="${one.id }" /></td>
							<td style="cursor: pointer; width: 10%"
								onclick="location.href='${pageContext.servletContext.contextPath}/view/detail?code=${one.itemCode }'">
								<img style="width: 100px; height: 100px" src="${pageContext.servletContext.contextPath }${one.item.image }">
							</td>
							<td style="cursor: pointer"
								onclick="location.href='${pageContext.servletContext.contextPath}/view/detail?code=${one.itemCode }'">${one.item.name}</td>
							<td id="piece" class="textcenter">
								<input type="number" class="cartpiece" name="piece" value="${one.cartPiece }" style="width: 50px"> 
								<input type="hidden" name="price" value="${one.item.price }" />
								<input type="hidden" name="itemcode" value="${one.itemCode}" />
								<input type="hidden" name="cartId" value="${one.id }" />
								<input type="hidden" id="deleteNo" name="deleteNo" value="deleteNo" />
								<button id="button"
									style="padding-top: 1px; padding-bottom: 1px; padding-left: 6px; padding-right: 6px"
									type="submit"
									formaction="${pageContext.servletContext.contextPath}/private/order/cartupdate">변경</button>
							</td>
							<td id="total" class="textcenter total"><fmt:formatNumber
									value="${one.item.price * one.cartPiece  }" pattern="#,###" /></td>
							<!-- 포인트적립 -->
							<td class="textcenter"><fmt:formatNumber
									value="${(one.item.price * one.cartPiece) / 100}"
									pattern="#,###" /></td>
							<!-- 선택버튼 -->
							<td style="text-align: center"><input type="hidden"
								name="point" value="${(one.item.price * one.cartPiece) / 100}">
								<input type="hidden" name="cartid" value="${one.id }" />
								<div style="display: block;">
									<div>
										<button
											style="margin-top: 3px; padding-left: 20px; padding-right: 18px"
											type="button" onclick="buyAtCartSubmit('${one.id}')">주문하기</button>
									</div>
									<div>
										<button
											style="margin-top: 3px; padding-left: 15px; padding-right: 15px"
											type="button" onclick="deleteSubmit('${one.id}')">삭제하기</button>
									</div>
								</div></td>
							<!-- 총 정보 나열?.. -->
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3"></td>
						<td colspan="3">합계 : <span id="final">0</span></td>
					</tr>
					<tr>
						<th colspan="3">총 상품금액</th>
						<th colspan="2">총 할인금액</th>
						<th colspan="3">결제예정금액</th>
					</tr>
					<tr>
						<td colspan="3" class="textcenter"><span id="underbartotal">0</span></td>
						<td colspan="2" class="textcenter">0</td>
						<td colspan="3" class="textcenter">=<span id="buytotal">0</span></td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 30px">
					<button type="button" name="check" onclick="proceedItemcode();">선택주문하기</button>
				</div>
			</form>
			<form
				action="${pageContext.servletContext.contextPath }/private/order/buycartall"
				style="display: none;" id="proceedCart"></form>
		</div>
	</div>
	<script>
	
		
	
	
		const cartElm =new Map();
		[...document.querySelectorAll(".checkBoxId")].forEach(function(elm) {
			elm.addEventListener("change", function(e) {
				if(e.target.checked) {
					if(cartElm.get(e.target)) {
						return;
					}
					const $input = document.createElement("input");
					$input.style.display="none";
					$input.name="cartId";
					$input.value= e.target.value;
					document.querySelector("#proceedCart").appendChild($input);
					cartElm.set(e.target, $input);
				}else{
					const $input = cartElm.get(e.target);
					if($input) {
						document.querySelector("#proceedCart").removeChild($input);
						cartElm.delete(e.target);
					}
				}
			})
		});
		
		
		function checktoggle(evt) {
			[...document.querySelectorAll(".checkBoxId")].forEach(function(elm) {
				elm.checked= evt.target.checked;
				elm.dispatchEvent(new Event("change"));
			})
		}
			
		function proceedItemcode() {
			document.querySelector("#proceedCart").submit();
		}
		
		// 총합계금액 기본설정
		let prefinal = 0;
		[...document.querySelectorAll('.total')].forEach(function(elm){
			let eachprice = Number(elm.firstChild.nodeValue.replace(',',''));
			prefinal = prefinal + eachprice;
			
		});
		document.querySelector('#final').innerHTML =  prefinal.toLocaleString();
		document.querySelector('#underbartotal').innerHTML = prefinal.toLocaleString();
		document.querySelector('#buytotal').innerHTML = prefinal.toLocaleString();
		
		// 수량 변경이 이루어졌을때의 event
		[...document.querySelectorAll('.cartpiece')].forEach(function(elm) {
					elm.addEventListener(
							'change',
							function(e) {
								let value = e.target.value;
								
								if (value > 20) {
									e.target.value = '20';
									window.alert("최대 주문 수량은 20개입니다.");
									return;
								}
								let itemPrice = e.target.nextElementSibling.value;
								let finaltotal =value * itemPrice;
								e.target.parentNode.nextElementSibling.innerHTML = finaltotal.toLocaleString();
								
								
								// 포인트
								let point = finaltotal/100; 
								e.target.parentNode.nextElementSibling.nextElementSibling.innerHTML = point.toLocaleString();
							
								// 총합계
								let final = 0;
								[...document.querySelectorAll('.total')].forEach(function(elm){
									let eachprice = Number(elm.firstChild.nodeValue.replace(',',''));
									final = final + eachprice;
									
								});
								document.querySelector('#final').innerHTML =  final.toLocaleString();
								document.querySelector('#underbartotal').innerHTML = final.toLocaleString();
								// 할인금액 무시하고 넣은 금액
								document.querySelector('#buytotal').innerHTML = final.toLocaleString();
								
								// parameter로 넘길 piece
								//[...document.querySelectorAll('.paramPiece')].forEach(function(ev){
								//	ev.target.value =value;
								//});
							});

				});
				
		document.querySelector('.cartpiece').addEventListener('input', function(e){
			let piece = e.target.value; // 선택한 상품의 개수
			if(piece < 0){
				e.target.value = '0';
				window.alert("최소 주문 수량은 1개입니다.");
				return;
			}
			
		});
		
		 // 장바구니에서 삭제하기
		function deleteSubmit(id){
			
			 console.log(id);
			document.getElementById("deleteNo").value =id;
			document.getElementById("kiroko").action = "${pageContext.servletContext.contextPath}/private/order/cartdelete";
			document.getElementById("kiroko").submit();
		}
		
		// 장바구니에서 주문하기
	 	function buyAtCartSubmit(id){
	 		 
	 		console.log(id);
			document.getElementById("deleteNo").value = id; 
			document.getElementById("kiroko").action = "${pageContext.servletContext.contextPath}/private/order/buycartall";
			document.getElementById("kiroko").submit();
		}
		 


		[...document.querySelectorAll('.checkBoxId')].forEach(function(elm) {
			elm.addEventListener(
					'change',
					function(e) {
						let value = e.target.value;
						e.target.value = 1;
						console.log(e.target.value);
						
					});

		});
		
		
	</script>
</body>
</html>