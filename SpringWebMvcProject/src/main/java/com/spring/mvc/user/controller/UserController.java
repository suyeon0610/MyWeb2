package com.spring.mvc.user.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	
	@PostMapping("/checkId")
	public String checkId(@RequestBody String account) {
		System.out.println("/user/checkId: POST");
		System.out.println("param: " + account);
		
		int checkNum = service.checkId(account);
		
		if(checkNum == 1) {
			System.out.println("아이디가 중복 되었습니다.");
			return "duplicated";
		} else { 
			System.out.println("아아디가 중복되지 않았습니다.");
			return "available";
		}
		
	}
	
	//회원 가입 요청 처리
	@PostMapping("/")
	public String register(@RequestBody UserVO vo) {
		System.out.println("/user/: POST");
		service.register(vo);
		return "joinSuccess";
	}
	
	//로그인 처리
	@PostMapping("/loginCheck")
	public String loginCheck(@RequestBody UserVO login,
							 /* HttpServletRequest request */
							 HttpSession session, HttpServletResponse response) {
		System.out.println("/uesr/loginCheck: POST");
//		if(service.checkId(login.getAccount()) == 0) {
//			return "idFail";
//		} else {
			
			// 서버에서 세션 객체를 얻는 방법
			// 1.HttpServletRequest 객체 사용.
//			HttpSession session = request.getSession();
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			UserVO user = service.selectOne(login.getAccount());
			
			if(user != null) {
				if(encoder.matches(login.getPassword(), user.getPassword())) {
					session.setAttribute("login", user);
					
					long limitTime = 60 * 60 * 24 * 90;
					
					//자동 로그인 체크 시 처리해야 할 내용
					if(login.isAutoLogin()) { //자동 로그인 희망
						//쿠키를 이용하여 자동 로그인 정보 저장
						System.out.println("자동 로그인 쿠키 생성 중...");
						//세션 아이디를 가지고 와서 쿠키에 저장(고유한 값 필요)
						Cookie loginCookie = new Cookie("loginCookie", session.getId());
						loginCookie.setPath("/"); //쿠키가 동작할 수 있는 유효한 URL
						loginCookie.setMaxAge((int)limitTime); //초로 시간 받음
						response.addCookie(loginCookie);
						
						//자동 로그인 유지 시간을 날짜 객체로 변환 (DB에 삽입하가 위해, 밀리초)
						long expiredDate = System.currentTimeMillis() + (limitTime * 1000);
						//Date 객체의 생성자에 매개값으로 밀리초의 시간을 전달하면 날짜 형태로 변경해 줌
						Date limitDate = new  Date(expiredDate);
						
						service.keepLogin(session.getId(), limitDate, login.getAccount());
					}
					return "loginSuccess";
				} else {
					return "pwFail";
				}
			} else {
				return "idFail";
			}
			
//			if(!login.getPassword().equals(user.getPassword())) {
//				return "pwFail";
//			} else {
//				session.setAttribute("login", user);
//				return "loginSuccess";
//			}
//		}
	}
	
	//로그아웃
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session, RedirectAttributes ra,
								HttpServletRequest request, HttpServletResponse response) {
		System.out.println("/user/logout: GET");
		
		UserVO user = (UserVO) session.getAttribute("login");
		
		if(session.getAttribute("login") != null) {
			session.removeAttribute("login");
			ra.addFlashAttribute("msg", "logout");
		}
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); //자동 로그인 쿠키
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			loginCookie.setValue(null);
			loginCookie.setPath("/"); //쿠키 생성 시 유효 url을 지정한 경로
			response.addCookie(loginCookie);
			service.keepLogin("none", new Date(), user.getAccount());
		}
		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("name", "value");
//		mv.setViewName("redirect:/");
		
		return new ModelAndView("redirect:/");
	}
	
}
