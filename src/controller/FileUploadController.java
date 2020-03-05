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
* @author 作者田陈伟
* @version 创建时间：2020年3月3日 上午10:23:14
* 类说明 文件上传控制器
*/
@Controller
public class FileUploadController {
	
	@RequestMapping(value="/fileUpload")
	@ResponseBody
	public ResultMessage uploadFile(MultipartFile file,HttpSession session){//注意在springmvc中上传文件必须要用file这个变量去接，不能变名字
			ResultMessage message=null;
		//如果不想用file，则需要声明如下：
		//public void uploadFile(@PathVariable("file")MultipartFile f){
		System.out.println("原始完整文件名："+file.getOriginalFilename());//获取原始文件名
		String oldFileName=file.getOriginalFilename();
		System.out.println("原始文件后缀"+oldFileName.substring(oldFileName.lastIndexOf(".")));
		String fileType=oldFileName.substring(oldFileName.lastIndexOf("."));//获取原始文件后缀
		//进行文件包装，换个名字，防止A和B同时上传名字都为123.jpg的情况，后者会覆盖前者
		String uid=UUID.randomUUID().toString();//随机获取一个不规则的序列号
		System.out.println(uid+fileType);//新文件名
		//获取上传文件的根路径
		String realPath=session.getServletContext().getRealPath("/media/fileUpload");
		System.out.println("根路径"+realPath);
		System.out.println("完整的文件信息"+realPath+"/"+uid+fileType);
		try {
			file.transferTo(new File(realPath+"/"+uid+fileType));
			message=new ResultMessage(200,uid+fileType);//上传成功，返回文件名字给前台展示
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("文件上传异常，异常信息："+e.getMessage());
			message=new ResultMessage(500, "上传失败，请联系管理员!");
		}
		System.out.println("文件上传成功啦!");
		return message;
	}
}
