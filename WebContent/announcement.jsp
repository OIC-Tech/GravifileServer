<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String msg;
	String redirect;
	try {
		msg = (String) request.getAttribute("msg");
		redirect = (String) request.getAttribute("redirect");
		if ((msg == null) || (msg == "")) {
			msg = "没有提示咦...怎么会这样,你怎么进来的?我都没话说了魂淡!!";
		}
		if (redirect == null || (redirect == "")) {
			redirect = "/";
		}
	} catch (Exception e) {
		msg = "没有提示咦...怎么会这样,你怎么进来的?我都没话说了魂淡!!";
		redirect = "/";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>bilibili - 提示</title>
<style type="text/css">
body {
	background-repeat: no-repeat;
	color: #000;
	font: 9pt/200% Verdana;
}

a {
	text-decoration: none;
	color: #659B28
}

a:hover {
	text-decoration: underline;
}
</style>
<style type="text/css"></style>
</head>
<body>
	<center>
		<div
			style="padding: 30px; padding: 36px 80px; border: 1px solid #a9a9a9; background: #ffffff; text-align: center; margin: 20% auto; background-repeat: no-repeat; width: 55%;">
			<script>
				var pgo = 0;
				function JumpUrl() {
					if (pgo == 0) {
						location = '<%=redirect%>';
						pgo = 1;
					}
				}
				document.write("<%=msg%>");
				document.write("<br /><a href='<%=redirect%>'>如果你的浏览器没反应，请点击这里...</a>");
				setTimeout('JumpUrl()', 5000);
			</script>
		</div>
	</center>


</body>
</html>