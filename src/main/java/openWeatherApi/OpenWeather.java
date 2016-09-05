package openWeatherApi;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * this interface is created open Weather api calls using retrofit library 
 * @author Sai Kotha
 *
 */
public interface OpenWeather {
		
	/**
	 * This method is created to call api by using city Name	 
	 * @param q: name of the city
	 * @param appid
	 * @return
	 */
	@GET("/data/2.5/weather") 
	Call<CityPojo> getCityByName(
			 @Query("q") String q, 
			 @Query("appid") String appid);
	/**
	 * This method is created to call api by using city ID	 
	 * @param ID: city ID
	 * @param appid
	 * @return
	 */
	@GET("/data/2.5/weather") 
	Call<CityPojo> getCityByID(
			 @Query("id") String id, 
			 @Query("appid") String appid);
	/**
	 * This method is created to call api by using Geographic Coordinates
	 * @param lat : latitude
	 * @param lon: longitude 
	 * @param appid
	 * @return
	 */
	
	@GET("/data/2.5/weather") 
	Call<CityPojo> getCityByGeographicCoordinates(
			 @Query("lat") String lat, 
			 @Query("lon") String lon,
			 @Query("appid") String appid);
	/**
	 * This method is created to call api by using City Zip code
	 * @param zip
	 * @param appid
	 * @return
	 */
	@GET("/data/2.5/weather") 
	Call<CityPojo> getCityByZipCode(
			 @Query("zip") String zip,			 
			 @Query("appid") String appid);
	
}
