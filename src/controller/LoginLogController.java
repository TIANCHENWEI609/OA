package controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Emp;
import pojo.Loginlog;
import service.LoginLogService;

/**
* @author 作者田陈伟
* @version 创建时间：2020年2月23日 下午6:00:13
* 类说明
*/
@Controller
public class LoginLogController {
	@Autowired
	LoginLogService logService;
	//查询登录足迹
	@RequestMapping(value="/getLoginList")
	@ResponseBody
	public List<Loginlog> getLoginList(HttpSession session) {
		//取得登录员工号,通过session取得员工号，loginEmp在登录的时候放到了session里面
		String LogNo=((Emp)session.getAttribute("loginEmp")).getNo();
		return logService.getLoginList(LogNo);
	}
}
