package JsonDeserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;


import openWeatherApi.Main;

/**
 * This class is used as JSON deserializer for Main
 * @author Sai Kotha
 *
 */
public class MainDeserializer implements JsonDeserializer<Main>{
    
	public Main deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
			throws JsonParseException {
		
        String temp = je.getAsJsonObject().get("temp").toString();
        String pressure = je.getAsJsonObject().get("pressure").toString();
        String humidity = je.getAsJsonObject().get("humidity").toString();
        String temp_min = je.getAsJsonObject().get("temp_min").toString();
        String temp_max = je.getAsJsonObject().get("temp_max").toString();
        return new Main(temp, pressure, humidity, temp_min, temp_max);
	}
	
	
}
