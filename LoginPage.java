package com.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class LoginPage<WebElements> extends BaseClass{
	 public LoginPage() {
		 
		PageFactory.initElements(driver, this);
		 
	 }

//@CacheLookup
@FindBys	 ({ @FindBy(id="email"), @FindBy(xpath="//input[@type='text']")})
	private WebElement txtusername;

   @FindAll ({@FindBy(id="pass"),@FindBy(xpath="//input[@type='password']")})
	private WebElement txtpassword;

@FindBy(name="login")
	private WebElement loginbtn;



public WebElement getTxtusername() {
	return txtusername;
}

public WebElement getTxtpassword() {
	return txtpassword;
}

public WebElement getLoginbtn() {
	return loginbtn;
}






































}


