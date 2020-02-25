package service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.DepartMapper;
import pojo.Depart;
import service.DepartService;

/**
* @author 作者田陈伟
* @version 创建时间：2020年2月24日 下午3:30:00
* 类说明
*/
@Service
public class DepartServiceImpl implements DepartService {
	
	@Autowired
	DepartMapper mapper;
	@Override
	public int addDepart(Depart depart) {
		// TODO Auto-generated method stub
		return mapper.addDepart(depart);
	}

}
