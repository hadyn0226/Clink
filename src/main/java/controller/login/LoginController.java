package controller.login;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Encry;
import common.ViewPath;
import provider.CustomAuthenticationProvider;
import service.login.LoginService;
import vo.loginLogVO.LoginLogVO;
import vo.userVO.UserVO;

@Controller
public class LoginController {
	private LoginService loginService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SessionRegistry csessionRegistry;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping("/joinForm")
	public String loginForm() {
		return ViewPath.LOGIN + "joinForm.jsp";
	}

	@RequestMapping("/join")
	public String join(Model model, UserVO vo, HttpServletRequest request) {
		String msg = null;
		String ip = getIp(request);
		vo.setUserId(loginService.nextSeq());
		LoginLogVO logvo = new LoginLogVO();
		logvo.setUserNo(vo.getUserId());
		logvo.setIp(ip);
		logvo.setFailCnt(0);
		logvo.setStat(0);
		int su = loginService.insert(vo);
		if (su != 0) {
			msg = "�쉶�썝媛��엯 �꽦怨�";
			loginService.firstLog(logvo);
		} else {
			msg = "�쉶�썝媛��엯 �떎�뙣";
		}
		model.addAttribute("msg", msg);
		return ViewPath.LOGIN + "loginForm.jsp";
	}

	@RequestMapping(value = "/checkId", produces = "application/text;charset=utf8")
	@ResponseBody
	public String checkId(String id) {
		String reStr = "0";
		String salt = loginService.getSalt(id);
		if (salt == null) {
			reStr = "1";
		}
		return reStr;
	}

