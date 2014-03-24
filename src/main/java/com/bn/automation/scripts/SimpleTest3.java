package com.bn.automation.scripts;



import com.bn.automation.staf.anno.AfterMethod;
import com.bn.automation.staf.anno.BeforeMethod;
import com.bn.automation.staf.anno.STAFScript;
import com.bn.automation.staf.anno.Test;
import com.bn.automation.staf.core.STAFDriver;
import com.bn.automation.staf.core.STAFRunner;

@STAFScript
public class SimpleTest3 {

	private static STAFDriver driver;

	@BeforeMethod
	public void setup() {
		driver = new STAFDriver();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(enabled = false, id = { 12,13 } )
	public void test() throws Throwable {
		System.out.println("hello from test method");

		System.out.println("hello from test method");
		
		STAFDriver sd = new STAFDriver();
		STAFDriver sd2 = new STAFDriver();
		//sd.get("http://google.com");
		
		//Thread.sleep(5000);
		//sd2.get("http://yahoo.com");
		//Thread.sleep(5000);
		sd.quit();
		Thread.sleep(2000);
		sd2.quit();

	    }
	
	@Test(enabled = true, id = { 15 })
	public void test2() throws InterruptedException{
		System.out.println("hello from test2");
		STAFDriver sd = new STAFDriver();
		//sd.get("http://gmail.com");
		//Thread.sleep(3000);
		sd.quit();
		
	}
	
	@Test(enabled = true, id = { 16,17})
	public void test3() throws InterruptedException{
		System.out.println("hello from test3");
		System.out.println("----------------->" + STAFRunner.getTestID());
        driver.setData("/src/test/resources/data/xmlDataFormat.xml");
        System.out.println("driver.getData() = " + driver.getData());
        //driver.get("http://gmail.com");
		//Thread.sleep(3000);

	}
	
	

}
