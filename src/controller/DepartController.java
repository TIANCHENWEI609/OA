package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import pojo.Depart;
import service.DepartService;
import util.PageUtil;
import util.ResultMessage;

/**
* @author �������ΰ
* @version ����ʱ�䣺2020��2��24�� ����3:32:14
* ��˵��
*/
@Controller
public class DepartController {
	@Autowired
	DepartService service;
	
	//���Ÿ���
	@ResponseBody
	@RequestMapping(value="/upDeptInfo")
	public String  updateDeptInfo(Depart depart,String id,HttpRequestHandlerServlet request,HttpSession session){
		System.out.println(id);
		session.setAttribute("departInfo", depart);
		return "departupdate";
	}
	//ɾ�����ţ���ɾ��������del״̬��
	@ResponseBody
	@RequestMapping(value="/delete_depart")
	public ResultMessage delDepart(int id){
		ResultMessage message=null;
		int count =service.delDepart(id);
		if (count>0) {
			message=new ResultMessage(200, "ɾ�����ųɹ�!");
		}else{
			message=new ResultMessage(500, "ɾ������ʧ��!");
		}
		return message;
	}
	
	//���Ź�����ҳ��ѯѰ������Ϣ��
	//����1�ӵ�ַ��ҳ���ҳ���С������
	@RequestMapping(value="/page_departlist/{pageIndex}/{pageSize}")
	//ע�⣺���������Model������Ҫ�����@ResponseBody����
	public String getDepartList(@PathVariable("pageIndex")long pageIndex,@PathVariable("pageSize")long pageSize,Model model){
		//��ҳ��ͨ��д��������Ǵ������������ͳһ����
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
			message=new ResultMessage(200,"�����ɹ�!");
		} else {
			message=new ResultMessage(500,"����ʧ�ܣ�����ϵ����Ա!");
		}
		return message;
	}
}
