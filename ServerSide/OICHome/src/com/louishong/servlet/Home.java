package com.louishong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Home extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		response.setContentType("text/html");
		PrintWriter out = null;
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
				"<head>\n" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" + 
				"<title>OIC Technology - Site is Building!</title>\n" + 
				"<style type=\"text/css\">\n" + 
				"body,td,th {\n" + 
				"	margin-top:250px;\n" + 
				"	font-family: Arial, Helvetica, sans-serif;\n" + 
				"	font-size: 18px;\n" + 
				"	line-height:4em;\n" + 
				"	horizontal-align: middle;\n" + 
				"	color: #000;\n" + 
				"}\n" + 
				"body {\n" + 
				"	background-image: url(/images/bg.png);\n" + 
				"	background-repeat: repeat;\n" + 
				"}\n" + 
				"</style>\n" + 
				"</head>\n" + 
				"\n" + 
				"<body>\n" + 
				"<center><h1>OIC Technology - Site is Building!</h1></center>\n" + 
				"\n" + 
				"</body>\n" + 
				"</html>\n" + 
				"");
		
		
		
	}
}
