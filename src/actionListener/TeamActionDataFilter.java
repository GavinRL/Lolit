package actionListener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import vo.TeamAction;

import dao.utils.DAOFactory;
import dao.interfaces.TeamActionDAO;

public class TeamActionDataFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// ��cookie�л�ȡ������Ϊ���ݣ�������������ݿ�
		Cookie[] cookies = (Cookie[]) ((HttpServletRequest) request)
				.getCookies();
		if (cookies == null) {
			filterChain.doFilter(request, response);
		} else {
			TeamAction tAction = new TeamAction();
			for (int i = 0; i < cookies.length; i++) {
				String cookieName = cookies[i].getName();
				if ("ip".equals(cookieName)) {
					tAction.setIp(cookies[i].getValue());
				} else if ("time".equals(cookieName)) {
					tAction.setTime(cookies[i].getValue());
				} else if ("registCount".equals(cookieName)) {
					tAction.setRegistCount(Integer.parseInt(cookies[i]
							.getValue()));
				} else if ("broswer".equals(cookieName)) {
					tAction.setBrowser(cookies[i].getValue());
				}
			}
			String ip = tAction.getIp();
			TeamActionDAO teamActionDAO = null;
			try {
				teamActionDAO = DAOFactory.getTeamActionDAOInstance();
				// �ж���Go���滹��ע��ɹ�����ץ��������Ϣ
				if (tAction.getRegistCount() == 1) {
					if (teamActionDAO.doUpdateRegistSuccess(ip)) {
						System.out.println("�û�ע��������³ɹ���");
					}
				} else if (tAction.getRegistCount() == 0) {
					// �ж��û��Ƿ��ǵ�һ�η��ʱ���վ
					if (teamActionDAO.doSelectTeamActionIp(ip)) {
						if (teamActionDAO.doUpdateTeamActionAgain(tAction)) {
							System.out.println("�û��ٴβ�����Ϣ¼��ɹ���");
						}
					} else {
						if (teamActionDAO.doInsertTeamAction(tAction)) {
							System.out.println("�û���һ�β�������¼��ɹ���");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			filterChain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
