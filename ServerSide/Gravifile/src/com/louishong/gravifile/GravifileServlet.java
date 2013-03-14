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
	String httpMethod = "GET";
	PrintWriter out;

	protected String printDocument(String firstName, String lastName, String userPoint) throws IOException
	{
		if ((firstName == "") || (firstName == null))
		{
			firstName = "No First Name";
		}
		if ((lastName == "") || (lastName == null))
		{
			lastName = "No Last Name";
		}
		
		BufferedReader bufferedReader;
		String line = null;
		String outputString = "";

		bufferedReader = new BufferedReader(new InputStreamReader(new URL("http://localhost/htmldoc/gravifile/gravifile.html").openStream()));
 
		while(!((line = bufferedReader.readLine()) == null))
		{
			outputString = outputString + line + "\n";
		}
		

		return String.format(outputString, firstName, lastName, userPoint);
		
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
	{
		response.setContentType("text/html");
		PrintWriter out = null;
		try
		{
			out = response.getWriter();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			out.print(printDocument(request.getParameter("firstName"),
					request.getParameter("lastName"),
					request.getParameter("userAge")));
		} catch (IOException e)
		{
			out.print("Document Not Found!");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
	{
		httpMethod = "POST";
		doGet(request, response);
		httpMethod = "GET";
	}

}