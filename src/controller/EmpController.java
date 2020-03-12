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
	//����ip�͵ص㣬��Ҫ�õ�LoginLog����Ϣ���������ע��
	@Autowired
	LoginLogService logService;
	@Autowired
	DepartService depService;
	
	//Ա����Ϣά���ύȷ��
	@RequestMapping(value="/empUpdate")
	@ResponseBody
	public ResultMessage empUpdate(Emp emp){
		ResultMessage message=null;
		int num=empService.updateEmpById(emp);
		if (num>0) {
			message=new ResultMessage(200, "���³ɹ�!");
		} else {
			message=new ResultMessage(500, "����ʧ�ܣ�����ϵ����Ա!");
		}
		return message;
	}
	//��ת��Ա����Ϣά������
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
			message=new ResultMessage(200, "ɾ��Ա���ɹ�!");
		} else {
			message=new ResultMessage(500, "ɾ��Ա��ʧ�ܣ�����ϵ����Ա!");
		}
		return message;
	}
	
	
	//չʾԱ���б�
	@RequestMapping(value="/page_Emptlist/{pageIndex}/{pageSize}")
	//ע�⣺���������Model������Ҫ�����@ResponseBody����
	public String getDepartList(@PathVariable("pageIndex")long pageIndex,@PathVariable("pageSize")long pageSize,Model model){
		//��ҳ��ͨ��д��������Ǵ������������ͳһ����
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
			message=new ResultMessage(200, "�����ɹ�!");
		} else {
			message=new ResultMessage(500, "����ʧ�ܣ�����ϵ����Ա!");
		}
		return message;
	}
	
	//��¼��֤
	//ע�⣡����@RequestMappingע�������Ӧget����Ҳ������Ӧpost����@getMapping֮��Ӧget����@postMappingֻ��Ӧpost����
	@RequestMapping(value="/emp_login")
	@ResponseBody	//��Ҫ����json�������Ҫ��Ӹ�ע�⣬���ֻ������ת�Ľ��棬��Ҫ����ע�⣬��Ȼ�᷵���ַ���
	public ResultMessage login(Emp emp,String loginIp,String loginCity,HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=utf-8");//�����������˱������������ȻҪ�����������ã���Ȼ���ǻ����룡����
		ResultMessage message=null;
		System.out.println("Ҫ��¼�Ķ�����"+emp.getNo()+"IP"+loginIp+"City"+loginCity);
		Emp emp1=empService.login(emp);
		if (emp1!=null) {
			//״̬����������ʾ��������Ҫ���µ�¼
			if (emp1.getFlag()==1) {
				//��¼�ɹ��ض�����ҳ
				//response.sendRedirect("index.jsp");//��jsp����webinf�ļ�����
				message=new ResultMessage(200,"��¼�ɹ���");
				//����½����浽sesson�д�ǰ̨����ȡֵ
				session.setAttribute("loginEmp", emp1);
				Loginlog logEntity=new Loginlog();
				logEntity.setIp(loginIp);
				logEntity.setLocation(loginCity);
				logEntity.setNo(emp.getNo());
				int a =logService.addIpAndCity(logEntity);
				System.out.println("Ӱ������"+a);
			} else {
				//response.getWriter().write("<script>alert('�˺�״̬�쳣������ϵ����Ա');location.href='login.jsp'</script>");
				message=new ResultMessage(300,"�˺�״̬�쳣������ϵ����Ա��");
			}
		} else {
//			response.getWriter().write("<script>alert('�˺Ż�������������µ�¼');location.href='login.jsp'</script>");
			message=new ResultMessage(500,"�˺Ż�������������µ�¼��");
			System.out.println(message.getResCode());
		}
		return message;
	}
}
