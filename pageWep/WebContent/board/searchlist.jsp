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

<title>게시판</title>   

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
<body >
<center><b>글목록(전체 글:${count})</b>
<table width="700">
<tr>
    <td align="right" bgcolor="${value_c}">
    
    <c:if test="${memId == null}">
   			<a href="/pageWep/member/loginForm.do">로그인</a>
   	</c:if>
    <c:if test="${memId != null}">
    		<a href="/pageWep/board/list.do">글목록</a>
    		<a href="/pageWep/board/writeForm.do"> 글쓰기 </a>
    		<a href="/pageWep/board/myWrite.do">내 글</a>
   	</c:if>
    </td>
</table>

<c:if test="${count==0}" >
	<table width="700" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center">
		    게시판에 저장된 글이 없습니다.
		</td>
	</table>
</c:if>
<c:if test = "${count > 0}" >
	<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
		<tr height="30"> 
			<td align="center"  width="50"  >번 호</td> 
		    <td align="center"  width="250" >제   목</td> 
		    <td align="center"  width="100" >작성자</td>
		    <td align="center"  width="150" >작성일</td> 
		    <td align="center"  width="50" >조 회</td>  
		</tr>
	<c:forEach var="article" items = "${articleList}">	
	<tr height="30">
    	<td align="center"  width="50" > 
    		<c:out value = "${number}"/>
    		<c:set var="number" value = "${number-1 }"/>
    	</td>
    	<td  width="250" >
			<c:if test ="${article.board_relevel > 0}">
				<img src="images/level.gif" width="${5*article.board_relevel}" height="16">
	  			<img src="images/re.gif">
	  		</c:if>
	  		<c:if test="${article.board_relevel == 0 }">
	  			<img src="images/level.gif" width="${5*article.board_relevel}" height="16">
	  		</c:if>
			
			<a href=<c:if test="${memId!=null}">"/pageWep/board/content.do?num=${article.board_num}&pageNum=${currentPage}"</c:if>
         <c:if test="${memId==null}">"/pageWep/member/loginForm.do"</c:if>>
			${article.board_subject}</a> 
				<c:if test = "${article.board_readcount >= 20}">
					<img src="images/hot.gif" border="0"  height="16"> </td>
				</c:if>
		</td>
		<td align="center"  width="100">${article.member_id}</td>
    	<td align="center"  width="150">${article.board_reg_date}</td>
    	<td align="center"  width="50">${article.board_readcount}</td>
    </tr>
	</c:forEach>
</table>
</c:if>

<c:if test = "${count>0}">
	<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
  	<c:set var="pageBlock" value="10" />
    <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
    <c:set var="startPage" value="${result * 10 + 1}" />
    <c:set var="endPage" value="${startPage + pageBlock-1}"/>
    <c:if test="${endPage > pageCount}">
        <c:set var="endPage" value="${pageCount}"/>
    </c:if> 
          
    <c:if test="${startPage > 10}">
        <a href="/pageWep/board/searchlist.do?pageNum=${startPage - 10 }">[이전]</a>
    </c:if>

    <c:forEach var="i" begin="${startPage}" end="${endPage}">
       <a href="/pageWep/board/searchlist.do?pageNum=${i}&search=${search}&ch=${ch}">[${i}]</a>
    </c:forEach>

    <c:if test="${endPage < pageCount}">
        <a href="/pageWep/board/searchlist.do?pageNum=${startPage + 10}">[다음]</a>
    </c:if>
</c:if>

	<form action = "/pageWep/board/searchlist.do" method = "post">
		<select name = "ch">
			<option value = "member_id">작성자 </option>
			<option value = "board_subject">제목 </option>
		</select>
		<input type = "text" name = "search" />
		<input type = "submit" value = "검색" />
	</form>
 </center>
</body>
<!-- 작업 부분   -->

<!-- Footer -->
<footer>
	<p class="credits">&copy;글로벌아이티 인재개발 의류몰 <a href="" title="sitemap">사이트맵</a></p>
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