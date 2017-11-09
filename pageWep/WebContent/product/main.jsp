<%
/* 라인 
상품 191~

*/
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->

<head>

<!-- Meta Tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>WellCome</title>   

<meta name="description" content="Insert Your Site Description" /> 

<!-- Mobile Specifics -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="HandheldFriendly" content="none"/>
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
<!-- End Analytics -->

</head>
<!-- This section is for Splash Screen -->
<div class="ole">
<section id="jSplash">
	<div id="circle"></div>
</section>
</div>
<!-- End of Splash Screen -->

<!-- Header -->
<header>
<c:if test="${sessionScope.memId == null}">
    <div class="sticky-nav">
    	<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>
        
        <div id="logo">
        	<a id="goUp" href="/pageWep/product/main.do" class="external" title="Brushed | Responsive One Page Template"></a>
        </div>
        <nav id="menu">
        	<ul id="menu-nav">
				<li><a href="/pageWep/product/main.do"class="external">의류</a></li>
                <li><a href="/pageWep/member/loginForm.do"class="external">LOGIN</a></li>
                <li><a href="/pageWep/board/list.do" class="external">게시판</a></li>
            </ul>
        </nav>
    </div>
</c:if>
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
	            	<li><a href="/pageWep/member/logout.do"class="external">LOGOUT</a></li>
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
	                <li><a href="/pageWep/member/logout.do" class="external">LOGOUT</a></li>
	                <li><a href="/pageWep/cart/cart.do" class="external">장바구니</a></li>
	                <li><a href="/pageWep/order/orderMypage.do" class="external">주문</a></li>
	                <li><a href="/pageWep/board/list.do" class="external">게시판</a></li>
	                <li><a href="/pageWep/cboard/list.do"class="external">코디 게시판</a></li>
	            </ul>
	            <span style="float:right">${sessionScope.memId}님</span>
	        </nav>
	    </div>
	</c:if>
</c:if>
</header>
<!-- End Header -->



<!-- Our Work Section -->
	<div id="work" class="page">
		<div class="container">
			<!-- Title Page -->
			<div class="row">
				<div class="span12">
					<div class="title-page">
						<h2 class="title">COSTUME</h2>
					</div>
				</div>
			</div>
			<!-- End Title Page -->

			<!-- Portfolio Projects -->

			<%-- 메뉴바  --%>
			<div class="row">
				<div class="span3">
					<!-- Filter -->
					<nav id="options" class="work-nav">
						<ul id="filters">
							<li class="type-work">Type of Costume</li>
							<li><a href="main.do">ALL</a></li>
							<li><a href="/pageWep/product/main.do?type=OUT">OUTER</a></li>
							<li><a href="/pageWep/product/main.do?type=TOP">TOP</a></li>
							<li><a href="/pageWep/product/main.do?type=BOTTOM">BOTTOM</a></li>
						</ul>
					</nav>
					<%-- 메뉴바  end--%>
					<!-- End Filter -->
				</div>

				<div class="span9">
					<div class="row">

						<c:if test="${count==0}">
							<table border="1">
								<tr>
									<td align="center">게시판에 저장된 글이 없습니다.</td>
								</tr>
							</table>
						</c:if>
						<c:if test="${count!=0}">
							<div class="div2" style="position: relative; height: 100%;">
								<c:forEach var="arti" items="${proList1}">
									<div class="div"
										style="float: left; width: 45%; padding: 20px;">
										<a
											href="/pageWep/product/procontent.do?product_num=${arti.product_num}"><img
											src="${arti.product_img}" width="300" height="350"></a></br>
										제품번호 : <a
											href="/pageWep/product/procontent.do?product_num=${arti.product_num}">${arti.product_num}</a></br>
										제품이름 : <a
											href="/pageWep/product/procontent.do?product_num=${arti.product_num}">${arti.product_name}</a></br>
										가격 : <a
											href="/pageWep/product/procontent.do?product_num=${arti.product_num}">${arti.product_price}원</a></br>
									</div>
								</c:forEach>
						</c:if>
						<div class="div1"
							style="position: absolute; bottom: 0; width: 100%; height: 70px;">
							<c:if test="${count>0}">
								<c:if test="${type==''}">
									<c:if test="${startPage>10}">
										<a href="main.do?pageNum=${startPage - 10}">[이전]</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}"
										step="1">
										<a href="main.do?pageNum=${i}">[${i}]</a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a href="main.do?pageNum=${startPage + 10}">[다음]</a>
									</c:if>
								</c:if>

								<c:if test="${type=='TOP'}">
									<c:if test="${startPage>10}">
										<a href="main.do?type=TOP&pageNum=${startPage - 10}">[이전]</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}"
										step="1">
										<a href="main.do?type=TOP&pageNum=${i}">[${i}]</a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a href="main.do?type=TOP&pageNum=${startPage + 10}">[다음]</a>
									</c:if>
								</c:if>

								<c:if test="${type=='BOTTOM'}">
									<c:if test="${startPage>10}">
										<a href="main.do?type=BOTTOM&pageNum=${startPage - 10}">[이전]</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}"
										step="1">
										<a href="main.do?type=BOTTOM&pageNum=${i}">[${i}]</a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a href="main.do?type=BOTTOM&pageNum=${startPage + 10}">[다음]</a>
									</c:if>
								</c:if>

								<c:if test="${type=='OUT'}">
									<c:if test="${startPage>10}">
										<a href="main.do?type=OUT&pageNum=${startPage - 10}">[이전]</a>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}"
										step="1">
										<a href="main.do?type=OUT&pageNum=${i}">[${i}]</a>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<a href="main.do?type=OUT&pageNum=${startPage + 10}">[다음]</a>
									</c:if>
								</c:if>
							</c:if>
						</div>
					</div>
				</div>

		</div>
	</div>

		<!-- End Our Work Section -->
		<!-- Footer -->
		<footer>
			<p class="credits">
				&copy;글로벌아이티 인재개발 의류몰 <a href="../sitemap.do" title="sitemap">사이트맵</a> 
			</p>
		</footer>
		<!-- End Footer -->

		<!-- Back To Top -->
		<a id="back-to-top" href="#"> <i class="font-icon-arrow-simple-up"></i>
		</a>
		<!-- End Back to Top -->


		<!-- Js -->
		<script
			src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<!-- jQuery Core -->
		<script src="../_include/js/bootstrap.min.js"></script>
		<!-- Bootstrap -->
		<script src="../_include/js/supersized.3.2.7.min.js"></script>
		<!-- Slider -->
		<script src="../_include/js/waypoints.js"></script>
		<!-- WayPoints -->
		<script src="../_include/js/waypoints-sticky.js"></script>
		<!-- Waypoints for Header -->
		<script src="../_include/js/jquery.isotope.js"></script>
		<!-- Isotope Filter -->
		<script src="../_include/js/jquery.fancybox.pack.js"></script>
		<!-- Fancybox -->
		<script src="../_include/js/jquery.fancybox-media.js"></script>
		<!-- Fancybox for Media -->
		<script src="../_include/js/jquery.tweet.js"></script>
		<!-- Tweet -->
		<script src="../_include/js/plugins.js"></script>
		<!-- Contains: jPreloader, jQuery Easing, jQuery ScrollTo, jQuery One Page Navi -->
		<script src="../_include/js/main.js"></script>
		<!-- Default JS -->
		<!-- End Js -->
</body>
</html>