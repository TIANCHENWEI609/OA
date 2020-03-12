package service;

import java.util.List;

import pojo.Depart;

/**
* @author �������ΰ
* @version ����ʱ�䣺2020��2��24�� ����3:27:02
* ��˵��
*/
public interface DepartService {
	//���Ӳ�����Ϣ
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
	public List<Depart> getDepartList(long pageStart,long pageSize);
	/**
	 * ����ɾ��������delΪ1���ò��Ų����ã�
	 * @param id ����id
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
	 * ��ѯ���в�����Ϣ
	 */
	public List<Depart> getAllDepart();
	
	public List<String> getAllDepartName();
	/**
	 * ����id��ȡ������Ϣ
	 * @param id
	 * @return
	 */
	public Depart getDepartById(int id);
}
