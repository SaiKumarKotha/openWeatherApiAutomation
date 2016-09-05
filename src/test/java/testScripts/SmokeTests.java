package testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import openWeatherApi.CityPojo;
import retrofit.http.Headers;
import utils.CityBean;
import utils.VerificationResult;

/**
 * This class contains actual test scripts to test open weather api 
 * @author Sai Kotha
 *
 */
public class SmokeTests extends BaseTest {

	@Test
	public void simpleTest() {

		try {
			CityPojo city = cityUtil.getCityPojoByID(2643743);
			// reporter.LogToReporter(cityUtil.response.headers().toString());

			if (city.name.equals("London")) {
				reporter.verificationStep("City Name " + city.name + " matched with id " + city.id,
						VerificationResult.PASSED);
			} else {
				reporter.verificationStep("City Name " + city.name + " not matched with id " + city.id,
						VerificationResult.FAILED);
			}

			if (city.coord.lat.equals("51.51"))
				reporter.verificationStep("City Latitude " + city.coord.lat + " is correct", VerificationResult.PASSED);
			else
				reporter.verificationStep("City Name " + city.coord.lat + " is not correct", VerificationResult.FAILED);

			if (city.coord.lon.equals("-0.13"))
				reporter.verificationStep("City longitude " + city.coord.lon + " is correct",
						VerificationResult.PASSED);
			else
				reporter.verificationStep("City longitude " + city.coord.lon + " is not correct",
						VerificationResult.FAILED);
		} catch (Exception e) {
			reporter.LogToReporter("Exception Occured " + e.getStackTrace());
		}

	}

	@Test
	public void cityCoordTest() {
		try {
			CityPojo city = cityUtil.getCityPojoByName("London");

			if (city.id.equals("2643743"))
				reporter.verificationStep("City Name " + city.name + " matched with id " + city.id,
						VerificationResult.PASSED);
			else
				reporter.verificationStep("City Name " + city.name + " not matched with id " + city.id,
						VerificationResult.FAILED);

			if (city.coord.lat.equals("51.51"))
				reporter.verificationStep("City Latitude " + city.coord.lat + " is correct", VerificationResult.PASSED);
			else
				reporter.verificationStep("City Name " + city.coord.lat + " is not correct", VerificationResult.FAILED);

			if (city.coord.lon.equals("-0.13"))
				reporter.verificationStep("City longitude " + city.coord.lon + " is correct",
						VerificationResult.PASSED);
			else
				reporter.verificationStep("City longitude " + city.coord.lon + " is not correct",
						VerificationResult.FAILED);

		} catch (Exception e) {
			reporter.LogToReporter("Exception Occured " + e.getStackTrace());
		}

	}

	@Test
	public void cityMaintest() {

		try {
			CityPojo city = cityUtil.getCityPojoByName("London");

			if (city.id.equals("2643743"))
				reporter.verificationStep("City Name " + city.name + " matched with id " + city.id,VerificationResult.PASSED);
			else
				reporter.verificationStep("City Name " + city.name + " not matched with id " + city.id,VerificationResult.FAILED);
			
			if(city.main.temp.matches("[0-9.]{1,6}"))
				reporter.verificationStep("City temp " + city.main.temp + " has correct numeric value",VerificationResult.PASSED);
			else
				reporter.verificationStep("City temp " + city.main.temp + " is not correct numeric value",VerificationResult.FAILED);
			
			if(city.main.pressure.matches("[0-9.]{1,6}"))
				reporter.verificationStep("City pressure " + city.main.pressure + " has correct numeric value",VerificationResult.PASSED);
			else
				reporter.verificationStep("City pressure " + city.main.pressure + " is not correct numeric value",VerificationResult.FAILED);
				
		} catch (Exception e) {
			reporter.LogToReporter("Exception Occured " + e.getStackTrace());
		}

	}

	@Test(dataProvider = "cityDataProvider")
	public void testCaseOne(long id, String cityName) throws IOException {

		CityPojo city = cityUtil.getCityPojoByID(id);
		reporter.LogToReporter("City id " + city.id + "City Name" + city.name);
		
		if (city.id.equals(id))
			reporter.verificationStep("City Name " + city.name + " matched with id " + city.id,VerificationResult.PASSED);
		else
			reporter.verificationStep("City Name " + city.name + " not matched with id " + city.id,VerificationResult.FAILED);
		
		if(city.name.equalsIgnoreCase(cityName))
			reporter.verificationStep("City Name " + city.name + " matched with id " + city.id,VerificationResult.PASSED);
		else
			reporter.verificationStep("City Name " + city.name + " not matched with id " + city.id,VerificationResult.FAILED);
	}

	@Test(dataProvider = "cityBeanDataProvider")
	public void testCaseTwo(CityBean cityBean) throws IOException {
		CityPojo city = cityUtil.getCityPojoByID(cityBean.id);

		if (city.name.equals(cityBean.name))
			reporter.verificationStep("City Name " + city.name + " matched with id " + city.id,
					VerificationResult.PASSED);
		else
			reporter.verificationStep("City Name " + city.name + " not matched with id " + city.id,
					VerificationResult.FAILED);
	}

	@Test(dataProvider = "cityExcelDataProvider")
	public void testCaseExcelDataProvider(String id, String name) throws IOException {
		// System.out.println(cityDetails);

		CityPojo city = cityUtil.getCityPojoByName(name);
		
		if (city.id.equals(id))
			reporter.verificationStep("City Name " + city.name + " matched with id " + city.id,VerificationResult.PASSED);
		else
			reporter.verificationStep("City Name " + city.name + " not matched with id " + city.id,VerificationResult.FAILED);
		
		if(city.name.equalsIgnoreCase(name))
			reporter.verificationStep("City Name " + city.name + " matched with id " + city.id,VerificationResult.PASSED);
		else
			reporter.verificationStep("City Name " + city.name + " not matched with id " + city.id,VerificationResult.FAILED);
		
	}
}
