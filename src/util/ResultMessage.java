package util;

import lombok.Data;

//��lombok���й�������ʡȥset��get��������������÷���pageUtil��ͨ����index��count����pageStart��pageEnd������
@Data
public class ResultMessage {
	
	private int resCode;//���󷵻�״̬��
	private String resInfo;//��������
	//����lombok�Ͳ���Ҫдset��get������
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
