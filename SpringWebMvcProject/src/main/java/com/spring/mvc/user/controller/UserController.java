package com.spring.mvc.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
