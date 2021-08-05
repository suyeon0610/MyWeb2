package com.spring.mvc.user.repository;

import com.spring.mvc.user.model.UserVO;

public interface IUserMapper {

	//아이디 중복 체크
	int checkId(String account);
	
	//회원 가입 기능
	void register(UserVO user);
	
	//회원 정보 조회 기능
	UserVO selectOne(String account);
	
	//회원 탈퇴
	void delete(String account);
}
