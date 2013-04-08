/** Description of ProfileWrapper 
 *
 * @author Louis Hong
 * @version Gravifile Alpha Mouse 1.3
 */

package com.louishong.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileWrapper {
    public static String sDriver = "org.sqlite.JDBC";
    
    //Where the database is stored on Louis's Computer
    public static String sUrl = "jdbc:sqlite:/Users/honglouis/Documents/Github Repo/OICWebsite/db/Profiles.sqlite";
    
    //Where the file is stored on The server
//     public static String sUrl = "jdbc:sqlite:C:\\OIC\\database\\Profiles.sqlite";
    
    
    public static SQliteBase sqlBase = new SQliteBase(sDriver, sUrl);

    /* ==========The Readers===========*/
    private static ResultSet getDatabase() {
	return sqlBase.executeQuery("SELECT * FROM Profiles");
    }

    public static String getUserPoint(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
		if (resultName.equals(name)) {
		    return results.getString("UserPoints");
		}
	    }
	} catch (SQLException e) {
	    
	    e.printStackTrace();
	}
	return null;
    }

    public static String getEmail(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
		if (resultName.equals(name)) {
		    return results.getString("Email");
		}
	    }
	} catch (SQLException e) {
	    
	    e.printStackTrace();
	}
	return null;
    }

    public static Boolean hasUser(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
		if (resultName.equals(name)) {
		    return true;
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }

    public static String getUserJob(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
		if (resultName.equals(name)) {

		    return results.getString("UserJob");
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String getUserPhone(String name) {
	ResultSet results = getDatabase();

	String resultName;
	try {

	    while (results.next()) {
		resultName = results.getString("ChineseName");
		if (resultName.equals(name)) {

		    return results.getString("UserPhone");
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static Map<String, ArrayList<String>> getUserList() {
	ResultSet results = getDatabase();

	try {
	    Map<String, ArrayList<String>> mapResults = new HashMap<String, ArrayList<String>>();
	    ArrayList<String> data = new ArrayList<String>();
	    ;
	    while (results.next()) {
		data.clear();
		data.add(results.getString("UserJob"));
		data.add(results.getString("Email"));
		data.add(results.getString("UserPoints"));
		data.add(results.getString("UserPhone"));
		mapResults.put(results.getString("ChineseName"), new ArrayList<String>(data));
	    }
	    return mapResults;
	} catch (SQLException e) {
	    e.printStackTrace();
	    return null;
	}

    }

    /* ==========The Writers===========*/

}
