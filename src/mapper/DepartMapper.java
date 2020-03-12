package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Depart;

public interface DepartMapper {
	//部门增加功能
	public int addDepart(Depart depart);
	
	//分页查询部门信息
	//获取部门总数
	public int departCount();
	//详细的部门信息
	/**
	 * 
	 * @param pageStart	起始记录
	 * @param pageSize	每页条数
	 * @return
	 */
	public List<Depart> getDepartList(@Param("pageStart")long pageStart,@Param("pageSize")long pageSize);//采用注解法传参数
	/**
	 * 部门删除
	 * @param id	部门id
	 * @return
	 */
	public int delDepart(int id);
	/**
	 * 根据ID修改部门信息
	 * @param id
	 * @param name
	 * @param time
	 * @return
	 */
	public int updateDeptById(Depart depart);
	/**
	 * 查询所有的部门信息
	 */
	public List<Depart> getAllDepart();
	
	public List<String> getAllDepartName();
	//根据id获取部门信息
	public Depart getDepartById(int id);

}