package com.louishong.gravifile;

import java.io.*;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;


@WebServlet("/")
public class GravifileServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5265128452538012292L;
	PrintWriter out;

	protected String printDocument() throws IOException
	{
		//get HTML from localhost
		BufferedReader bufferedReader;
		String line = "";
		String outputString = "";

		//Read from the file
		bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://localhost/htmldoc/gravifile/gravifile.html").openStream(), "UTF-8"));
 
		while(!((line = bufferedReader.readLine()) == null))
		{
			outputString = outputString + line + "\n";
		}
		
		//Search for last name and user points

		//Output the html
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
			out.println(printDocument());
		} catch (IOException e)
		{
			e.printStackTrace();
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		doGet(request, response);
	}

}
