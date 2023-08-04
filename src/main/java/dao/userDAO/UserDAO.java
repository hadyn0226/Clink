package dao.userDAO;

import org.apache.ibatis.session.SqlSession;

import vo.userVO.UserVO;

public class UserDAO {
	private SqlSession sqlSession;
	
	public UserDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(UserVO vo) {
		return sqlSession.insert("user.insert", vo);
	}
	
	public String getSalt(String email) {
		return sqlSession.selectOne("user.getSalt", email);
	}
	
	public UserVO login(UserVO userVO) {
		return sqlSession.selectOne("user.login", userVO);
	}
}
