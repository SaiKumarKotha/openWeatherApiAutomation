package utils;

import org.testng.Reporter;

/**
 * This class is created to generate custom HTML reports (both suite level and test case level).   
 * This report stores in <project folder>/openWeatherReports/index.html
 * It also talks to TestNG reporter
 * Work in progress  
 * @author Sai Kotha
 *
 */
public class MyReporter {

	public static int stepCount = 1;
	int index = 0;
	int testngLogger = 0;

	public MyReporter() {
		index = 0;
		testngLogger = 0;
	}

	public void logResponseString(String responseString) {
		responseString = "" + responseString + "";
		logToReporter(responseString, "class-green");
	}

	public void logStepNo(String stepNumber) {
		stepNumber = "" + stepNumber + "";
		logToReporter(stepNumber, "class-blue");
	}
	
	/**
	 * This method used to log soft assertions.
	 * It can also used for error bucketing (not implemented)
	 * @param verificationResult
	 * @param verification
	 */

	public void verificationStep(String verificationResult, VerificationResult verification) {
		
		switch (verification) {
		case PASSED:
			verificationResult = "" + verificationResult + "";
			if(verificationResult!=null)
				EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+stepCount+"</td><td bgcolor=green>"+verificationResult +"</td></tr>";			
			break;
		case FAILED:
			verificationResult = "" + verificationResult + "";
			Reporter.getCurrentTestResult().setAttribute("TEST_RESULT_FAILED", "TRUE");
			if(verificationResult!=null)
				EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+stepCount+"</td><td bgcolor=red>"+verificationResult +"</td></tr>";
			
			break;
		case WARNING:
			verificationResult = "" + verificationResult + "";
			Reporter.getCurrentTestResult().setAttribute("TEST_RESULT_WARNING", "TRUE");
			if(verificationResult!=null)
				EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+stepCount+"</td><td bgcolor=red>"+verificationResult +"</td></tr>";
			break;
		default:
			break;
		}
		stepCount++;
	}

	public void LogToReporter(String aMessage) {
		logToReporter(aMessage, "class-none");
	}
	
	

	private void logToReporter(String aMessage, String styleClassName) {
		
		String logenet = (String) Reporter.getCurrentTestResult().getAttribute("LOGEVENT");
		EnvironmentVariables.currentTestLogMessages = EnvironmentVariables.currentTestLogMessages +"<tr><td>"+stepCount+"</td><td>"+aMessage +"</td></tr>";
		if (logenet == null)
			logenet = "";
		index++;
		if (Reporter.getCurrentTestResult().getMethod().isAfterMethodConfiguration() && aMessage == null) {
			index = 0;
			testngLogger = 0;
			return;
		}
		if (Reporter.getCurrentTestResult().getMethod().isTest() && testngLogger == 0) {
			String supporturl = ".\\";
			testngLogger = 1;
		}

		logenet += "" + index + "" + aMessage + "";
		Reporter.getCurrentTestResult().setAttribute("LOGEVENT", logenet);
		stepCount++;
	}

}
