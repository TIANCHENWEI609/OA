package service;

import java.util.List;

import pojo.Emp;
import util.EmpCount;

public interface EmpService {
	public Emp login(Emp emp);
	//Ա������
    public int addEmp(Emp emp);
    //��ȡԱ������
	public int empCount();
	//��ҳ��ȡԱ����Ϣ
	public List<Emp> getEmpList(long pageStart, long pageSize);
	//ɾ��Ա��
	public int empDel(int id);
	/**
	 * ��ȡÿ�����ŵ�Ա������
	 * @return
	 */
	public List<EmpCount> getEmp_Dep();
}
