<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>��ϵͳ�û�ת��</title>
</head>
<body>

	<h1>ע��</h1>

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
	<form action="OldUserRegister" method="post" accept-charset="UTF-8">
		��ʵ����: <input name="realname" placeholder="��ʵ����" type="text" /> �������ע��󽫱�����ǳƸ��� <br /> 
		�û���: <input name="username" placeholder="�˺�" type="text" /> - �û��� 3~16��,Ӣ���ַ�,���� ���� "_"<br /> 
		�ǳ�: <input name="nickname" placeholder="�ǳ�" type="text" /> - �ǳ�,��������,������ 16���ַ�<br /> 
		����: <input name="password" placeholder="����" type="password" /> - ���� 6~64������
		Ӣ���ַ�,���� ���� "_"<br /> <input name="register" value="ע��" type="submit" /><br />
	</form>

</body>
</html>