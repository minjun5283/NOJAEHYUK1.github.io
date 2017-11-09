<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value = "UTF-8"/>
<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->

<head>

<!-- Meta Tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>주문관리</title>   

<meta name="description" content="Insert Your Site Description" /> 

<!-- Mobile Specifics -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="HandheldFriendly" content="true"/>
<meta name="MobileOptimized" content="320"/>   

<!-- Mobile Internet Explorer ClearType Technology -->
<!--[if IEMobile]>  <meta http-equiv="cleartype" content="on">  <![endif]-->

<!-- Bootstrap -->
<link href="../_include/css/bootstrap.min.css" rel="stylesheet">

<!-- Main Style -->
<link href="../_include/css/main.css" rel="stylesheet">

<!-- Supersized -->
<link href="../_include/css/supersized.css" rel="stylesheet">
<link href="../_include/css/supersized.shutter.css" rel="stylesheet">

<!-- FancyBox -->
<link href="../_include/css/fancybox/jquery.fancybox.css" rel="stylesheet">

<!-- Font Icons -->
<link href="../_include/css/fonts.css" rel="stylesheet">

<!-- Shortcodes -->
<link href="../_include/css/shortcodes.css" rel="stylesheet">

<!-- Responsive -->
<link href="../_include/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="../_include/css/responsive.css" rel="stylesheet">

<!-- Supersized -->
<link href="../_include/css/supersized.css" rel="stylesheet">
<link href="../_include/css/supersized.shutter.css" rel="stylesheet">

<!-- Google Font -->
<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,200italic,300,300italic,400italic,600,600italic,700,700italic,900' rel='stylesheet' type='text/css'>

<!-- Fav Icon -->
<link rel="shortcut icon" href="#">

<link rel="apple-touch-icon" href="#">
<link rel="apple-touch-icon" sizes="114x114" href="#">
<link rel="apple-touch-icon" sizes="72x72" href="#">
<link rel="apple-touch-icon" sizes="144x144" href="#">

<!-- Modernizr -->
<script src="../_include/js/modernizr.js"></script>

<!-- Analytics -->
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'Insert Your Code']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<script type="text/javascript">
function go_check(){
    var isChk = false;
    var arr_check = document.getElementsByName("check");
    for(var i=0;i<arr_check.length;i++){
        if(arr_check[i].checked == true) {
           isChk = true;
            break;
        }
    }
    if(!isChk){
        alert("한개 이상 선택해주세요.");
        return false;
    }
}
</script>

<!-- End Analytics -->
</head>
<body>

<!-- This section is for Splash Screen -->
<div class="ole">
<section id="jSplash">
	<div id="circle"></div>
</section>
</div>
<!-- End of Splash Screen -->

<!-- Header -->
<!-- Header -->
<header>

<c:if test="${sessionScope.memId != null}">
	<c:if test="${sessionScope.memId == 'admin'}">
	    <div class="sticky-nav">
	    	<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>
	        
	        <div id="logo">
	        	<a id="goUp" href="/pageWep/product/main.do" class="external" title="Brushed | Responsive One Page Template">SHOP</a>
	        </div>
	        <nav id="menu">
	        	<ul id="menu-nav">
					<li><a href="/pageWep/product/main.do"class="external">의류</a></li>
	                <li><a href="/pageWep/product/prolist.do" class="external">상품</a></li>
	                <li><a href="/pageWep/cboard/list.do"class="external">코디 게시판</a></li>
	                <li><a href="/pageWep/board/list.do" class="external">게시판</a></li>
	            	<li><a href="/pageWep/member/supervisemember.do" class="external">회원관리</a></li>
	            	<li><a href="/pageWep/order/orderList.do" class="external">주문관리</a></li>
	            	<li><a href="/pageWep/member/logout.do" class="external">LOGOUT</a></li>
	            </ul>
	            <span style="float:right">${sessionScope.memId}님</span>
	        </nav>
	    </div>
	</c:if>
	<c:if test="${sessionScope.memId != 'admin'}">
	    <div class="sticky-nav">
	    	<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>
	        
	        <div id="logo">
	        	<a id="goUp" href="/pageWep/product/main.do" class="external" title="Brushed | Responsive One Page Template">SHOP</a>
	        </div>
	        
	        <nav id="menu"><%-- 메뉴바  --%>
	        	<ul id="menu-nav">
	                <li><a href="/pageWep/product/main.do"class="external">의류</a></li>
	                <li><a href="/pageWep/member/mypage.do"class="external">MyPage</a></li>
	                <li><a href="/pageWep/cart/cart.do" class="external">장바구니</a></li>
	                <li><a href="/pageWep/order/orderMypage.do" class="external">주문</a></li>
	                <li><a href="/pageWep/board/list.do" class="external">게시판</a></li>
	                <li><a href="/pageWep/cboard/list.do"class="external">코디 게시판</a></li>
	                <li><a href="/pageWep/member/logout.do" class="external">LOGOUT</a></li>
	            </ul>
	            <span style="float:right">${sessionScope.memId}님</span>
	        </nav>
	    </div>
	</c:if>
