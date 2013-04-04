/**
 * 
 */
package com.louishong.gravifile;

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

@WebServlet("/UserList")
public class GravifileUserList extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 2507342611212424269L;

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

	Map<String, ArrayList<String>> userList = ProfileWrapper.getUserList();

	// Create XML

	// XML Structure Outline
	// profile
	// *****REPEAT*****
	// ----name
	// ----job
	// ----points
	// ****************

	Element xmlRoot = exportDoc.createElement("profile");
	exportDoc.appendChild(xmlRoot);

	Iterator<String> userListKeys = userList.keySet().iterator();
	
	
	Element xmlName = exportDoc.createElement("name");
	Element xmlJob = exportDoc.createElement("job");
	Element xmlPoints = exportDoc.createElement("points");

	Text textName = exportDoc.createTextNode("");
	Text textJob = exportDoc.createTextNode("");
	Text textPoints = exportDoc.createTextNode("");

	xmlName.appendChild(textName);
	xmlJob.appendChild(textJob);
	xmlPoints.appendChild(textPoints);

	xmlRoot.appendChild(xmlName);
	xmlRoot.appendChild(xmlJob);
	xmlRoot.appendChild(xmlPoints);
	
	return outputDocument(exportDoc);

    }

    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) {

    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) {
	doGet(request, response);
    }

}
