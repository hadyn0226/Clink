package controller.actLogTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.ViewPath;
import service.actLog.ActLogService;
import vo.actCateVO.ActCateVO;
import vo.actLogVO.ActLogVO;
import vo.bigCateVO.BigCateVO;

@Controller
public class ActLogTestController {
	private ActLogService actLogService;

	public ActLogTestController(ActLogService actLogService) {
		super();
		this.actLogService = actLogService;
	}
	
	@RequestMapping("/test")
	public String Test() {
		
//		BigCateVO bigCateVO = new BigCateVO();
//		bigCateVO.setSummary("테스트 관련"); // 대 카테 설명문
//		
//		ActCateVO actCateVO = new ActCateVO();
//		actCateVO.setBigcate(3); // 대 카테 pk
//		actCateVO.setUuid("TUUID"); // UUID 설정
//		actCateVO.setSummary("테스트 상세"); // 상세 카테 설명문
//		
//		ActLogVO actLogVO = new ActLogVO();
//		actLogVO.setMemberNo(12); //유저 pk
//		actLogVO.setFeatureNo(3); // 상세 카테 pk
//		actLogVO.setTargetNo(1); // 타겟 pk 유저면 유저 pk 게시글이면 게시글 pk
//		
//		System.out.println("BigCate insert Test : " + actLogService.bigCateInsert(bigCateVO));
//		bigCateVO.setBigcateNo(4);
//		System.out.println("BigCate update Test : " + actLogService.bigCateUpdate(bigCateVO));
//		System.out.println("BigCate select Test : " + actLogService.bigCateSellectAll());
//		System.out.println("BigCate delete Test : " + actLogService.bigCateDelete(3));
//		actCateVO.setActcateNo(4);
//		System.out.println("ActCate insert Test : " + actLogService.actCateInsert(actCateVO));
//		System.out.println("ActCate update Test : " + actLogService.actCateUpdate(actCateVO));
//		System.out.println("ActCate select Test : " + actLogService.actCateSellectAll());
//		System.out.println("ActCate delete Test : " + actLogService.actCateDelete(4));
//		actLogVO.setLogactNo(4);
//		System.out.println("ActLog insert Test : " + actLogService.actLogInsert(actLogVO));
//		System.out.println("ActLog update Test : " + actLogService.actLogUpdate(actLogVO));
//		System.out.println("ActLog select Test : " + actLogService.actLogSellectAll());
//		System.out.println("ActLog delete Test : " + actLogService.actLogDelete(4));
		
		return ViewPath.HOME;
	}
	
}
