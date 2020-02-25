package util;

import java.util.List;

import lombok.Data;

/**
* @author �������ΰ
* @version ����ʱ�䣺2020��2��25�� ����11:39:41
* ��ҳ������
 * @param <T>
*/
@Data
public class PageUtil<T> {
	private long pageIndex;//��ǰҳ��
	private long pageSize;//ҳ���С
	private long totalCount;//������
	private long pageCount;//��ҳ��
	private long pageStart;//ҳ����ʼ
	private long pageEnd;//ҳ����ֹ
	private List<T> records;//ÿҳ�����ݼ���



	public PageUtil(long pageIndex, long pageSize, long totalCount,
			List<T> records) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.records = records;
		//����ҳ��
		if (totalCount%pageSize==0) {
			this.pageCount=totalCount/pageSize;
		} else {
			this.pageCount=totalCount/pageSize+1;
		}
		//��ѧ�㷨
		//����ҳ����ʾ��ʽ
		if (this.pageCount<10) {
			this.pageStart=1;
			this.pageEnd=this.pageCount;
		} else {
			this.pageStart=pageIndex-4;
			this.pageEnd=pageIndex+5;
			if (this.pageStart<1) {
				this.pageStart=1;
				this.pageEnd=10;
			} else if(this.pageEnd>this.pageCount){
				this.pageStart=this.pageCount-9;
				this.pageEnd=this.pageCount;
			}
		}
	}
	
}
