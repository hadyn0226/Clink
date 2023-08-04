package service.login;

import common.Encry;
import dao.userDAO.UserDAO;
import vo.userVO.UserVO;

public class LoginService {

	private UserDAO userDAO;
	
	public LoginService(UserDAO userDAO) {
		this.userDAO = userDAO;
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
	
}
