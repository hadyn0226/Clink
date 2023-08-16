package dao.actCateDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.actCateVO.ActCateVO;

public class ActCateDAO {
	private SqlSession sqlSession;

	public ActCateDAO(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public int insert(ActCateVO vo) {
		return this.sqlSession.insert("actcate.insert", vo);
	}

	public int update(ActCateVO vo) {
		return this.sqlSession.update("actcate.update", vo);
	}

	public List<ActCateVO> selectAll() {
		return this.sqlSession.selectList("actcate.selectAll");
	}

	public int delete(int no) {
		return this.sqlSession.delete("actcate.delete", no);
	}
}
