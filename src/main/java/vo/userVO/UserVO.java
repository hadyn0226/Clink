package vo.userVO;

public class UserVO {
	private int userId;
	private String userFirstname;
	private String userLastname;
	private String email;
	private String userPassword;
	private String userSalt;
	private String userDate;
	private String userBirth;
	
	public UserVO() {
		super();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userFirstname=" + userFirstname + ", userLastname=" + userLastname
				+ ", email=" + email + ", userPassword=" + userPassword + ", userSalt=" + userSalt + ", userDate=" + userDate
				+ ", userBirth=" + userBirth + "]";
	}
	
	
	
	
	
}
