<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Gravifile - Login</title>
</head>
<body>
You're current IP: <%=request.getRemoteAddr() %>
<form action="/LoginAuth" method="post">
<input name="username" placeholder="Username" type="text"/><br/>
<input name="password" placeholder="Password" type="password"/><br/>
<input name="submit" value="Login" type="button"/><br/>

</form>
</body>
</html>