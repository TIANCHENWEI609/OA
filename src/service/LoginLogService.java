package service;

import java.util.List;

import pojo.Loginlog;

/**
* @author 作者田陈伟
* @version 创建时间：2020年2月23日 下午5:53:42
* 类说明
*/
public interface LoginLogService {
	public int  addIpAndCity(Loginlog loginLog);
	public List<Loginlog> getLoginList(String no);
}
