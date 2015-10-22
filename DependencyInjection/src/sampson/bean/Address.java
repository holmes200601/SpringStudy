package sampson.bean;

public class Address {
	private String 		detailInfo;
	private String      postCode;
	
	public Address(String info, String code) {
		this.setDetailInfo(info);
		this.setPostCode(code);
	}
	
	public String getDetailInfo() {
		return detailInfo;
	}
	
	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	
}
