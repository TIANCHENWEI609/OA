package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Depart;

public interface DepartMapper {
	//�������ӹ���
	public int addDepart(Depart depart);
	
	//��ҳ��ѯ������Ϣ
	//��ȡ��������
	public int departCount();
	//��ϸ�Ĳ�����Ϣ
	/**
	 * 
	 * @param pageStart	��ʼ��¼
	 * @param pageSize	ÿҳ����
	 * @return
	 */
	public List<Depart> getDepartList(@Param("pageStart")long pageStart,@Param("pageSize")long pageSize);//����ע�ⷨ������
}