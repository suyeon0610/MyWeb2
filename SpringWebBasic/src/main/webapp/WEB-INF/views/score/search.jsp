<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		- /selectOne 요청을 통해 학생 정보 조회
		search-result.jsp에 모든 학생 정보 호출
	 --%>

	<form action="<c:url value='/score/selectOne' />">
		<p>
			#조회할 학번: <input type="text" name="stuNum" size="5">
			<input type="submit" value="확인">
		</p>
	</form>

<%-- "학번 정보가 없습니다" 띄우기 --%>
	<script>
		const msg = "${msg}";
		if(msg === "noSearch") {
		alert('정보가 없습니다.');		
		}
	</script>
</body>
</html>