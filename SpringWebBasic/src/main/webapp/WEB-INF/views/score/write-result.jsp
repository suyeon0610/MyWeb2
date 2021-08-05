<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>점수 등록 성공!</h2>
	<p>

		<!-- 
			<c:url value="uri 결로" />
			-value 속성 안에 컨ㄴ텍스트 루트 경로를 제외한 절대 경로 작성.
			- 
		 -->
		<a href="<c:url value='/score/register' />">다른 점수 등록하기</a> 
		<a href="<c:url value='/score/list' />">점수 전체 조회하기</a>
		<a href="<c:url value='/score/search' />">점수 개별 조회하기</a>
	</p>

</body>
</html>