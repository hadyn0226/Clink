package service.login;

import java.util.Map;

import common.Encry;
import dao.loginLogDAO.LoginLogDAO;
import dao.userDAO.UserDAO;
import vo.loginLogVO.LoginLogVO;
import vo.userVO.UserVO;

public class LoginService {

	private UserDAO userDAO;
	private LoginLogDAO loginLogDAO;
	
	public LoginService(UserDAO userDAO, LoginLogDAO loginLogDAO) {
		this.userDAO = userDAO;
		this.loginLogDAO = loginLogDAO;
	}
	
	public int insert(UserVO vo) {
		String salt = Encry.getSalt();
		vo.setUserSalt(salt);
		
		String password = vo.getUserPassword();
		password = Encry.encry(password, salt);
		
		vo.setUserPassword(password);
		return userDAO.insert(vo);
	}
	
	public String getSalt(String email) {
		return userDAO.getSalt(email);
	}
	
	public Map<String, Object> login(UserVO userVO) {
		return userDAO.login(userVO);
	}
	
	public int nextSeq() {
		return userDAO.nextSeq();
	}
	
	public int firstLog(LoginLogVO vo) {
		return loginLogDAO.firstLog(vo);
	}
	
	public int increFail(String userEmail) {
		return loginLogDAO.increFail(userEmail);
	}
	
	public int statIn(Map<String, Object> map) {
		return loginLogDAO.statIn(map);
	}
	
	public int statOut(int userId) {
		return loginLogDAO.statOut(userId);
	}
}
