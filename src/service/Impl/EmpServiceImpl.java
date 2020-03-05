package service.Impl;


import java.util.List;

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
	//员工新增
	@Override
	public int addEmp(Emp emp) {
		return empMapper.addEmp(emp);
	}
	//获取员工总数
	@Override
	public int empCount() {
		// TODO Auto-generated method stub
		return empMapper.empCount();
	}
	@Override
	public List<Emp> getEmpList(long pageStart, long pageSize) {
		// TODO Auto-generated method stub
		return empMapper.getEmpList(pageStart, pageSize);
	}
	/**
	 * 根据id删除员工
	 */
	@Override
	public int empDel(int id) {
		// TODO Auto-generated method stub
		return empMapper.empDel(id);
	}

}
