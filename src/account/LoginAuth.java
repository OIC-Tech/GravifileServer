package account;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.louishong.database.ProfileWrapper;

/**
 * Servlet implementation class LoginAuth
 */
@WebServlet("/account/LoginAuth")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProfileWrapper profileWrapper;

	class VerifyFailed extends Exception {
		private static final long serialVersionUID = 7898537494224195211L; };
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String msg = "";
		String redirect = "";
		try {
			profileWrapper = new ProfileWrapper();

			if (profileWrapper.verifyPassword(username, password)) {
				userSession.setAttribute("authed", true);
				if (profileWrapper.hasAlphaPerm(username)) {
					userSession.setAttribute("alphaPerm", true);
				} else {
					userSession.setAttribute("alphaPerm", false);
				}
				profileWrapper.recordIP(username, request.getRemoteAddr());
				request.getSession().setAttribute("username", username);
				request.setAttribute("msg", username + ", 欢迎回来!");
				request.getRequestDispatcher("../announcement.jsp").forward(request, response);
			} else {
				throw new VerifyFailed();
			}
		} catch (VerifyFailed e) {
			msg = "你的用户名或者密码错误,是不是打错了?";
			redirect = "login.jsp";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(redirect).forward(request, response);
		} catch (SQLException e) {
			msg = "你的用户名或者密码错误,是不是打错了?";
			redirect = "login.jsp";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(redirect).forward(request, response);
		} catch (Exception e) {
			msg = "服务器内部错误, 错误记录";
			e.printStackTrace();
			redirect = "login.jsp";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(redirect).forward(request, response);
		}

	}

}
