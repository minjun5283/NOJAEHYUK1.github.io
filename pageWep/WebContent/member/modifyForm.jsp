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

<title>회원관리</title>   

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
<script language="javascript">
	function checkIt(){
		var userinput = eval("document.userinput");
		
		if(!userinput.pw.value){
			alert("비밀번호를 입력해주세요");
			return false;
		}
		if(userinput.pw.value != userinput.pw2.value){
			alert("비밀번호를 동일하게 입력해주세요");
			return false;
		}
		if(!userinput.name.value){
				alert("이름을 입력해주세요.");
				return false;
		}
		if(!userinput.phone.value){
			alert("전화번호를 입력해주세요.");
			return false;
		}
		if(!userinput.address.value){
			alert("주소를 입력해주세요.");
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
<body>
<form method="post" action="/pageWep/member/modifyPro.do" name = "userinput" onsubmit="return checkIt()">

	<table width = "500" border="1" cellspacing="0" cellpadding="1" align="center">
	<tr>
		<td colspan="2" height="32" align="center">
		<font size="+1"><b>회원 정보 수정</b></font></td>
	</tr>
	<tr>
		<td colspan="2" align="left">사이트 이용정보 입력</td>
	</tr>
	<tr>
		<td width="150" align="left">ID</td>
		<td width="350">${c.getId()}</td>
	</tr>
	<tr>
		<td align="left">PASSWORD</td>
		<td><input type="password" name="pw" size="52" maxlength ="30" value="">
		</td>
	</tr>
		<tr>
		<td align="left">PASSWORD CHECK</td>
		<td><input type="password" name="pw2" size="52" maxlength ="30" value="">
		</td>
	</tr>
		<td colspan="2" align="left">개인 정보 입력</td>
	</tr>
	<tr>
		<td align="left">NAME</td>
		<td>
		<c:if test="${c.getName()==null }">
		<input type="text"  name="name" size="52" maxlength="15" />
		</c:if>
		<c:if test="${c.getName()!=null }">
		<input type = "text" name="name" size="52" maxlength="15" value="${c.getName()}">
		</c:if>
		</td>
	</tr>
	<tr>
		<td align= "left">PHONE</td>
		<td>
		<c:if test="${c.getPhone()==null }">
		<input type="text"  name="phone" size="52" maxlength="15" />
		</c:if>
		<c:if test="${c.getPhone()!=null }">
		<input type= "text" name = "phone" value="${c.getPhone()}" size="25" maxlength="15">*-는 빼고 작성해주세요.
		</c:if>	
		</td>
	</tr>
	<tr>
		<td align ="left">ADDRESS</td>
		<td>
		<c:if test="${c.getAddress()==null }">
		<textarea  name="address" cols="52" rows="2" ></textarea>
		</c:if>
		<c:if test="${c.getAddress()!=null }">
		<textarea cols="52" rows="2" name="address">${c.getAddress()}</textarea>
		</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="submit" name="modify" value="수 정">
		<input type="button" value="취 소" onclick="javascript:window.location='/pageWep/product/main.do'">
		<input type = "button" value="뒤로가기" onclick="window.location='/pageWep/member/mypage.do'">
		</td>
	</tr>
	</table>
</form>
</body>

<!-- 작업 부분   -->

<!-- Footer -->
<footer>
	<p class="credits">&copy;글로벌아이티 인재개발 의류몰 <a href="" title="sitemap">사이트맵</a>
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