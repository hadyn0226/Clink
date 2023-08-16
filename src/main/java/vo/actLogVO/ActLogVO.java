package vo.actLogVO;

public class ActLogVO {
	
	private int logactNo;
	private int memberNo;
	private int featureNo;
	private int targetNo;
	private String actDate;
	
	public ActLogVO() {
		super();
	}

	public int getLogactNo() {
		return logactNo;
	}

	public void setLogactNo(int logactNo) {
		this.logactNo = logactNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getFeatureNo() {
		return featureNo;
	}

	public void setFeatureNo(int featureNo) {
		this.featureNo = featureNo;
	}

	public int getTargetNo() {
		return targetNo;
	}

	public void setTargetNo(int targetNo) {
		this.targetNo = targetNo;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}
	
	
	
}
