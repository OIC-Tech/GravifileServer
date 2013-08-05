package com.louishong.ibg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.louishong.database.GravifileAIDatabase;

/**
 * Servlet implementation class UserTeach
 */
@WebServlet("/ibg/TeachIBG")
public class TeachIBG extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String teach_input = new String(request.getParameter("teach_input").getBytes("ISO-8859-1"), "UTF-8");
		String teach_response = new String(request.getParameter("teach_response").getBytes("ISO-8859-1"), "UTF-8");

		PrintWriter out = response.getWriter();

		GravifileAIDatabase gravifileAI = null;
		try {
			gravifileAI = new GravifileAIDatabase();

			if (gravifileAI.isUnique(teach_input, teach_response, false)) {
				gravifileAI.addConversation(teach_input, teach_response, false);
				out.println("Your response has been added to the Database!");
			} else {
				out.println("Your reponse already exists!");
			}
		} catch (Exception e) {
			out.println("Server Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				gravifileAI.cutConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
