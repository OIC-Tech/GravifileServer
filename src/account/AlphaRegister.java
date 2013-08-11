package account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.louishong.database.ProfileWrapper;

/**
 * Servlet implementation class AlphaRegister
 */
@WebServlet("/account/AlphaRegister")
public class AlphaRegister extends HttpServlet {
	private static ProfileWrapper profileWrapper;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String alphaKey = request.getParameter("alphakey");

		System.out.println("Request Data");
		System.out.println(username);
		System.out.println(alphaKey);

		String msg = "";

		try {
			profileWrapper = new ProfileWrapper();
			if (profileWrapper.hasUser(username)) {
				if (!profileWrapper.hasAlphaPerm(username)) {
					if (validateKey(alphaKey)) {
						profileWrapper.giveAlphaPerm(username);
						request.getSession().setAttribute("alphaPerm", true);
						msg = "��ϲ��ϲ!����˺��Ѿ���÷���ʸ�!";
						request.setAttribute("msg", msg);
						request.setAttribute("redirect", "/gravifile/server/ibg/userTeach.jsp");
						request.getRequestDispatcher("../announcement.jsp").forward(request, response);
						return;
					} else {
						msg = "�ڲ�����ЧӴ";
					}
				} else {
					msg = "����˻��Ѿ��з���ʸ���";
				}
			} else {
				msg = "����û���������ѽ, �����ǲ���צ����";
			}
		} catch (Exception e) {
			msg = "�������ڲ�����, �����¼ OwO";
			e.printStackTrace();
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("../account/AlphaRegister.jsp").forward(request, response);

	}

	private static boolean validateKey(String key) {
		try {
		String[] keySplit = key.split("-");
		int sum = 0;
		for (int i = 0; i < keySplit.length; i++) {
			sum = sum + new Integer(keySplit[i]);
		}

		return sum % 73 == 0 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}
}
