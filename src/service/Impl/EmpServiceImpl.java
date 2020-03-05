package service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.EmpMapper;
import pojo.Emp;
import service.EmpService;
@Service
public class EmpServiceImpl implements EmpService {

	//��Ҫע��mapper��
	@Autowired
	EmpMapper empMapper;
	//��д�ӿڵķ���
	@Override
	public Emp login(Emp emp) {
		// TODO Auto-generated method stub
		return empMapper.login(emp);
	}
	//Ա������
	@Override
	public int addEmp(Emp emp) {
		return empMapper.addEmp(emp);
	}
	//��ȡԱ������
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
	 * ����idɾ��Ա��
	 */
	@Override
	public int empDel(int id) {
		// TODO Auto-generated method stub
		return empMapper.empDel(id);
	}

}
