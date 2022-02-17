package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.Depart;
import service.DepartService;
import util.PageUtil;
import util.ResultMessage;

/**
* @author 作者田陈伟
* @version 创建时间：2020年2月24日 下午3:32:14
* 类说明
*/
@Controller
public class DepartController {
	@Autowired
	DepartService service;
	
	//测试一下分支代码的合并
	//新增员工查询部门下拉框信息
	@RequestMapping(value="/getAllDeptList")
	@ResponseBody
	public List<Depart> getAllDept(){
		return service.getAllDepart();
	}
	/**
	 * 修改部门信息
	 * @param id
	 * @param name
	 * @param crtTime
	 * @return
	 */
	@RequestMapping(value="/upDeptInfo")
	@ResponseBody
	public ResultMessage updateDept(int id,String name,String crtTime){
		ResultMessage message =null;
		Depart depart=new Depart();
		depart.setId(id);
		depart.setName(name);
		depart.setCreatetime(crtTime);
		int num=service.updateDeptById(depart);
		if (num>0) {
			message=new ResultMessage(200, "更新成功");
		} else {
			message=new ResultMessage(500, "更新失败，请联系管理员!");
		}
		return message;
	}
	
	//部门更新
	@RequestMapping(value="/goUpDeptPage/{id}")
	public String goUpDeptPage(@PathVariable("id") int id ,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");//就算是增加了编码过滤器，依然要进行这样设置，不然还是会乱码！！！
		Depart departObj=service.getDepartById(id);
		model.addAttribute("Depart", departObj);
		return "departupdate";
	}
	//删除部门（假删除，更新del状态）
	@ResponseBody
	@RequestMapping(value="/delete_depart")
	public ResultMessage delDepart(int id){
		ResultMessage message=null;
		int count =service.delDepart(id);
		if (count>0) {
			message=new ResultMessage(200, "删除部门成功!");
		}else{
			message=new ResultMessage(500, "删除部门失败!");
		}
		return message;
	}
	
	//部门管理（分页查询寻部门信息）
	//方法1从地址将页码和页面大小传过来
	@RequestMapping(value="/page_departlist/{pageIndex}/{pageSize}")
	//注意：如果声明了Model，则不需要再添加@ResponseBody声明
	public String getDepartList(@PathVariable("pageIndex")long pageIndex,@PathVariable("pageSize")long pageSize,Model model){
		//分页的通用写法，最好是创建工具类进行统一管理
		int totalCount=service.departCount();
		List<Depart> depart= service.getDepartList((pageIndex-1)*pageSize, pageSize);
		PageUtil<Depart> PageUtil=new PageUtil<Depart>(pageIndex, pageSize, totalCount, depart);
		model.addAttribute("PageUtil", PageUtil);
		return "departlist";
	}
	
	@RequestMapping(value="/addDepart")
	@ResponseBody
	public ResultMessage addDepart(Depart depart){
		ResultMessage message=null;
		int num=service.addDepart(depart);
		if (num>0) {
			message=new ResultMessage(200,"新增成功!");
		} else {
			message=new ResultMessage(500,"新增失败，请联系管理员!");
		}
		return message;
	}
}