</c:if>
</header>
<!-- End Header -->

<!-- 작업 부분   -->
<center>
<c:if test="${sessionScope.memId == 'admin'}">
<div>   <%-- 메뉴바  --%>
        <ul>
            <li><a href="/pageWep/product/prolist.do">상품리스트</a></li>
            <li><a href="/pageWep/product/prowriterForm.do">상품등록</a></li>
            <li><a href="/pageWep/order/orderList.do">주문리스트</a></li>
        </ul>    
</div>

<form method="post" action="orderListSelect.do?pageNum=1">
<table>
<tr>
<td>
<select name="selectPro">
<option value="order_num">주문번호</option>
<option value="product_num">제품번호</option>
<option value="member_id">사용자ID</option>
<option value="order_state">배송상태</option>
</select>
</td>
<td>
     <input type="text" name="getTex"/>
     <input type="submit" value="검색" />
</td>
</tr>
</table>
</form>


<table border="1">
<c:if test="${count==0}">
<table border="1">
<tr><td align="center">
게시판에 저장된 글이 없습니다.
</td></tr>
</table>
</c:if>
<c:if test="${count!=0}">
<table border="1">
<tr>
<td align="center"></td>
<td align="center">번호</td>
<td align="center">주문번호</td>
<td align="center">주문자ID</td>
<td align="center">제품번호</td>
<td align="center">사이즈</td>
<td align="center">색상</td>
<td align="center">수량</td>
<td align="center">총 가격</td>
<td align="center">등록일</td>
<td align="center">배송상태</td>
<td align="center">관리</td>
</tr>

<c:forEach var="arti" items="${orderList}">
<form method="post" action="orderdeleteForm.do?order_num=${arti.order_num}&pageNum=${pageNum} onSubmit="return go_check()"">	
<tr>
<td><input type="checkbox" name="check" value="${arti.order_num}"></td>
<td><c:out value="${number}"/>
    <c:set var="number" value="${number - 1}"/></td>
<td>${arti.order_num}</td>
<td>${arti.member_id}</td>
<td>${arti.product_num}</td>
<td>${arti.order_size}</td>
<td>${arti.order_color}</td>
<td>${arti.order_count}</td>
<td>${arti.order_price}</td>
<td><fmt:formatDate value="${arti.order_date}" type="date" pattern="yyyy년MM월dd일"/></td>
<td>${arti.order_state}</td>
<td>
<input type="button" value="관리" onclick="window.location='/pageWep/order/orderData.do?order_num=${arti.order_num}&pageNum=${pageNum}'"/>
</td>
</tr>
</c:forEach>
<input type="submit" name="delete" value="삭제">
</table>
</form>
</c:if>


<c:if test="${count>0}">
   <c:if test="${startPage>10}">
   <a href="orderList.do?pageNum=${startPage - 10}">[이전]</a>
   </c:if>
   <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
   <a href="orderList.do?pageNum=${i}">[${i}]</a>
   </c:forEach>
   <c:if test="${endPage < pageCount}">
   <a href="orderList.do?pageNum=${startPage + 10}">[다음]</a>
   </c:if>
</c:if>

</c:if>

 </center>
<!-- 작업 부분   -->
		<!-- Footer -->
		<footer>
			<p class="credits">
				&copy;글로벌아이티 인재개발 의류몰 <a href="../sitemap.do" title="sitemap">사이트맵</a> 
			</p>
		</footer>
		<!-- End Footer -->
<!-- Js -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> <!-- jQuery Core -->
<script src="../_include/js/bootstrap.min.js"></script> <!-- Bootstrap -->
<script src="../_include/js/supersized.3.2.7.min.js"></script> <!-- Slider -->
<script src="../_include/js/waypoints.js"></script> <!-- WayPoints -->
<script src="../_include/js/waypoints-sticky.js"></script> <!-- Waypoints for Header -->
<script src="../_include/js/jquery.isotope.js"></script> <!-- Isotope Filter -->
<script src="../_include/js/jquery.fancybox.pack.js"></script> <!-- Fancybox -->
<script src="../_include/js/jquery.fancybox-media.js"></script> <!-- Fancybox for Media -->
<script src="../_include/js/jquery.tweet.js"></script> <!-- Tweet -->
<script src="../_include/js/plugins.js"></script> <!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
<script src="../_include/js/main.js"></script> <!-- Default JS -->
<!-- End Js -->


</body>
</html>