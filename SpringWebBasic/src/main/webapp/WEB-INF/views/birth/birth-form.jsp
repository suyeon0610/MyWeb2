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
		1. BirthController를 생성 후, 현재 페이지를 열어주는 메서드 선언
		(/birth: GET)
		
		2. 다음 생년월일을 받아서 콘솔에 출력을 진행하는 메서드 생성. (/birth: POST)
		조건) BirthVO 커맨드 객체 사용
		콘솔창에는 전송된 값을 붙여서 출력. ex) 19921013
		(한 자리 숫자일 경우 앞에 0이 붙어야 함. setter메서드 조작)
		
		3. birth-result.jsp 페이지에
		"당신의 생일은 xxxx년 xx월 xx일 입니다." 출력.
	 --%>

	<form action="/basic/birth" method="post"> 
		<fieldset>
			<legend>생일 등록 양식</legend>
			<p>
				# 생년월일
				<input type="text" name="year" size="6" maxlength="4" placeholder="연도(4자리)">
				
				<select name="month">
					<c:forEach var="m" begin="1" end="12">
						<option value="${m}">${m}월</option>
					</c:forEach>
				</select>
				
				<select name="day">
					<c:forEach var="d" begin="1" end="31">
						<option value=${d}>${d}일</option>
					</c:forEach>
				</select>
				
				<input type="submit" value="확인">
			</p>
		</fieldset>
	</form>

</body>
</html>