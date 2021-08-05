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
	
	<h2>${one.stuName}의 점수</h2>
	<p>
		#국어: ${one.kor } <br>
		#영어: ${one.eng } <br>
		#수학: ${one.math } <br>
		#총점: ${one.total } <br>
		#평균: ${one.average }
	</p>
	
	<a href="<c:url value='/score/search'/>" >점수 개별 조회</a>

</body>
</html>