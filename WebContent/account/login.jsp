<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	try {
		boolean authed = (Boolean) session.getAttribute("authed");

		if (authed) {
			request.setAttribute("msg", "你已经登录了");
			request.setAttribute("redirect", "/");
			request.getRequestDispatcher("../announcement.jsp").forward(request, response);
		}
	} catch (NullPointerException e) {
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gravifile - 登录</title>
</head>
<body>
	<h1>登录</h1>

	你的IP地址:
	<%=request.getRemoteAddr()%><br />
	<%
		String msg = (String) request.getAttribute("msg");
		try {
			if (!msg.equals(null)) {
	%>
	<%=msg%><br />
	<%
		}
		} catch (NullPointerException e) {
		}
	%>

	<form action="LoginAuth" method="post">
		用户名: <input name="username" placeholder="用户名" type="text" /><br />
		密码: <input name="password" placeholder="密码" type="password" /><br />
		<input name="submit" value="登录" type="submit" /> <br />
	</form>
	<br />
	<a href="register.jsp">没有账号? 点击注册</a>
	<br />
	<b><a href="oldUserRegister.jsp"><font color="red">老用户(IB
				Gravity 成员) 点击这里注册!!</font></a></b>
</body>
</html>