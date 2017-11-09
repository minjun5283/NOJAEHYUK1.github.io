<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 
 <h1>상품 등록 삭제</h1>
 <form method="post" action="orderdeletePro.do?pageNum=${pageNum}">
 <table border="1">
 <input type="hidden" name="order_num" value="${order_num}"/>
 <c:forEach var="j" begin="0" end="${checklength-1}" step="1">
 <input type="hidden" name="check" value="${check[j]}"/>
 </c:forEach>
 <tr>
 <td>
삭제하시겠습니까?
 </td>
 </tr>
 <tr>
 <td>
 <input type="submit" value="삭제"/>
 <input type="button" value="목록으로" onclick="location='orderList.do?pageNum=${pageNum}'"/>
 </td>
 </tr>
 </table> 
 </form>
