package dao.interfaces;

import java.util.List;

import vo.Team;


public interface TeamDAO {

	
	// �ر����ݿ�����
	public void closeDBC3() throws Exception;

	public void closeDBC2() throws Exception;
	
	//��֤�������Ƿ��Ѿ�����
	public boolean doSelectTeamName(String tName) throws Exception;
	
	//��֤ѧ���Ƿ�Ψһ
	public boolean doSelectPlayerId(int pId) throws Exception;

	// ��������ʱ�����ݿ��������
	public boolean doInsert(Team team) throws Exception;

	// ���������֤
	public boolean doSelectForSignIn(Long tId, String tPwd) throws Exception;
	
	// �����Ƿ��Ѿ���ǩ��֤
	public boolean doSelectForIfLotted(Long tId) throws Exception;

	// �����ǩ˳λ
	public boolean doInsertOrder(Long tId) throws Exception;

	// ��ȡ���ж���˳λ
	public List<Team> doSelectForMatchList() throws Exception;
	
	//��ȡ���пɲ����������
	public List<Team> doSelectForTeamList(int state) throws Exception;
	
	//��ȡ����������������
	public List<Integer> doSelectForServerCount()throws Exception;
	
	//��ȡʤ����������
	public List<Integer> doSelectForWinCount()throws Exception;
	
	//�鿴��ѧԺ��������
	public List<Integer> doSelectForInstituteCount()throws Exception;
	
	//�鿴������������  ��Ҫ�� �����ϣ����� ��Է 4������
	public List<Integer> getBelongAreaCount(List<Integer> instituteCount) throws Exception;
	
	//�鿴��rank��������
	public List<Integer> doSelectRankCount() throws Exception;
}
