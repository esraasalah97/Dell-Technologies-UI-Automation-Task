package com.saucedemo.DellTechnologiesTask.validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
public class ValidateField {
	
	public static boolean validateUrl(WebDriver driver,String url) {
		return driver.getCurrentUrl().equals(url)? false:true;
	}
    public static boolean validateUsername(String username,String regex) {
    	return isStringMatchRegex(username, regex);
    }
    public static boolean validatePassword(String password, String regex) {
    	return isStringMatchRegex(password,regex);
    }
    private static boolean isStringMatchRegex(String string,String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
