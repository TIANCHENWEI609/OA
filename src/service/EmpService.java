package service;

import java.util.List;

import pojo.Emp;
import util.EmpCount;

public interface EmpService {
	public Emp login(Emp emp);
	//员工新增
    public int addEmp(Emp emp);
    //获取员工总数
	public int empCount();
	//分页获取员工信息
	public List<Emp> getEmpList(long pageStart, long pageSize);
	//删除员工
	public int empDel(int id);
	/**
	 * 获取每个部门的员工总数
	 * @return
	 */
	public List<EmpCount> getEmp_Dep();
}
