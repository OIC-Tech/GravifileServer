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
						msg = "恭喜恭喜!这个账号已经获得封测资格!";
						request.setAttribute("msg", msg);
						request.setAttribute("redirect", "/gravifile/server/ibg/userTeach.jsp");
						request.getRequestDispatcher("../announcement.jsp").forward(request, response);
						return;
					} else {
						msg = "内测码无效哟";
					}
				} else {
					msg = "这个账户已经有封测资格了";
				}
			} else {
				msg = "这个用户名不存在呀, 看看是不是爪抖了";
			}
		} catch (Exception e) {
			msg = "服务器内部错误, 错误记录 OwO";
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
