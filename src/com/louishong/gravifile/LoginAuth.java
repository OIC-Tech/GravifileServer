package com.louishong.gravifile;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginAuth
 */
@WebServlet("/LoginAuth")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println("Session Data");
		System.out.println(userSession.getAttribute("username"));
		System.out.println(userSession.getAttribute("password"));
		System.out.println("Request Data");
		System.out.println(username);
		System.out.println(password);

		String msg = "";
		
		if (password.equals("huijiaoffice")) {
			userSession.setAttribute("authed", true);
			msg = "The user is authenticated";
		} else {
			msg = "Login Failed";
		}
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

}
