<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gravifile - 注册账号</title>
</head>
<body>

	<h1>注册</h1>

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
	<form action="Register" method="post" accept-charset="UTF-8">
		用户名: <input name="username" placeholder="账号" type="text" /> - 用户名
		3~16字,英文字符,数字 或者 "_"<br /> 昵称: <input name="nickname"
			placeholder="昵称" type="text" /> - 昵称,任意内容,不超过 16个字符<br /> 密码: <input
			name="password" placeholder="密码" type="password" /> - 密码 6~64字密码
		英文字符,数字 或者 "_"<br /> <input name="register" value="注册" type="submit" /><br />
	</form>

</body>
</html>