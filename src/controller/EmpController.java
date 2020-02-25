package controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import message.ResultMessage;
import pojo.Emp;
import pojo.Loginlog;
import service.EmpService;
import service.LoginLogService;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	//增加ip和地点，需要用到LoginLog的信息，增加相关注入
	@Autowired
	LoginLogService logService;
	
	//登录验证
	//注意！！！@RequestMapping注解可以相应get请求也可以相应post请求，@getMapping之相应get请求，@postMapping只相应post请求
	@RequestMapping(value="/emp_login")
	@ResponseBody
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
				message=new ResultMessage();
				message.setResCode(200);
				message.setResInfo("登录成功！");
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
				message=new ResultMessage();
				message.setResCode(300);
				message.setResInfo("账号状态异常，请联系管理员！");
			}
		} else {
//			response.getWriter().write("<script>alert('账号或密码错误，请重新登录');location.href='login.jsp'</script>");
			message=new ResultMessage();
			message.setResCode(500);
			message.setResInfo("账号或密码错误，请重新登录！");
		}
		return message;
	}
}
