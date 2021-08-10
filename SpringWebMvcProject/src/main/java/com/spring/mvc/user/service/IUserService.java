package com.spring.mvc.user.service;

import java.util.Date;

import com.spring.mvc.user.model.UserVO;

public interface IUserService {
	
	// 아이디 중복 체크
	int checkId(String account);

	// 회원 가입 기능
	void register(UserVO user);

	// 회원 정보 조회 기능
	UserVO selectOne(String account);

	// 회원 탈퇴
	void delete(String account);
	
	//자동로그인 추가 정보 저장
	void keepLogin(String sessionId, Date limitDate, String account);
	
	//세션아이디를 통해 회원 정보 가져오기
	UserVO getUserWithSessionId(String sessionId);

}
