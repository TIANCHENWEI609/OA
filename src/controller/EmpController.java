package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pojo.Depart;
import pojo.Emp;
import pojo.Loginlog;
import service.DepartService;
import service.EmpService;
import service.LoginLogService;
import util.EmpCount;
import util.PageUtil;
import util.ResultMessage;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	//增加ip和地点，需要用到LoginLog的信息，增加相关注入
	@Autowired
	LoginLogService logService;
	@Autowired
	DepartService depService;
	
	//员工信息维护提交确认
	@RequestMapping(value="/empUpdate")
	@ResponseBody
	public ResultMessage empUpdate(Emp emp){
		ResultMessage message=null;
		int num=empService.updateEmpById(emp);
		if (num>0) {
			message=new ResultMessage(200, "更新成功!");
		} else {
			message=new ResultMessage(500, "更新失败，请联系管理员!");
		}
		return message;
	}
	//跳转到员工信息维护界面
	@RequestMapping(value="/toEmpUpdPg/{id}")
	public String toEmpUpdatePg(@PathVariable("id") int id,Model model){
		Emp empInfo=empService.getEmpById(id);
		model.addAttribute("empInfoUpt",empInfo);
		return "empupdate";
	}
	@RequestMapping(value="/toEmpCount")
	public String getEmpCount(Model model){
		List<String> depList=depService.getAllDepartName();
		String depInfo=JSONObject.toJSONString(depList);
		List<EmpCount> empCount=empService.getEmp_Dep();
		String emp_dep=JSONObject.toJSONString(empCount);
		model.addAttribute("depInfo", depInfo);
		model.addAttribute("emp_dep", emp_dep);
		return "empCount";
	}
	
	@RequestMapping(value="/emp_Del")
	@ResponseBody
	public ResultMessage empDel(int id){
		ResultMessage	message=null;
		int count=empService.empDel(id);
		if (count>0) {
			message=new ResultMessage(200, "删除员工成功!");
		} else {
			message=new ResultMessage(500, "删除员工失败，请联系管理员!");
		}
		return message;
	}
	
	
	//展示员工列表
	@RequestMapping(value="/page_Emptlist/{pageIndex}/{pageSize}")
	//注意：如果声明了Model，则不需要再添加@ResponseBody声明
	public String getDepartList(@PathVariable("pageIndex")long pageIndex,@PathVariable("pageSize")long pageSize,Model model){
		//分页的通用写法，最好是创建工具类进行统一管理
		int totalCount=empService.empCount();
		List<Emp> emp= empService.getEmpList((pageIndex-1)*pageSize, pageSize);
		PageUtil<Emp> PageUtil=new PageUtil<Emp>(pageIndex, pageSize, totalCount, emp);
		model.addAttribute("empPageUtil", PageUtil);
		return "emplist";
	}
	
	@RequestMapping(value="/empAdd")
	@ResponseBody
	public ResultMessage addEmp(Emp emp){
		ResultMessage message=null;
		int count=empService.addEmp(emp);
		if (count>0) {
			message=new ResultMessage(200, "新增成功!");
		} else {
			message=new ResultMessage(500, "新增失败，请联系管理员!");
		}
		return message;
	}
	
	//登录验证
	//注意！！！@RequestMapping注解可以相应get请求也可以相应post请求，@getMapping之相应get请求，@postMapping只相应post请求
	@RequestMapping(value="/emp_login")
	@ResponseBody	//需要返回json对象才需要添加该注解，如果只返回跳转的界面，不要＋该注解，不然会返回字符串
	public ResultMessage login(Emp emp,String loginIp,String loginCity,HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=utf-8");//就算是增加了编码过滤器，依然要进行这样设置，不然还是会乱码！！！
		ResultMessage message=null;
		System.out.println("要登录的对象是"+emp.getNo()+"IP"+loginIp+"City"+loginCity);
		Emp emp1=empService.login(emp);
		if (emp1!=null) {
			//状态不可用则提示，并且需要重新登录
			if (emp1.getFlag()==1) {
				//登录成功重定向到首页
				//response.sendRedirect("index.jsp");//将jsp放在webinf文件夹下
				message=new ResultMessage(200,"登录成功！");
				//将登陆对象存到sesson中从前台进行取值
				session.setAttribute("loginEmp", emp1);
				Loginlog logEntity=new Loginlog();
				logEntity.setIp(loginIp);
				logEntity.setLocation(loginCity);
				logEntity.setNo(emp.getNo());
				int a =logService.addIpAndCity(logEntity);
				System.out.println("影响条数"+a);
			} else {
				//response.getWriter().write("<script>alert('账号状态异常，请联系管理员');location.href='login.jsp'</script>");
				message=new ResultMessage(300,"账号状态异常，请联系管理员！");
			}
		} else {
//			response.getWriter().write("<script>alert('账号或密码错误，请重新登录');location.href='login.jsp'</script>");
			message=new ResultMessage(500,"账号或密码错误，请重新登录！");
			System.out.println(message.getResCode());
		}
		return message;
	}
}
