package com.spring.mvc.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"})
public class UserMapperTest {

	//mapper 계층의 객체 필요
	@Autowired
	private IUserMapper mapper;
	
	//회원가입 진행
	@Test
	public void registerTest() {
		System.out.println("회원가입 요청!");
		UserVO user = new UserVO();
		user.setAccount("abc1234");
		user.setPassword("a1234");
		user.setName("홍길동");
		mapper.register(user);
	}
	
	//회원 가입한 아이디로 중복확인 해서
	//COUNT(*) 이용해서 1 리턴 되는지 확인
	@Test
	public void checkIdTest() {
		System.out.println("아이디 중복 체크 요청!");
		String account = "abc1234";
		
		if(mapper.checkId(account) == 1) {
			System.out.println("아이디 이미 존재함");
		} else {
			System.out.println("아이디 존재하지 않음");
		}
	}
	
	//가입한 회원의 모든 정보 얻어서 출력
	@Test
	public void selectOneTest() {
		System.out.println("회원 정보 조회 요청!");
		System.out.println(mapper.selectOne("abc1234"));
	}
	
	//계정 탈퇴 진행
	@Test
	public void deleteTest() {
		System.out.println("회원 삭제 요청!");
		String account = "abc1234";
		mapper.delete(account);
		if(mapper.selectOne(account) == null) {
			System.out.println("회원 탈퇴 성공!");
		} else {
			System.out.println("회원 탈퇴 실패!");
		}
	}
	
}
