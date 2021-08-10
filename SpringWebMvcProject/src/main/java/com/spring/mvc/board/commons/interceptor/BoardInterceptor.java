package com.spring.mvc.board.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//인터셉터 클래스를 만드려면
public class BoardInterceptor extends HandlerInterceptorAdapter {

	//preHamdle은 컨트롤러로 들어가기 전 처리해야 할 로직 작성
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("게시판 인터셉터 발동");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			System.out.println("회원 인증 실패");
			return false; //false 리턴 시 컨트롤러로 요청 전달 안됨
		}
		
		System.out.println("회원 인증 성공");
		return true;
	}
	
	//postHandle은 컨트롤러를 나갈 때 공통 처리해야 할 로직 작성
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("게시판 인터셉터의 postHendle 호출");
		
		System.out.println("모델 객체 내부: " + modelAndView.getModel());
		
		Object data = modelAndView.getModel().get("article");
		System.out.println("article이라는 이름의 데이터: " + data);
		
		//컨트롤러에서 로직을 처리하고 나가는 흐름을 붙잡아서
		//모델 데이터가 제대로 전송이 되는지 확인하고, 추가할 내용이나 수정할 내용이 있다면
		//모델 객체를 받아와서 수정도 가능 함.
		//기타 특징을 이용하여 흐름을 제어할 수 있음 (sendRedirect, viewName 수정)
	}
}
