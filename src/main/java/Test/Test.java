package Test;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import ExtentReport.ExtentReport;
import PageObject.HomePage;
import PageObject.Login;
import PageObject.NewCustomer;
public class Test {

    WebDriver driver;

    Login objLogin;

    HomePage objHomePage;
    
    NewCustomer objNewCustomer;

  ExtentReport extRpt = new ExtentReport();
  Logger log = Logger.getLogger("Samraj Execution Logs");

    @BeforeTest

    public void setup(){

        System.setProperty("webdriver.chrome.driver","E:\\Samraj\\Selenium\\Colombo Selenium\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        log.info("Launching browser");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://demo.guru99.com/V4/");

    }

    /**

     * This test go to http://demo.guru99.com/V4/

     * Verify login page title as guru99 bank

     * Login to application

     * Verify the home page using Dashboard message

     */

    @org.testng.annotations.Test(priority=0)

    public void test_Home_Page_Appear_Correct(){
    	 log.info("Login Page");
        //Create Login Page object
    	extRpt.setUpReport();
    	extRpt.startTestCase("User Login");
    objLogin = new Login(driver);

    //Verify login page title

    String loginPageTitle = objLogin.getLoginTitle();

    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

    //login to application

    objLogin.loginToGuru99("mgr123", "mgr!23");

    // go the next page

    objHomePage = new HomePage(driver);
    extRpt.logEventsPass("Logged Successfully");
    log.info("Successful Login");
    //Verify home page

    Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));

    }
    
    @org.testng.annotations.Test(priority=1)
    
    public void NewCustomerGeneration(){
    	 log.info("Launched Home Page");
    	extRpt.startTestCase("Home Page");
    	objNewCustomer = new NewCustomer(driver);
    	extRpt.logEventsPass("Navigated Home Screen");
    	
    	//Calling Navigation Method
    	log.info("Moved to new customer Page");
    	extRpt.startTestCase("New Customer Navigation");
    	objNewCustomer.navigateNewCustomer(driver);
    	extRpt.logEventsPass("Navigated to New User");
    	//Form Fillling Method
    	log.info("Filling forms");
    	extRpt.startTestCase("New Customer Form Filling");
    	objNewCustomer.createNewCustomer();
    	extRpt.logEventsPass("Completed Form Filling");
    	log.info("View Form Summary");
    	extRpt.createFinalReport();
    	driver.quit();
    	
    	
    }

    

}