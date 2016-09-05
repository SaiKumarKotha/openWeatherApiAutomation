package openWeatherApi;

/**
 * This class created for MAIN JSON object 
 * @author Sai Kotha
 *
 */

public class Main {
	public String temp;
	public String pressure;
	public String humidity;
	public String temp_min;
	public String temp_max;

	public Main(String temp, String pressure, String humidity, String temp_min, String temp_max) {	
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
	}
	
 
}
