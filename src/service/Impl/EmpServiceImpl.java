package service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.EmpMapper;
import pojo.Emp;
import service.EmpService;
@Service
public class EmpServiceImpl implements EmpService {

	//需要注入mapper层
	@Autowired
	EmpMapper empMapper;
	//重写接口的方法
	@Override
	public Emp login(Emp emp) {
		// TODO Auto-generated method stub
		return empMapper.login(emp);
	}

}
