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

<title>주문하기</title>   

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
        var arr_check = document.getElementsByName("check1");
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
        if(document.cartform.order_phone.value==""){
            alert("연락처를 입력하세요.");
            document.cartform.order_phone.focus();
            return false;
        }
       if(document.cartform.order_add.value==""){
            alert("배송 주소를 입력하세요.");
            document.cartform.order_add.focus();
            return false;
        }
       if(document.cartform.order_etc.value==""){
          alert("배송 메세지를 입력하세요.");
          document.cartform.order_etc.focus();
          return false;
        }      
	}
    function itemSum()
    {
       var sum = 0;
       var count = document.cartform.check1.length;
       var table = ${'table1'};
       if(count){
       for(var i=0; i<count; i++ ){
           if(document.cartform.check1[i].checked == true){
            sum += parseInt(table.rows[i+1].cells[7].innerHTML);
           }
           document.cartform.total_sum.value = sum;
       }
       }else{
           sum = parseInt(table.rows[1].cells[7].innerHTML);
           document.cartform.total_sum.value = sum;
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
<center>
<body onload="itemSum();">
<form method="post"  name="cartform" action="orderFormPro.do?ch=${ch}" onSubmit="return go_check()">
<h2>주문/결제<br>
<hr color="black">
</h2>

<h3>주문리스트</h3>
<table border="1" id="table1">
<tr>
  <td></td>
  <td></td>
  <td>제품 번호</td>
  <td>제품명 </td>
  <td>수량</td>
  <td>사이즈 </td>
  <td>색상</td>
  <td>총 가격</td>
</tr>
<c:if test="${ch=='true'}">
<c:set var="i" value="${i+1}"/>
<c:forEach var="arti" items="${checklist}">
  <tr>
  <td><input type="checkbox" name="check1" value="${arti[i-1].product_num}" checked="checked" onClick="itemSum();"></td>
  <td><img src="${arti[i-1].product_img}" width="50" height="50"></td>
  <td>${arti[i-1].product_num}</td>
  <td>${arti[i-1].product_name} </td>
  <td>${arti[i-1].amount}</td>
  <td>${arti[i-1].product_size} </td>
  <td>${arti[i-1].product_color}</td>
  <td>${arti[i-1].product_price}원</td>
  </tr>  
</c:forEach>
</c:if>
<c:if test="${ch!='true'}">
	<c:forEach var="arti" items="${proList1}">
	<tr>
	  <td><input type="checkbox" name="check1" value="${arti.product_num}" checked="checked" onClick="itemSum();"></td>
	  <td><img src="${arti.product_img}" width="50" height="50"></td>
	  <td>${arti.product_num}</td>
	  <td>${arti.product_name}</td>
	  <td>${order_count}</td>
	  <td>${product_size} </td>
	  <td>${product_color}</td>
	  <td>${order_price}원</td>
	</tr>
	  <input type="hidden" name="product_num" value="${arti.product_num}"/>
	  <input type="hidden" name="order_name" value="${arti.product_name}"/>
	  <input type="hidden" name="order_count" value="${order_count}"/>
	  <input type="hidden" name="order_size" value="${product_size}"/>
	  <input type="hidden" name="order_color" value="${product_color}"/>
	  <input type="hidden" name="order_price" value="${order_price}"/>
	</c:forEach>
	</c:if>

</table>
<br><br>
<h3>주문자/배송정보</br>
<hr color="black"> </h3>

<table>
  
  <tr>
    <th>이름</th>
    <td><input type="text" name="member_id" value="${id}" readonly="readonly"></td>
  </tr>
  <tr>
    <th>연락처</th>
    <td><input type="text" name="order_phone"></td>
  </tr>
  <tr>
    <th>배송지 주소</th>
    <td><input type="text" size="50" name="order_add"></td>
  </tr>
    <tr>
    <th>배송 메세지</th>
    <td><textarea name="order_etc"></textarea>
    </td>
  </tr>
  
</table>



<h3>최종 결제 금액</br>
<hr color="black"> </h3>
<input type="text" name="total_sum" readonly="readonly"/> 
<input type="submit" value="구매하기">
<input type="button" value="취소하기" onclick="javascript:history.back()">

</form>
</body>
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