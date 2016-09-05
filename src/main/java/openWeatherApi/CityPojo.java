package openWeatherApi;

/**
 * This class is POJO class for City JSON
 * @author Sai Kotha
 *
 */
public class CityPojo {

	public String id;	 
	public String name;	 
	public String visibility;
	
	public Coord coord;
	public Main main;
	//public List<Weather> weather;	
	//public Wind wind;
	//public Clouds clouds;
	//public Sys sys;
	
	public String toString(){
		return "id: " + id + " name:" +name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public Coord getCoord() {
		return coord;
	}
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	

}
