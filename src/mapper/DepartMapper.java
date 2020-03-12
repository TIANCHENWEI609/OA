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
	/**
	 * ����ɾ��
	 * @param id	����id
	 * @return
	 */
	public int delDepart(int id);
	/**
	 * ����ID�޸Ĳ�����Ϣ
	 * @param id
	 * @param name
	 * @param time
	 * @return
	 */
	public int updateDeptById(Depart depart);
	/**
	 * ��ѯ���еĲ�����Ϣ
	 */
	public List<Depart> getAllDepart();
	
	public List<String> getAllDepartName();
	//����id��ȡ������Ϣ
	public Depart getDepartById(int id);

}