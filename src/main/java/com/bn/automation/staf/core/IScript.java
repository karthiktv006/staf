package com.bn.automation.staf.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.bn.automation.staf.helpers.STAFConstant;

public abstract class IScript {

	public static WebDriver driver; // change to stafdriver
	public static String browser_name = String.valueOf(System
			.getProperty(STAFConstant.BROWSER_NAME_ARG));
	public static String url = String.valueOf(System.getProperty(STAFConstant.URL_ARG));
	public static String config = String.valueOf(System.getProperty(STAFConstant.CONFIG_ARG));
	public static String xmldata = String
			.valueOf(System.getProperty(STAFConstant.XML_DATA_ARG));
	public static String log_path = String.valueOf(System
			.getProperty(STAFConstant.LOG_PATH_ARG));
	public static boolean grid = Boolean.valueOf(System.getProperty(STAFConstant.GRID_ARG));
	public static String hub_url = String
			.valueOf(System.getProperty(STAFConstant.HUB_URL_ARG));
	public static String browser_version = String.valueOf(System
			.getProperty(STAFConstant.BROWSER_VERSION_ARG));
	public static String platform = String.valueOf(System
			.getProperty(STAFConstant.PLATFORM_ARG));
	public static String machine_name = String.valueOf(System
			.getProperty(STAFConstant.MACHINE_NAME_ARG));
	public static boolean screenshot = Boolean.valueOf(System
			.getProperty(STAFConstant.SCREENSHOT_ARG));
	private static final String NULL = STAFConstant.NULL;
	private static Map<String, Object> info = new HashMap<String, Object>();
	
	public static Map<String, Object> getInfo() {
		return info;
	}

	

	//private static final Logger logger = LogManager.getLogger(IScript.class);
    private static Logger logger;
    static {
        String executionStartTime = new SimpleDateFormat(STAFConstant.DATE_FORMAT).format(Calendar.getInstance().getTime());
        String logPath = STAFConstant.LOG_PATH_PREFIX+executionStartTime;
        System.setProperty(STAFConstant.LOG4J2_FILE_NAME,logPath);
        logger = LogManager.getLogger();
        putInfoMap(STAFConstant.START_TIME,executionStartTime);
        putInfoMap(STAFConstant.XML_LOG,STAFConstant.LOG_PREFIX + executionStartTime+".xml");
        putInfoMap(STAFConstant.USER_DIR,System.getProperty(STAFConstant.USER_DIR));
        putInfoMap(STAFConstant.ALL_XML_LOCATION,getInfo().get(STAFConstant.USER_DIR).toString()+STAFConstant.XML_LOG_REF);
        putInfoMap(STAFConstant.XML_LOG_PATH,getInfo().get(STAFConstant.ALL_XML_LOCATION).toString()+getInfo().get(STAFConstant.XML_LOG).toString());
        putInfoMap("result_log",getInfo().get(STAFConstant.ALL_XML_LOCATION)+"ResultLog_"+executionStartTime+".xml");
        putInfoMap("html_log1",STAFRunner.getInfo().get(STAFConstant.USER_DIR)+STAFConstant.HTML_LOG_REF+STAFConstant.LOG_PREFIX+STAFRunner.getInfo().get(STAFConstant.START_TIME)+".html");
        putInfoMap("html_log2",STAFRunner.getInfo().get(STAFConstant.USER_DIR)+STAFConstant.HTML_LOG_REF+"ResultLog_"+STAFRunner.getInfo().get(STAFConstant.START_TIME)+".html");
    }

	protected static void setEnv() {
		createInfoMap();
	}

	public IScript() {

	}

	private static void createInfoMap() {
       // String currentDirectory = System.getProperty("user.dir");
        //config = currentDirectory + config;
        //config = "config.Config.xml";
        putInfoMap(STAFConstant.CONFIG_KEY, config);
		putInfoMap(STAFConstant.BROWSER_NAME_KEY,browser_name);
		putInfoMap(STAFConstant.URL_KEY,url);
		putInfoMap(STAFConstant.XML_DATA_KEY,xmldata);
		putInfoMap(STAFConstant.LOG_PATH_KEY,log_path);
		putInfoMap(STAFConstant.GRID_KEY,grid);
		putInfoMap(STAFConstant.HUB_URL_KEY,hub_url);
		putInfoMap(STAFConstant.BROWSER_VERSION_KEY,browser_version);
		putInfoMap(STAFConstant.PLATFORM_KEY,platform);
		putInfoMap(STAFConstant.MACHINE_NAME_KEY,machine_name);
		putInfoMap(STAFConstant.SCREENSHOT_KEY,screenshot);
		
		


	}

	protected static void putInfoMap(String key, Object value) {
		if(!value.equals(NULL) && !info.containsKey(key)){
			logger.debug("info map is created with key->" + key + " & value->" + value);
			info.put(key, value);
		} else {
			logger.debug("info map is not created for key->" + key + " & value->" + value);
		}
	}

	protected static void viewInfoMap() {
		logger.info(STAFConstant.DASH);
		logger.info("INFO MAP FOR STAF EXECUTION");
		logger.info(STAFConstant.DASH);
		for (Map.Entry<String, Object> entry : info.entrySet()) {
			
			logger.info("{} -> {} ", entry.getKey(),entry.getValue());

		}
		logger.info(STAFConstant.DASH);

	}
	
	



	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		IScript.driver = driver;
	}

	public static String getBrowser_name() {
		return browser_name;
	}

	public static void setBrowser_name(String browser_name) {
		IScript.browser_name = browser_name;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		IScript.url = url;
	}

	public static String getConfig() {
		return config;
	}

	public static void setConfig(String config) {
		IScript.config = config;
	}

	public static String getXmldata() {
		return xmldata;
	}

	public static void setXmldata(String xmldata) {
		IScript.xmldata = xmldata;
	}

	public static String getLog_path() {
		return log_path;
	}

	public static void setLog_path(String log_path) {
		IScript.log_path = log_path;
	}

	public static boolean isGrid() {
		return grid;
	}

	public static void setGrid(boolean grid) {
		IScript.grid = grid;
	}

	public static String getHub_url() {
		return hub_url;
	}

	public static void setHub_url(String hub_url) {
		IScript.hub_url = hub_url;
	}

	public static String getBrowser_version() {
		return browser_version;
	}

	public static void setBrowser_version(String browser_version) {
		IScript.browser_version = browser_version;
	}

	public static String getPlatform() {
		return platform;
	}

	public static void setPlatform(String platform) {
		IScript.platform = platform;
	}

	public static String getMachine_name() {
		return machine_name;
	}

	public static void setMachine_name(String machine_name) {
		IScript.machine_name = machine_name;
	}

	public static boolean isScreenshot() {
		return screenshot;
	}

	public static void setScreenshot(boolean screenshot) {
		IScript.screenshot = screenshot;
	}

	public static Logger getLogger() {
		return logger;
	}

}
