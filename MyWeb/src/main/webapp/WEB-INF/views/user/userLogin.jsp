﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    	<%@ include file="../include/header.jsp" %>
    

    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7 col-xs-10 login-form">
                    <div class="titlebox">
                        로그인
                    </div>
                    <form action="<c:url value='/user/login' />" method="post" id="loginForm">
                        <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">아이디</label>
                            <input type="text" class="form-control" id="id" name="id" placeholder="아이디">
                         </div>
                         <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">비밀번호</label>
                            <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호">
                         </div>
                         <div class="form-group">
                            <button type="button" class="btn btn-info btn-block" id="loginBtn">로그인</button>
                            <button type="button" class="btn btn-primary btn-block">회원가입</button>
                         </div>
                    </form>                
                </div>
            </div>
        </div>
    </section>
    
        <%@ include file="../include/footer.jsp" %>
        
<script>

	const msg = '${msg}';

	if(msg === "success") {
		alert('회원가입이 정상 처리되었습니다.');
	} else if(msg === 'loginFail') {
		alert('로그인 실패! 아이디와 비밀번호를 확인하세요.');
	}
	
	$('#loginBtn').click(function() {
		if($('#id').val() === '' || $('#pw').val() === '') {
			alert('아이디와 비밀번호를 입력해 주세요.');
		} else {
			$('#loginForm').submit();
		}
	}); //로그인 버튼 끝
	
</script>
    
