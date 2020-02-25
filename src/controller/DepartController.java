package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import message.ResultMessage;
import pojo.Depart;
import service.DepartService;

/**
* @author 作者田陈伟
* @version 创建时间：2020年2月24日 下午3:32:14
* 类说明
*/
@Controller
public class DepartController {
	@Autowired
	DepartService service;
	
	@RequestMapping(value="/addDepart")
	@ResponseBody
	public ResultMessage addDepart(Depart depart){
		ResultMessage message=null;
		int num=service.addDepart(depart);
		if (num>0) {
			message=new ResultMessage();
			message.setResCode(200);
			message.setResInfo("新增成功!");
		} else {
			message=new ResultMessage();
			message.setResCode(500);
			message.setResInfo("新增失败，请联系管理员!");
		}
		return message;
	}

}
