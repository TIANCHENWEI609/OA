package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pojo.Depart;
import pojo.Emp;
import util.EmpCount;
@Mapper
public interface EmpMapper {
	//登录方法
    public Emp login(Emp emp);
    //员工新增
    public int addEmp(Emp emp);
    //员工总数
    public int empCount();
    /**
	 * 注意：查询员工信息需要联合部门表进行查询
	 * @param pageStart	起始记录
	 * @param pageSize	每页条数
	 * @return
	 */
	public List<Emp> getEmpList(@Param("pageStart")long pageStart,@Param("pageSize")long pageSize);//采用注解法传参数
	/**
	 * 根据id删除员工
	 * @param id
	 * @return
	 */
	public int empDel(int id);
	/**
	 * 获取每个部门的员工总数
	 * @return
	 */
	public List<EmpCount> getEmp_Dep();
}