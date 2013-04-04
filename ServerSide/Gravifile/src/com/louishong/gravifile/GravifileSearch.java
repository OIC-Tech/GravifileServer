package com.louishong.gravifile;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import com.louishong.database.ProfileWrapper;

@WebServlet("/Search")
public class GravifileSearch extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -6655222082981235150L;
    /**
	 * 
	 */
    PrintWriter out;

    protected static String createXML(String userName) {
	
	if (userName == null) {
	    userName = "";
	}
	
	try {
	    userName = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
	    System.out.println(userName);
	} catch (UnsupportedEncodingException e1) {
	    e1.printStackTrace();
	}

	// Create Document
	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
		.newInstance();
	DocumentBuilder docBuilder;
	Document exportDoc;
	
	try {
	    docBuilder = docBuilderFactory.newDocumentBuilder();
	    exportDoc = docBuilder.newDocument();
	    exportDoc.setXmlVersion("1.0");
	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	    return null;
	}

	// Search Information in the Database
	
	    String userJob = "";
	    String userPoints = "";
	if (ProfileWrapper.hasUser(userName)) {
	    userJob = ProfileWrapper.getUserJob(userName);
	    userPoints = ProfileWrapper.getUserPoint(userName);
	} else {
	    userName = "";
	}

	// Create XML

	// XML Structure Outline
	// Profile
	// ----Name
	// ----Job
	// ----Points

	// Make Root node
	Element xmlRoot = exportDoc.createElement("profile");
	exportDoc.appendChild(xmlRoot);
	
	//Make Name node
	Element xmlName = exportDoc.createElement("name");
	xmlRoot.appendChild(xmlName);
	
	//FIll in Name node
	Text textName = exportDoc.createTextNode(userName);
	xmlName.appendChild(textName);

	// Make Job node
	Element xmlJob = exportDoc.createElement("job");
	xmlRoot.appendChild(xmlJob);

	// Fill in Job node
	Text textJob = exportDoc.createTextNode(userJob);
	xmlJob.appendChild(textJob);

	// Make Points node
	Element xmlPoints = exportDoc.createElement("points");
	xmlRoot.appendChild(xmlPoints);
	
	//Fill in Points node
	Text textPoints = exportDoc.createTextNode(userPoints);
	xmlPoints.appendChild(textPoints);

	// Output Document
	try {
	    DOMSource domSource = new DOMSource(exportDoc);
	    StringWriter stringWriter = new StringWriter();
	    StreamResult result = new StreamResult(stringWriter);
	    TransformerFactory transFactory = TransformerFactory.newInstance();
	    Transformer transformer = transFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	    transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
	    transformer.transform(domSource, result);
	    stringWriter.flush();

	    // Return Final XML
	    return stringWriter.toString();

	} catch (TransformerException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) {

	System.out.println(request.getRemoteHost());
	
	// Set Response Type
	response.setContentType("text/xml;charset=UTF-8");
	try {
	    request.setCharacterEncoding("UTF-8");
	    out = response.getWriter();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// Get Request Parameters
	String requestName = request.getParameter("name");
	
	// Create XML
	String xmlDoc = createXML(requestName);
	
	//Return XML
	out.print(xmlDoc);
	out.flush();
	out.close();

    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) {
	doGet(request, response);
    }

}
