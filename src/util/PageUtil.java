package util;

import java.util.List;

import lombok.Data;

/**
* @author 作者田陈伟
* @version 创建时间：2020年2月25日 上午11:39:41
* 分页工具类
 * @param <T>
*/
@Data
public class PageUtil<T> {
	private long pageIndex;//当前页码
	private long pageSize;//页面大小
	private long totalCount;//总条数
	private long pageCount;//总页数
	private long pageStart;//页码起始
	private long pageEnd;//页码终止
	private List<T> records;//每页的数据集合



	public PageUtil(long pageIndex, long pageSize, long totalCount,
			List<T> records) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.records = records;
		//计算页数
		if (totalCount%pageSize==0) {
			this.pageCount=totalCount/pageSize;
		} else {
			this.pageCount=totalCount/pageSize+1;
		}
		//数学算法
		//计算页数显示方式
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
