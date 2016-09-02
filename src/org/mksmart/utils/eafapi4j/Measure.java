package org.mksmart.utils.eafapi4j;

import org.json.JSONObject;

public class Measure{

    private JSONObject jo;

    Measure(JSONObject jo){
	this.jo = jo;
    }

    public Reading getLatestReading(){
	return new Reading(jo.getJSONObject("latestReading"));
    }

    public String getUnitName(){
	return jo.getString("unitName");
    }

    public String getParameterName(){
	return jo.getString("parameterName");
    }

    public String getQualifier(){
	return jo.getString("qualifier");
    }

}
