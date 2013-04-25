package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.TeamAction;

import dao.interfaces.TeamActionDAO;
import dao.utils.DatabaseConn;

public class TeamActionImpl implements TeamActionDAO {

	private Connection conn = null; // �������ݿ����Ӷ���
	private PreparedStatement pstmt = null; // �������ݿ��������
	private DatabaseConn dbc = null;// �������ݿ�����
	private ResultSet rs = null;

	public TeamActionImpl() throws Exception { // �������ݿ�����

		this.dbc = new DatabaseConn();// ʵ�������ݿ�����
		this.conn = dbc.getConnection();// ��ȡ���ݿ�����

	}

	public void closeDBC3() throws Exception {
		try {
			dbc.close(conn, pstmt, rs);
		} catch (Exception e) {
			throw e;
		}
	}

	public void closeDBC2() throws Exception {
		try {
			dbc.close(conn, pstmt);
		} catch (Exception e) {
			throw e;
		}
	}

	// �ж����ݿ���ip�Ƿ��Ѿ�����
	public boolean doSelectTeamActionIp(String ip) throws Exception {
		String sql = "";
		try {
			sql = "SELECT * FROM lolit.teamaction WHERE ip = '" + ip + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("��ӭ�ٴη���");
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;

	}

	// �����ݿ�¼���û�������Ϣ����һ�η���
	public boolean doInsertTeamAction(TeamAction tAction) throws Exception {

		String ip = tAction.getIp();
		String firstTime = tAction.getTime();
		String firstBrowser = tAction.getBrowser();
		int registCount = tAction.getRegistCount();
		int visitCount = 1;

		int inta = 0;
		String sql = "";
		try {
			sql = "INSERT INTO lolit.teamaction (ip,firstTime,firstBrowser,visitCount,registCount) VALUES ('"
					+ ip
					+ "','"
					+ firstTime
					+ "','"
					+ firstBrowser
					+ "','"
					+ visitCount + "','" + registCount + "')";
			pstmt = conn.prepareStatement(sql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("�û���һ�η��ʲ�����Ϣ¼�����ݿ�ɹ���");
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	// �����ݿ�¼���û�������Ϣ���ٴη���
	public boolean doUpdateTeamActionAgain(TeamAction tAction) throws Exception {

		String ip = tAction.getIp();
		String lastTime = tAction.getTime();
		String lastBrowser = tAction.getBrowser();
		int visitCount = 1;// Ĭ�ϵ�һ�η���

		int inta = 0;
		String sql = "";
		try {
			sql = "SELECT visitCount FROM lolit.teamaction WHERE ip = '" + ip
					+ "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				visitCount = rs.getInt("visitCount") + 1;
			}
			sql = "UPDATE lolit.teamaction SET lastTime = '" + lastTime
					+ "' , lastBrowser = '" + lastBrowser + "',visitCount = '"
					+ visitCount + "' WHERE ip = '" + ip + "'";
			pstmt = conn.prepareStatement(sql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("�û��ٴη��ʲ�����Ϣ¼�����ݿ�ɹ���");
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	// �����û�ע��ɹ�����
	public boolean doUpdateRegistSuccess(String ip) throws Exception {
		String sql = "";
		int registCount = 0;
		int inta = 0;
		try {
			sql = "SELECT registCount FROM lolit.teamaction WHERE ip = '" + ip
					+ "'";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				registCount = rs.getInt("registCount") + 1;
			}
			sql = "UPDATE lolit.teamaction SET registCount = '" + registCount
					+ "' WHERE ip = '" + ip + "' ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("���ݿ�ע��������³ɹ���");
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	// �����ݿ�¼���û�ע���쳣��Ϣ
	public boolean doInsertErrorNumber(String ip, int eNumber) throws Exception {
		int inta = 0;
		int eCount = 0;
		String sql = "";
		try {
			sql = "INSERT INTO lolit.teamaction_has_errorprompt (teamaction_ip,errorPrompt_eNumber)VALUES('"
					+ ip + "','" + eNumber + "')";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("ע���쳣��Ϣ¼��ɹ���");
			}
			sql = "SELECT eCount FROM lolit.errorprompt WHERE eNumber = '"
					+ eNumber + "'";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eCount = rs.getInt("eCount") + 1;
			}
			sql = "UPDATE lolit.errorprompt SET eCount = '" + eCount
					+ "'WHERE eNumber = '" + eNumber + "'";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("�쳣�������¼��ɹ���");
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

}
