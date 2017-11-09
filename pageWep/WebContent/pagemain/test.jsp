<%
/* 라인 
상품 191~

*/
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@page import="product.ProductDataBean"%>
<%@page import="product.productDBBean"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if (IE 7)&!(IEMobile)]><html class="no-js lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if (IE 8)&!(IEMobile)]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if (IE 9)]><html class="no-js ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--> <html lang="en-US"> <!--<![endif]-->

<head>

<!-- Meta Tags -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>상품 | 게시판</title>   

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
<c:if test = "${memId == null}">
	<script>
		alert("로그인 후 글쓰기 가능하니다");
		window.location='/pageWep/member/main.do';
	</script>
</c:if>

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
<header>
<c:if test="${sessionScope.memId == null}">
    <div class="sticky-nav">
    	<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>
        
        <div id="logo">
        	<a id="goUp" href="#home-slider" title="Brushed | Responsive One Page Template">SHOP</a>
        </div>
        <nav id="menu">
        	<ul id="menu-nav">
				<li><a href="#work">의류</a></li>
                <li><a href="#about">About Us</a></li>
                <li><a href="#contact">Contact</a></li>
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
	        	<a id="goUp" href="#home-slider" title="Brushed | Responsive One Page Template">SHOP</a>
	        </div>
	        <nav id="menu">
	        	<ul id="menu-nav">
					<li><a href="#work">의류</a></li>
	                <li><a href="#about">About Us</a></li>
	                <li><a href="#contact">Contact</a></li>
	                <li><a href="#contact">상품 등록</a></li>
	                <li><a href="/pageWep/member/loginForm.do"class="external">LOGOUT</a></li>
	                <li><a href="/pageWep/board/list.do" class="external">게시판</a></li>
	            </ul>
	            <span style="float:right">${sessionScope.memId}님</span>
	        </nav>
	    </div>
	</c:if>
	<c:if test="${sessionScope.memId != 'admin'}">
	    <div class="sticky-nav">
	    	<a id="mobile-nav" class="menu-nav" href="#menu-nav"></a>
	        
	        <div id="logo">
	        	<a id="goUp" href="#home-slider" title="Brushed | Responsive One Page Template">SHOP</a>
	        </div>
	        <nav id="menu">
	        	<ul id="menu-nav">
	                <li><a href="#about">About Us</a></li>
	                <li><a href="#contact">Contact</a></li>
	                <li><a href="/pageWep/member/loginForm.do"class="external">LOGOUT</a></li>
	                <li><a href="/pageWep/board/list.do" class="external">게시판</a></li>
	            </ul>
	            <span style="float:right">${sessionScope.memId}님</span>
	        </nav>
	    </div>
	</c:if>
</c:if>
</header>
<!-- End Header -->


<!-- Contact Section -->
<div id="contact" class="page">
<div class="container">
    <!-- Title Page -->
    <div class="row">
        <div class="span12">
            <div class="title-page">
                <h2 class="title">상품 문의 게시판</h2>
                <h3 class="title-description">내용 입력</h3>
            </div>
        </div>
    </div>
    <!-- End Title Page -->
    
    <!-- Contact Form -->
    <div class="row">
    	<div class="span9">
        
        	<form id="contact-form" class="contact-form" method="post" name="writeform"
        			action="/pageWep/board/writePro.do" onsubmit="return writeSave()">
				<input type="hidden" name="num" value="${num}">
				<input type="hidden" name="ref" value="${ref}">
				<input type="hidden" name="restep" value="${restep}">
				<input type="hidden" name="relevel" value="${relevel}">
				<input type="hidden" name="writer" value ="${memId}"></td>
            	<p class="contact-name">
            	<c:if test="${num == 0}">
            		<input id="contact_name" type="text" placeholder="subject" value="" name="subject" />
            	</c:if>
            	<c:if test="${num != 0}">
            		<input id="contact_name" type="text" placeholder="subject" value="[답변]" name="subject" />
            	</c:if>
                </p>
                <p class="contact-message">
                	<textarea id="contact_message" placeholder="Your Message" name="content" rows="15" cols="40"></textarea>
                </p>
                <p class="contact-email">
                	<input id="contact_email" type="password" placeholder="password" value="" name="passwd" />
                </p>
                <p class="contact-submit">
                	<input type="submit" value="글쓰기" >  
 					<input type="reset" value="다시작성">
 					<input type="button" value="목록보기" OnClick="window.location='/pageWep/board/list.do'">
                </p>
                
                <div id="response">
                
                </div>
            </form>
        </div>
    </div>
    <!-- End Contact Form -->
</div>
</div>
<!-- End Contact Section -->

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