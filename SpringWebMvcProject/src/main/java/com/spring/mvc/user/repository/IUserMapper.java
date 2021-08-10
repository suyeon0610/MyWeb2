package com.spring.mvc.user.repository;

import java.util.Date;
import java.util.Map;

import com.spring.mvc.user.model.UserVO;

public interface IUserMapper {

	// 아이디 중복 체크
	int checkId(String account);

	// 회원 가입 기능
	void register(UserVO user);

	// 회원 정보 조회 기능
	UserVO selectOne(String account);

	// 회원 탈퇴
	void delete(String account);

	// 자동로그인 추가 정보 저장
	void keepLogin(Map<String, Object> datas);

	// 세션아이디를 통해 회원 정보 조회
	UserVO getUserWithSessionId(String sessionId);
}
