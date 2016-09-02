package org.mksmart.utils.eafapi4j;

import org.json.JSONObject;
import org.json.JSONArray;

public class Station {

    private JSONObject jo;
    private String id;

    Station(JSONObject jo){
	this.jo = jo;
    }

    public Station(String id){
	this.id = id;
    }

    private void getJO(){
	if (jo!=null) return;
	else {
	    jo = EAFAPI.query(EAFAPI.root+"/id/stations/"+id);
	    if (jo == null) jo = new JSONObject();
	    else jo = jo.getJSONObject("items");
	}
    }

    public String getName(){
	getJO();
	return jo.getString("label");
    }

    public double getLat(){
	getJO();
	return jo.getDouble("lat");
    }

    public double getLong(){
	getJO();
	return jo.getDouble("long");
    }

    public Measure[] getMeasures(){
	getJO();
	try{
	    JSONArray meas = jo.getJSONArray("measures");
	    Measure[] result = new Measure[meas.length()];
	    for (int i = 0; i < meas.length(); i++){
		result[i] = new Measure(meas.getJSONObject(i));
	    }
	    return result;
	} catch(Exception e){
	    JSONObject meas = jo.getJSONObject("measures");
	    Measure[] result = {new Measure(meas)};
	    return result;
	}
    }
  
}
