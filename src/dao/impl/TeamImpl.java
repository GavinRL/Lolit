package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vo.Global;
import vo.Player;
import vo.Team;
import dao.interfaces.TeamDAO;
import dao.utils.DatabaseConn;

public class TeamImpl implements TeamDAO {

	private Connection conn = null; // �������ݿ����Ӷ���
	private PreparedStatement pstmt = null; // �������ݿ��������
	private DatabaseConn dbc = null;// �������ݿ�����
	private ResultSet rs = null;

	public TeamImpl() throws Exception { // �������ݿ�����

		this.dbc = new DatabaseConn();// ʵ�������ݿ�����
		this.conn = dbc.getConnection();// ��ȡ���ݿ�����

	}

	//��ѯ���������Ƿ��Ѿ�����
	public boolean doSelectTeamName(String tName) throws Exception {

		String teamName = tName;
		String sql = "select * from Lolit.team where tName = '" + teamName
				+ "' ";// �鿴�������Ƿ��Ѿ�����

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("�������Ѿ�����");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	//��ѯ�ӳ�ѧ���Ƿ��Ѿ�����
	public boolean doSelectPlayerId(int pId) throws Exception {

		String sql = "select * from Lolit.player where pId = '" + pId + "' ";// �鿴�ӳ�ѧ���Ƿ��Ѿ�����

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("ѧ���Ѿ�����");
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
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

	//�������ע����Ϣ
	public boolean doInsert(Team team) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false; // �ж��Ƿ�ע��ɹ�
		boolean teamflag = false; // �ж϶�����Ϣ�����Ƿ�ɹ�
		boolean playerflag = false; // �ж϶�Ա��Ϣ�����Ƿ�ɹ�

		Long tId = team.gettId();
		String tName = team.gettName();
		String tPwd = team.gettPwd();
		long tPhone = team.gettPhone();
		String tBelonging = team.gettBelonging();
		// boolean tZeros = team.gettZero();
		// int tZero = 0;
		// if (tZeros) {
		// tZero = 1;
		// }

		int pId;
		String pName = null;
		String pGender = null;
		String pDormitory = null;
		String pLolExp = null;
		String pServer = null;
		int pRank;
		int pWin;

		String teamsql = "";
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(team.getP1());
		// players.add(team.getP2());
		// players.add(team.getP3());
		// players.add(team.getP4());
		// players.add(team.getP5());
		// if (team.getP6() != null) {
		// players.add(team.getP6());
		// if (team.getP7() != null) {
		// players.add(team.getP7());
		// }
		// }
		// �Ȳ��������Ϣ
		// teamsql = "select * from Lolit.team where tName = '" + tName +
		// "' ";// �鿴�������Ƿ��Ѿ�����
		// System.out.println(teamsql);
		int inta = 0; // �ж�team��Ϣ�Ƿ�������ݿ�ɹ�
		int pinta = 0; // �ж�player��Ϣ�Ƿ�������ݿ�ɹ�
		try {
			// rs = pstmt.executeQuery();
			// if (rs.next()) {
			// System.out.println("���������Ѵ��ڣ�");
			// } else {
			// System.out.println(tZero);
			teamsql = "insert into Lolit.team (tId,tName,tPwd,tPhone,tBelonging,tState) values ('"
					+ tId
					+ "','"
					+ tName
					+ "','"
					+ tPwd
					+ "','"
					+ tPhone
					+ "','" + tBelonging + "','1')"; // ���������Ϣsql���
			System.out.println(teamsql);
			pstmt = conn.prepareStatement(teamsql);
			inta = pstmt.executeUpdate();
			if (inta > 0) {
				System.out.println("������Ϣ¼��ɹ���");
				teamflag = true;
			}
			// �ٲ����Ա��Ϣ,ʹ��ѭ���������ж�Ա��Ϣ
			for (int i = 0; i < players.size(); i++) {
				String playersql = "";
				Player p = new Player();
				p = players.get(i);
				pId = p.getpId();
				pName = p.getpName();
				pGender = p.getpGender();
				pDormitory = p.getpDormitory();
				pLolExp = p.getpLolExp();
				pServer = p.getpServer();
				pWin = p.getpWin();
				pRank = p.getpRank();

				playersql = "insert into Lolit.player (pId,pName,pGender,pDormitory,pLolExp,pServer,pWin,team_tId,pRank) values ('"
						+ pId
						+ "','"
						+ pName
						+ "','"
						+ pGender
						+ "','"
						+ pDormitory
						+ "','"
						+ pLolExp
						+ "','"
						+ pServer
						+ "','" + pWin + "','" + tId + "','" + pRank + "')";
				System.out.println("�����Ա��" + playersql);
				pinta = pstmt.executeUpdate(playersql);
				if (pinta > 0) {
					System.out.println("��Ա��Ϣ¼��ɹ���");
					playerflag = true;
				}
			}

		} catch (Exception e) {
			throw e;
		}
		// �����Ա��Ϣ�Ͷ�����Ϣ������ɹ����򷵻�ע��ɹ�
		if (teamflag && playerflag) {
			flag = true;
		}
		return flag;
	}

	//��ѯ�����Ƿ��¼
	public boolean doSelectForSignIn(Long teamId, String teamPwd)
			throws Exception {

		Long tId = teamId;
		String tPwd = teamPwd;

		String sql = "SELECT tPwd FROM team WHERE tId = '" + tId + "'";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("tPwd").equals(tPwd)) {
					return true;
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return false;
	}

	//��ѯ�Ƿ��Ѿ���ǩ
	public boolean doSelectForIfLotted(Long tId) throws Exception {

		// 999ΪĬ�Ͽ�ֵ����ʾû��ǩ
		int tOrder = 999;

		// ��֤�Ƿ���Գ�ǩ
		String sqlCheck = "SELECT tOrder FROM team WHERE tId = '" + tId
				+ "' AND tState = 2";

		try {
			pstmt = conn.prepareStatement(sqlCheck);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("tOrder") == tOrder) {
					return true;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	//¼������ǩ��
	public boolean doInsertOrder(Long tId) throws Exception {

		int tOrder;

		// ��ȡ�����ѳ�ǩ�ĳ�ǩ��
		String sqlGetNotNullOrder = "SELECT tOrder FROM team WHERE tOrder <>'999'";
		// �����ǩ��
		String sqlInsertOrder = "";

		try {
			pstmt = conn.prepareStatement(sqlGetNotNullOrder);
			rs = pstmt.executeQuery();

			ArrayList<Integer> tOrderList = new ArrayList<Integer>();
			Random rd = new Random();

			if (rs.next()) {
				tOrderList.add(rs.getInt("tOrder"));
				while (rs.next()) {
					tOrderList.add(rs.getInt("tOrder"));
				}

				// ���Ψһ�ĳ�ǩ�ţ�ͨ�����Ѿ��еĺűȽϣ�������е��ѳ�ǩ�Ŷ������������ȣ���f=1�������������ѭ����
				while (true) {
					int t = rd.nextInt(Global.getTeamCount() - 1);
					int f = 0;
					for (int i = 0; i < tOrderList.size(); i++) {
						System.out.println(tOrderList.size());
						if (tOrderList.get(i) == t) {
							f = 0;
							break;
						}
						f = 1;
					}
					if (f == 1) {
						tOrder = t;
						break;
					}
				}
			} else {
				tOrder = rd.nextInt(Global.getTeamCount() - 1);
			}

			sqlInsertOrder = "UPDATE team SET tOrder = '" + tOrder
					+ "' WHERE tId = '" + tId + "'";

			pstmt = conn.prepareStatement(sqlInsertOrder);
			int inta = pstmt.executeUpdate();

			if (inta > 0) {
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}

	//��ѯ��ǩ�����Ϣ
	public List<Team> doSelectForMatchList() throws Exception {

		String sql = "SELECT * FROM team WHERE tState = '2' ORDER BY tOrder";
		ArrayList<Team> tList = new ArrayList<Team>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Team team = new Team();
				team.settId(rs.getLong("tId"));
				team.settName(rs.getString("tName"));
				team.settLogo(rs.getString("tLogo"));
				team.settOrder(rs.getInt("tOrder"));
				tList.add(team);
			}
			return tList;
		} catch (Exception e) {
			throw e;
		}
	}

	// ����state =1ʱΪע����ɣ�=2ʱΪ���ͨ����=3ʱΪ��ʽ����
	public List<Team> doSelectForTeamList(int state) throws Exception {

		String sql = "SELECT * FROM team INNER JOIN player ON tId = team_tId WHERE tState = '"
				+ state + "'";
		System.out.println("teamList s1 sql:" + sql);

		ArrayList<Team> tList = new ArrayList<Team>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Team team = new Team();
				team.settId(rs.getLong("tId"));
				team.settName(rs.getString("tName"));
				team.settLogo(rs.getString("tLogo"));
				team.settOrder(rs.getInt("tOrder"));
				team.settPhone(rs.getLong("tPhone"));
				team.settZero(rs.getBoolean("tZero"));
				team.settBelonging(rs.getString("tBelonging"));
				for (int i = 0; i < 1; i++) {
					Player player = new Player();
					player.setpName(rs.getString("pName"));
					player.setpServer(rs.getString("pServer"));
					player.setpWin(rs.getInt("pWin"));

					switch (i + 1) {
					case 1:
						team.setP1(player);
						break;
					default:
						break;
					}
				}
				tList.add(team);
			}
			return tList;
		} catch (Exception e) {
			throw e;
		}
	}

	// �鿴����������������
	public List<Integer> doSelectForServerCount() throws Exception {
		String[] servers = { "��30����", "�ȶ�������", "��ŷ����", "������ר��", "����֮��", "����֮��",
				"Ӱ��", "�������", "ˮ��֮��", "��������", "��Ӱ��", "��ɫõ��", "�þ�֮��", "��ɪ�ر�",
				"�����", "Ť������", "ս��ѧԺ", "ˡ����", "Ƥ�����ַ�", "��η�ȷ�", "��¶���", "���׶�׿��",
				"ŵ����˹", "��������", "�氲" };
		String sql = "";
		List<Integer> serverCount = new ArrayList<Integer>();

		try {
			for (int i = 0; i < servers.length; i++) {
				sql = "SELECT COUNT(*) FROM lolit.player WHERE pServer = '"
						+ servers[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					serverCount.add(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}
		return serverCount;
	}

	// �鿴��ʤ����������
	public List<Integer> doSelectForWinCount() throws Exception {
		List<Integer> winCount = new ArrayList<Integer>();
		String sql = "";
		// ʤ������
		int[] min = { 0, 100, 500, 1000 };
		int[] max = { 100, 500, 1000, 9999 };

		try {
			for (int i = 0; i < 4; i++) {
				sql = "SELECT COUNT(*) FROM lolit.player WHERE pWin >= '"
						+ min[i] + "' AND pWin < '" + max[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					winCount.add(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}
		return winCount;
	}

	// �鿴��ѧԺ��������
	public List<Integer> doSelectForInstituteCount() throws Exception {
		List<Integer> instituteCount = new ArrayList<Integer>();
		String[] institute = { "%������װ", "%��������", "%��֯", "%���Ͽ�ѧ", "%������ѧ",
				"%��е����", "%�����Զ���", "%������Ϣ", "%��", "%����", "%����", "%�����뷨",
				"%�����", "%Ӧ�ü���", "%���ʽ���" };
		String sql = "";

		try {
			for (int i = 0; i < institute.length; i++) {
				sql = "SELECT COUNT(*) FROM lolit.team WHERE tBelonging LIKE '"
						+ institute[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					instituteCount.add(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}

		return instituteCount;
	}

	// �鿴������������
	public List<Integer> getBelongAreaCount(List<Integer> instituteCount)
			throws Exception {

		// 0%������װ 1%�������� 2%��֯ 3%���Ͽ�ѧ 4%������ѧ
		// 5%��е���� 6%�����Զ��� 7%������Ϣ 8%�� 9%����
		// 10%���� 11%�����뷨 12%����� 13%Ӧ�ü��� 14%���ʽ���
		List<Integer> areaCount = new ArrayList<Integer>();
		int eastCount = 0, westCount = 0, northCount = 0;
		eastCount = instituteCount.get(3) + instituteCount.get(4)
				+ instituteCount.get(5) + instituteCount.get(6)
				+ instituteCount.get(7) + instituteCount.get(0)
				+ instituteCount.get(14);
		westCount = instituteCount.get(8) + instituteCount.get(9)
				+ instituteCount.get(10) + instituteCount.get(11)
				+ instituteCount.get(12);
		northCount = instituteCount.get(2) + instituteCount.get(1);
		areaCount.add(eastCount);
		areaCount.add(westCount);
		areaCount.add(northCount);
		return areaCount;
	}

	// �鿴��rank��������
	public List<Integer> doSelectRankCount() throws Exception {
		List<Integer> rankCount = new ArrayList<Integer>();
		String sql = "";
		// rank����
		int[] min = { 0, 500, 1000, 1300, 1500, 1600, 1800 };
		int[] max = { 1, 1000, 1300, 1500, 1600, 1800, 2800 };

		try {
			for (int i = 0; i < min.length; i++) {
				sql = "SELECT COUNT(*) FROM lolit.player WHERE pRank >= '"
						+ min[i] + "' AND pRank < '" + max[i] + "'";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					rankCount.add(rs.getInt(1));
				}
			}
		} catch (Exception e) {
			throw e;// TODO: handle exception
		}
		return rankCount;
	}

}
