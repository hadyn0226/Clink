package vo.userVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserVO implements UserDetails {
	private int userId;
	private String userFirstname;
	private String userLastname;
	private String userEmail;
	private String userPassword;
	private String userSalt;
	private String userDate;
	private String userBirth;
	private Collection<? extends GrantedAuthority> authorities;
	private int userStatus;

	public UserVO() {
		super();
	}
	
	

	public UserVO(int userId,String userEmail, String userPassword, int userStatus, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.authorities = authorities;
		this.userStatus = userStatus;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserDate() {
		return userDate;
	}

	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserSalt() {
		return userSalt;
	}

	public void setUserSalt(String userSalt) {
		this.userSalt = userSalt;
	}
	
	public int getUserStatus() {
		return userStatus;
	}



	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}



	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userFirstname=" + userFirstname + ", userLastname=" + userLastname
				+ ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userSalt=" + userSalt + ", userDate="
				+ userDate + ", userBirth=" + userBirth + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		// ����ڰ� "ROLE_USER" ���� ������ �ִٰ� �����Ͽ� �ش� ������ �߰��մϴ�.
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
