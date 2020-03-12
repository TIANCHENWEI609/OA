package service;

import java.util.List;

import pojo.Depart;

/**
* @author 作者田陈伟
* @version 创建时间：2020年2月24日 下午3:27:02
* 类说明
*/
public interface DepartService {
	//增加部门信息
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
	public List<Depart> getDepartList(long pageStart,long pageSize);
	/**
	 * 部门删除（更新del为1设置部门不可用）
	 * @param id 部门id
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
	 * 查询所有部门信息
	 */
	public List<Depart> getAllDepart();
	
	public List<String> getAllDepartName();
	/**
	 * 根据id获取部门信息
	 * @param id
	 * @return
	 */
	public Depart getDepartById(int id);
}
