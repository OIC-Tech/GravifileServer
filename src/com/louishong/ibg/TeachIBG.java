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

		PrintWriter out = response.getWriter();
		try {
		if (!(Boolean) request.getSession().getAttribute("alphaPerm")) {
			out.print("���Ѿ��ǳ�����û�з��Ȩ��");
			return;
		} 
		} catch (NullPointerException e) {
			out.print("���Ѿ��ǳ�����û�з��Ȩ��");
			return;
		}
		
		String teach_input = request.getParameter("teach_input");
		String teach_response = request.getParameter("teach_response");
		String username = (String) request.getSession().getAttribute("username");


		GravifileAIDatabase gravifileAI = null;
		try {
			gravifileAI = new GravifileAIDatabase();

			if (gravifileAI.isUnique(teach_input, teach_response, false)) {
				gravifileAI.addConversation(teach_input, teach_response, false, false, false, username);
				out.println("G����ѧ�����µĻش�!");
			} else {
				out.println("G����ѧ������ش�");
			}
		} catch (Exception e) {
			out.println("�������ڲ�����: " + e.getMessage());
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
