<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>

<c:if test="${check==1}">
    <c:if test="${sessionScope.memId == 'admin'}">
    <c:redirect url="/product/main.do"/>
    </c:if>
    <c:if test="${sessionScope.memId != 'admin'}">
	<c:redirect url="/product/main.do"/>
	</c:if>
</c:if>
<c:if test="${check==0}">
	<script>
		alert("PASSWORD가 맞지 않습니다.");
		history.go(-1);
	</script>
</c:if>
<c:if test="${check ==-1 }">
	<script>
		alert("ID가 맞지 않습니다.");
		history.go(-1);
	</script>
</c:if>	

<!-- 작성자 : 조문영  /수정날짜:20171018   --> 
<!-- 작성자 : 노재혁  /수정날짜:20171025   --> 