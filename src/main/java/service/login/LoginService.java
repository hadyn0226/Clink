package service.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import common.Encry;
import dao.loginLogDAO.LoginLogDAO;
import dao.userDAO.UserDAO;
import vo.loginLogVO.LoginLogVO;
import vo.userVO.UserVO;

public class LoginService implements UserDetailsService{

	private UserDAO userDAO;
	private LoginLogDAO loginLogDAO;
	private PasswordEncoder passwordEncoder;
	
	public LoginService(UserDAO userDAO, LoginLogDAO loginLogDAO) {
		this.userDAO = userDAO;
		this.loginLogDAO = loginLogDAO;
	}
	
	public int insert(UserVO vo) {
		String salt = Encry.getSalt();
		vo.setUserSalt(salt);
		
		String password = vo.getUserPassword();
		password = passwordEncoder.encode(password);
		
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVO user = userDAO.getUser(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		System.out.println("service : " + user.getUserEmail());
		System.out.println("service : " + user.getPassword());
		return new UserVO(user.getUserId(), user.getUserEmail(), user.getUserPassword(), user.getAuthorities());
	}
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
