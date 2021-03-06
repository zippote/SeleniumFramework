package com.demo.baseTest;

import com.demo.pages.KbcPage;
import com.demo.pages.LoginPage;
import com.demo.pages.SimplifyCommercePage;
import com.demo.utils.Screenshots;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage login;
    protected SimplifyCommercePage simplifyLogin;
    protected KbcPage kbc;
    protected Screenshots screenshots;

    @BeforeMethod
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void closeDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            screenshots = new Screenshots();
            System.out.println("FAILED: " + result.getMethod().getMethodName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("PASSED: " + result.getName());
        }
        driver.quit();
    }

    public void initLogin() {
        login = new LoginPage(driver);
        driver.get(login.getBaseURL());
    }

    public void initSimplify() {
        simplifyLogin = new SimplifyCommercePage(driver);
        driver.get(simplifyLogin.getSimplifyLoginURL());
    }

    public void initKBC() {
            kbc = new KbcPage(driver);
            driver.get(kbc.getKbcRegSaverCalcURL());
            kbc.clickCookies();
    }
}
