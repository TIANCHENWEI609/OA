package service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mapper.LoginlogMapper;
import pojo.Loginlog;
import service.LoginLogService;

/**
* @author 作者田陈伟
* @version 创建时间：2020年2月23日 下午5:54:11
* 类说明
*/
@Service
public class LoginLogServiceImpl implements LoginLogService {
	@Autowired
	LoginlogMapper mapper;

	@Override		//登录足迹插入t_loginlog表
	public int addIpAndCity(Loginlog loginLog) {
		return mapper.addIpAndCity(loginLog);
	}
	
	@Override		//查询登录足迹
	public List<Loginlog> getLoginList(String no) {
		// TODO Auto-generated method stub
		return mapper.getLoginList(no);
	}
}
