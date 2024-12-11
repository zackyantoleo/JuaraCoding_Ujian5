package com.ujian;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.LoginPage;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        System.out.println("=== Valid Login Test === ");
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void invalidUsernameLoginTest() {
        System.out.println("=== Invalid Username Login Test === ");
        loginPage.login("invalid_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"));
    }

    @Test
    public void invalidPasswordLoginTest() {
        System.out.println("=== Invalid Password Test === ");
        loginPage.login("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"));
    }

    @Test
    public void emptyCredentialsLoginTest() {
        System.out.println("=== Empty Credentials Test === ");
        loginPage.login("", "");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
