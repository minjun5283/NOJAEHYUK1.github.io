<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>HOME</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
 <c:if test="${sessionScope.memId ==null}">
<form name = "inform" method = "post" action = "/pageWep/member/loginForm.do">
	
	<td><span style="float:right"><input type = "submit"  align="right" value="LOGIN">
		<input type = "button"  value = "JOIN"
		onclick="javascript:window.location='/pageWep/member/inputForm.do'">
		<input type = "button"  value = "MYPAGE" 
		onClick="javascript:window.location='/pageWep/member/loginForm.do'">
		<input type="button" value="코디 게시판">
		<input type = "button"  value = "Order">
		<input type = "button" value = "Cart"></span><br/>
		
       
</form>
</c:if>
 <c:if test="${sessionScope.memId!=null}"> 
     
   	<c:if test ="${sessionScope.memId == 'admin'}">
           <td><span style="float:right">
             <form  method="post" action="/pageWep/member/logout.do">  
              	관리자 
             <input type="submit"  value="LOGOUT">
             <input type ="button" value="상품 등록" onclick="location='/pageWep/product/prowriterForm.do'">
			 <input type="button" value="게시판"
			 onClick="javascript:window.location='/pageWep/board/list.do'">
			 <input type="button" value="코디 게시판">
			 <input type = "button"  align="right" value = "주문현황">
			 </span>
            
         </td>
        </tr>
     <br>
     </c:if>
     <c:if test ="${sessionScope.memId != 'admin'}">
     	<td><span style="float:right">
     	 <form  method="post" action="/pageWep/member/logout.do">  
             ${sessionScope.memId}님  
             <input type="submit"  value="LOGOUT">
             <input type ="button" value="MYPAGE" onclick="window.location='/pageWep/member/mypage.do'">
			 <input type="button" value="게시판"
			 onClick="javascript:window.location='/pageWep/board/list.do'">
			 <input type="button" value="코디 게시판">
			 <input type="button" value="Order">
			 <input type = "button"  align="right" value = "CART">
			 </span>
     </c:if>
 	</c:if>
</form>
</html>

<!-- 작성자 : 노재혁  /수정날짜:20171024   --> 
<!-- 작성자 : 조문영  /수정날짜:20171018   --> 