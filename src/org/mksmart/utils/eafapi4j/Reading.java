package org.mksmart.utils.eafapi4j;

import org.json.JSONObject;

public class Reading{

    private JSONObject jo;
    
    Reading(JSONObject jo){
	this.jo = jo;
    }

    public String getDateTime(){
	return jo.getString("dateTime");
    }
    
    public double getValue(){
	return jo.getDouble("value");
    }

}
