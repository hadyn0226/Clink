package dao.actLogDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.actLogVO.ActLogVO;

public class ActLogDAO {
	private SqlSession sqlSession;

	public ActLogDAO(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	
	public int insert(ActLogVO vo) {
		return this.sqlSession.insert("actlog.insert", vo);
	}
	
	public int update(ActLogVO vo) {
		return this.sqlSession.update("actlog.update", vo);
	}
	
	public List<ActLogVO> selectAll(){
		return this.sqlSession.selectList("actlog.selectAll");
	}
	
	public int delete(int no) {
		return this.sqlSession.delete("actlog.delete", no);
	}
}
