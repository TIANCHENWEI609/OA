package controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import util.ResultMessage;

/**
* @author �������ΰ
* @version ����ʱ�䣺2020��3��3�� ����10:23:14
* ��˵�� �ļ��ϴ�������
*/
@Controller
public class FileUploadController {
	
	@RequestMapping(value="/fileUpload")
	@ResponseBody
	public ResultMessage uploadFile(MultipartFile file,HttpSession session){//ע����springmvc���ϴ��ļ�����Ҫ��file�������ȥ�ӣ����ܱ�����
			ResultMessage message=null;
		//���������file������Ҫ�������£�
		//public void uploadFile(@PathVariable("file")MultipartFile f){
		System.out.println("ԭʼ�����ļ�����"+file.getOriginalFilename());//��ȡԭʼ�ļ���
		String oldFileName=file.getOriginalFilename();
		System.out.println("ԭʼ�ļ���׺"+oldFileName.substring(oldFileName.lastIndexOf(".")));
		String fileType=oldFileName.substring(oldFileName.lastIndexOf("."));//��ȡԭʼ�ļ���׺
		//�����ļ���װ���������֣���ֹA��Bͬʱ�ϴ����ֶ�Ϊ123.jpg����������߻Ḳ��ǰ��
		String uid=UUID.randomUUID().toString();//�����ȡһ������������к�
		System.out.println(uid+fileType);//���ļ���
		//��ȡ�ϴ��ļ��ĸ�·��
		String realPath=session.getServletContext().getRealPath("/media/fileUpload");
		System.out.println("��·��"+realPath);
		System.out.println("�������ļ���Ϣ"+realPath+"/"+uid+fileType);
		try {
			file.transferTo(new File(realPath+"/"+uid+fileType));
			message=new ResultMessage(200,uid+fileType);//�ϴ��ɹ��������ļ����ָ�ǰ̨չʾ
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�ļ��ϴ��쳣���쳣��Ϣ��"+e.getMessage());
			message=new ResultMessage(500, "�ϴ�ʧ�ܣ�����ϵ����Ա!");
		}
		System.out.println("�ļ��ϴ��ɹ���!");
		return message;
	}
}
