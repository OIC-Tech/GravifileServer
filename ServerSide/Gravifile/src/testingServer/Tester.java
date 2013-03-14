package testingServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

@WebServlet("/Tester")
public class Tester extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5265128452538012292L;

	String httpMethod = "GET";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements())
		{
			out.println(String.format("==========%s=========", httpMethod));
			System.out.println(String.format("==========%s=========", httpMethod));
			String paramName = paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			out.println("Parameter Name: " + paramName);
			System.out.println("Parameter Name: " + paramName);
			for (int paramValueCount = 0;paramValueCount<paramValues.length;paramValueCount++)
			{				
				out.println(String.format("Parameter Value %s: ", paramValueCount+1) + paramValues[paramValueCount]);
				System.out.println(String.format("Parameter Value %s: ", paramValueCount+1) + paramValues[paramValueCount]);
			}
			out.println();
		}
		
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		httpMethod = "POST";
		doGet(request, response);
		httpMethod = "GET";
	}
	
}
