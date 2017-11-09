<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head><title>ID 중복 확인</title>

<fmt:requestEncoding value = "UTF-8"/>

<c:if test = "${check ==1}"> 

<table width = "230" cellspacing="0"  cellpadding="2">
<tr>
		<td height = "35"> ${id}이미 사용중인 아이디 입니다.</td>
	</tr>
</table>
<form name = "checkForm" method ="post" action = "/pageWep/member/confirmId.do">
<table width = "230" border="1" cellspacing="0" cellpadding = "3">
	<tr>
		<td align="center">다른아이디를 선택하세요.<p>
		<input type= "text" size = "20" maxlength ="15" name = "id">
		<input type = "submit" value = "ID중복확인">
		</td>
	</tr>	
</table>
</form>		
</c:if>

<c:if test = "${check !=1}">
<table width ="230" border = "1" cellspacing = "0" cellpaddig ="2">
	<tr>
		<td align = "center">
		<p>사용 가능한 ID입니다.</p>
		<input type = "button" value = "close" onclick = "setid()">
		</td>
	</tr>	
</table>
</c:if>
</body>
</html>

<script language="javascript">

  function setid()
    {		
    	opener.document.userinput.id.value="${id}";
		self.close();
		}

</script>

<!-- 작성자 : 조문영  /수정날짜:20171018   --> 