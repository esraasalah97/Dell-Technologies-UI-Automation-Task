package com.saucedemo.DellTechnologiesTask.testpackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.saucedemo.DellTechnologiesTask.objectmodel.SauceDemo;
import com.saucedemo.DellTechnologiesTask.reader.objectrepository.ObjectRepository;
import com.saucedemo.DellTechnologiesTask.reader.testdata.Testdata;
import com.saucedemo.DellTechnologiesTask.validation.ValidateField;
import com.saucedemo.DellTechnologiesTask.Constant;


@Listeners(com.saucedemo.DellTechnologiesTask.listener.ListenerTest.class)
public class LoginScenario {
	boolean result;
	private Testdata testdata=new Testdata("TestData",Constant.SHEETNAME,Constant.TestDataValueColumnNumber);
	private ObjectRepository objects=new ObjectRepository("ObjectRepository",Constant.SHEETNAME);

	static WebDriver browserObject;
    SauceDemo sauceDemo=new SauceDemo(browserObject);
    WebElement loginButton, usernameField, passwordField,errorMessage;
    @BeforeClass
	@Parameters({"browser"})
	public void setUp(@Optional("chrome")String browser) throws Exception {
		// Check if parameter passed from TestNG is 'firefox'
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			System.setProperty("webdriver.gecko.driver", "drivers//geckodriver.exe");
			browserObject = new FirefoxDriver();
		}
		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("chrome")) {
			// set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
			// create chrome instance
			browserObject = new ChromeDriver();
		}
		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("me")) {
			// set path to Edge.exe
			System.setProperty("webdriver.edge.driver", "drivers//msedgedriver.exe");
			// create Edge instance
			browserObject = new EdgeDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		browserObject.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		 //Create object of Dimensions class
	      Dimension dm = new Dimension(1024,768);
	      //Setting the current window to that dimension
	      browserObject.manage().window().setSize(dm);
	}


	@BeforeMethod

	public void navigation() {	
    String url=testdata.getTestData().get("url");
	browserObject.navigate().to(url);
	browserObject.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	}
	  @Test
		public void positiveScenario() {
			usernameField= findBy(objects.getObjects().get("username").get(0),objects.getObjects().get("username").get(1));
			usernameField.sendKeys(testdata.getTestData().get("username"));
			passwordField= findBy(objects.getObjects().get("password").get(0),objects.getObjects().get("password").get(1));
			passwordField.sendKeys(testdata.getTestData().get("password"));
			loginButton =findBy(objects.getObjects().get("login-button").get(0),objects.getObjects().get("login-button").get(1));
			loginButton.click();
		    assertUrl(testdata.getTestData().get("homePageUrl"));
	    }
    @Test //(enabled=false)   
	public void coverAllTestcases() {
    	browserObject.navigate().to(testdata.getTestData().get("url"));
		usernameField= findBy(objects.getObjects().get("username").get(0),objects.getObjects().get("username").get(1));
		usernameField.sendKeys(testdata.getTestData().get("username"));
		assertUsername(usernameField);
		passwordField= findBy(objects.getObjects().get("password").get(0),objects.getObjects().get("password").get(1));
		passwordField.sendKeys(testdata.getTestData().get("password"));
		assertPassword(passwordField);
		loginButton =findBy(objects.getObjects().get("login-button").get(0),objects.getObjects().get("login-button").get(1));
		loginButton.click();
		errorMessage=findBy(objects.getObjects().get("error-message").get(0),objects.getObjects().get("login-button").get(1));
		assertErrorMessage(errorMessage);
    }

   public void assertUrl(String changedURL) {
	result = ValidateField.validateUrl(browserObject, changedURL);
	AssertJUnit.assertFalse(result);
    }
	public void assertUsername(WebElement we) {
		result = ValidateField.validateUsername(we.getAttribute("value"),Constant.usernameRegex);
		AssertJUnit.assertTrue(result);

	}
	public void assertPassword(WebElement we) {
		result = ValidateField.validatePassword(we.getAttribute("value"),Constant.passwordRegex);
		AssertJUnit.assertTrue(result);
	}
	public void assertErrorMessage(WebElement we) {
		AssertJUnit.assertTrue(we.isDisplayed());
	}
	@AfterClass
	public void close() {
		browserObject.close();

	}
	
public static void takeScreenshot() throws IOException {
	 File file = ((TakesScreenshot)browserObject).getScreenshotAs(OutputType.FILE);
     File screenshotsFolder=new File(Constant.screenshotFolderPath);
     FileUtils.copyFileToDirectory(file,screenshotsFolder);
}
 private WebElement findBy(String identifier, String value) {
	 if(identifier.equalsIgnoreCase("id")) 
	  return browserObject.findElement(By.id(value));
	 else if(identifier.equalsIgnoreCase("name"))
	 return browserObject.findElement(By.name(value));
	 else if(identifier.equalsIgnoreCase("xpath"))
		 return browserObject.findElement(By.xpath(value));
	 return null; 
	 }

}
