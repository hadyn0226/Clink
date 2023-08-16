package dao.bigCateDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.bigCateVO.BigCateVO;

public class BigCateDAO {
	private SqlSession sqlSession;

	public BigCateDAO(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	public int insert(BigCateVO vo) {
		return this.sqlSession.insert("bigcate.insert", vo);
	}

	public int update(BigCateVO vo) {
		return this.sqlSession.update("bigcate.update", vo);
	}

	public List<BigCateVO> selectAll() {
		return this.sqlSession.selectList("bigcate.selectAll");
	}

	public int delete(int no) {
		return this.sqlSession.delete("bigcate.delete", no);
	}
}
