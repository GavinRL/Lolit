package dao.interfaces;

import vo.Member;

public interface MemberDAO {

	// �ر����ݿ�����
	public void closeDBC3() throws Exception;

	public void closeDBC2() throws Exception;

	// ��̨��¼
	public boolean doSelectForSignIn(Member member) throws Exception;

}
