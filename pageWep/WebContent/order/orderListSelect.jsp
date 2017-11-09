
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
         /* 기본 설정*/
         a{text-decoration:none; color:#000000;}         
         a:hover{color:#ff0000;}                     
         
         /* div tag */
         div ul{padding-top:10px;}                     
         div ul li {
            display:inline;                   <%-- 반대로   --%>         
            border-left:1px solid #999;       <%-- | 로 분류  --%>        
            font:bold 12px Dotum;             <%-- 폰트 및 글씨 크기  --%>           
            padding:0 10px;                   <%-- 간격  --%>      
        }
         div ul li:first-child{border-left:none;}    <%-- 제일 왼쪽 | 제거 --%>      
</style>

<fmt:requestEncoding value="UTF-8"/>    
    

<c:if test="${sessionScope.memId == 'admin'}">


<form  method="post" action="/pageWep/member/logout.do">  
      ${sessionScope.memId}님  
      <input type="submit"  value="LOGOUT">
      <input type="button" value="MYPAGE" onclick="window.location='/pageWep/member/mypage.do'">
	  <input type="button" value="게시판">
	  <input type="button" value="코디 게시판">
	  <input type="button" value="Order">
	  <input type="button"  align="right" value = "CART">
</form>	 


<div>   <%-- 메뉴바  --%>
        <ul>
            <li><a href="/pageWep/product/prolist.do">상품리스트</a></li>
            <li><a href="/pageWep/product/prowriterForm.do">상품등록</a></li>
            <li><a href="/pageWep/order/orderList.do">주문리스트</a></li>
        </ul>    
</div>

<table border="1">
<tr>
  <td>
  <a href="/pageWep/order/orderList.do">원래목록</a>
  </td>
</tr>
</table>
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
<form method="post" action="orderdeleteForm.jsp?order_num=${arti.order_num}&pageNum=${pageNum}">	
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
</c:if>

<%-- 나중에 수정해야함  --%>
<c:if test="${count>0}">
   <c:if test="${startPage>10}">
   <a href="orderListSelect.do?pageNum=${startPage - 10}">[이전]</a>
   </c:if>
   <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
   <a href="orderListSelect.do?pageNum=${i}&selectPro=${selectPro}&getTex=${getTex}">[${i}]</a>
   </c:forEach>
   <c:if test="${endPage < pageCount}">
   <a href="orderListSelect.do?pageNum=${startPage + 10}">[다음]</a>
   </c:if>
</c:if>

</c:if>