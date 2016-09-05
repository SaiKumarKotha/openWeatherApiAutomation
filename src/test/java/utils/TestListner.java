package utils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.IResultListener;
import org.testng.xml.XmlSuite;

/**
 * This class implements TestNG IResult Listener to create Custom HTML reports.
 * This report stores in <project folder>/openWeatherReports/index.html
 * @author Sai Kotha
 *
 */
public class TestListner implements IResultListener {

	String testBeforeMethoddeatils = null;
	String testAfterMethoddeatils = null;
	HashMap<String, List<ITestResult>> mFailedResult = new HashMap<String, List<ITestResult>>();

	XmlSuite xmlsuites;
	PrintWriter htmlWriter = null;
	String folderToSave = "openWeatherReports";
	int counter = 0;

	private HashMap<String, ITestResult> listOfBeforeResultsPerTestCase = new HashMap<String, ITestResult>();

	public void onFinish(ITestContext arg0) {
		//closingSuiteReport(arg0.getSuite());
		ISuite suite = arg0.getSuite();
		Set<String> keys = suite.getResults().keySet();
		int Total = 0, Pass = 0, skip = 0, fail = 0;
		long totaltimetoexecute = 0;
		for (String key : keys) {
			Pass += suite.getResults().get(key).getTestContext().getPassedTests().size();
			skip += suite.getResults().get(key).getTestContext().getSkippedTests().size();
			fail += suite.getResults().get(key).getTestContext().getFailedTests().size();
			totaltimetoexecute = (suite.getResults().get(key).getTestContext().getEndDate().getTime()
					- suite.getResults().get(key).getTestContext().getStartDate().getTime());
		}
		Total = Pass + fail + skip;

		Html_footer_Summary(Total, Pass, fail, skip, totaltimetoexecute);

		htmlWriter.println("</body>");
		htmlWriter.println("</html>");
		htmlWriter.flush();
		htmlWriter.close();
		
	}

	public void closingSuiteReport(ISuite arg0) {
		Set<String> keys = arg0.getResults().keySet();
		int Total = 0, Pass = 0, skip = 0, fail = 0;
		long totaltimetoexecute = 0;
		for (String key : keys) {
			Pass += arg0.getResults().get(key).getTestContext().getPassedTests().size();
			skip += arg0.getResults().get(key).getTestContext().getSkippedTests().size();
			fail += arg0.getResults().get(key).getTestContext().getFailedTests().size();
			totaltimetoexecute = (arg0.getResults().get(key).getTestContext().getEndDate().getTime()
					- arg0.getResults().get(key).getTestContext().getStartDate().getTime());
		}
		Total = Pass + fail + skip;

		Html_footer_Summary(Total, Pass, fail, skip, totaltimetoexecute);

		htmlWriter.println("</body>");
		htmlWriter.println("</html>");
		htmlWriter.flush();
		htmlWriter.close();
		FaildDeatils();

		//createFailed_Skipped_Suite(arg0);
		listOfBeforeResultsPerTestCase.clear();
	}

	public void onStart(ITestContext arg0) {
		System.out.println("inside onStart");
		createSuiteReport(arg0);
		
		
		htmlWriter.println("<TR><TD COLSPAN=\"5\" align=\"center\"><b>" + arg0.getName() + "</b></td></tr>");
		htmlWriter.println("<TR><TD COLSPAN=\"5\" align=\"center\"><b>"				+ arg0.getCurrentXmlTest().getSuite().getFileName() + "</b></td></tr>");
		htmlWriter.flush();
	}

