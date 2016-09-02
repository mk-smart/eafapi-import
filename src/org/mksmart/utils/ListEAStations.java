package org.mksmart.utils;

import org.mksmart.utils.eafapi4j.EAFAPI;
import org.mksmart.utils.eafapi4j.Station;
import org.mksmart.utils.eafapi4j.Measure;
import org.mksmart.utils.eafapi4j.Reading;

import java.util.HashMap;

public class ListEAStations {


    public static void main (String[] args){
	EAFAPI api = new EAFAPI();
	// get list of all stations (no filter)
	// HashMap<String,Station> stations = api.getStations("");
	// get list of all stations in geo area
	// maybe 10km is enough instead of 15
	HashMap<String,Station> stations = api.getStations("?lat=52.0381382&long=-0.7576441&dist=15");      

	for(String station : stations.keySet()){
	    System.out.println(station);
	    //	    System.out.println(stationToEeml(stations.get(station)));
	}	

    }


}
