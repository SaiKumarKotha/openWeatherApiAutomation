package testScripts;

import PojoUtil.CityPojoUtil;
import utils.CityBean;
import utils.EnvironmentVariables;
import utils.ExcelReader;
import utils.MyReporter;
import utils.MySoftAssertion;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.File;

import org.junit.BeforeClass;
import org.testng.annotations.AfterMethod;

/**
 * This class is base test for test cases. it contains all common objects for test scripts  
 * @author Sai Kotha
 *
 */
public class BaseTest {

	protected CityPojoUtil cityUtil;
	protected MyReporter reporter;
	protected MySoftAssertion softAssertion;
	
	/********************************* TestNG Annotations START***********************************************************/
	@BeforeMethod
	public void beforeMethod() {
		cityUtil = new CityPojoUtil();
		reporter = new MyReporter();
		softAssertion = new MySoftAssertion(reporter);
		EnvironmentVariables.currentTestLogMessages = "";
	}

	@AfterMethod
	public void afterMethod() {
		reporter.LogToReporter(null);
		MyReporter.stepCount = 1;
	}
	
	@BeforeClass
	public void beforeClass(){
		
	}
	
	/********************************* TestNG Annotations END***********************************************************/
	
	/********************************* Data  Providers Start***********************************************************/
	
	@DataProvider(name = "cityDataProvider", parallel = true)
	public static Object[][] cityDataProvider() {
		return new Object[][] { { 2643743, "London" }, { 5391959, "San Francisco" }, { 1269843, "Hyderabad" },
				{ 4887398, "Chicago" } };
	}

	@DataProvider(name = "cityBeanDataProvider", parallel = true)
	public static CityBean[][] cityBeanDataProvider() {
		return new CityBean[][] { { new CityBean(2643743, "London") }, { new CityBean(5391959, "San Francisco") },
				{ new CityBean(1269843, "Hyderabad") }, { new CityBean(4887398, "Chicago") } };
	}

	@DataProvider(name = "cityExcelDataProvider", parallel = true)
	public static String[][] cityExcelDataProvider() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 1);
		String fileName = path + "src\\test\\java\\utils\\CityDetailsOne.xls";
		String sheetName = "CityDetails";
		return new ExcelReader(fileName, sheetName).getData();
	}
	/********************************* Data  Providers End***********************************************************/
}
