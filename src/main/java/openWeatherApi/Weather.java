package openWeatherApi;

/**
 * This class created for Weather JSON object 
 * @author Sai Kotha
 *
 */
public class Weather {
	public String id;
	public String main;
	public String description;
	public String icon;
	
	public Weather(String id,String main, String description, String icon){
		this.id = id;
		this.main = main;
		this.description = description;
		this.icon = icon;
	}
}
