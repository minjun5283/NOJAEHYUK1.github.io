<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원 탈퇴</title>
</head>
<c:if test = "${check==1}">
<body>
<form method="post" action="/pageWep/member/main.do" name="userinput">
<table width="250" border="0" cellspacing="0" cellpadding="2">
	<tr>
		<td height="30" align = "center">
		<p>회원정보가 삭제되었습니다.</p></td>
	</tr>
	<tr>
		<td align="center">
			<input type= "submit" value="확인">
		</td>
	</tr>		
</table>
</form>
</c:if>

<c:if test="${check!=1 }">
	<script>
		alert("비밀번호가 틀립니다.");
		history.go(-1);
	</script>
</c:if>	
</body>
</html>

<!-- 작성자 : 조문영  /수정날짜:20171018   --> 