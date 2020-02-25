package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	/**
	 * 通用的页面跳转方法，不用重复进行编写跳转方法
	 * /page_index
	 * /page_clock
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/page_{page}")//这个{page}也可以用{XXX}来代替，只是一个通配符
	public String toPage(@PathVariable("page")String page){
//		return "/WEB-INF/jsp/"+page+".jsp";//不采用视图解析器方式返回
		return page;//采用视图解析器方式进行返回，默认组装成前缀+page+后缀的形式进行返回
	}
	
//	//跳转到首页
//	@RequestMapping(value="/toIndex")
//	public String tiaoIndex(){
//		System.out.println("进行toIndex跳转啦");
//		return "/WEB-INF/jsp/index.jsp";//服务器内部转发访问
//	}
//	//跳转到日历页
//	@RequestMapping(value="/toClock")
//	public String tiaoClock(){
//		System.out.println("进行toIndex跳转啦");
//		return "/WEB-INF/jsp/clock.jsp";//服务器内部转发访问
//	}
}
