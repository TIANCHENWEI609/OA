package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
/**
 * 单元测试方法
 */
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mapper.DepartMapper;
import mapper.EmpMapper;
import pojo.Depart;
import pojo.Emp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMybatis {
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
} 
