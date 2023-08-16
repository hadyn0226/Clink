package service.actLog;

import java.util.List;

import dao.actCateDAO.ActCateDAO;
import dao.actLogDAO.ActLogDAO;
import dao.bigCateDAO.BigCateDAO;
import vo.actCateVO.ActCateVO;
import vo.actLogVO.ActLogVO;
import vo.bigCateVO.BigCateVO;

public class ActLogService {
	private ActLogDAO actLogDAO;
	private ActCateDAO actCateDAO;
	private BigCateDAO bigCateDAO;
	
	public ActLogService(ActLogDAO actLogDAO, ActCateDAO actCateDAO, BigCateDAO bigCateDAO) {
		super();
		this.actLogDAO = actLogDAO;
		this.actCateDAO = actCateDAO;
		this.bigCateDAO = bigCateDAO;
	}
	
	public int bigCateInsert(BigCateVO vo) {
		return bigCateDAO.insert(vo);
	}
	
	public int bigCateUpdate(BigCateVO vo) {
		return bigCateDAO.update(vo);
	}
	
	public List<BigCateVO> bigCateSellectAll(){
		return bigCateDAO.selectAll();
	}
	
	public int bigCateDelete(int no) {
		return bigCateDAO.delete(no);
	}
	
	public int actCateInsert(ActCateVO vo) {
		return actCateDAO.insert(vo);
	}
	
	public int actCateUpdate(ActCateVO vo) {
		return actCateDAO.update(vo);
	}
	
	public List<ActCateVO> actCateSellectAll(){
		return actCateDAO.selectAll();
	}
	
	public int actCateDelete(int no) {
		return actCateDAO.delete(no);
	}
	
	public int actLogInsert(ActLogVO vo) {
		return actLogDAO.insert(vo);
	}
	
	public int actLogUpdate(ActLogVO vo) {
		return actLogDAO.update(vo);
	}
	
	public List<ActLogVO> actLogSellectAll(){
		return actLogDAO.selectAll();
	}
	
	public int actLogDelete(int no) {
		return actLogDAO.delete(no);
	}
	
	
}
