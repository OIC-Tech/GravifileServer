<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	try {
		if (!(Boolean) session.getAttribute("authed")) {
			throw new NullPointerException();
		}

	} catch (NullPointerException e) {
		request.setAttribute("msg", "你没有登录!");
		request.getRequestDispatcher("../announcement.jsp").forward(request, response);
		return;
	}
	request.setAttribute("msg", "你已成功登出!");
	request.getSession().invalidate();
	request.getRequestDispatcher("../announcement.jsp").forward(request, response);
%>