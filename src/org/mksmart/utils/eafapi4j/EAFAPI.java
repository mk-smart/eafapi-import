package org.mksmart.utils.eafapi4j;

import java.util.HashMap;
import java.util.Vector;
import java.io.StringWriter;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

public class EAFAPI {

    
    static final String root = "http://environment.data.gov.uk/flood-monitoring";

    public EAFAPI(){
    };

    public HashMap<String,Station> getStations(String filters){
	HashMap<String,Station> result = new HashMap<String,Station>();
	JSONObject jo = query(root+"/id/stations"+filters);
	JSONArray  ja = jo.getJSONArray("items");
	for(int i = 0; i < ja.length(); i++){
	    JSONObject jo2 = ja.getJSONObject(i);
	    String stId = jo2.getString("notation");
	    result.put(stId, new Station(stId));
	}
	return result;
    }

    public Vector<Alert> getAlerts(String query){
	Vector<Alert> result = new Vector<Alert>();
	JSONObject jo = null;
	if (query!=null)
	    jo = query(root+"/id/floods?"+query);
	else
	    jo = query(root+"/id/floods");
	JSONArray  ja = jo.getJSONArray("items");
	for(int i = 0; i < ja.length(); i++){
	    JSONObject jo2 = ja.getJSONObject(i);
	    result.add(new Alert(jo2.getString("@id")));
	}
	return result;
    }

    static JSONObject query(String url){
	System.err.println("querying "+url);
	HttpGet httpGet = new HttpGet(url);
	CloseableHttpClient httpclient = HttpClients.createDefault();
	JSONObject result = null;
	try{
	    CloseableHttpResponse response2 = httpclient.execute(httpGet);
	    // System.err.println(response2.getStatusLine());
	    HttpEntity entity2 = response2.getEntity();
	    // create JSONObject
	    StringWriter writer = new StringWriter();
	    IOUtils.copy(entity2.getContent(), writer, StandardCharsets.UTF_8);
	    String rs = writer.toString();
	    EntityUtils.consume(entity2);
	    result = new JSONObject(rs);
	} catch(Exception e){
	    e.printStackTrace();
	} 
	return result;
    }
}


