<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@page import="product.ProductDataBean"%>
<%@page import="product.productDBBean"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
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

<title>Cart List</title>   

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
 <script language='javascript'>
 function change(num)
 {
 var x  = document.form;
 var y = Number(x.order_count.value) + num;
 if(y < 1) {
	 y = 1;
	 alert("본 상품은 최소 한 개 이상 주문하셔야 합니다.");
 }
 x.order_count.value = y;
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

<form method="post" name="form" action=<c:if test="${sessionScope.memId!=null}">"/pageWep/order/orderForm.do?product_num=${product_num}&ch=false"</c:if>
<c:if test="${sessionScope.memId==null}">"/pageWep/member/loginForm.do"</c:if>>
<table>

<c:forEach var="arti" items="${proList1}">
<tr>
<td><img src="${arti.product_img}" width="200" height="200"></td>
<td width="100" height="210" align="right">
        <b>제품번호  </br></br>
                         제품명     </br></br>
           size  </br></br>
           color </br></br>
                         수량        </br></br>
                         가격        </b>
</td>
<td width="200" height="210" align="left">
      ${arti.product_num}</br></br>
      ${arti.product_name}</br></br>
      <select name="product_size" size="1">
      <option value="" disabled="disabled" selected="selected">선택하시오</option>
      <c:forEach var="size1" items="${sizedata}">
      <option value="${size1}">${size1}</option> 
      </c:forEach>
      </select>
      </br></br>
      
      <select name="product_color" size="1">
      <option value="" disabled="disabled" selected="selected">선택하시오</option>
      <c:forEach var="color" items="${colordata}">
      <option value="${color}">${color}</option> 
      </c:forEach>
      </select>
      </br></br>
      
      <input type="text" name="order_count"  size="2" value=${order_count}>
      <a href='#' onclick='javascript_:change(1);'>▲</a>
      <a href='#' onclick='javascript_:change(-1);'>▼</a>
      </br></br>
      ${arti.product_price}원
</tr>
<tr>
<td width="500" colspan="3" align="center" >
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="submit" value="주문하기">

<script>
	function cartSubmit(form){
			form.action="/pageWep/product/productCartPro.do?product_num=${product_num}";	
			form.submit();
	}
</script>

<input type="button" value="장바구니" onClick="cartSubmit(this.form)">
</td>
</tr>
</c:forEach>
</table>
</form>
   	</div>
            </div>
        </div>
        <!-- End Porduct Projects -->
    </div>
    
</div>
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