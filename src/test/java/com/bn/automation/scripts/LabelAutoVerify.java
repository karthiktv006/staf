package com.bn.automation.scripts;

import org.openqa.selenium.By;

import com.bn.automation.staf.core.STAFDriver;
import com.bn.automation.staf.helpers.STAFConstant;

public class LabelAutoVerify {
	
	public static void main(String[] args) throws Throwable {
		STAFDriver driver = STAFDriver.getInstance(STAFConstant.FIREFOX);
		driver.get("http://qwecweb01.hq.bn-corp.com/bn/");
		driver.setTestCaseID("187");
		driver.setDataFileLocation("\\src\\test\\resources\\data\\xmlDataFormat.xml");
		driver.findElement(By.id("signInLink")).click();
		Thread.sleep(1000L);
		driver.findElement(By.id("createAccountBtn")).click();
		Thread.sleep(1000L);
		SO so = new SO();
		driver.autoVerify("verifyLabel", so);
		
	}

}
