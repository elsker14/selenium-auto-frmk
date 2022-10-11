package testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extentReports = ExtentReporterNG.getRerportObject();
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal(); // making it Thread Safe
    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = extentReports.createTest(iTestResult.getMethod().getMethodName());
        threadLocal.set(test);  // when we ran parallel every test will be added on its own thread, so they don't overlap
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, " ::: TEST PASSED ::: ");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        threadLocal.get().log(Status.FAIL, " ::: TEST FAILED ::: ");
        threadLocal.get().fail("REASON: " + iTestResult.getThrowable());

        // Take screenshot
        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String filePath = null;
        try {
            filePath = String.valueOf(getScreenshot(iTestResult.getTestName(), driver));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Attach to report
        threadLocal.get().addScreenCaptureFromPath(filePath);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        threadLocal.get().log(Status.SKIP, " ::: TEST SKIPPED ::: ");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extentReports.flush();
    }
}
