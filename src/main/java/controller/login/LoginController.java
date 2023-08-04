package controller.login;

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
		return ViewPath.LOGIN + "result.jsp";
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
}
