package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.myweb.command.UserVO;

public class UserLoginSuccessHandler extends HandlerInterceptorAdapter{

	//로그인 처리 이후에 실행되는 핸들러 (PostHandle) 오버라이딩
	//1. 로그인 요청으로 들어올 때 실행되도록 등록 (xml 파일에 빈으로 등록 후 매핑)
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("로그인 인터셉터");
		
		//modelAndView 객체에 있는 모델 데이터가 map의 형식으로 반환.
//		ModelMap mv = modelAndView.getModelMap();
		Object vo = modelAndView.getModel().get("user");   
		
		System.out.println("user: " + vo);
		
		if(vo != null) { //controller에서 로그인 성공했던 사람
			
			//로그인 성공한 회원에게 세션 데이터를 생성해서 로그인 유지를 하게 해줌.
			HttpSession session = request.getSession();
			session.setAttribute("login", vo);
			response.sendRedirect(request.getContextPath()); //home으로 리다이렉트
			return;
			
		} else {
			
			modelAndView.addObject("msg", "loginFail");
			modelAndView.setViewName("/user/userLogin");
//			response.sendRedirect(location);
			
		}
	}
	
}
