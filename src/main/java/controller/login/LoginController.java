package controller.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Encry;
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
	
	@RequestMapping("/login")
	@ResponseBody
	public String checkLogin(HttpServletRequest request,HttpServletResponse response,UserVO vo) {
		System.out.println("login Controller");
		// �븫�샇�솕
		String salt = loginService.getSalt(vo.getEmail());
		if(salt == null) {
			return "-1";
		}
		String password = vo.getUserPassword();
		System.out.println(salt);
		System.out.println(vo.getEmail());
		System.out.println(vo.getUserPassword());
		password = Encry.encry(password, salt);
		vo.setUserPassword(password);
		UserVO checkVo = loginService.login(vo);
		if(checkVo == null) {
			return "0";
		}

		
//		// �쑀���쓽 �긽�깭( 0,1,2 )
		int no = checkVo.getUserId();
		
		
//		// �쑀���쓽 �긽�깭( 0,1,2 )
//		int st = loginService.checkStatus(vo);
//		if(st == 2) {
//			return "redirect:/uuser/joinform";
//		}
//		
//		int no = loginService.checkLogin(vo);
		

		if(no != 0) {
			request.getSession().setAttribute("login", no);
			//�븘�씠�뵒 湲곗뼲�븯湲� 泥댄겕 �쑀臾�
			String ckid = request.getParameter("ckid");
			System.out.println("ckid : " + ckid);
			
			Cookie ck = null;
			
			//荑좏궎�뙆�씪 �씫�뼱 �삤湲�...
			Cookie[] cks = request.getCookies();
			
			//湲곗〈 荑좏궎�뙆�씪 寃��깋
			if(cks != null){
				for(Cookie c : cks){
					if(c.getName().equals("ckid")){
						ck = c;
						break;
					}
				}
			}
			
			if(ckid != null){ //泥댄겕 �릺�뼱 �엳�쓣�븣
				if(ck == null){ // 荑좏궎�뙆�씪 �뾾�쓣�븣
					ck = new Cookie("ckid",vo.getEmail());
					
					//root濡� 寃쎈줈 �꽕�젙
					ck.setPath("/");
				
					//�쑀�슚�떆媛� �꽕�젙
					ck.setMaxAge(60*60*24);
				
					//�겢�씪�씠�뼵�듃�뿉寃� 荑좏궎�뙆�씪 �깮�꽦
					response.addCookie(ck);
				}else{ //�엳�쓣�븣
					if(!ck.getValue().equals(vo.getEmail())){
						ck.setValue(vo.getEmail());
						ck.setPath("/");
						response.addCookie(ck);
					}
				}
			}else{ // 泥댄겕 �븞�릺�뼱 �엳�쓣�븣
				if(ck != null){
					if(ck.getValue().equals(vo.getEmail())){
						ck.setPath("/");
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
				}
			}
			
		}
		return "1";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return ViewPath.HOME;
	}
}
