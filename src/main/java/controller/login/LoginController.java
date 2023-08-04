package controller.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.ViewPath;
import service.login.LoginService;
import vo.userVO.UserVO;

@Controller
public class LoginController {
	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping("/joinForm")
	public String loginForm() {
		return ViewPath.LOGIN + "joinForm.jsp";
	}
	
	@RequestMapping("/join")
	public String join(Model model, UserVO vo) {
		String msg = null;
		int su = loginService.insert(vo);
		if(su != 0) {
			msg = "회원가입 성공";
		}else {
			msg = "회원가입 실패";
		}
		model.addAttribute("msg",msg);
		return ViewPath.LOGIN + "loginForm.jsp";
	}
	
	@RequestMapping(value = "/checkId" ,produces = "application/text;charset=utf8" )
	@ResponseBody
	public String checkId(String id) {
		String reStr = "0";
		System.out.println("ajax! : " + id);
		String salt = loginService.getSalt(id);
		System.out.println(salt);
		if(salt == null) {
			reStr = "1";
		}
		return reStr;
	}
	
	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest request,String id) {
		boolean check = false;
		
		if(id == null) {
			Cookie[] cks = request.getCookies();
			
			if(cks != null) {
				for(Cookie ck : cks) {
					if(ck.getName().equals("ckid")) {
						id = ck.getValue();
						check = true;
						break;
					}
				}
			}
		}
		
		request.setAttribute("id", id);
		request.setAttribute("check", check);
		
		return ViewPath.LOGIN + "loginForm.jsp";
		
	}
}
