package service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.DepartMapper;
import pojo.Depart;
import service.DepartService;

/**
* @author �������ΰ
* @version ����ʱ�䣺2020��2��24�� ����3:30:00
* ��˵��
*/
@Service
public class DepartServiceImpl implements DepartService {
	
	@Autowired
	DepartMapper mapper;
	@Override
	public int addDepart(Depart depart) {
		// TODO Auto-generated method stub
		return mapper.addDepart(depart);
	}
	@Override
	public int departCount() {
		// TODO Auto-generated method stub
		return mapper.departCount();
	}
	@Override
	public List<Depart> getDepartList(long pageStart, long pageSize) {
		// TODO Auto-generated method stub
		return mapper.getDepartList(pageStart, pageSize);
	}
	@Override
	public int delDepart(int id) {
		// TODO Auto-generated method stub
		return mapper.delDepart(id);
	}

}
