package mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pojo.Loginlog;

@Mapper
public interface LoginlogMapper {
   //记录当前登录的IP和地点
	public int addIpAndCity(Loginlog loginLog);
	public List<Loginlog> getLoginList(String no);
}