	public void createSuiteReport(ITestContext arg0) {
		System.out.println("create suite report");
		// deleting the old files
		String supporturl = System.getProperty("user.dir") + "\\" + folderToSave + "\\support\\*.html";
		xmlsuites = arg0.getSuite().getXmlSuite();
		counter = 0;
		try {
			String[] command = new String[4];
			command[0] = "cmd";
			command[1] = "/C";
			command[2] = "del";
			command[3] = supporturl;
			Runtime.getRuntime().exec(command);
			supporturl = System.getProperty("user.dir") + "\\" + folderToSave + "\\support\\*.png";
			command[0] = "cmd";
			command[1] = "/C";
			command[2] = "del";
			command[3] = supporturl;
			Runtime.getRuntime().exec(command);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Unable to delete old files");
		}
		try {
			htmlWriter = createHTMLWriter(folderToSave, "index.html");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to create report");
			return;
		}

		htmlWriter.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		htmlWriter.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		htmlWriter.println("<head>");
		htmlWriter.println("<title>Open Weather API Automation Result</title>");
		htmlWriter.println("<style>");
		htmlWriter.println(".warning{color:yellow}");
		htmlWriter.println(".fail{color:red}");
		htmlWriter.println(".pass{color:green}");
		htmlWriter.println("</style>");
		htmlWriter.println("</head>");
		htmlWriter.println("<body>");
		Calendar cal = Calendar.getInstance();
		htmlWriter.println("<br/><h1>" + arg0.getSuite().getName() + "</h1>");
		htmlWriter.println("<br/><div align=\"left\">Report generated on: " + cal.getTime() + "</div><br/><br/>");
		htmlWriter.println("<table border=\"1\" CELLPADDING=\"4\" CELLSPACING=\"3\">"
				+ "<thead><TR><TH COLSPAN=\"5\"><BR><H3>" + arg0.getSuite().getName()
				+ "</H3> </TH> </TR>  <tr><th>S.No</th> <th>Name</th><th>Result</th><th>Executed Date</th>  <th>Test Case Execution Time in(ms)</th> </tr> </thead>");

		htmlWriter.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	public void onTestFailure(ITestResult arg0) {
		
		String fullyQualifiedMethodName = arg0.getMethod().getInstance().getClass().getName() + "." + arg0.getName();		
		generateResultReport(arg0,fullyQualifiedMethodName);
		
		List<ITestResult> temp = mFailedResult.get(arg0.getThrowable().getClass().getName());
		if (temp == null) {
			temp = new ArrayList<ITestResult>();

		}
		temp.add(arg0);
		mFailedResult.put(arg0.getThrowable().getClass().getName(), temp);
	}

	public void onTestSkipped(ITestResult arg0) {
		String fullyQualifiedMethodName = arg0.getMethod().getInstance().getClass().getName() + "." + arg0.getName();		
		generateResultReport(arg0,fullyQualifiedMethodName);
	}

	public void onTestStart(ITestResult arg0) {
		String fullyQualifiedMethodName = arg0.getMethod().getInstance().getClass().getName() + "." + arg0.getName();
		//System.out.println("method name"+ fullyQualifiedMethodName);		
		ITestResult beforeResult = (ITestResult) listOfBeforeResultsPerTestCase.get(fullyQualifiedMethodName);		
		Reporter.setCurrentTestResult(arg0);
	}

	public void onTestSuccess(ITestResult arg0) {
		
		String fullyQualifiedMethodName = arg0.getMethod().getInstance().getClass().getName() + "." + arg0.getName();		
		generateResultReport(arg0,fullyQualifiedMethodName);

		// mAllTestResult.add(arg0);
		// mCurrentResult = arg0;

	}

	public void onConfigurationFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		 System.out.println(arg0.getMethod());
		if (arg0.getMethod().isBeforeMethodConfiguration()) {

			// beforeResult = arg0;
			Object[] parameters = arg0.getParameters();
			String methodName = null;
			if (parameters.length > 0) {
				if (parameters[1] instanceof Method) {
					methodName = ((Method) parameters[1]).getName();
					String fullyQualifiedMethodName = arg0.getMethod().getInstance().getClass().getName() + "."
							+ methodName;
					listOfBeforeResultsPerTestCase.put(fullyQualifiedMethodName, arg0);
				}
			}
		} else if (arg0.getMethod().isAfterMethodConfiguration()) {
			// mAfterMethodConfigResult.put(testname, arg0);
			//generateResultReport(arg0);
		} else if (arg0.getMethod().isBeforeSuiteConfiguration()) {
			createSuiteReport(arg0.getTestContext());
		} else if (arg0.getMethod().isAfterSuiteConfiguration()) {
			closingSuiteReport(arg0.getTestContext().getSuite());
		}
	}

