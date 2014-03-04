package com.bn.automation.staf.util;

public interface IDataContainer {
	
	IField getField(String fieldName);
	
	ICookie getCookie(String cookieProperty);
	
	IAttribute getAttribute(String attName);

}
