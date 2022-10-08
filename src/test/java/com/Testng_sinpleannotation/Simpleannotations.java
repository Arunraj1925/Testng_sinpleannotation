package com.Testng_sinpleannotation;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Simpleannotations {
	public static WebDriver driver;
	
	public static void selector(WebElement selectelement, String text)
	{
	Select s=new Select(selectelement);
	s.selectByValue(text);
	}
	
	@BeforeSuite
	public void setPropertyForDriver() {
		System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
	}
	
	@BeforeTest
	public void LaunchBrowser() {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeClass
	public void LaunchUrl() {
		driver.get("http://adactinhotelapp.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@BeforeMethod
	public void appLogin() {
		WebElement username=driver.findElement(By.xpath("//input[@id='username']"));
		WebElement password=driver.findElement(By.xpath("//input[@id='password']"));
		WebElement login=driver.findElement(By.xpath("//input[@id='login']"));
		username.sendKeys("Arunsj25");
		password.sendKeys("Sh@ktim@n_25");
		login.click();
	}
	
	@Test
	public void hotelSearch() throws IOException {
	//------------------Hotel Search----------------------------------------
	WebElement location=driver.findElement(By.xpath("//select[@id='location']"));
	WebElement Hotels=driver.findElement(By.xpath("//select[@id='hotels']"));
	WebElement roomtype=driver.findElement(By.xpath("//select[@id='room_type']"));
	WebElement numberofrooms=driver.findElement(By.xpath("//select[@id='room_nos']"));
	WebElement adultsperroom=driver.findElement(By.xpath("//select[@id='adult_room']"));
	WebElement childrenperroom=driver.findElement(By.xpath("//select[@id='child_room']"));
	WebElement checkindate=driver.findElement(By.xpath("//input[@id='datepick_in']"));
	WebElement checkoutdate=driver.findElement(By.xpath("//input[@id='datepick_out']"));
	WebElement search=driver.findElement(By.xpath("//input[@id='Submit']"));
	selector(location,"London");
	selector(Hotels,"Hotel Creek");
	selector(roomtype,"Standard");
	selector(numberofrooms,"2");
	checkindate.clear();
	checkindate.sendKeys("10/08/2022");
	checkoutdate.clear();
	checkoutdate.sendKeys("15/08/2022");
	selector(adultsperroom,"2");
	selector(childrenperroom,"1");
	search.click();
	//------------------------------Select Hotel----------------------------
	WebElement select=driver.findElement(By.xpath("//input[@id='radiobutton_0']"));
	WebElement selectcontinue=driver.findElement(By.xpath("//input[@id='continue']"));
	select.click();
	selectcontinue.click();
	//---------------------Book A Hotel--------------------------------------
	JavascriptExecutor js=(JavascriptExecutor)driver;
	WebElement booknow=driver.findElement(By.xpath("//input[@id='book_now']"));
	WebElement fname=driver.findElement(By.xpath("//input[@id='first_name']"));
	WebElement lname=driver.findElement(By.xpath("//input[@id='last_name']"));
	WebElement address=driver.findElement(By.xpath("//textarea[@id='address']"));
	WebElement creditcardnum=driver.findElement(By.xpath("//input[@id='cc_num']"));
	WebElement cvv=driver.findElement(By.xpath("//input[@id='cc_cvv']"));
	WebElement creditcardtype=driver.findElement(By.xpath("//select[@id='cc_type']"));
	WebElement ccexpiremon=driver.findElement(By.xpath("//select[@id='cc_exp_month']"));
	WebElement ccexpireyr=driver.findElement(By.xpath("//select[@id='cc_exp_year']"));
	js.executeScript("arguments[0].scrollIntoView();",booknow);
	fname.sendKeys("Arun");
	lname.sendKeys("Raj");
	address.sendKeys("India");
	creditcardnum.sendKeys("1234567890987654");
	selector(creditcardtype,"MAST");
	selector(ccexpiremon,"12");
	selector(ccexpireyr,"2022");
	cvv.sendKeys("123");
	booknow.click();
	//--------------------------------Booking Confirmation---------------------------
	WebElement myitinerary=driver.findElement(By.xpath("//input[@id='my_itinerary']"));
	js.executeScript("arguments[0].scrollIntoView();", myitinerary);
	myitinerary.click();
	//------------------------------Booked Itinerary-----------------------------------
	TakesScreenshot ts=(TakesScreenshot)driver;
	File src=ts.getScreenshotAs(OutputType.FILE);
	File trg=new File(".\\Screenshot\\BookedItinerary.png");
	FileUtils.copyFile(src, trg);
	}
	
	@AfterMethod
	public void logout() {
		WebElement logout=driver.findElement(By.xpath("//input[@id='logout']"));
		logout.click();
	}
	
	@AfterClass
	public void homePage() {
		WebElement loginagain=driver.findElement(By.linkText("Click here to login again"));
		loginagain.click();
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
	
}