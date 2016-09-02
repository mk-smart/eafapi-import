package org.mksmart.utils.eafapi4j;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Vector;

public class Alert {

    public static String[] severity = {"", "Severe", "Moderate", "Minor", "Unknown"};

    private JSONObject jo;
    private String id;

    Alert(JSONObject jo){
	this.jo=jo;
    }

    Alert(String id){
	this.id=id;
    }    

    private void getJO(){
	if (jo!=null) return;
	else {
	    jo = EAFAPI.query(id);
	    if (jo == null) jo = new JSONObject();
	    else jo = jo.getJSONObject("items");
	}
    }

    public String getID(){
	return id;
    }

    public String getDescription(){
	getJO();
	return jo.getString("description");
    }

    public String getMessage(){
	getJO();
	if (jo.has("message")) 
	    return jo.getString("message");
	else return getDescription();
    }

    public String getTimeRaised(){
	getJO();
	return jo.getString("timeRaised");
    }

    public String getSeverity(){
	getJO();
	return jo.getString("severity");
    }

    public int getSeverityLevel(){
	getJO();
	return jo.getInt("severityLevel");
    }

    public String getRiverOrSea(){
	getJO();
	return jo.getJSONObject("floodArea").getString("riverOrSea");
    }

    public double getLat(){
	getJO();
	return jo.getJSONObject("floodArea").getDouble("lat");
    }

    public double getLong(){
	getJO();
	return jo.getJSONObject("floodArea").getDouble("long");
    }

    public String getPolygon(){
	getJO();
	return jo.getJSONObject("floodArea").getString("polygon");
    }
    
    

}
