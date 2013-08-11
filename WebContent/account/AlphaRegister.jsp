<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gravifile - 封测申请</title>
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" type="text/javascript"></script>
<script src="../account/js/jquery.maskedinput.min.js" type="text/javascript"></script>
<script src="../account/js/keyFormatter.js" type="text/javascript"></script>

</head>

<body>
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
	<form action="../account/AlphaRegister" method="POST">
		激活封测用户名: <input name="username" type="text" placeholder="用户名"></input><br />
		封测码: <input id="alphakey" name="alphakey" maxlength=17 size=22 type="text" placeholder="XX-XX-XX-XX-XX-XX"></input> <br /> 
		<input value="申请" type="submit"></input>
	</form>
</body>
</html>