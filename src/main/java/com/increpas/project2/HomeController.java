package com.increpas.project2;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vo.userVO.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
//		Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication(); //세션에서 현재 인증을 가져옴
		
//		System.out.println(authentication2); //인증 출력
//		Object obj = authentication2.getPrincipal(); //인증에서 객체를 가져옴 
//		System.out.println(obj); 
		/*
		 * 출력 예
		 * 
		 * 로그인시
		 * UserVO [userId=12, userFirstname=null, userLastname=null, userEmail=test11@test.com, userPassword=$2a$10$RGoq2iC.UIsZAtqubbRTuu4mCp4jwbWp8xHRVy0rpR/NL6ZyHIK1S, userSalt=null, userDate=null, userBirth=null]
		 * 
		 * 
		 * 미로그인시
		 * anonymousUser
		 */
		
		
//		if(obj.getClass().equals(new UserVO().getClass())) { //인즌에서 가져온 객체가 UserVo클래스인지 확인
//			System.out.println(((UserVO)obj).getUserId());//Uservo일경우 getUserId()메소드 사용하여 값 가져오기
//		}
		return "/WEB-INF/views/home.jsp";
	}
	
}
