package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
/**
 * 单元测试方法
 */
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;

import mapper.DepartMapper;
import mapper.EmpMapper;
import pojo.Depart;
import pojo.Emp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JUnitTest {
	
	
	//增我今天改了测试内容了，我就随便增肌了点东西
	@Autowired
	EmpMapper empMapper;
	@Autowired
	DepartMapper depMapper;
	
	@Test
	public void testLogin(){
		Emp emp=new Emp();
		emp.setNo("zhangsan");
		emp.setPass("zhangsan");
		Emp emp1=empMapper.login(emp);
		System.out.println(emp1.getName());
	}
	@Test
	public void addDepart(){
		Depart depart=new Depart();
		for (int i = 101; i <= 200; i++) {
			depart.setName("测试部门"+i);
			depart.setCreatetime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
			depMapper.addDepart(depart);
		}
	}
	@Test
	public void addEmp(){
		Emp emp=new Emp();
		emp.setDel(0);
		emp.setDid(2);
		emp.setEmail("789789@qq.com");
		emp.setFlag(1);
		emp.setPass("888888");
		emp.setPhone("456789");
		emp.setQq("855623545");
		emp.setSex("男");
		for (int i = 1; i < 100; i++) {
			emp.setCreatedate(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
			emp.setName("测A"+i);
			emp.setNo("A620"+i);
			empMapper.addEmp(emp);
		}
	}
	
	@Test
	public void getEmapInfo(){
		List<Emp> emp=empMapper.getEmpList(0, 5);
		for (int i = 0; i < emp.size(); i++) {
			emp.get(i).getDepart().getName();
		}
	}
	@Test
	public void getDepartName(){
		List depList=depMapper.getAllDepartName();
		String depName=JSONObject.toJSONString(depList);
		System.out.println(depName);
	}
	@Test
	public void getEmp_dep(){
		List depList=empMapper.getEmp_Dep();
		String depName=JSONObject.toJSONString(depList);
		System.out.println(depName);
	}
} 
