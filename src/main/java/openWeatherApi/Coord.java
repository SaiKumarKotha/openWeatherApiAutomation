package openWeatherApi;

/**
 * This class representing COORD JSON Object
 * @author Sai Kotha
 *
 */
public class Coord{
	 
	 public String lon;	 
	 public String lat;
	 
	 public Coord(String lon, String lat){
		 this.lon = lon;
		 this.lat= lat;		 
	 }		 
}