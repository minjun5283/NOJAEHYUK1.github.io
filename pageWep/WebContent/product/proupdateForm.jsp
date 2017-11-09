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

<title>상품수정</title>   

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

function writeSave(){
	
	if(document.writeform.product_name.value==""){
	  alert("이름을 작성하세요.");
	  document.writeform.product_name.focus();
	  return false;
	}
	if(document.writeform.product_price.value==""){
	  alert("가격을 등록하세요.");
	  document.writeform.product_price.focus();
	  return false;
	}
	if(document.writeform.product_size.options.value==""){
	  alert("사이즈 선택하세요.");
	  return false;
	}
	if(document.writeform.product_total.value==""){
	  alert("총재고를 입력하세요.");
	  document.writeform.product_total.focus();
	  return false;
	}
	if(document.writeform.product_color.value==""){
	  alert("색상을 입력하세요.");
	  document.writeform.product_color.focus();
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
	                <li><a href="/pageWep/member/loginForm.do"class="external">LOGOUT</a></li>
	                <li><a href="/pageWep/board/list.do" class="external">게시판</a></li>
	                <li><a href="/pageWep/member/supervisemember.do" class="external">회원관리</a></li>
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
	                <li><a href="/pageWep/member/loginForm.do" class="external">LOGOUT</a></li>
	                <li><a href="/pageWep/cart/cart.do" class="external">장바구니</a></li>
	                <li><a href="/pageWep/board/list.do" class="external">게시판</a></li>
	            </ul>
	            <span style="float:right">${sessionScope.memId}님</span>
	        </nav>
	    </div>
	</c:if>
</c:if>
</header>
<!-- End Header -->

<!-- 작업 부분   -->
<c:if test="${sessionScope.memId == 'admin' }">
<center>

<div>   <%-- 메뉴바  --%>
        <ul>
            <li><a href="prolist.do">상품리스트</a></li>
            <li><a href="prowriterForm.do">상품등록</a></li>
        </ul>    
</div>

    <form method="post" name="writeform" action="proupdatePro.do?pageNum=${pageNum}" enctype="multipart/form-data" onsubmit="return writeSave()" >
    <input type="hidden" name="product_num" value="${product_num}">
    <table border="1">
    <tr>
      <td>제품이름</td>
      <td><input type="text" name="product_name" value="${arti.product_name}"/></td>
    </tr>
    <tr>
      <td>상품이미지 등록</td>
      <td><input type="file" name="upload1" />${arti.product_img}</td>
    </tr>
    <tr>
      <td>제품가격</td>
      <td><input type="text" name="product_price" value="${arti.product_price}"/></td>
    </tr>
    <tr>
      <td>제품종류</td>
      <td>
      <c:if test="${arti.product_type == 'OUT'}">
      <select name="product_type">
      <option value="OUT" selected="selected">OUT</option> 
      <option value="TOP">TOP</option> 
      <option value="BOTTOM">BOTTOM</option>  
      </select>
      </c:if>
      
      <c:if test="${arti.product_type == 'TOP'}">
      <select name="product_type">
      <option value="OUT">OUT</option> 
      <option value="TOP" selected="selected">TOP</option> 
      <option value="BOTTOM">BOTTOM</option>  
      </select>
      </c:if>
      
      <c:if test="${arti.product_type == 'BOTTOM'}">
      <select name="product_type">
      <option value="OUT">OUT</option> 
      <option value="TOP">TOP</option> 
      <option value="BOTTOM" selected="selected">BOTTOM</option>  
      </select>
      </c:if>
      </td>
    </tr>
    <tr>
      <td>사이즈(재입력)</td>
      <td>

             이전 선택값:${arti.product_size}
      <select multiple="multiple" name="product_size" size="3">
      <option value="" disabled="disabled" selected="selected">선택하시오</option> 
      <option value="XL">XL</option> 
      <option value="L" >L</option> 
      <option value="M" >M</option>
      <option value="F" >FREE</option> 
      </select>
    </td>
    </tr>
    <tr>
      <td>색상</td>
      <td><input type="text" name="product_color" value="${arti.product_color}"/></td>
    </tr>
    <tr>
      <td>총재고</td>
      <td><input type="text" name="product_total" value="${arti.product_total}"/></td>
    </tr>
    <tr>
      <td>승인여부</td>
    <c:if test="${arti.product_check == 'yes' }">
      <td><input type="radio" name="product_check" value="yes" checked="checked"/>yes
          <input type="radio" name="product_check" value="no" />no
      </td>
    </c:if>
    <c:if test="${arti.product_check == 'no' }">
      <td><input type="radio" name="product_check" value="yes" />yes
          <input type="radio" name="product_check" value="no" checked="checked"/>no
      </td>
    </c:if>
    </tr>
    <tr colspan="2">
      <td align="center">
      <input type="submit" value="수정하기">
      <input type="button" value="목록" OnClick="window.location='/pageWep/product/prolist.do?pageNum=${pageNum}'">
      <input type="reset" value="초기화"></td>
    </tr>
    </table>
    </form>
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