	public void onConfigurationSkip(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getMethod());
		if (arg0.getMethod().isBeforeMethodConfiguration()) {
			Object[] parameters = arg0.getParameters();
			String methodName = null;
			if (parameters.length > 0) {
				if (parameters[1] instanceof Method) {
					methodName = ((Method) parameters[1]).getName();
					String fullyQualifiedMethodName = arg0.getMethod().getInstance().getClass().getName() + "."
							+ methodName;
					listOfBeforeResultsPerTestCase.put(fullyQualifiedMethodName, arg0);
				}
			}
			// beforeResult = arg0;
		} else if (arg0.getMethod().isAfterMethodConfiguration()) {
			// mAfterMethodConfigResult.put(testname, arg0);
			// generateResultReport(arg0);
		} else if (arg0.getMethod().isBeforeSuiteConfiguration()) {
			createSuiteReport(arg0.getTestContext());
		} else if (arg0.getMethod().isAfterSuiteConfiguration()) {
			closingSuiteReport(arg0.getTestContext().getSuite());
		}
	}

	public void onConfigurationSuccess(ITestResult arg0) {
		//System.out.println("configuration success");

		if (arg0.getMethod().isBeforeMethodConfiguration()) {
			Object[] parameters = arg0.getParameters();
			String methodName = null;
			if (parameters.length > 0) {
				if (parameters[1] instanceof Method) {
					methodName = ((Method) parameters[1]).getName();
					String fullyQualifiedMethodName = arg0.getMethod().getInstance().getClass().getName() + "."
							+ methodName;
					listOfBeforeResultsPerTestCase.put(fullyQualifiedMethodName, arg0);
				}
			}
			// beforeResult = arg0;
		} else if (arg0.getMethod().isAfterMethodConfiguration()) {
			//generateResultReport(arg0);
		} else if (arg0.getMethod().isBeforeSuiteConfiguration()) {
			createSuiteReport(arg0.getTestContext());
		} else if (arg0.getMethod().isAfterSuiteConfiguration()) {
			closingSuiteReport(arg0.getTestContext().getSuite());
		}

	}

	public void GenerateReport(ITestContext arg0) {

		// Creating Html page
		PrintWriter htmlWriter = null;
		try {
			htmlWriter = createHTMLWriter(folderToSave, "firtsReport.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to create report");
			return;
		}

	}

	private PrintWriter createHTMLWriter(String outdir, String filename) throws IOException {
		new File(outdir).mkdirs();
		return new PrintWriter(new BufferedWriter(new FileWriter(new File(outdir, filename))));
	}

	private void Html_footer_Summary(int total, int pass, int fail, int skipp, long timetoexecute) {

		//htmlWriter.println("</tbody><tfoot><tr><td COLSPAN=\"5\" align=\"center\"><br/><div align=\"left\"><b>Toal :</b>&nbsp "
				//+ total + "&nbsp&nbsp <b>Pass :</b>&nbsp " + pass + "&nbsp&nbsp <b>Fail :</b>&nbsp " + fail
					//	+ "&nbsp&nbsp <b>Skip :</b>&nbsp " + skipp + "</div></td> </tr>  </tfoot></table><br/><br/>");
		//htmlWriter.println("<br/> Total Time to Execute <b>" + timetoexecute + "</b><br/><br/>");

		//htmlWriter.println("<br/><a href='./support/analysis.html'>Analysist Report</a></br>");

		htmlWriter.println("<br/><a href='../test-output/index.html'>TestNG default Report</a>");
		htmlWriter.flush();
	}

	private void generateResultReport(
			/* ITestResult beforeTest,ITestResult r, */ITestResult afterTest,String methodName) {

		ITestResult r = afterTest;
		// afterTest.getAttribute("SK17");
		//}
		//ITestResult beforeTest = listOfBeforeResultsPerTestCase.get(methodName);
		String resultstatus = " <font color='";

		if (r.getStatus() == ITestResult.SUCCESS) {
			if (r.getAttributeNames().contains("TEST_RESULT_FAILED")) {
				if (r.getAttribute("TEST_RESULT_FAILED").equals("TRUE")) {
					resultstatus += "red'>FAILED";
				}
			} else if (r.getAttributeNames().contains("TEST_RESULT_WARNING")) {
				resultstatus += "green'>PASS";
			} else {
				resultstatus += "green'>PASS";
			}

		} else if (r.getStatus() == ITestResult.FAILURE) {
			resultstatus += "RED'>FAILED";
		} else {
			resultstatus += "yellow'>SKIPPED";

		}
		resultstatus += "</font></td>";
		String deatils = EnvironmentVariables.currentTestLogMessages;		
	
 		String supporturl = ".\\support\\";		
		//String filetosave = "<a href='" + supporturl + r.getName() + "_" + r.getStartMillis() + ".html'>" + r.getName()+ "</a>";
		String filetosave = "<a href='" + supporturl + r.getName() + "_" + r.getStartMillis() + ".html'>" + methodName+ "</a>";		
		counter += 1;
		String testCaseId = (String) r.getAttribute("TESTID");
		htmlWriter.println("<tr><td>" + counter + "</td><td> " + filetosave + "</td><td align=\"center\">"
				+ resultstatus + "</td><td>" + new Date(r.getStartMillis()) + "</td><td>"
				+ (-r.getStartMillis() + r.getEndMillis()) + "</font></td></tr>");
		createDeatilsPage(deatils, r);

		htmlWriter.flush();
	}

	public void createDeatilsPage(String deatils, ITestResult r) {
		// Object[] ob = r.getParameters();

		PrintWriter writer = null;
		try {
			writer = createHTMLWriter(folderToSave + "\\support", r.getName() + "_" + r.getStartMillis() + ".html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("unable to create " + r.getName() + "_" + r.getEndMillis());
			return;
		}
		writer.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		writer.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		writer.println(
				"<head><style>.class-green{background-color:green;} .class-blue{background-color:blue;}</style><script> "
						+ "function goBack()   " + "{   " + "window.history.back()   } </script></head> ");
		writer.println("<body><table border=\"1\" CELLPADDING=\"1\" CELLSPACING=\"1\">");
		writer.println("<thead><tr><th COLSPAN=\"2\"><h4>Execution Steps for " + r.getName() + "</h4></th></tr>"
				+ "<tr><th>Step No</th> <th>Action Details</th></tr>" + "</thead>");
		writer.println(deatils);		
		writer.println("</tbody></table> ");
		if (r.getAttribute("LOGSRC") != null) {
			writer.println(
					"</br><br/><a href='" + r.getName() + "_" + r.getStartMillis() + ".png'>Reference Screen</a>");
		}
		// writer.println("</br><br/><input type=\"button\" value=\"Back To Test
		// Cases\" onclick=\"goBack()\"/> </body>");
		writer.println("</body>");
		writer.println("</html>");
		writer.flush();
		writer.close();
	}

	public void FaildDeatils() {

		PrintWriter writer = null;
		try {
			writer = createHTMLWriter(folderToSave + "\\support", "analysis.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("unable to create analysis.html ");
			return;
		}

		writer.println(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
		writer.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		writer.println(
				"<head><script> " + "function goBack()   " + "{   " + "window.history.back()   } </script></head> ");
		writer.println("<body>");

		Set keyset = mFailedResult.keySet();
		if (keyset.size() == 0) {
			writer.println("<h2>No Failures.</h2>");
			writer.println("</body>");
			writer.println("</html>");
			writer.flush();
			writer.close();
			return;
		}
		writer.println("<h3>Total no Exception types &nbsp&nbsp: &nbsp&nbsp </h3>" + keyset.size() + "</br>");
		writer.println(
				"<table border=\"1\" CELLPADDING=\"4\" CELLSPACING=\"3\"><thead><TR><TH COLSPAN=\"2\"><BR><H3>Analysis</H3> </TH></TR></thead><tbody>");
		Iterator ir = keyset.iterator();

		while (ir.hasNext()) {
			String excename = (String) ir.next();
			List<ITestResult> faildlist = mFailedResult.get(excename);
			writer.println("<tr> <th COLSPAN=\"2\">" + excename + "</th></tr>");
			writer.println(" <tr><th>S.No</th> <th>Test Case Name</th> </tr> ");
			int i = 1;
			if (faildlist != null) {
				for (ITestResult r : faildlist) {

					// String supporturl =
					// System.getProperty("user.dir")+"\\"+folderToSave+"\\support\\";
					String supporturl = ".\\";
					writer.println("<tr><td>" + i++ + "</td><td> <a href='" + supporturl + r.getName() + "_"
							+ r.getStartMillis() + ".html'>" + r.getName() + "</a></td></tr>");
				}
			}
			i++;
		}

		writer.println(
				"</tbody></table></br><br/> <input type=\"button\" value=\"Back To Test Cases\" onclick=\"goBack()\"/> </body>");
		writer.println("</body>");
		writer.println("</html>");
		writer.flush();
		writer.close();
	}

}
	