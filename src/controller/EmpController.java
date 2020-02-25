package controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pojo.Emp;
import pojo.Loginlog;
import service.EmpService;
import service.LoginLogService;
import util.ResultMessage;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	//����ip�͵ص㣬��Ҫ�õ�LoginLog����Ϣ���������ע��
	@Autowired
	LoginLogService logService;
	
	//��¼��֤
	//ע�⣡����@RequestMappingע�������Ӧget����Ҳ������Ӧpost����@getMapping֮��Ӧget����@postMappingֻ��Ӧpost����
	@RequestMapping(value="/emp_login")
	@ResponseBody
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
