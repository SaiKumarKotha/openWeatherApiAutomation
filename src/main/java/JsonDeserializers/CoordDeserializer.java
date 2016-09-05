package JsonDeserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import openWeatherApi.Coord;

/**
 * This class is used as JSON deserializer for COORD
 * @author Sai Kotha
 *
 */
public class CoordDeserializer implements JsonDeserializer<Coord>{
    
	public Coord deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
			throws JsonParseException {
		
        String lon = je.getAsJsonObject().get("lon").toString();
        String lat = je.getAsJsonObject().get("lat").toString();
        return new Coord(lon,lat);
	}
	
	
}
