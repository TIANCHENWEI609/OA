package util;

import lombok.Data;

//用lombok进行管理，可以省去set和get方法，更方便的用法再pageUtil中通过，index和count进行pageStart和pageEnd的设置
@Data
public class ResultMessage {
	
	private int resCode;//请求返回状态码
	private String resInfo;//返回描述
	//用了lombok就不需要写set和get方法了
//	public int getResCode() {
//		return resCode;
//	}
//	public void setResCode(int resCode) {
//		this.resCode = resCode;
//	}
//	public String getResInfo() {
//		return resInfo;
//	}
//	public void setResInfo(String resInfo) {
//		this.resInfo = resInfo;
//	}
	public ResultMessage(int resCode, String resInfo) {
		super();
		this.resCode = resCode;
		this.resInfo = resInfo;
	}
	
	
}
