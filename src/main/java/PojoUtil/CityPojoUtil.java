package PojoUtil;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import JsonDeserializers.CoordDeserializer;
import JsonDeserializers.MainDeserializer;
import openWeatherApi.CityPojo;
import openWeatherApi.Coord;
import openWeatherApi.Main;
import openWeatherApi.OpenWeather;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import utils.EnvironmentVariables;
import utils.MyReporter;

/**
 * This Class created for City Pojo Utility. It contains the method calls for open weather api will be used in test scripts. 
 * @author Sai Kotha
 *
 */
public class CityPojoUtil {
	private final String API_URL = "http://api.openweathermap.org";
	private final String appID = "a09872ef0f6308b9e74e0a3011ef4af8";
	private Gson gson;
	private Retrofit retrofit;
	private OpenWeather weather; 
	public CityPojo cityPojo;
	public Response response;
	
	public CityPojoUtil(){
		gson = new GsonBuilder()
			        .registerTypeAdapter(Coord.class, new CoordDeserializer())			        		        
			        .registerTypeAdapter(Main.class, new MainDeserializer())
			        .create();
		
		retrofit = new Retrofit.Builder()
		        .baseUrl(API_URL)
		        .addConverterFactory(GsonConverterFactory.create(gson))
		        .build();
		
		weather = retrofit.create(OpenWeather.class);
		
	}
	
	/**
	 * Wrapper method to invoke open weather api by city name
	 * @param cityName
	 * @return
	 * @throws IOException
	 */
	public CityPojo getCityPojoByName(String cityName) throws IOException{		
		Call<CityPojo> call = weather.getCityByName(cityName, appID);
		response = call.execute();		
		cityPojo  = (CityPojo) response.body();		
		EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+MyReporter.stepCount+"</td><td> <font color=red>Headers: </font> <br>"+response.headers().toString() +"</td></tr>";
		MyReporter.stepCount++;
		EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+MyReporter.stepCount+"</td><td> <font color=red>Body: </font> <br>"+ gson.toJson(cityPojo) +"</td></tr>";
		MyReporter.stepCount++;
		return cityPojo;		
	}
	
	/**
	 * Wrapper method to invoke open weather api by city id
	 * @param cityid
	 * @return
	 * @throws IOException
	 */
	public CityPojo getCityPojoByID(long cityid) throws IOException{		
		Call<CityPojo> call = weather.getCityByID(Long.toString(cityid), appID);
		response = call.execute();		
		cityPojo  = (CityPojo) response.body();
		EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+MyReporter.stepCount+"</td><td> <font color=red>Headers: </font> <br>"+response.headers().toString() +"</td></tr>";
		MyReporter.stepCount++;
		EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+MyReporter.stepCount+"</td><td> <font color=red>Body: </font> <br>"+ gson.toJson(cityPojo) +"</td></tr>";
		MyReporter.stepCount++;
		//cityPojo  = call.execute().body();		
		return cityPojo;		
	}
	
	/**
	 * Wrapper method to invoke open weather api by city Geographic Coordinates
	 * @param lat
	 * @param lon
	 * @return
	 * @throws IOException
	 */
	public CityPojo getCityPojoByGeographicCoordinates(String lat, String lon) throws IOException{		
		Call<CityPojo> call = weather.getCityByGeographicCoordinates(lat, lon, appID);
		response = call.execute();		
		cityPojo  = (CityPojo) response.body();
		EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+MyReporter.stepCount+"</td><td> <font color=red>Headers: </font> <br>"+response.headers().toString() +"</td></tr>";
		MyReporter.stepCount++;
		EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+MyReporter.stepCount+"</td><td> <font color=red>Body: </font> <br>"+ gson.toJson(cityPojo) +"</td></tr>";
		MyReporter.stepCount++;
		//cityPojo  = call.execute().body();		
		return cityPojo;		
	}
	
	/**
	 * Wrapper method to invoke open weather api by city zipcode
	 * @param zipCode
	 * @return
	 * @throws IOException
	 */
	public CityPojo getCityPojoByZipCode(String zipCode) throws IOException{		
		Call<CityPojo> call = weather.getCityByZipCode(zipCode, appID);
		response = call.execute();		
		cityPojo  = (CityPojo) response.body();
		EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+MyReporter.stepCount+"</td><td> <font color=red>Headers: </font> <br>"+response.headers().toString() +"</td></tr>";
		MyReporter.stepCount++;
		EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+MyReporter.stepCount+"</td><td> <font color=red>Body: </font> <br>"+ gson.toJson(cityPojo) +"</td></tr>";
		MyReporter.stepCount++;
		//cityPojo  = call.execute().body();		
		return cityPojo;		
	}
	
}















