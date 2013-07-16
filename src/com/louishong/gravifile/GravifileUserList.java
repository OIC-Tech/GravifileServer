/**
 * 
 */
package com.louishong.gravifile;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.louishong.database.ProfileWrapper;

/**
 * @author Louis Hong
 * 
 */

@WebServlet("/userlist")
public class GravifileUserList extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 2507342611212424269L;
    PrintWriter out;

    static Document createXMLDocument() throws Exception {
	// Create Document
	DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
		.newInstance();
	DocumentBuilder docBuilder;
	Document exportDoc;

	try {
	    docBuilder = docBuilderFactory.newDocumentBuilder();
	    exportDoc = docBuilder.newDocument();
	    exportDoc.setXmlVersion("1.0");
	    return exportDoc;
	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	    throw e;
	}

    }

    static String outputDocument(Document exportDoc) throws TransformerException {
	// Output Document
	DOMSource domSource = new DOMSource(exportDoc);
	StringWriter stringWriter = new StringWriter();
	StreamResult result = new StreamResult(stringWriter);
	TransformerFactory transFactory = TransformerFactory.newInstance();
	Transformer transformer = transFactory.newTransformer();
	transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	transformer.transform(domSource, result);
	stringWriter.flush();

	// Return Final XML
	return stringWriter.toString();

    }

    protected static String createXML() throws Exception {
	Document exportDoc = createXMLDocument();

	// Search Information in the Database

	ProfileWrapper profileWrapper = new ProfileWrapper();
	Map<String, ArrayList<String>> userList = profileWrapper.getUserList();

	// Create XML

	// XML Structure Outline
	// profile
	// *****REPEAT*****
	// ----name
	// ----job
	// ----points
	// ****************


	Iterator<String> userListKeys = userList.keySet().iterator();
	
	//appends the root node "list"
	Element xmlList = exportDoc.createElement("list");
	exportDoc.appendChild(xmlList);
	
	while (userListKeys.hasNext()) {
	//gets the key of the current user
	String key = userListKeys.next();
	
	//create a note named profile stroring all the information about this user
	Element xmlProfile = exportDoc.createElement("profile");
	
	//makes a name node to store name
	Element xmlName = exportDoc.createElement("name");
	//makes a job node to store job
	Element xmlJob = exportDoc.createElement("job");
	//Makes a points node to store points
	Element xmlPoints = exportDoc.createElement("points");

	//Makes text node with the name stored inside
	Text textName = exportDoc.createTextNode(key);
	//Makes a text node with the job stored inside
	Text textJob = exportDoc.createTextNode(userList.get(key).get(0));
	//Makes a text node with the points stored inside
	Text textPoints = exportDoc.createTextNode(userList.get(key).get(2));

	//appends the name text node onto the name node
	xmlName.appendChild(textName);
	//etc
	xmlJob.appendChild(textJob);
	//etc
	xmlPoints.appendChild(textPoints);

	//appends the the profile to the root
	xmlList.appendChild(xmlProfile);
	//appends to profile
	xmlProfile.appendChild(xmlName);
	//appends to profile
	xmlProfile.appendChild(xmlJob);
	//appends to profile
	xmlProfile.appendChild(xmlPoints);
	
	
	}
	return outputDocument(exportDoc);
    }

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) {
	response.setContentType("text/xml;charset=UTF-8");
	try {
	    out = response.getWriter();
	    out.print(createXML());
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) {
	doGet(request, response);
    }

}
