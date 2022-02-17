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
* @author �������ΰ
* @version ����ʱ�䣺2020��2��24�� ����3:32:14
* ��˵��
*/
@Controller
public class DepartController {
	@Autowired
	DepartService service;
	
	//����һ�·�֧����ĺϲ�
	//����Ա����ѯ������������Ϣ
	@RequestMapping(value="/getAllDeptList")
	@ResponseBody
	public List<Depart> getAllDept(){
		return service.getAllDepart();
	}
	/**
	 * �޸Ĳ�����Ϣ
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
			message=new ResultMessage(200, "���³ɹ�");
		} else {
			message=new ResultMessage(500, "����ʧ�ܣ�����ϵ����Ա!");
		}
		return message;
	}
	
	//���Ÿ���
	@RequestMapping(value="/goUpDeptPage/{id}")
	public String goUpDeptPage(@PathVariable("id") int id ,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");//�����������˱������������ȻҪ�����������ã���Ȼ���ǻ����룡����
		Depart departObj=service.getDepartById(id);
		model.addAttribute("Depart", departObj);
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