	@RequestMapping("/loginForm")
	public String loginForm(HttpServletRequest request, String id) {
		boolean check = false;

		if (id == null) {
			Cookie[] cks = request.getCookies();

			if (cks != null) {
				for (Cookie ck : cks) {
					if (ck.getName().equals("ckid")) {
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

//	@RequestMapping("/login")
//	@ResponseBody
//	public String checkLogin(HttpServletRequest request, HttpServletResponse response, UserVO vo) {
//		int failCnt, stat, status;
//		System.out.println("login Controller");
//		String ip = getIp(request);
//		// 占쎈릊占쎌깈占쎌넅
//		String salt = loginService.getSalt(vo.getEmail());
//		if (salt == null) {
//			return "-1";
//		}
//		String password = vo.getUserPassword();
////		System.out.println(salt);
////		System.out.println(vo.getEmail());
////		System.out.println(vo.getUserPassword());
//		password = Encry.encry(password, salt);
//		vo.setUserPassword(password);
//		Map<String, Object> checkVo = loginService.login(vo);
//
//		if (checkVo == null) {
//			loginService.increFail(vo.getEmail());
//			return "0";
//		}
//		failCnt = ((BigDecimal) checkVo.get("FAIL_CNT")).intValue() + 1;
//		if (failCnt >= 5) {
//			return "-2";
//		}
//		stat = ((BigDecimal) checkVo.get("STAT")).intValue();
//		if (stat == 1) {
//			return "2";
//		}
//		status = ((BigDecimal) checkVo.get("USER_STATUS")).intValue();
//		if (status == 2) {
//			return "3";
//		}
//
////		System.out.println("FAIL_CNT : "+ checkVo.get("FAIL_CNT"));
////		System.out.println("STAT : " + checkVo.get("STAT"));
//		int no = ((BigDecimal) checkVo.get("USER_ID")).intValue();
//
////		
////		int st = loginService.checkStatus(vo);
////		if(st == 2) {
////			return "redirect:/uuser/joinform";
////		}
////		
////		int no = loginService.checkLogin(vo);
//
//		if (no != 0) {
//			Map<String, Object> map = new HashMap<String, Object>();
//			request.getSession().setAttribute("login", no);
//			map.put("userId", no);
//			map.put("ip", ip);
//			loginService.statIn(map);
//			String ckid = request.getParameter("ckid");
//			System.out.println("ckid : " + ckid);
//
//			Cookie ck = null;
//
//			//
//			Cookie[] cks = request.getCookies();
//
//			//
//			if (cks != null) {
//				for (Cookie c : cks) {
//					if (c.getName().equals("ckid")) {
//						ck = c;
//						break;
//					}
//				}
//			}
//
//			if (ckid != null) { //
//				if (ck == null) { //
//					ck = new Cookie("ckid", vo.getEmail());
//
//					//
//					ck.setPath("/");
//
//					//
//					ck.setMaxAge(60 * 60 * 24);
//
//					//
//					response.addCookie(ck);
//				} else { //
//					if (!ck.getValue().equals(vo.getEmail())) {
//						ck.setValue(vo.getEmail());
//						ck.setPath("/");
//						response.addCookie(ck);
//					}
//				}
//			} else { //
//				if (ck != null) {
//					if (ck.getValue().equals(vo.getEmail())) {
//						ck.setPath("/");
//						ck.setMaxAge(0);
//						response.addCookie(ck);
//					}
//				}
//			}
//
//		}
//		return "1";
//	}

	@RequestMapping("/login")
	@ResponseBody
	public ResponseEntity<String> checkLogin(HttpServletRequest request, HttpServletResponse response, UserVO vo) {
		String username = vo.getUserEmail();
		String password = vo.getPassword();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

		try {
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String ckid = request.getParameter("ckid");
			System.out.println("ckid : " + ckid);

			Cookie ck = null;
			Cookie[] cks = request.getCookies();

			if (cks != null) {
				for (Cookie c : cks) {
					if (c.getName().equals("ckid")) {
						ck = c;
						break;
					}
				}
			}

			if (ckid.equals("true")) { //
				if (ck == null) { //
					ck = new Cookie("ckid", vo.getUserEmail());

					//
					ck.setPath("/");

					//
					ck.setMaxAge(60 * 60 * 24);

					//
					response.addCookie(ck);
				} else { //
					if (!ck.getValue().equals(vo.getUserEmail())) {
						ck.setValue(vo.getUserEmail());
						ck.setPath("/");
						response.addCookie(ck);
					}
				}
			}else{ // 泥댄겕 �븞�릺�뼱 �엳�쓣�븣
				if(ck != null){
					if(ck.getValue().equals(vo.getUserEmail())){
						ck.setPath("/");
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
				}
			}
			HttpSession session = request.getSession();
			if (session != null) {
				System.out.println("session id : " + session.getId());
				csessionRegistry.registerNewSession(session.getId(), authentication.getPrincipal());
//				Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
//				System.out.println(authentication2);
//				if (authentication2 != null) {
//					UserVO vo2 = (UserVO) authentication2.getPrincipal();
//					
//					System.out.println("session principal count : " + getActiveSessionCount());
//					System.out.println("vo2 : " + vo2);
//				}
			}
			System.out.println("session count : " + getActiveSessionCount());
			return ResponseEntity.ok("1");
		} catch (org.springframework.security.core.AuthenticationException e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		} catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("2");
		}
	}

	@RequestMapping("/exit")
	@ResponseBody
	public void exit(HttpSession session) {
		System.out.println("unload");
		Integer no = (Integer) session.getAttribute("login");
		if (no != null) {
			loginService.statOut(no);
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		Integer no = (Integer) session.getAttribute("login");
		if (no != null) {
			loginService.statOut(no);
		}
		session.invalidate();
		return ViewPath.HOME;
	}

	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");

		System.out.println(">>>> X-FORWARDED-FOR : " + ip);

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println(">>>> Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // �쎒濡쒖쭅
			System.out.println(">>>> WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println(">>>> HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println(">>>> HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}
	
	public int getActiveSessionCount() {
	    int activeSessions = 0;
	    
	    for (Object principal : csessionRegistry.getAllPrincipals()) {
	        activeSessions += csessionRegistry.getAllSessions(principal, false).size();
	    }
	    
	    return activeSessions;
	}
	
	@RequestMapping("/expired")
	public String expired() {
		System.out.println("session is expired");
		return ViewPath.HOME;
	}

}
