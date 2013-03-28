package com.louishong.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

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
	/**
	 * 
	 */
	PrintWriter out;

	protected String printDocument(String inputName) throws IOException
	{
	
		//get HTML from localhost
		BufferedReader bufferedReader;
		String line = "";
		String outputString = "";

		//Read from the file
		bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://localhost/htmldoc/OICHome.html").openStream(), "UTF-8"));
 
		while(!((line = bufferedReader.readLine()) == null))
		{
			outputString = outputString + line + "\n";
		}
		
		//Search for last name and user points

		//Output the HTML
		return outputString;
		
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
	{
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace();
			return;
		}
		try
		{
			out.print(printDocument(request.getParameter("name")));
		} catch (IOException e)
		{
			out.print("Document Not Found!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		doGet(request, response);
	}
}
