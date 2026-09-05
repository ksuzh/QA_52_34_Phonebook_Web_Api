package utils;

import manager.AppManager;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    Logger logger = LoggerFactory.getLogger(TestNGListener.class);
    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        logger.info("Start test -> " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        logger.info("Test success -> " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        logger.info("Test failed -> " + result.getName() +
                " status -> " +result.getStatus());
        this.driver = ((AppManager)result.getInstance()).getDriver();
        TakeScreenShot.takeScreenShot((TakesScreenshot) driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        logger.warn("Test skipped -> " + result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        logger.error("test failed with timeout -> " + result.getName());
        this.driver = ((AppManager)result.getInstance()).getDriver();
        TakeScreenShot.takeScreenShot((TakesScreenshot) driver);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        logger.info(context.getName() + " test started on " +
                context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        logger.info(context.getName() + " test finished ");
    }

}
