package utilities;

import Reporter.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    private ExtentReports extentReports = ExtentReporterNG.getReporterObject();
    private ExtentTest test;
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName());

    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed");
       test.fail(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        System.out.println("Test execution starts");
    }

    public void onFinish(ITestContext context) {
        System.out.println("Test execution finished");
        extentReports.flush();
    }
}
