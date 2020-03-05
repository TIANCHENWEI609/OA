package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pojo.Depart;
import pojo.Emp;
@Mapper
public interface EmpMapper {
	//��¼����
    public Emp login(Emp emp);
    //Ա������
    public int addEmp(Emp emp);
    //Ա������
    public int empCount();
    /**
	 * 
	 * @param pageStart	��ʼ��¼
	 * @param pageSize	ÿҳ����
	 * @return
	 */
	public List<Emp> getEmpList(@Param("pageStart")long pageStart,@Param("pageSize")long pageSize);//����ע�ⷨ������
	/**
	 * ����idɾ��Ա��
	 * @param id
	 * @return
	 */
	public int empDel(int id);
}