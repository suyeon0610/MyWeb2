package com.spring.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.user.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	//서비스 객체 주입
	@Autowired
	private IUserService service;
	
	//주소 API 승인키
	//devU01TX0FVVEgyMDIxMDgxNzE1NDIyNDExMTUzNDY=
	
	@GetMapping("/userJoin")
	public String userJoin() {
		System.out.println("/user/userJoin: GET");
		return "user/userJoin";
	}
	
	@ResponseBody //Rest Controller가 아닌 경우에는 아노테이션을 붙여야 통신이 가능하다.
	@PostMapping("/idCheck")
	public String idCheck(@RequestBody String userId) {
		System.out.println("/user/idCheck: POST");
		
		int result = service.idCheck(userId);
		
		if(result == 1) {
			return "duplicated";
		} else {
			return "ok";
		}
	}
	
	@PostMapping("/join")
	public String join(UserVO vo, RedirectAttributes ra) {
		System.out.println("/user/join: POST");
		
		service.join(vo);
		ra.addFlashAttribute("msg", "success");
		System.out.println("회원가입 정상 처리됨");
		
		return "redirect:/user/userLogin";
	}
	
	@GetMapping("/userLogin")
	public void userLogin() {
		System.out.println("/user/userLogin: GET");
	}
	
	//로그인 처리
	@PostMapping("/login")
	public String login(String id, String pw, Model model, RedirectAttributes ra) {
		System.out.println("/user/login: POST");
		
		
		System.out.println("로그인 정보: " + service.login(id, pw));
		model.addAttribute("user", service.login(id, pw));
		
		return "user/userMypage";
		
		/*
		if(service.login(id, pw) != null) {
			model.addAttribute("user", service.login(id, pw));
			System.out.println("로그인 정상 처리됨" + service.login(id, pw));
			return "redirect:/";
			
		} else {
			ra.addFlashAttribute("msg", "loginFail");
			return "redirect:/user/userLogin";
		}
		*/
	}
	
	@GetMapping("/userMyPage")
	public String userMyPage() { 
		
		return "user/userMypage";
	}
}














