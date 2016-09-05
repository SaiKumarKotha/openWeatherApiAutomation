package openWeatherApi;

/**
 * This class created for SYS JSON object 
 * @author Sai Kotha
 *
 */

public class Sys {
	public String type;
	public String id;
	public String message;
	public String country;
	public String sunrise;
	public String sunset;
	
	
	public Sys(String type, String id, String message, String country, String sunrise, String sunset) {
		
		this.type = type;
		this.id = id;
		this.message = message;
		this.country = country;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}
	
}
