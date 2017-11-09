<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>


<%-- 
<c:forEach var="arti" items="cartList">

<input type="hidden" name="product_num" value="${arti.product_num}">
<input type="hidden" name="product_img" value="${arti.product_img}">
<input type="hidden" name="product_name" value="${arti.product_name}">
<input type="hidden" name="order_count" value="${arti.order_count}">
<input type="hidden" name="product_size" value="${arti.product_size}">
<input type="hidden" name="product_color" value="${arti.product_color}">
<input type="hidden" name="product_price" value="${arti.product_price}">

</c:forEach>
--%>
<input type="hidden" name="ch" value="${ch}">

<c:redirect url="/order/orderForm.do?product_num=${product_num}"/>